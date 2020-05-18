package prg.vereinverwaltung.ui.dummi;

import java.util.List;

import prg.vereinverwaltung.business.api.Verwaltung;
import prg.vereinverwaltung.domain.Person;

/**
 * Diese Klasse dient als temporäre Helper-Klasse und stellt eine stark
 * vereinfachte Implementierung der Schnittstelle <em>Verwaltung<em> zur
 * Verfügung, die nur dazu da ist, um die Funktionalitäten der UI-Komponente
 * während der Implementierung laufend testen zu können.
 * 
 * @author jsucur
 * @version 1.0
 */
public class VerwaltungDummy implements Verwaltung {

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#personHinzuguefen(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public Person personHinzuguefen(Person person) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#personAktualisieren(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public Person personAktualisieren(Person person) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#personLoeschen(prg.
	 * vereinverwaltung.domain.Person)
	 */
	@Override
	public boolean personLoeschen(Person person) throws Exception {
		// TODO Auto-generated method stub
		return false;
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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#finde(int)
	 */
	@Override
	public Person finde(int personNummer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see prg.vereinverwaltung.business.api.Verwaltung#alle()
	 */
	@Override
	public List<Person> alle() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
