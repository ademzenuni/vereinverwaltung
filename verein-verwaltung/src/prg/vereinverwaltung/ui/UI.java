package prg.vereinverwaltung.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prg.vereinverwaltung.business.api.Verwaltung;
import prg.vereinverwaltung.domain.Person;

/**
 * Diese Klasse stellt die Benutzerschnittstelle zur Verfügung.
 * 
 * @author jsucur
 * @version 1.0
 */
public class UI {

	private static Logger logger = LogManager.getLogger(UI.class);

	/* Menue-Konstanten */
	private static final String MENU_1_0_0 = "Mitglied hinzufuegen [1]     Daten Laden [2]     Beenden [3]";
	private static final String MENU_2_0_0 = "Mitglied hinzufuegen [1]     Daten Anzeigen [2]     Zurueck [3]";
	private static final String MENU_2_1_0 = "Mitglied-Nr [1]     Name und Vorname [2]     Alle [3]   Zurueck [4]";
	private static final String MENU_2_1_1 = "Mitglied editieren [1]     Mitglied Loeschen [2]     Zurueck [3]";
	private static final String MENU_2_1_2 = "Mitglied-Nr [1]     Name und Vorname [2]     Exportieren [3] Zurueck [4]";

	
	/* Liste, in der Personen-Instanzen verwaltet werden */
	// Array List Resizable-array implementation of the List interface. Implements all optional list operations, and permits all elements, including null.
	//Eine personListe erstellen mit Personen
	private List<Person> personListe = new ArrayList<>();
	
	
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
	 * @throws ParseException 
	 */
	public void execute() throws ParseException {
		int wahl = 0;
		
		menu = MENU_1_0_0;
		System.out.println(menu);

		wahl = eingabeEinlesen();

		switch (wahl) {
		case 1:
			personHinzufuegen();
			auswahlMenu2_0_0();
			break;
		case 2:
			System.out.println("Daten Laden");
			datenLaden();
			auswahlMenu2_0_0();
			break;
		case 3:
			System.out.println("Programm wurde beendet...");
			System.exit(0);
			break;
		default:
			System.out.println("Ihre wahl ist ungültig.");
			execute();
			break;
		}

	}

	private void auswahlMenu2_0_0() throws ParseException {
		int wahl = 0;

		
		menu = MENU_2_0_0;
		System.out.println(menu);
		
		wahl = eingabeEinlesen();

		switch (wahl) {
		case 1:
			personHinzufuegen();
			auswahlMenu2_0_0();
			break;
		case 2:
			System.out.println("Daten Anzeigen");
			// Alle Daten laden noch machen aus file
			auswahlMenu2_1_0();
			break;
		case 3:
			System.out.println("Zurueck");
			execute();
			break;
		default:
			System.out.println("Ihre wahl ist ungültig.");
			auswahlMenu2_0_0();
			break;
		}
		
	}

	private void auswahlMenu2_1_0() throws ParseException {
		int wahl = 0;
		
		System.out.println("\nSuchkriterium waehlen: ");
		menu = MENU_2_1_0;
		System.out.println(menu);
		

		
		wahl = eingabeEinlesen();

		switch (wahl) {
		case 1:
			//Mitglied nr eingeben und equals und anzeigen
			auswahlMenu2_1_1();
			break;
		case 2:
			// Name und Vorname eingeben und suchen und anzeigen todo
			auswahlMenu2_1_2();

			break;
		case 3:
			// alle anzeigen todo
			personListe = findeAllePersonen();
			
			//Wenn true
			if (personListe.size() > 0) {
				menu = MENU_2_1_2;
				showAllPersonen(personListe);
				
			//Wenn false	
			} else {
				System.out.println("\n Keine Daten gefunden!");
				menu = MENU_2_1_0;
			}
			//auswahlMenu2_1_2();

			break;
		case 4:
			auswahlMenu2_0_0();
			break;
		default:
			System.out.println("Ihre wahl ist ungültig.");
			auswahlMenu2_1_0();
			break;
		}
		
	}

	private void showAllPersonen(Iterable<Person> personen) {
		// TODO Auto-generated method stub
		//show Methode
			for (Person person : personen) {
				//+= Addiert einen Wert zu der angegebenen Variablen
				String str = "";
				str += "Person-Nr:    " + person.getPersonenNummer();
				str += "\nName und Vorname: " + person.getName() + " " + person.getVorname();
				str += "\nGeburtsdatum:     " + person.getGeburtsdatum();
				str += "\nAdresse:          " + person.getAdresse().getStrasse() + ", " + person.getAdresse().getPlz() + " "
						+ person.getAdresse().getOrt();
				str += "\nTelefon:          " + person.getKontakt().getTelefon();
				str += "\nE-Mail:		  " + person.getKontakt().getEmail();

				System.out.println(str);			
				}
		
	}

