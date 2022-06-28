package bd_banque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bd_banque.models.*;
import bd_banque.repositories.PersistenceHelper;
import jakarta.persistence.EntityManager;

public class ConnectionJpa {

	
	
	public static void main(String[] args) {
		
		EntityManager em = PersistenceHelper.getEntityManager();
		
		PersistenceHelper.beginTx(em);
		
		if(em.isOpen())
			System.out.println("open");
		
		Banque banque = new Banque();
        banque.setNom("Banque Principale");
        em.persist(banque);

        Compte compte1 = new Compte();
        compte1.setNumero("10000");
        compte1.setSolde(500D);
        Compte compte2 = new Compte();
        compte2.setNumero("10001");
        compte2.setSolde(321D);
        LivretA livretA1 = new LivretA();
        livretA1.setNumero("10002");
        livretA1.setSolde(1000D);
        livretA1.setTaux(0.015);
        LivretA livretA2 = new LivretA();
        livretA2.setNumero("10004");
        livretA2.setSolde(3D);
        livretA2.setTaux(0.003);
        AssuranceVie assurance1 = new AssuranceVie();
        assurance1.setNumero("10004");
        assurance1.setSolde(3D);
        assurance1.setDateFin(LocalDate.of(2012, 12, 21));
        assurance1.setTaux(0.003);
        em.persist(compte1);
        em.persist(compte2);
        em.persist(livretA1);
        em.persist(livretA2);
        em.persist(assurance1);
        
        
        List<Compte> listeComptes1 = new ArrayList<>();
        listeComptes1.add(compte1);
        listeComptes1.add(compte2);
        listeComptes1.add(livretA1);
        listeComptes1.add(assurance1);
        List<Compte> listeComptes2 = new ArrayList<>();
        listeComptes2.add(compte2);
        listeComptes1.add(livretA2);

        Operation ope1 = new Operation();
        //suite des opé à faire
        
        Adresse adresseC1 = new Adresse();
        adresseC1.setNum(42);
        adresseC1.setRue("Rue de la vie, l'univers et tout le reste");
        adresseC1.setVille("Montpellier");
        adresseC1.setCodePostal(34000);
        Adresse adresseC2 = new Adresse();
        adresseC2.setNum(3);
        adresseC2.setRue("Rue du GameDesign");
        adresseC2.setVille("Balaruc-les-bains");
        adresseC2.setCodePostal(34540);

        Client client1 = new Client();
        client1.setNom("Warin");
        client1.setPrenom("Thomas");
        client1.setBanque(banque);
        client1.setComptes(listeComptes1);
        client1.setAdresse(adresseC1);
        Client client2 = new Client();
        client2.setNom("Warin");
        client2.setPrenom("Bastien");
        client2.setBanque(banque);
        client2.setComptes(listeComptes2);
        client2.setAdresse(adresseC2);
        Client client3 = new Client();
        client2.setNom("Boivin");
        client2.setPrenom("Edwige");
        client2.setBanque(banque);
        client2.setComptes(listeComptes1);
        client2.setAdresse(adresseC1);
        em.persist(client1);
        em.persist(client2);
        em.persist(client3);
		
		
		PersistenceHelper.commitTxAndClose(em);
		
	}

}
