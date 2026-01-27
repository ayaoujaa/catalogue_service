package controller;

import java.util.Scanner;

import model.Model;
import service.Service;

public class mainMVC {

    public static void main(String[] args) throws Exception {

        Model model = new Model();
        Service service = new Service(model);

        service.chargerDonnees();
        
        
        
        // DMD DE SUPPRESSION
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Supprimer (1) un produit ou (2) une categorie ?");
        int choixSupprimer = sc.nextInt();
        sc.nextLine(); 

        if (choixSupprimer == 1) {
            System.out.println("Identifiant du produit :");
            int id = sc.nextInt();
            sc.nextLine();
            service.supprimerProduit(id);
        }
        else if (choixSupprimer == 2) {
            System.out.println("Identifiant de la categorie :");
            int id = sc.nextInt();
            sc.nextLine();
            service.supprimerCategorie(id);
        }
        
        
        // DMD D'AJOUT

        System.out.println("Ajouter (1) un produit ou (2) une categorie ?");
        int choixAjout = sc.nextInt();
        sc.nextLine();

        if (choixAjout == 1) {

            System.out.println("Nom du produit :");
            String nom = sc.nextLine();

            System.out.println("Prix :");
            float prix = sc.nextFloat();

            System.out.println("Quantité en stock :");
            int qtt = sc.nextInt();

            System.out.println("Id de la categorie :");
            int id_categ = sc.nextInt();
            sc.nextLine();

            service.ajouterProduit(nom, prix, qtt, id_categ);
        }

        else if (choixAjout == 2) {

            System.out.println("Nom de categorie :");
            String nom = sc.nextLine();

            System.out.println("Description :");
            String description = sc.nextLine();

            System.out.println("Id de la categorie :");
            int id_parent = sc.nextInt();
            sc.nextLine();

            service.ajouterCategorie(nom, description, id_parent);
        }

        
        
        // DMD DE MAJ
        
        System.out.println("Modifier (1) une categorie ou (2) un produit ? ");
        int choixMaj = sc.nextInt();
        sc.nextLine();
        
        
        if (choixMaj == 1) {
        	System.out.println("Identifiant de la categorie : ");
        	int id_categ = sc.nextInt();
        	 sc.nextLine();
        	
        	System.out.println("Nom : ");
        	String nom = sc.nextLine();
        	
        	System.out.println("Description : ");
        	String description = sc.nextLine();
        	
    
        	service.modifierCategorie( id_categ,  nom,  description, null);
        	
        }
        
        else if (choixMaj == 2) {
        	System.out.println("Identifiant du produit : ");
        	int id_produit = sc.nextInt();
        	sc.nextLine();
        	
        	System.out.println("Nom : ");
        	String nom = sc.nextLine();
        	
        	System.out.println("Prix : ");
        	float prix = sc.nextFloat();
        	sc.nextLine();
        	
        	System.out.println("Quantité de stock : ");
        	int qtt_stock = sc.nextInt();
        	sc.nextLine();
        	
        	System.out.println("Identifiant de la categorie : ");
        	int id_categ = sc.nextInt();
        	sc.nextLine();
        	
        	service.modifierProduit(id_produit, nom, prix, qtt_stock, id_categ);
        }
        
        
        
        System.out.println("Lier/Delier (1) une categorie ou (2) un produit ? ");
        int choixLierDelier = sc.nextInt();
        sc.nextLine();
        
        if (choixLierDelier == 1) {
        	System.out.println("Identifiant de la categorie : ");
        	int id_categ = sc.nextInt();
        	sc.nextLine();
        	
        	
        	System.out.println("Identifiant du parent de la catégorie : ");
        	int id_parent = sc.nextInt();
        	sc.nextLine();
        	
        	
        	service.lierDelierCategorie(id_categ, id_parent);
        	
        }
        
        if (choixLierDelier == 2) {
        	System.out.println("Identifiant du produit : ");
        	int id_produit = sc.nextInt();
        	sc.nextLine();
        	
        	System.out.println("Identifiant de la categorie liée : ");
        	int id_categ = sc.nextInt();
        	sc.nextLine();
        	
        	service.lierDelierProduit(id_produit, id_categ); 
        	
        	
        }
        
        
        
        
    }
}

