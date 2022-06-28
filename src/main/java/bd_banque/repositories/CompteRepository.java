package bd_banque.repositories;

import java.util.List;

import bd_banque.models.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CompteRepository {

	public static void create(String num, Double solde) {
		Compte CompteToCreate = new Compte(num, solde);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(CompteToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public static Compte findById(Long id) {

		return PersistenceHelper.getEntityManager().find(Compte.class, id);
	}

	public static List<Compte> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<Compte> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT co FROM Compte co",
                // Le type de retour du resultat - en gros le FROM de la req
                Compte.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public static Compte update(Compte CompteUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (CompteUpdated == null || CompteUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        Compte CompteMerged = em.merge(CompteUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return CompteMerged;
	}

	public static void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        Compte personneToDelete = em.find(Compte.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}
}
