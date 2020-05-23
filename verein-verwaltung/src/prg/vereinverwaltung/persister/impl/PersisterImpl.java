package prg.vereinverwaltung.persister.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	
	public static Persister getPersister() throws Exception {
		String userHome = System.getProperty("user.home");

		/* File Name - Daten als Plain Text */
		String fileName = "localDatabase.txt";

		
		//File.separator: You might want to use File.separator in UI, however, because it's best to show people what will make sense in their OS, rather than what makes sense to Java.
		File file = new File(userHome + File.separator + fileName);
		Persister persister = new PersisterImpl(file);

		return persister;

	}
	
	
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

		// Alles auslesen, Wert f�r 'nextId' um eins gr�sser als 'maxValue'
		
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
					.append(p.getAdresse().getStrasse()).append(DELIMITER).append(p.getAdresse().getPlz()).append(DELIMITER)
					.append(p.getAdresse().getOrt()).append(DELIMITER).append(p.getKontakt().getTelefon()).append(DELIMITER)
					.append(p.getKontakt().getEmail());

			return sBuilder.toString();
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
