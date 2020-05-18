package prg.vereinverwaltung.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prg.vereinverwaltung.business.api.Verwaltung;

/**
 * Diese Klasse stellt die Benutzerschnittstelle zur Verfügung.
 * 
 * @author jsucur
 * @version 1.0
 */
public class UI {

	private static Logger logger = LogManager.getLogger(UI.class);

	/* Menue-Konstanten */
	private static final String MENU_1_0_0 = "Person hinzufuegen [1]     Daten Laden [2]     Beenden [0]";

	/* Start-Menu */
	private static String menu = MENU_1_0_0;

	/**
	 * Anwendungslogik-Komponente
	 */
	private Verwaltung verwaltung;

	public UI(Verwaltung verwaltung) {
		this.verwaltung = verwaltung;
	}

	/**
	 * Steuert die Ausführung des Programms
	 */
	public void execute() {

		int wahl = 0;
		menu = MENU_1_0_0;

		do {

			// TODO ...

		} while (wahl != 0);

		System.out.println("\nProgramm wird beendet.\n");

	}

}
