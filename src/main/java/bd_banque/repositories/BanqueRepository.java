package bd_banque.repositories;

import java.util.List;

import bd_banque.models.Banque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class BanqueRepository {

	public void Create(String nom) {
		Banque BanqueToCreate = new Banque(nom);
		
		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        em.persist(BanqueToCreate);
        PersistenceHelper.commitTxAndClose(em);
		
	}

	public Banque findById(Long id) {

		return PersistenceHelper.getEntityManager().find(Banque.class, id);
	}

	public List<Banque> findAll() {

		// On va chercher l'EM
        EntityManager em = PersistenceHelper.getEntityManager();
        
        // On commence une Query JPQL
        TypedQuery<Banque> tq = em.createQuery(
                // La requ�te JPQL
                "SELECT p FROM Personne p",
                // Le type de retour du resultat - en gros le FROM de la req
                Banque.class);
        
        // Execute la requ�te et retourne LISTE de resultats
        return tq.getResultList();
	}

	public Banque update(Banque BanqueUpdated) {

		// utiliser merge
        // on peut aussi v�rifier que la personne a bien une ID
        // et refuser la mise � jour si elle n'en a pas (optionnel)
        if (BanqueUpdated == null || BanqueUpdated.getId() == null) {
            throw new RuntimeException();
        }

        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);
        Banque BanqueMerged = em.merge(BanqueUpdated);
        PersistenceHelper.commitTxAndClose(em);
        return BanqueMerged;
	}

	public void delete(Long id) {

		EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        // 1ere �tape est de "find" la personne
        Banque personneToDelete = em.find(Banque.class, id);
        //  et de v�rifier si elle existe
        if (personneToDelete != null) {
            // 2nd �tape = delete
            em.remove(personneToDelete);
        }
        PersistenceHelper.commitTxAndClose(em);
		
	}
}
