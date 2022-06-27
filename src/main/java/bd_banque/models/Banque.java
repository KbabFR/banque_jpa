package bd_banque.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Banque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@OneToMany(mappedBy = "banque")
	private List<Client> clients;
	
	// Constructor without param
	public Banque() {
		super();
	}
	
	/**
	 * @param nom
	 */
	public Banque(String nom) {
		this.nom = nom;
	}


	@Override
	public String toString() {
		return "Banque [id=" + id + ", nom=" + nom + ", client=" + clients + "]";
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the client
	 */
	public List<Client> getClient() {
		return clients;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(List<Client> client) {
		this.clients = client;
	}
	
	

}
