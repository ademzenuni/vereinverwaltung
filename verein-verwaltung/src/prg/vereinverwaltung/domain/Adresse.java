package prg.vereinverwaltung.domain;

/**
 * Diese Klasse bildet eine Adresse ab.
 * 
 * @author azenuni
 * @version 1.0
 *
 */
public class Adresse {
	
	private int plz;
	private String strasse;
	private String ort;
	private String land;
	
	public Adresse(int plz, String strasse, String ort, String land) {
		this.plz=plz;
		this.strasse=strasse;
		this.ort=ort;
		this.land=land;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

}