	private void auswahlMenu2_1_1() throws ParseException {
		int wahl = 0;
		
		menu = MENU_2_1_1;
		System.out.println(menu);
		
		
		wahl = eingabeEinlesen();
		
		switch (wahl) {
		case 1:
			//Mitglied editieren TODO
			auswahlMenu2_1_0();
			break;
		case 2:
			//Mitglied lieschen TODO
			auswahlMenu2_1_0();
			break;
		case 3:
			auswahlMenu2_1_0();
			break;
		default:
			System.out.println("Ihre wahl ist ungültig.");
			auswahlMenu2_1_1();
			break;
		}
		
		
		
		
	}

	private void auswahlMenu2_1_2() throws ParseException {
		int wahl = 0;
		
		System.out.println("Sortieren nach: ");
		menu = MENU_2_1_2;
		System.out.println(menu);
		
		
		wahl = eingabeEinlesen();		
		
		switch (wahl) {
		case 1:
			//Mitglied sortieren nummer TODO
			auswahlMenu2_1_2();
			break;
		case 2:
			//Sortieren nach name und vorname  TODO
			auswahlMenu2_1_2();
			break;
		case 3:
			//Exportieren
			auswahlMenu2_1_2();
			break;
		case 4:
			// Zurueck
			auswahlMenu2_1_0();
			break;
		default:
			System.out.println("Ihre wahl ist ungültig.");
			auswahlMenu2_1_2();
			break;
		}
	}

//Person hinzufuegen
	private void personHinzufuegen() throws ParseException{
		try {
		System.out.println("Geben Sie den Vornamen ein: ");
		Scanner vorname = new Scanner(System.in);
		String Vorname = vorname.nextLine();

		System.out.println("Geben Sie den Namen ein: ");
		Scanner name = new Scanner(System.in);
		String Name = name.nextLine();

		System.out.println("Geben Sie das Geburtsdatum ein (YYYY-MM-DD): ");
		Scanner geburtsdatum = new Scanner(System.in);
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate Geburtsdatum = null;
		String line = geburtsdatum.nextLine();
		Geburtsdatum = LocalDate.parse(line, dateFormatter);
		

		System.out.println("Geben Sie die Postleitzahl ein: ");
		Scanner plz = new Scanner(System.in);
		int PLZ = plz.nextInt();

		System.out.println("Geben Sie die Strasse ein: ");
		Scanner strasse = new Scanner(System.in);
		String Strasse = strasse.nextLine();

		System.out.println("Geben Sie den Ort ein: ");
		Scanner ort = new Scanner(System.in);
		String Ort = ort.nextLine();

		System.out.println("Geben Sie das Land ein: ");
		Scanner land = new Scanner(System.in);
		String Land = land.nextLine();

		System.out.println("Geben Sie die Telefonnummer ein: ");
		Scanner tel = new Scanner(System.in);
		int Telefon = tel.nextInt();

		System.out.println("Geben Sie die Email ein: ");
		Scanner email = new Scanner(System.in);
		String Email = email.nextLine();

		//Person erzeugen

		Person person = new Person(Vorname, Name, Geburtsdatum, PLZ, Strasse, Ort, Land, Telefon, Email);

		//Person speichern


		Person pers = verwaltung.personHinzufuegen(person);
			
		//Ungleich-Operator != Der Ungleichheits-Operator f�hrt einen Vergleich zweier Operanden aus. Wenn sich die beiden Operanden unterscheiden, 
		//so gibt der Operator den boolschen Wert true zur�ck, ansonsten false 
			if (pers != null) {
				System.out.println("\nPerson erfolgreich hinzugefuegt!");
			} else {
				System.err.println("\nPerson konnte nicht hinzugefuegt werden!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Daten laden
		private List<Person> datenLaden() {

			//Initialisierung
			List<Person> personen = null;

			try {
				personen = verwaltung.alle();
				
			} catch (Exception e) {
				logger.error("Fehler beim Versuch, daten zu Laden: ", e);
				System.out.println("\nDaten konnten nicht geladen werden!");
			}

			return personen;
		}
		
	private static int eingabeEinlesen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nBitte Ihre Auswahl eingeben: ");

		int eingabe = sc.nextInt();

		return eingabe;

	}
	
	private List<Person> findeAllePersonen() {

		List<Person> liste = null;

		try {
			liste = verwaltung.alle();
		} catch (Exception e) {
			logger.error("Fehler beim Versuch, daten zu laden: ", e);
			System.err.println("ERROR:\nDaten konnten nicht geladen werden!");
		}

		return liste;
	}
	


}
