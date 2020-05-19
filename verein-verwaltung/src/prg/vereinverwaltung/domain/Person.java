package prg.vereinverwaltung.domain;
import java.util.Date;
/**
 * Diese Klasse bildet eine Person ab.
 * 
 * @author azenuni
 * @version 1.0
 */
public class Person {

	private String vorname;
	private String name;
	private Date geburtsdatum; 
	private Kontakt kontakt;
	private Adresse adresse;
	
	
	public Person(String vorname, String name, Date geburtsdatum, int plz, String strasse, String ort, String land, int telefon, String email){
		this.vorname=vorname;
		this.name=name;
		this.geburtsdatum=geburtsdatum;
	
		this.kontakt = new Kontakt(telefon, email);
		this.adresse = new Adresse(plz, strasse, ort,land);
		
	}
	
	public void setVorname(String vorname) {
		this.vorname=vorname;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum=geburtsdatum;
	}
	
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	
	public Kontakt getKontakt() {
		return kontakt;
	}

	public void setKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
