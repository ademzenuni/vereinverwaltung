package prg.vereinverwaltung.domain;

/**
 * Diese Klasse bildet die Kontaktdaten ab.
 * 
 * @author azenuni
 * @version 1.0
 */

public class Kontakt {

	private int telefon;
	private String email;
	
	public Kontakt(int telefon, String email) {
		this.telefon=telefon;
		this.email=email;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}