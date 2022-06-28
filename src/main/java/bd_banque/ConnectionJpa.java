package bd_banque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPopupMenu.Separator;

import bd_banque.models.*;
import bd_banque.repositories.CompteRepository;
import bd_banque.repositories.OperationRepository;
import bd_banque.repositories.PersistenceHelper;
import jakarta.persistence.EntityManager;

public class ConnectionJpa {

	static String SEPARATOR = ", ";
	
	public static void main(String[] args) {
		
		//---Début de la création des tables---
		
		EntityManager em = PersistenceHelper.getEntityManager();
		
		PersistenceHelper.beginTx(em);
		
		//Confirmation que EntityManager est ouvert
		if(em.isOpen())
			System.out.println("open");
		
		//Création des Banque
		Banque banque = new Banque();
        banque.setNom("Banque Principale");
        em.persist(banque);

        //Création des Compte, LivretA(fille) et AssuranceVie(fille)
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
        livretA2.setNumero("10003");
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
        
        //Créationdes List de Compte, LivretA(fille) et AssuranceVie(fille)
        List<Compte> listeComptes1 = new ArrayList<>();
        listeComptes1.add(compte1);
        listeComptes1.add(compte2);
        listeComptes1.add(livretA1);
        listeComptes1.add(assurance1);
        List<Compte> listeComptes2 = new ArrayList<>();
        listeComptes2.add(compte2);
        listeComptes1.add(livretA2);

        //Création des Operations et Virement(fille)
        Operation ope1 = new Operation();
        ope1.setDate(LocalDate.of(2010, 12, 04));
        ope1.setMontant(-10D);
        ope1.setMotif("Loyer");
        ope1.setCompte(compte1);
        Operation ope2 = new Operation();
        ope2.setDate(LocalDate.of(2010, 12, 05));
        ope2.setMontant(-15D);
        ope2.setMotif("MacDo");
        ope2.setCompte(compte1);
        Virement vir1 = new Virement();
        vir1.setDate(LocalDate.of(2010, 12, 06));
        vir1.setMontant(+30D);
        vir1.setMotif("Salaire MacDo");
        vir1.setCompte(compte2);
        vir1.setBeneficiaire("Bastien Warin, propriétaire du compte2");
        Virement vir2 = new Virement();
        vir2.setDate(LocalDate.of(2010, 12, 07));
        vir2.setMontant(+42D);
        vir2.setMotif("Epargne");
        vir2.setCompte(livretA1);
        vir2.setBeneficiaire("Thomas Warin, propriétaire du compte2");
        em.persist(ope1);
        em.persist(ope2);
        em.persist(vir1);
        em.persist(vir2);
        
        //Création des Adresse
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

        // Création des Clients
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
        client3.setNom("Boivin");
        client3.setPrenom("Edwige");
        client3.setBanque(banque);
        client3.setComptes(listeComptes1);
        client3.setAdresse(adresseC1);
        em.persist(client1);
        em.persist(client2);
        em.persist(client3);
		
		PersistenceHelper.commitTxAndClose(em);
		
		//---Fin de la création des tables---
		
		//---Début des requetes---
		
		em = PersistenceHelper.getEntityManager();
		
		PersistenceHelper.beginTx(em);
		
		//on récupère les numéros, solde et classe des Comptes
		CompteRepository.findAll().forEach(co -> System.out.println(co.getNumero() + SEPARATOR + co.getSolde() + SEPARATOR + co.getClass()));
		
		//on récupère les motif, montant et classe des Operator
		OperationRepository.findAll().forEach(op -> System.out.println(op.getMotif() + SEPARATOR + op.getMontant() + SEPARATOR + op.getClass()));
		
		PersistenceHelper.commitTxAndClose(em);
		
		//---Fin des requetes---
		
	}

}
