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
import prg.vereinverwaltung.domain.Adresse;
import prg.vereinverwaltung.domain.Kontakt;
import prg.vereinverwaltung.domain.Person;

/**
 * Diese Klasse stellt die Benutzerschnittstelle zur VerfÃ¼gung.
 * 
 * @author jsucur
 * @version 1.0
 */
public class UI {

	private static Logger logger = LogManager.getLogger(UI.class);

	/* Menu Strings die in jedem einzelnen Menu ausgegeben werden */
	private static final String MENU_1_0_0 = "___________________________________________________________\nMitglied hinzufuegen [1]     Daten Laden [2]     Beenden [3]";
	private static final String MENU_2_0_0 = "___________________________________________________________\nMitglied hinzufuegen [1]     Daten Anzeigen [2]     Zurueck [3]";
	private static final String MENU_2_1_0 = "___________________________________________________________\nMitglied-Nr [1]     Name und Vorname [2]     Alle [3]   Zurueck [4]";
	private static final String MENU_2_1_1 = "___________________________________________________________\nMitglied editieren [1]     Mitglied Loeschen [2]     Zurueck [3]";
	private static final String MENU_2_1_2 = "___________________________________________________________\nMitglied-Nr [1]     Name und Vorname [2]     Exportieren [3] Zurueck [4]";

	// Eine personListe erstellen mit Personen - Speicher für geladene Daten
	private List<Person> personListe = new ArrayList<>();
	// Personenliste für Suche nach Namen und Vornamen
	private List<Person> sucheNameVorname = new ArrayList<>();
	private Person person;

	/* Start-Menu */
	private static String menu = MENU_1_0_0;

	private Verwaltung verwaltung;

	public UI(Verwaltung verwaltung) {
		this.verwaltung = verwaltung;
	}

