package prg.vereinverwaltung.business.api;

import java.util.List;

import prg.vereinverwaltung.domain.Person;

/**
 * Diese Schnittstelle gibt die Funktionalitäten vor, welche für die Verwaltung
 * von Pesonendaten benötigt werden.
 * 
 * @author jsucur
 * @version 1.0
 */
public interface Verwaltung {

	/**
	 * Diese Methode fügt die übergebene Person in den Datenbestand ein.
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Person personHinzufuegen(Person person) throws Exception;

	/**
	 * Diese Methode aktualisiert die übergebene Person.
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Person personAktualisieren(Person person) throws Exception;

	/**
	 * Diese Methode entfernt die übergebene Person aus dem Datenbestand.
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	boolean personLoeschen(Person person) throws Exception;

	/**
	 * Diese Methode liefert alle Personen für die übergebenen Namen und Vornamen
	 * zurück.
	 * 
	 * @param name
	 * @param vorname
	 * @return
	 * @throws Exception
	 */
	List<Person> finde(String name, String vorname) throws Exception;

	/**
	 * Diese Methode liefert die Personen für die übergebene Person-Nummer zurück.
	 * 
	 * @param personNummer
	 * @return
	 * @throws Exception
	 */
	Person finde(int personNummer) throws Exception;

	/**
	 * Diese Methode liefert alle Personen zurück.
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Person> alle() throws Exception;
}
