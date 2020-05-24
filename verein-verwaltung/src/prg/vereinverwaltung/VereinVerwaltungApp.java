package prg.vereinverwaltung;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prg.vereinverwaltung.business.api.Verwaltung;
import prg.vereinverwaltung.business.impl.VerwaltungImpl;
import prg.vereinverwaltung.persister.api.Persister;
import prg.vereinverwaltung.persister.impl.PersisterImpl;
import prg.vereinverwaltung.ui.UI;
/**
 * Diese Klasse ermöglicht das Starten der Applikation.
 *
 * @author jsucur
 * @version 1.0
 */

public class VereinVerwaltungApp {
	
	//Logger object: is used to log messages for a specific system or application component
	//getLogger: Method to find a named logger. getLogger(String name)
	private static Logger logger = LogManager.getLogger(VereinVerwaltungApp.class);

	//Plain text Persister instantiieren 
	//In Vorlage
	
	public static void main(String[] args) {
		/* Persister instantiieren */
		// Persister persister = new
		// prg.vereinverwaltung.persister.impl.PersisterImpl();
		try {
		Persister persister = getPersister();
		
		
		/* Verwaltung-Komponente instantiieren */
		Verwaltung verwaltung = new VerwaltungImpl(persister);
		
		
		/* UI-Komponente instantieeren */
		UI ui = new UI(verwaltung);
		
		
		/* Ausführung starten */
		ui.execute();
		System.out.println("\nDie Programmausführung wird beendet.\n");
		
		} catch (Exception e) {
			logger.error(">> Fehler: ", e);
			System.out.println("Die App wurde aus einem unbekannten Grund heruntergefahren.");
		}
		
	}
	
	//Eigener Code Persister 
	private static Persister getPersister() throws Exception {

		String userHome = System.getProperty("user.home");

		/* File Name - Daten als Plain Text */
		String fileName = "localDatabase.txt";

		
		//File.separator: You might want to use File.separator in UI, however, because it's best to show people what will make sense in their OS, rather than what makes sense to Java.
		File file = new File(userHome + File.separator + fileName);
		Persister persister = new PersisterImpl(file);

		return persister;

	}
}