package prg.vereinverwaltung.business.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prg.vereinverwaltung.business.api.Verwaltung;
import prg.vereinverwaltung.domain.Person;
import prg.vereinverwaltung.persister.api.Persister;

/**
 * Diese Klasse stellt eine konkrete Implementierung der Schnittstelle
 * 'Verwaltung' dar.
 * 
 * @author azenuni, shollenstein
 * @version 1.0
 */
public class VerwaltungImpl implements Verwaltung {

	// Logger erstellen
	private static Logger logger = LogManager.getLogger(VerwaltungImpl.class);

	/**
	 * Persister-Komponente
	 */

	// Persister erstellen
	private Persister persister;

	//Konstruktor
	public VerwaltungImpl(Persister persister) {
		this.persister = persister;
	}

	@Override
	// Person wird uebergeben
	public Person personHinzufuegen(Person person) throws Exception {
		Person newPerson = persister.speichern(person);
		logger.info("INFO: Person hinzugefuegt: " + newPerson.toString());
		return newPerson;
	}

	@Override
	// Uebergabe der neuen Person
	public Person personAktualisieren(Person person) throws Exception {
		Person personNeu = persister.aktualisieren(person);
		logger.info("INFO: Person aktualisiert: " + personNeu.toString());

		return personNeu;
	}

	@Override
	// Uebergabe des Boolean wertes ob Person geloescht wurde
	public boolean personLoeschen(Person person) throws Exception {
		boolean success = persister.loeschen(person);
		logger.info("INFO: Person geloescht: " + person.toString());

		return success;
	}

	@Override
	//Uebergabe des Namen und Vornamen
	public List<Person> finde(String name, String vorname) throws Exception {
		logger.info("INFO: Person gefunden: " + name + vorname);
		return persister.finde(name, vorname);
	}

	@Override
	// Uebergabe der MitgliederNummer
	public Person finde(int mnr) throws Exception {
		logger.info("INFO: Person gefunden: " + mnr);
		return persister.finde(mnr);
	}

	@Override
	// Uebergabe der Personenliste die ausgegeben werden muss
	public List<Person> alle() throws Exception {
		return persister.alle();
	}
}
