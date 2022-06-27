package bd_banque.models;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "virementId")
public class Virement extends Operation{

	private String beneficiaire;

	
	public Virement() {
		super();
	}
	
	/**
	 * @param beneficiaire
	 */
	public Virement(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	
	@Override
	public String toString() {
		return "Virement [beneficiaire=" + beneficiaire + "]";
	}

	
	/**
	 * @return the beneficiaire
	 */
	public String getBeneficiaire() {
		return beneficiaire;
	}

	/**
	 * @param beneficiaire the beneficiaire to set
	 */
	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	
	
}
