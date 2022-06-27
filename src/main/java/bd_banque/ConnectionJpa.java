package bd_banque;

import jakarta.persistence.*;

@Entity
public class ConnectionJpa {

	private static EntityManagerFactory emf;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("mariadb-pu");

		EntityManager em = emf.createEntityManager();

		if (em.isOpen())
			System.out.println("EntityManager ouvert");
		
		
		em.close();

	}

}
