package prg.vereinverwaltung.domain;
import java.util.Date;
/**
 * Diese Klasse bildet eine Person ab.
 * 
 * @author jsucur
 * @version 1.0
 */
public class Person {

	private String vorname;
	private String name;
	private Date geburtsdatum; 
	
	private Kontakt Kontakt;
	private Adresse Adresse;
	
	
	public Person(String vorname, String name, Date geburtsdatum, int plz, String strasse, String ort, String land, int telefon, String email){
		this.vorname=vorname;
		this.name=name;
		this.geburtsdatum=geburtsdatum;
		
		this.Kontakt = new Kontakt(telefon, email);
		this.Adresse = new Adresse(plz, strasse, ort,land);
		
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
}
