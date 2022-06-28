package bd_banque.repositories;

import java.time.LocalDateTime;
import java.util.List;

import bd_banque.models.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class OperationRepository {

	public static void create(LocalDateTime date, Double montant, String motif) {
		Operation OperationToCreate = new Operation(date, montant, motif);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(OperationToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public static Operation findById(Long id) {

		return PersistenceHelper.getEntityManager().find(Operation.class, id);
	}

	public static List<Operation> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<Operation> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT o FROM Operation o",
                // Le type de retour du resultat - en gros le FROM de la req
                Operation.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public static Operation update(Operation OperationUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (OperationUpdated == null || OperationUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        Operation OperationMerged = em.merge(OperationUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return OperationMerged;
	}

	public static void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        Operation personneToDelete = em.find(Operation.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}	
	

}