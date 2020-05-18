package prg.vereinverwaltung;

import prg.vereinverwaltung.business.api.Verwaltung;
import prg.vereinverwaltung.ui.UI;
import prg.vereinverwaltung.ui.dummi.VerwaltungDummy;

/**
 * Diese Klasse ermöglicht das Starten der Applikation.
 * 
 * @author jsucur
 * @version 1.0
 */
public class VereinVerwaltungApp {

	public static void main(String[] args) {

		/* Persister instantiieren */
		// Persister persister = new
		// prg.vereinverwaltung.persister.impl.PersisterImpl();

		/* Verwaltung-Komponente instantiieren */
		// Verwaltung verwaltung = new VerwaltungImpl(persister);

		/*
		 * Bis die finale Verwaltung-Implementierung zur Verfügung steht, wird mit
		 * VerwaltungDummy-Klasse gearbeitet
		 */
		Verwaltung verwaltung = new VerwaltungDummy();

		/* UI-Komponente instantieeren */
		UI ui = new UI(verwaltung);

		/* Ausführung starten */
		ui.execute();

		System.out.println("\nDie Programmausführung wird beendet.\n");

	}
}
