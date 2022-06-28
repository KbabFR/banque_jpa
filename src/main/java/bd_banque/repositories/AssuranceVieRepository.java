package bd_banque.repositories;

import java.time.LocalDate;
import java.util.List;

import bd_banque.models.AssuranceVie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AssuranceVieRepository {

	public static void create(LocalDate dateFin, Double taux) {
		AssuranceVie AssuranceVieToCreate = new AssuranceVie(dateFin, taux);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(AssuranceVieToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public static AssuranceVie findById(Long id) {

		return PersistenceHelper.getEntityManager().find(AssuranceVie.class, id);
	}

	public static List<AssuranceVie> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<AssuranceVie> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT av FROM AssuranceVie av",
                // Le type de retour du resultat - en gros le FROM de la req
                AssuranceVie.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public static AssuranceVie update(AssuranceVie AssuranceVieUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (AssuranceVieUpdated == null || AssuranceVieUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        AssuranceVie AssuranceVieMerged = em.merge(AssuranceVieUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return AssuranceVieMerged;
	}

	public static void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        AssuranceVie personneToDelete = em.find(AssuranceVie.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}	
	

}
