package prg.vereinverwaltung.business.dummy;

import java.util.List;

import prg.vereinverwaltung.domain.Person;
import prg.vereinverwaltung.persister.api.Persister;

/**
 * Diese Klasse dient als Helper-Klasse und stellt eine stark vereinfachte
 * Implementierung der Schnittstelle <em>Persister<em> zur Verfügung, die nur
 * dazu da ist, um die Funktionalitäten der Verwaltung-Komponente während der
 * Implementierung laufend testen zu können.
 * 
 * @author jsucur
 * @version 1.0
 */
public class PersisterDummy implements Persister {

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#speichern(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public Person speichern(Person person) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#aktualisieren(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public Person aktualisieren(Person person) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#loeschen(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public boolean loeschen(Person person) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#loeschen(int)
	 */
	@Override
	public boolean loeschen(int personNummer) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#finde(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<Person> finde(String name, String vorname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#finde(int)
	 */
	@Override
	public Person finde(int personNummer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.persister.api.Persister#alle()
	 */
	@Override
	public List<Person> alle() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
