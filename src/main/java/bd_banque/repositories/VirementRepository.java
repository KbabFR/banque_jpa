package bd_banque.repositories;

import java.util.List;

import bd_banque.models.Virement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VirementRepository {

	public static void create(String beneficiaire) {
		Virement VirementToCreate = new Virement(beneficiaire);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(VirementToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public static Virement findById(Long id) {

		return PersistenceHelper.getEntityManager().find(Virement.class, id);
	}

	public static List<Virement> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<Virement> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT cl FROM Virement cl",
                // Le type de retour du resultat - en gros le FROM de la req
                Virement.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public static Virement update(Virement VirementUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (VirementUpdated == null || VirementUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        Virement VirementMerged = em.merge(VirementUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return VirementMerged;
	}

	public static void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        Virement personneToDelete = em.find(Virement.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}	
	

}
