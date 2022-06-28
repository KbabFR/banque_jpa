package bd_banque.repositories;

import java.util.List;

import bd_banque.models.LivretA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class LivretARepository {

	public static void create(Double taux) {
		LivretA LivretAToCreate = new LivretA(taux);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(LivretAToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public static LivretA findById(Long id) {

		return PersistenceHelper.getEntityManager().find(LivretA.class, id);
	}

	public static List<LivretA> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<LivretA> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT la FROM LivretA la",
                // Le type de retour du resultat - en gros le FROM de la req
                LivretA.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public static LivretA update(LivretA LivretAUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (LivretAUpdated == null || LivretAUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        LivretA LivretAMerged = em.merge(LivretAUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return LivretAMerged;
	}

	public static void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        LivretA personneToDelete = em.find(LivretA.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}	
	

}
