package bd_banque.models;

import jakarta.persistence.*;

@Embeddable
public class Adresse {
	
	private int num;
	
	private String rue;
	
	private int codePostal;
	
	private String ville;
	
	
	// Constructor without param
	public Adresse() {
		super();
	}

	/**
	 * @param id
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Adresse(int num, String rue, int codePostal, String ville) {
		this.num = num;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	
	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
	

}
