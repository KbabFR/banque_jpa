package bd_banque.repositories;

import java.time.LocalDate;
import java.util.List;

import bd_banque.models.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ClientRepository {

	public static void create(String nom, String prenom, LocalDate dateNaissance) {
		Client clientToCreate = new Client(nom, prenom, dateNaissance);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(clientToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public static Client findById(Long id) {

		return PersistenceHelper.getEntityManager().find(Client.class, id);
	}

	public static List<Client> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<Client> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT cl FROM Client cl",
                // Le type de retour du resultat - en gros le FROM de la req
                Client.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public static Client update(Client clientUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (clientUpdated == null || clientUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        Client clientMerged = em.merge(clientUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return clientMerged;
	}

	public static void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        Client personneToDelete = em.find(Client.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}	
	

}
