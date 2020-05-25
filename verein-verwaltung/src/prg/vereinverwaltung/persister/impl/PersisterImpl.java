package prg.vereinverwaltung.persister.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prg.vereinverwaltung.domain.Person;
import prg.vereinverwaltung.persister.api.Persister;

/**
 * Diese Klasse stellt eine konkrete Implementierung der Schnittstelle
 * 'Persister' dar.
 * 
 * @author jsucur
 * @version 1.0
 */
public class PersisterImpl implements Persister {

	// Logger erstellen
	private static Logger logger = LogManager.getLogger(PersisterImpl.class);

	// Delimiter = Abgrenzer Seperatoren => ;
	static final String DELIMITER = ";";

	// Datei in die gespeichert wird
	private File localDatabase;

	// ID von personNummer generieren
	private static int nextId;

	// Persister Konstruktor
	public PersisterImpl(File localDatabase) throws Exception {
		this.localDatabase = localDatabase;
		nextId = 0;
		init();
	}

	private void init() throws Exception {

		// Wenn schon eine Datenbank besteht -> initNextId Ansonsten start bei Wert 1
		if (localDatabase.exists()) {
			initNextId();
		} else {
			nextId = 1;
		}
	}

	private void initNextId() {
		int neueId = 0;

		// Mitglied Nummer auslesen und in letzteID Speichern.
		try (BufferedReader reader = new BufferedReader(new FileReader(localDatabase))) {
			String line = null;

			// Iteration ueber alle Eintraege bis zur letzten Zeile
			while ((line = reader.readLine()) != null) {

				// String wird anhand der Semikolon aufgeteilt / gesplittet
				String[] parts = line.split(DELIMITER);

				// Erster Teil des Strings wird in int letzteId umgewandelt und gespeichert
				int letzteId = Integer.parseInt(parts[0]);

				// Speichern des letzten Werts in int neueId
				neueId = letzteId;
			}

			// nextId setzen
			nextId = neueId++;

		} catch (Exception e) {
			logger.error("ERROR:\n Die Mitgliednummer konnte nicht ausgelesen werden!", e);
		}
	}

	@Override
	public Person speichern(Person person) throws Exception {

		// PrintWriter erstellt einen Text Stream der dann mit dem FileWriter in das
		// Textfile geschrieben wird.
		try (PrintWriter writer = new PrintWriter(new FileWriter(localDatabase, true), true)) {

			if (person.getPersonenNummer() == 0) {
				// Neue Person: Person-Nummer inkrementieren
				person.setPersonenNummer(nextId++);
			}

			// personAsString siehe unten implementierung
			String str = personAsString(person);
			writer.println(str);
		}

		return person;
	}

	private String personAsString(Person p) {
		// Klasse personAsString
		StringBuilder sBuilder = new StringBuilder();

		// Bilden des sBuilder mit Delimiter dazwischen
		sBuilder.append(p.getPersonenNummer()).append(DELIMITER).append(p.getName()).append(DELIMITER)
				.append(p.getVorname()).append(DELIMITER).append(p.getGeburtsdatum()).append(DELIMITER)
				.append(p.getAdresse().getPlz()).append(DELIMITER).append(p.getAdresse().getStrasse()).append(DELIMITER)
				.append(p.getAdresse().getOrt()).append(DELIMITER).append(p.getAdresse().getLand()).append(DELIMITER)
				.append(p.getKontakt().getTelefon()).append(DELIMITER).append(p.getKontakt().getEmail());

		return sBuilder.toString();
	}

	@Override
	public Person aktualisieren(Person person) throws Exception {
		// Vorhandenes Objekt loeschen
		// Neues Objekt speichern
		loeschen(person.getPersonenNummer());
		return speichern(person);
	}

	@Override
	public boolean loeschen(Person person) throws Exception {
		return loeschen(person.getPersonenNummer());
	}

	@Override
	public boolean loeschen(int personNummer) {
		// neues Array
		ArrayList<String> liste = new ArrayList<>();

		// Aus Datei lesen
		try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {

			// line ist hier leerer Wert
			String line = null;
			String id = "" + personNummer;

			// while line = BufferedReader = ungleich null
			while ((line = br.readLine()) != null) {

				// Zeile mit 'personNummer' auslassen
				if (!line.startsWith(id)) {
					liste.add(line);
				}
			}
		}

		catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Auslesen der Datei: ", e);
		}

		// Alles in Datei schreiben
		try (PrintWriter writer = new PrintWriter(new FileWriter(localDatabase, false))) {

			for (String str : liste) {
				writer.println(str);
			}
		}

		catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Schreiben der Datei: ", e);
		}

		return true;
	}

	@Override
	public List<Person> finde(String name, String vorname) {
		// Finden einer Person anhand Name und Vorname (Achtung! koennen auch mehrere
		// Personen sein!)

		// Neues Array mit Personenliste
		List<Person> liste = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
			String line = null;

			// != ungleich null
			while ((line = br.readLine()) != null) {

				// String wird anhand der Semikolon aufgeteilt / gesplittet
				String[] parts = line.split(DELIMITER);

				// Wenn alle Argumente true sind wird Person in die Liste gepseichert
				if ((parts[1].equals(name)) && (parts[2].equals(vorname))) {
					liste.add(getAsPerson(line));
				}
			}
		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim finden der Personen: ", e);
		}
		return liste;
	}

	@Override
	public Person finde(int personNummer) {
		// Finden einer Person anhand der Mitgliednummer
		Person person = null;

		try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
			String line = null;

			// != ungleich null
			while ((line = br.readLine()) != null) {

				// String wird anhand der Semikolon aufgeteilt / gesplittet
				String[] parts = line.split(DELIMITER);

				// Wenn alle Argumente true sind wird Person in die Liste gepseichert
				if ((Integer.parseInt(parts[0]) == personNummer)) {
					person = getAsPerson(line);
				}
			}
		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim finden der Person: ", e);
		}
		return person;
	}

	@Override
	public List<Person> alle() {
		//Finde alle Personen
		// Neues Array mit Personenliste
		List<Person> liste = new ArrayList<>();

		try {
			if (localDatabase.exists()) {
			}

			try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
				String line = null;

				// != ungleich
				while ((line = br.readLine()) != null) {
					liste.add(getAsPerson(line));
				}
			}

		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim finden aller Personen: ", e);
		}

		return liste;
	}

	private Person getAsPerson(String line) throws Exception {
		//Aufteilen eines Strings in ein Personen Objekt
		String[] parts = line.split(DELIMITER);

		int personenNummer = Integer.parseInt(parts[0]);
		String vorname = parts[1];
		String name = parts[2];
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate geburtsdatum = LocalDate.parse(parts[3], dateFormatter);
		int plz = Integer.parseInt(parts[4]);
		String strasse = parts[5];
		String ort = parts[6];
		String land = parts[7];
		int tel = Integer.parseInt(parts[8]);
		String email = parts[9];

		Person person = new Person(vorname, name, geburtsdatum, plz, strasse, ort, land, tel, email);
		person.setPersonenNummer(personenNummer);

		return person;
	}

}
