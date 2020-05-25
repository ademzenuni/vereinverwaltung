package prg.vereinverwaltung.persister.api;

import java.util.List;

import prg.vereinverwaltung.domain.Person;

/**
 * Diese Schnittstelle gibt die Funktionalitäten vor, welche für die
 * Persistierung von Pesonendaten benötigt werden.
 * 
 * @author azenuni
 * @version 1.0
 */
public interface Persister {

	/**
	 * Speichert die übergebene Person.
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Person speichern(Person person) throws Exception;

	/**
	 * Aktualisiert die übergebene Person.
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Person aktualisieren(Person person) throws Exception;

	/**
	 * Entfernt die übergehbene Person aus dem Datenbestand.
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	boolean loeschen(Person person) throws Exception;

	/**
	 * Entfernt die Person für die übergebene Person-Nummer aus dem Datenbestand.
	 * 
	 * @param personNummer
	 * @return
	 * @throws Exception
	 */
	boolean loeschen(int personNummer) throws Exception;

	/**
	 * Liefert die Personen für die übergebenen Namen und Vornamen zurück.
	 * 
	 * @param name
	 * @param vorname
	 * @return
	 * @throws Exception
	 */
	List<Person> finde(String name, String vorname) throws Exception;

	/**
	 * Liefert die Person für die übergebene Person-Nummer zurück.
	 * 
	 * @param personNummer
	 * @return
	 * @throws Exception
	 */
	Person finde(int personNummer) throws Exception;

	/**
	 * Liefert alle Personen zurück.
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Person> alle() throws Exception;
}
