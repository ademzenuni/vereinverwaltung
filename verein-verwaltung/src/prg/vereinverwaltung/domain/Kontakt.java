package prg.vereinverwaltung.domain;

/**
 * Diese Klasse bildet die Kontaktdaten ab.
 * 
 * @author jsucur
 * @version 1.0
 */
public class Kontakt {
	
	/* Test komment */ 
	private String name;
	private String vorname;
	private String telefon;
	private String email;

	
	public Kontakt(String telefon, String email, String name, String vorname) {
		this.telefon=telefon;
		this.email=email;
		this.setName(name);
		this.setVorname(vorname);
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}