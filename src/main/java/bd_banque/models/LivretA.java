package bd_banque.models;

import jakarta.persistence.*;

//Table per Class + SequenceGenerator (fin de slide part3)
@Entity
public class LivretA extends Compte{
	
	private Double taux;

	// Constructor without param
	public LivretA() {
		super();
	}
	
	/**
	 * @param taux
	 */
	public LivretA(Double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "LivretA [taux=" + taux + "]";
	}

	/**
	 * @return the taux
	 */
	public Double getTaux() {
		return taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(Double taux) {
		this.taux = taux;
	}
	

}
