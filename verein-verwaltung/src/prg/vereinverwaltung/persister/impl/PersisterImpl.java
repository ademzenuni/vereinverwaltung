package prg.vereinverwaltung.persister.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prg.vereinverwaltung.domain.Adresse;
import prg.vereinverwaltung.domain.Kontakt;
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
	
	//Logger object: is used to log messages for a specific system or application component
	//getLogger: Method to find a named logger. getLogger(String name)
	private static Logger logger = LogManager.getLogger(PersisterImpl.class);
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#speichern(prg.
	 * vereinverwaltung.domain.Person)
	 */
	//Delimiter = Abgrenzer Seperatoren => ;
	static final String DELIMITER = ";";
	
	// Datei in die gespeichert wird
	private File localDatabase;

	// ID von personNummer generieren
	private static int nextId;
	
	
	public PersisterImpl(File localDatabase) throws Exception {
		this.localDatabase = localDatabase;
		nextId = 0;
		
		//definiert in com.sun.java.util.jar.pack
		init();
	}

	private void init() throws Exception {

		if (localDatabase.exists()) {
			initNextId();
		} else {
			nextId = 1;
		}
		 // wenn die n�chste ID gleich 0 Fehlermeldung
		if (nextId == 0) {
			String msg = "Der Wert 'nextId' konnte nicht ausgelesen werden!";
			//Exceptionhandling mit Message
			throw new Exception(msg);
		}
	}

	private void initNextId() throws IOException {
		int maxValue = 0;

		// Alles auslesen, Wert für 'nextId' um eins grösser als 'maxValue'
		
		//FileReader: Creates a new FileReader, given the File to read from.
		//Buffered Reader:JAVA Docs: will buffer the input from the specified file. Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file, 
		//converted into characters, and then returned, which can be very inefficient.
		try (BufferedReader bReader = new BufferedReader(new FileReader(localDatabase))) {
			String line = null;

			// != ungleich
			while ((line = bReader.readLine()) != null) {
				
				//String wird gesplittet in verschiedene Teile mit Delimiter dazwischen
				String[] parts = line.split(DELIMITER);
				
				//parseInt =  parsing a String method argument into an Integer object
				int value = Integer.parseInt(parts[0]);

				//value muss gr�sser sein als maxValue siehe oben int maxValue
				if (value > maxValue) {
					maxValue = value;
				}
			}

			// nextId setzen
			nextId = maxValue + 1;

		} catch (FileNotFoundException e) {
			logger.error("Datei nicht gefunden: ", e);
			throw e;
		} catch (IOException e) {
			logger.error("Fehler beim Auslesen von 'nextId': ", e);
			throw e;
		}
	}
	

	@Override
	// Class in Vorlage
	public Person speichern(Person person) throws Exception {
		
		//PrintWriter: Prints formatted representations of objects to a text-output stream. This class implements all of the print methods found in PrintStream. 
		//FileWriter: FileWriter(FileName, boolean). Constructs a FileWriter object given a file name with a boolean indicating whether or not to append the data written.
		try (PrintWriter writer = new PrintWriter(new FileWriter(localDatabase, true), true)) {

			if (person.getPersonenNummer() == 0) {
				// Neue Person: Person-Nummer setzen inkrementieren nextId++= nextId +1 Postfix
				person.setPersonenNummer(nextId++);
			}

			//personAsString siehe unten implementierung
			String str = personAsString(person);
			writer.println(str);
		}

		return person;
	}

	private String personAsString(Person p) {
			// Klasse personAsString
			StringBuilder sBuilder = new StringBuilder();
			
			//Bilden des sBuilder mit Delimiter dazwischen
			sBuilder.append(p.getPersonenNummer()).append(DELIMITER).append(p.getName()).append(DELIMITER)
					.append(p.getVorname()).append(DELIMITER).append(p.getGeburtsdatum()).append(DELIMITER)
					.append(p.getAdresse().getPlz()).append(DELIMITER).append(p.getAdresse().getStrasse()).append(DELIMITER)
					.append(p.getAdresse().getOrt()).append(DELIMITER).append(p.getAdresse().getLand()).append(DELIMITER)
					.append(p.getKontakt().getTelefon()).append(DELIMITER)
					.append(p.getKontakt().getEmail());

			return sBuilder.toString();
	}

	@Override
	public Person aktualisieren(Person person) throws Exception {
		// != ungleich
		if (person.getPersonenNummer() != 0) {
			// Objekt bereits gespeichert: Update
			loeschen(person.getPersonenNummer());
			return speichern(person);
		} else {
			// Objekt nocht nicht gespeichert: Speichern
			return speichern(person);
		}
	}

	@Override
	public boolean loeschen(Person person) throws Exception {
		// TODO Auto-generated method stub
		return loeschen(person.getPersonenNummer());
	}


	@Override
	public boolean loeschen(int personNummer) throws Exception {
		//neues Array
		ArrayList<String> liste = new ArrayList<>();

		// Aus Datei lesen
		//FileReader: Creates a new FileReader, given the File to read from.
		//Buffered Reader:JAVA Docs: will buffer the input from the specified file. Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file, 
		//converted into characters, and then returned, which can be very inefficient.
		try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
			
			//line ist hier leerer Wert
			String line = null;
			String id = "" + personNummer;

			//while line = BufferedReader = ungleich null
			while ((line = br.readLine()) != null) {

				// Zeile mit 'personNummer' auslassen
				if (!line.startsWith(id)) {
					liste.add(line);
				}
			}
		}

		// Alles in Datei schreiben
		//PrintWriter: Prints formatted representations of objects to a text-output stream. This class implements all of the print methods found in PrintStream.
		//FileWriter: Convenience class for writing character files. The constructors of this class assume that the default character encoding and the default byte-buffer size are acceptable.
		//
		try (PrintWriter writer = new PrintWriter(new FileWriter(localDatabase, false))) {

			for (String str : liste) {
				writer.println(str);
			}
		}

		return true;
	}


	@Override
	public List<Person> finde(String name, String vorname) throws Exception {
		
		//Neues Array mit Personenliste
		List<Person> liste = new ArrayList<>();

		///FileReader: Creates a new FileReader, given the File to read from.
		//Buffered Reader:JAVA Docs: will buffer the input from the specified file. Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file, 
		//converted into characters, and then returned, which can be very inefficient.
		try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
			String line = null;

			// != ungleich null
			while ((line = br.readLine()) != null) {
				
				//String wird gesplittet in verschiedene Teile mit Delimiter dazwischen
				String[] parts = line.split(DELIMITER);

				// && = Und, true, genau dann wenn alle Argumente true sind
				if ((parts[1].equals(name)) && (parts[2].equals(vorname))) {
					liste.add(getAsPerson(line));
				}
			}
		}
		return liste;
	}

	@Override
	public Person finde(int personNummer) throws Exception {
		Person person = null;

		try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
			String line = null;

			// != ungleich null
			while ((line = br.readLine()) != null) {
				
				//String wird gesplittet in verschiedene Teile mit Delimiter dazwischen
				String[] parts = line.split(DELIMITER);

				// && = Und, true, genau dann wenn alle Argumente true sind
				if ((Integer.parseInt(parts[0]) == personNummer)) {
					person = getAsPerson(line);
				}
			}
		}
		return person;
	}

	@Override
	public List<Person> alle() throws Exception {
		
		//Neues Array mit Personenliste
		List<Person> liste = new ArrayList<>();

		if (localDatabase.exists()) {
			
			//FileReader: Creates a new FileReader, given the File to read from.
			//Buffered Reader:JAVA Docs: will buffer the input from the specified file. Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file, 
			//converted into characters, and then returned, which can be very inefficient.
			try (BufferedReader br = new BufferedReader(new FileReader(localDatabase))) {
				String line = null;

				// != ungleich
				while ((line = br.readLine()) != null) {
					liste.add(getAsPerson(line));
				}
			}
		}

		return liste;
	}
	
	private Person getAsPerson(String line) throws ParseException {

		
		String[] parts = line.split(DELIMITER);

		//Erstellung, Zueweisen der parts der verschiedenen Variablen
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