	// Starten des ersten Menus
	public void execute() throws Exception {
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
			System.out.println("Ihre Eingabe ist ungueltig.");
			execute();
			break;
		}

	}

	private void auswahlMenu2_0_0() throws Exception {
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
			System.out.println("Daten anzeigen");
			// Alle Daten laden noch machen aus file
			auswahlMenu2_1_0();
			break;
		case 3:
			System.out.println("Zurueck");
			execute();
			break;
		default:
			System.out.println("Ihre Eingabe ist ungueltig.");
			auswahlMenu2_0_0();
			break;
		}

	}

	private void auswahlMenu2_1_0() throws Exception {
		int wahl = 0;

		System.out.println("\nSuchkriterium waehlen: ");
		menu = MENU_2_1_0;
		System.out.println(menu);

		wahl = eingabeEinlesen();

		switch (wahl) {
		case 1:
			// Mitglied nr eingeben und equals und anzeigen
			Person person = sucheMitgliedNr();
			showPersonAsString(person);
			auswahlMenu2_1_1(person);
			break;
		case 2:
			// Name und Vorname eingeben und suchen und anzeigen
			sucheNameVorname = sucheNameVorname();
			showPersonenAsString(sucheNameVorname);
			auswahlMenu2_1_2();
			break;
		case 3:
			// alle anzeigen
			personListe = findeAllePersonen();

			// Wenn true
			if (personListe.size() > 0) {
				showPersonenAsString(personListe);
				auswahlMenu2_1_2();

				// Wenn false
			} else {
				System.out.println("\n Es wurden keine Daten gefunden!");
				execute();
			}
			// auswahlMenu2_1_2();

			break;
		case 4:
			auswahlMenu2_0_0();
			break;
		default:
			System.out.println("Ihre Eingabe ist ungueltig.");
			auswahlMenu2_1_0();
			break;
		}

	}

	private void auswahlMenu2_1_1(Person person) throws Exception {
		int wahl = 0;

		menu = MENU_2_1_1;
		System.out.println(menu);

		wahl = eingabeEinlesen();

		switch (wahl) {
		case 1:
			// Mitglied editieren TODO
			personendatenBearbeiten(person);
			auswahlMenu2_1_0();
			break;
		case 2:
			// Mitglied lieschen TODO
			personLoeschen(person);
			auswahlMenu2_1_0();
			break;
		case 3:
			auswahlMenu2_1_0();
			break;
		default:
			System.out.println("Ihre Eingabe ist ungueltig.");
			auswahlMenu2_1_1(person);
			break;
		}

	}

	private void auswahlMenu2_1_2() throws Exception {
		int wahl = 0;

		System.out.println("Sortieren nach: ");
		menu = MENU_2_1_2;
		System.out.println(menu);

		wahl = eingabeEinlesen();

		switch (wahl) {
		case 1:
			// Mitglied sortieren nummer TODO
			auswahlMenu2_1_2();
			break;
		case 2:
			// Sortieren nach name und vorname TODO
			auswahlMenu2_1_2();
			break;
		case 3:
			// Exportieren
			auswahlMenu2_1_2();
			break;
		case 4:
			// Zurueck
			auswahlMenu2_1_0();
			break;
		default:
			System.out.println("Ihre wahl ist ungÃ¼ltig.");
			auswahlMenu2_1_2();
			break;
		}
	}

	// Scanner fuer Menuauswahl
	private static int eingabeEinlesen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nBitte Ihre Auswahl eingeben: ");

		int eingabe = sc.nextInt();
		System.out.println("\n\n");
		return eingabe;

	}

	// M_1_0_0 und M_2_0_0
	// Erstellen eines neuen Mitglieds
	private void personHinzufuegen() {
		try {

			System.out.println("Geben Sie den Namen ein: ");
			Scanner name = new Scanner(System.in);
			String Name = name.nextLine();

			System.out.println("Geben Sie den Vornamen ein: ");
			Scanner vorname = new Scanner(System.in);
			String Vorname = vorname.nextLine();

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

			// Person erzeugen

			Person person = new Person(Vorname, Name, Geburtsdatum, PLZ, Strasse, Ort, Land, Telefon, Email);

			// Person speichern

			Person pers = verwaltung.personHinzufuegen(person);

			// Ungleich-Operator != Der Ungleichheits-Operator fï¿½hrt einen Vergleich
			// zweier Operanden aus. Wenn sich die beiden Operanden unterscheiden,
			// so gibt der Operator den boolschen Wert true zurï¿½ck, ansonsten false
			if (pers != null) {
				System.out.println("\nPerson erfolgreich hinzugefuegt!");
			} else {
				System.err.println("\nPerson konnte nicht hinzugefuegt werden!");
			}
		} catch (Exception e) {
			logger.error("ERROR:\n Person konnte nicht hinzugefuegt werden!", e);
			System.err.println("ERROR:\n Person konnte nicht hinzugefuegt werden!");
		}
	}

	// M_1_0_0
	// Laden der Daten aus einem File in eine Liste
	private List<Person> datenLaden() {

		// Initialisierung
		List<Person> personen = null;

		try {
			personen = verwaltung.alle();

		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Versuch, daten zu laden: ", e);
			System.err.println("ERROR:\n Daten konnten nicht geladen werden!");
		}

		return personen;
	}

	// MENU PUNKT M_2_1_0
	// Aufruf der finde Methode und uebergabe des Parameter
	private List<Person> findeAllePersonen() {

		List<Person> liste = null;

		try {
			liste = verwaltung.alle();
		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Versuch, daten zu laden: ", e);
			System.err.println("ERROR:\n Daten konnten nicht geladen werden!");
		}

		return liste;
	}

	// MENU PUNKT M_2_1_0
	// Aufruf der finde Methode und uebergabe des Parameter
	private Person sucheMitgliedNr() {

		Scanner sc = new Scanner(System.in);

		Person Person = null;
		try {
			System.out.println();
			System.out.print("Mitglied-Nr: ");
			int mnr = sc.nextInt();

			Person = verwaltung.finde(mnr);

			// Exceptionhandling
		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Versuch, daten zu laden: ", e);
			System.err.println("ERROR:\n Daten konnten nicht geladen werden!");
		}

		return Person;
	}

	// MENU PUNKT M_2_1_0
	// Aufruf der finde Methode und uebergabe der Parameter
	private List<Person> sucheNameVorname() {

		List<Person> liste = null;

		Scanner sc = new Scanner(System.in);

		try {
			System.out.println();

			System.out.print("Name: ");
			String name = sc.nextLine();

			System.out.print("Vorname: ");
			String vorname = sc.nextLine();

			liste = verwaltung.finde(name, vorname);

			// Exceptionhandling
		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Versuch, daten zu laden: ", e);
			System.err.println("ERROR:\n Daten konnten nicht geladen werden!");
		}

		return liste;
	}

	// MENU PUNKT M_2_1_0
	// Ausgabe der erhaltenen Person in einem String
	private void showPersonAsString(Person person) {
		// TODO Auto-generated method stub

		String str = "";

		try {
			str += "Person-Nr:    " + person.getPersonenNummer();
			str += "\nName und Vorname: " + person.getName() + " " + person.getVorname();
			str += "\nGeburtsdatum:     " + person.getGeburtsdatum();
			str += "\nAdresse:          " + person.getAdresse().getStrasse() + ", " + person.getAdresse().getPlz() + " "
					+ person.getAdresse().getOrt();
			str += "\nTelefon:          " + person.getKontakt().getTelefon();
			str += "\nE-Mail:		  " + person.getKontakt().getEmail();

		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Versuch, einen String zu erstellen: ", e);
			System.err.println("ERROR:\n Fehler beim Versuch, einen String zu erstellen!");
		}

		System.out.println(str);

	}

	// MENU PUNKT M_2_1_0
	// Ausgabe der erhaltenen Personen aus dem Personen Array in einem String
	private void showPersonenAsString(Iterable<Person> personen) {

		try {

			for (Person person : personen) {
				// += Addiert einen Wert zu der angegebenen Variablen
				String str = "";
				str += "Person-Nr:    " + person.getPersonenNummer();
				str += "\nName und Vorname: " + person.getName() + " " + person.getVorname();
				str += "\nGeburtsdatum:     " + person.getGeburtsdatum();
				str += "\nAdresse:          " + person.getAdresse().getStrasse() + ", " + person.getAdresse().getPlz()
						+ " " + person.getAdresse().getOrt();
				str += "\nTelefon:          " + person.getKontakt().getTelefon();
				str += "\nE-Mail:		  " + person.getKontakt().getEmail();
				System.out.println(str);
			}

		} catch (Exception e) {
			logger.error("ERROR:\n Fehler beim Versuch, einen String zu erstellen: ", e);
			System.err.println("ERROR:\n Fehler beim Versuch, einen String zu erstellen!");
		}

	}

	// MENU PUNKT M_2_1_1
	// Uebergebenes PersonenObjekt wird geloescht
	private void personLoeschen(Person person) {

		try {
			verwaltung.personLoeschen(person);
			System.out.println("\nDie Person wurde geloescht");
		} catch (Exception e) {
			logger.error("ERROR:\n Die Person konnte nicht entfernt werden: ", e);
			System.out.println("ERROR:\n Person wurde nicht geloescht!");
		}
	}

	// MENU PUNKT M_2_1_1
	// Übergebenes Personobjekt wird mit den neuen Werten überschrieben/bearbeitet.
	private void personendatenBearbeiten(Person person) {

		try {
			// ID vom alten Objekt wird übernommen
			person.setPersonenNummer(person.getPersonenNummer());

			Scanner sc = new Scanner(System.in);

			System.out.println("Geben Sie den Namen ein: ");
			Scanner name = new Scanner(System.in);
			String Name = name.nextLine();
			person.setName(Name);

			System.out.println("Geben Sie den Vornamen ein: ");
			Scanner vorname = new Scanner(System.in);
			String Vorname = vorname.nextLine();
			person.setVorname(Vorname);

			System.out.println("Geben Sie das Geburtsdatum ein (YYYY-MM-DD): ");
			Scanner geburtsdatum = new Scanner(System.in);

			DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
			LocalDate Geburtsdatum = null;
			String line = geburtsdatum.nextLine();
			Geburtsdatum = LocalDate.parse(line, dateFormatter);
			person.setGeburtsdatum(Geburtsdatum);

			System.out.println("Geben Sie die Postleitzahl ein: ");
			Scanner plz = new Scanner(System.in);
			int PLZ = plz.nextInt();
			person.getAdresse().setPlz(PLZ);

			System.out.println("Geben Sie die Strasse ein: ");
			Scanner strasse = new Scanner(System.in);
			String Strasse = strasse.nextLine();
			person.getAdresse().setStrasse(Strasse);

			System.out.println("Geben Sie den Ort ein: ");
			Scanner ort = new Scanner(System.in);
			String Ort = ort.nextLine();
			person.getAdresse().setOrt(Ort);

			System.out.println("Geben Sie das Land ein: ");
			Scanner land = new Scanner(System.in);
			String Land = land.nextLine();
			person.getAdresse().setLand(Land);

			System.out.println("Geben Sie die Telefonnummer ein: ");
			Scanner tel = new Scanner(System.in);
			int Telefon = tel.nextInt();
			person.getKontakt().setTelefon(Telefon);

			System.out.println("Geben Sie die Email ein: ");
			Scanner email = new Scanner(System.in);
			String Email = email.nextLine();
			person.getKontakt().setEmail(Email);

			// Bearbeitets Personenobjekt wird an die Verwaltungsklasse uebergeben!
			person = verwaltung.personAktualisieren(person);

			// Ungleich-Operator != Der Ungleichheits-Operator fï¿½hrt einen Vergleich
			// zweier Operanden aus. Wenn sich die beiden Operanden unterscheiden,
			// so gibt der Operator den boolschen Wert true zurï¿½ck, ansonsten false
			if (person != null) {
				System.out.println("\nPerson erfolgreich aktualisiert!");
			} else {
				System.out.println("\nPerson konnte nicht aktualisiert werden!");
			}

		} catch (Exception e) {
			logger.error("ERROR:\n Die Person konnte nicht bearbeitet werden: ", e);
			System.out.println("ERROR:\n Person wurde nicht bearbeitet!");
		}
	}

}
