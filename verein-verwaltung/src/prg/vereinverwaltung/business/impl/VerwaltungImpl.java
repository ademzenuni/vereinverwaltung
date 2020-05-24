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
 * @author jsucur
 * @version 1.0
 */
public class VerwaltungImpl implements Verwaltung {
	
	//Eigener Code 
		private static Logger logger = LogManager.getLogger(VerwaltungImpl.class);

	/**
	 * Persister-Komponente
	 */
	private Persister persister;

	public VerwaltungImpl(Persister persister) {
		this.persister = persister;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#personHinzuguefen(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public Person personHinzufuegen(Person person) throws Exception {
		//Eigener Code
		//Person wird durch speichern in PersisterImpl abgespeichert
		//Erstellen des Logeintrags
		Person newPerson = persister.speichern(person);
		logger.info("PERSON HINZUGEFUEGT: " + newPerson.toString());
		return newPerson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#personAktualisieren(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public Person personAktualisieren(Person person) throws Exception {
		//Eigener Code
		//Logeintrag das Person aktualisiert wird
		//Aufruf aktualisieren welche in PersisterImpl f�r speichern des Aktualisierung zust�ndig ist
		logger.info("PERSON WIRD AKTUALISIERT: " + person.toString());
		Person pAktualisiert = persister.aktualisieren(person);
		logger.info("PERSON AKTUALISIERT: " + pAktualisiert.toString());

		return pAktualisiert;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#personLoeschen(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public boolean personLoeschen(Person person) throws Exception {
		//Eigener Code
		//Aufruf loeschen in PersisterImpl
		boolean success = persister.loeschen(person);
		logger.info("PERSON GELOESCHT: " + person.toString());

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#finde(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<Person> finde(String name, String vorname) throws Exception {
		// TODO Auto-generated method stub
		return persister.finde(name, vorname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#finde(int)
	 */
	@Override
	public Person finde(int mnr) throws Exception {
		// TODO Auto-generated method stub
		return persister.finde(mnr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#alle()
	 */
	@Override
	public List<Person> alle() throws Exception {
		// TODO Auto-generated method stub 
		return persister.alle();
	}
}
