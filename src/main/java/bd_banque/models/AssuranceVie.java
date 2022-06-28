package bd_banque.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class AssuranceVie extends Compte{
	
	private LocalDate dateFin;
	
	private Double taux;

	
	public AssuranceVie() {
		super();
	}
	
	/**
	 * @param dateFin
	 * @param taux
	 */
	public AssuranceVie(LocalDate dateFin, Double taux) {
		super();
		this.dateFin = dateFin;
		this.taux = taux;
	}

	
	@Override
	public String toString() {
		return "AssuranceVie [dateFin=" + dateFin + ", taux=" + taux + super.toString() + " ]";
	}

	
	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
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
