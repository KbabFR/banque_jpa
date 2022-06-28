package bd_banque.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	
	private Double montant;
	
	private String motif;
	
	@ManyToOne
	private Compte compte;

	
	// Constructor without param
	public Operation() {
		super();
	}
	/**
	 * @param date
	 * @param montant
	 * @param motif
	 */
	public Operation(LocalDate date, Double montant, String motif) {
		this.date = date;
		this.montant = montant;
		this.motif = motif;
	}
	
	
	@Override
	public String toString() {
		return "Operation [id=" + id + ", date=" + date + ", montant=" + montant + ", motif=" + motif + ", compte="
				+ compte + "]";
	}
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @param localDate the date to set
	 */
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	/**
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}
	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}
	/**
	 * @param compte the compte to set
	 */
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
}
