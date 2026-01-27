package service;

import java.sql.SQLException;

import model.Categorie;
import model.Model;
import model.Produit;

public class Service {

    private Model model;

    public Service(Model model) {
        this.model = model;
    }

    // RECUP LES DONNEES

    public void chargerDonnees() throws SQLException {
        model.getAll();
    }
    
    
    
    // Exceptions 
    
    public static class operationInvalide extends Exception {
        public operationInvalide(String message) {
            super(message);
        }
    }
    
    
     public static class champInvalide extends Exception {
            public champInvalide (String message) {
                super(message);
            }
     }
        
    
    
    // AJOUT PRODUIT

    public void ajouterProduit(String nom, float prix, int qtt_stock, int id_categ)
    
        throws SQLException, champInvalide {

	        if (nom == null) {
	        	throw new champInvalide("Nom du produit obligatoire");
	
	        }
	
	        if (prix <= 0) {
	        	throw new champInvalide("Le prix doit etre supérieur a 0");
	        }
	
	        if (qtt_stock < 0) {
	        	throw new champInvalide("La quantité de stock doit etre supérieure à 0");
	        }

        model.addProduit(nom, prix, qtt_stock, id_categ);
    }
    
    
    
    
    // AJOUT CATEGORIE

    public void ajouterCategorie(String nom, String description, int id_parent)throws SQLException, champInvalide {

        if (nom == null) {
        	throw new champInvalide("Nom de la categorie   obligatoire");
        }

        model.addCategorie(nom, description, id_parent);
    }

    
    
    
    
    
    // SUPPRESSION PRODUIT

    public void supprimerProduit(int id_produit) throws SQLException, champInvalide {

        if (id_produit <= 0) {
        	 throw new champInvalide("Identifiant du produit invalide car il ne doit pas etre inférieur ou égal à 0");
        }
            

        model.suppProduit(id_produit);
    }
    
    
    // SUPPRESSION CATEGORIE
    
    public void supprimerCategorie(int id_categ) throws SQLException, champInvalide, operationInvalide {

        if (id_categ <= 0) {
            throw new champInvalide("Identifiant catégorie invalide car il ne doit pas être inférieur ou égal à 0");
        }
        
        
        // verif si sous categ
        for (Categorie c : model.getListCateg()) {
            if (c.getId_parent() == id_categ) {
               throw new operationInvalide("Impossible de supprimer cette catégorie car elle a des sous-catégories");
            }
        }
        
        //verif si la categ a un produit relié 
        for ( Produit p : model.getListProduit()) {
        	if (p.getId_produit() == id_categ) {
        		throw new operationInvalide("Impossible de supprimer cette catégorie car elle a des produits rattachés");
        	}
        }

        
        model.suppCateg(id_categ);
        	
    }



    
    
    
    
    
    // MAJ PRODUIT

    public void modifierProduit(int id_produit, String nom, float prix,int qtt_stock, int id_categ)throws SQLException, champInvalide {

        if (id_produit <= 0) {
        	throw new champInvalide("Identifiant produit invalide car il ne peut pas etre inférieur ou égal a 0");
           
        }

        model.MajProduit(id_produit, nom, prix, qtt_stock, id_categ);
    }
    
    
    
    //  MAJ CATEG

    public void modifierCategorie(int id_categ, String nom,String description, Integer id_parent)throws SQLException, champInvalide {
    	
        if (id_categ <= 0) {
        	throw new champInvalide("Identifiant categorie invalide car il est inférieur ou égal à 0");
            
        }

        model.MajCateg(id_categ, nom, description, id_parent);
    }
    
    
    
    
    
    
    // LIER DELIER CATEG
    
    public void lierDelierCategorie(int id_categ, Integer id_parent) throws SQLException, operationInvalide {
    	
    	if (id_parent != 0 && id_parent == id_categ ) {
    		throw new operationInvalide ("Une catégorie ne peut pas etre son propre parent");
    		
    	}
    	
    	if (id_categ <= 1) {
    		throw new operationInvalide ("L'identifiant de la categorie doit etre supérieur ou égal à 1");
    		
    	}
    	
    	// DÉLIER SI LE PARENT DEVIENT NULL
        if (id_parent == 0) {
        	
            model.delierCateg(id_categ, id_parent);
            return;
        }
        
        
        model.lierCateg(id_categ, id_parent);
    }
    
    
    // LIER DELIER PRODUIT
    public void lierDelierProduit(int id_produit, int id_categ) throws SQLException, operationInvalide {
    	
    	if( id_produit <= 1) {
    		throw new operationInvalide("L'identifiant du produit doit etre supérieur ou égal à 1");
    		
    	}
    	
    	
    	if(id_categ == 0) {  // relier le produit a aucune categorie = impossible
    		throw new operationInvalide("L'identifiant de la catégorie liée ne peut pas etre null");
    		
    	}
    	
    	model.lierProduit(id_produit, id_categ);
    }
}
