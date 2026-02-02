package controller;

import java.sql.SQLException;
import java.util.Scanner;
import exception.OperationInvalide;
import exception.ChampInvalide;
import model.Categorie;
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
        	
            supprProduit(sc, service);
            
        }
        else if (choixSupprimer == 2) {
        	
        	
        	supprCateg(sc, service);
            
        }
        
        
        // DMD D'AJOUT

        System.out.println("Ajouter (1) un produit ou (2) une categorie ?");
        int choixAjout = sc.nextInt();
        sc.nextLine();

        if (choixAjout == 1) {
        	
        	
        	creationProduit(sc, service);
           
       }

        else if (choixAjout == 2) {
        	creationCateg(sc, service);
        }

        
        
        // DMD DE MAJ
        
        System.out.println("Modifier (1) une categorie ou (2) un produit ? ");
        int choixMaj = sc.nextInt();
        sc.nextLine();
        
        
        if (choixMaj == 1) {
        	modifCateg(sc,service);
        	
        }
        
        else if (choixMaj == 2) {
        	modifProduit(sc,service);
        }
        
        
        
        System.out.println("Lier/Delier (1) une categorie ou (2) un produit ? ");
        int choixLierDelier = sc.nextInt();
        sc.nextLine();
        
        if (choixLierDelier == 1) {
        	delierLierCateg(sc, service);
        	
        }
        
        if (choixLierDelier == 2) {
        	delierLierProduit(sc, service);
        	
        	
        }
        
    
    }
    
    
    
    
    // recusrvité lier/delier categ
    public static void delierLierCateg(Scanner sc, Service service) throws SQLException {
    	System.out.println("Identifiant de la categorie : ");
    	int id_categ = sc.nextInt();
    	sc.nextLine();
    	
    	
    	System.out.println("Identifiant du parent de la catégorie : ");
    	int id_parent = sc.nextInt();
    	sc.nextLine();
    	
    	try {
    		service.lierDelierCategorie(id_categ, id_parent);
    	}catch(OperationInvalide e) {
    		System.out.println(e.getMessage());
			delierLierCateg(sc, service);
    	}
    }
    
    // recusrvité lier/delier produit
    public static void delierLierProduit(Scanner sc, Service service) throws SQLException {
    	System.out.println("Identifiant du produit : ");
    	int id_produit = sc.nextInt();
    	sc.nextLine();
    	
    	System.out.println("Identifiant de la categorie liée : ");
    	int id_categ = sc.nextInt();
    	sc.nextLine();
    	
    	try {
    		service.lierDelierProduit(id_produit, id_categ); 
    	}catch(OperationInvalide e) {
    		System.out.println(e.getMessage());
    		delierLierProduit(sc, service);
    	}
    }
    
    
    
    
    
    
    // Recusrvisite pr modif categ 
    public static void modifCateg(Scanner sc, Service service) throws SQLException {
    	System.out.println("Identifiant de la categorie : ");
    	int id_categ = sc.nextInt();
    	 sc.nextLine();
    	
    	System.out.println("Nom : ");
    	String nom = sc.nextLine();
    	
    	System.out.println("Description : ");
    	String description = sc.nextLine(); 
    	
    	try {
    		service.modifierCategorie( id_categ,  nom,  description, null);
    	}
    	catch(ChampInvalide e) {
    		System.out.println(e.getMessage());
			modifCateg(sc, service);
    	}
    }
    
    
    // Recursivité pr modif produit
    public static void modifProduit(Scanner sc , Service service) throws SQLException {
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
    	
    	
    	try {
    		service.modifierProduit(id_produit, nom, prix, qtt_stock, id_categ);
       }catch (ChampInvalide e) {
    	   System.out.println(e.getMessage());
    	   modifProduit(sc, service);
       }
     }
    
    
    
    
    
    
    // Recusrvitié pour ajt de categ
    
    public static void creationCateg(Scanner sc, Service service) throws SQLException {
    	int id_categ = 0;

        System.out.println("Nom de categorie :");
        String nom = sc.nextLine();

        System.out.println("Description :");
        String description = sc.nextLine();

        System.out.println("Id de la categorie parente :");
        int id_parent = sc.nextInt();
        sc.nextLine();
        try {
        	service.ajouterCategorie(nom, description, id_parent);
        }catch(ChampInvalide e) {
        	System.out.println(e.getMessage());
        	creationCateg(sc, service);
        }
    }
    
    
    
    // Recursivité pour l'ajt de produit  = la methode s'appelle elle meme 
    public static void creationProduit(Scanner sc, Service service) throws SQLException {
    	 System.out.println("Nom du produit :");
         String nom = sc.nextLine();
         
     
         System.out.println("Prix :");
         float prix = sc.nextFloat();
         
         System.out.println("Quantité en stock :");
         int qtt = sc.nextInt();

         System.out.println("Id de la categorie :");
         int id_categ = sc.nextInt();
         sc.nextLine();
         
         try {
			service.ajouterProduit(nom, prix, qtt, id_categ);

		} catch (ChampInvalide e) {
			System.out.println(e.getMessage());
			creationProduit(sc, service);
		}
    }
    
    
    
    
    
    // Recursivité sur la suppression produit
    public static void supprProduit(Scanner sc, Service service) throws SQLException {
    	System.out.println("Identifiant du produit :");
        int id = sc.nextInt();
        sc.nextLine();
        
        try {
        	service.supprimerProduit(id);
        }catch (ChampInvalide e) {
        	System.out.println(e.getMessage());
        	supprProduit(sc, service);
        }
    	
    }
    
    // Recursivité sur la suppresion categ
    public static void supprCateg(Scanner sc, Service service) throws SQLException, OperationInvalide, ChampInvalide {
    	
    	System.out.println("Identifiant de la categorie :");
        int id = sc.nextInt();
        sc.nextLine();
        
        
        try {
			service.supprimerCategorie(id);

		} catch (ChampInvalide | OperationInvalide e) {
			System.out.println(e.getMessage());
			supprCateg(sc, service);
		}
   }
}

