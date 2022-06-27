package bd_banque.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Cette classe regroupe les m�thodes fr�quemment appel�es pour g�rer la
 * data persistence de notre projet.
 * 
 * @author jomage
 */
public class PersistenceHelper {

    private static final String MARIADB_PERSISTENCE_UNIT = "mariadb-pu";

    private static EntityManagerFactory entityManagerFactory;

    /**
     * @return un EntityManager cr�� via EntityManagerFactory
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(MARIADB_PERSISTENCE_UNIT);
        }
        return entityManagerFactory.createEntityManager();
    }

    
    /**
     * R�cup�re la transaction en cours de l'entityManager fourni, effectue un
     * commit dessus et ferme l'entityManager.
     * @param entityManager un Entity Manager qui g�re une transaction.
     */
    public static void commitTxAndClose(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    
    /**
     * R�cup�re la transaction en cours de l'entityManager fourni, effectue un
     * ROLLBACK dessus et ferme l'entityManager.
     * @param entityManager un Entity Manager qui g�re une transaction.
     */
    public static void rollbackTxAndClose(EntityManager entityManager) {
        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    
    /**
     * Ouvre une transaction sur l'entityManager fourni.
     * @param entityManager l'Entity Manager dont on veut ouvrir la transaction.
     */
    public static void beginTx(EntityManager entityManager) {
        entityManager.getTransaction().begin();
    }
}