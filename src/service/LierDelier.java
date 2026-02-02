package service;

import java.sql.SQLException;

import exception.OperationInvalide;
import model.Model;

public class LierDelier {

	 private Model model;

	 public LierDelier(Model model) {
	     this.model = model;
	 }

	    // RECUP LES DONNEES

	 public void chargerDonnees() throws SQLException {
	     model.getAll();
	 }
	
	
	
	

    // LIER DELIER CATEG
    
    public void lierDelierCategorie(int id_categ, Integer id_parent) throws SQLException, OperationInvalide {
    	
    	if (id_parent != 0 && id_parent == id_categ ) {
    		throw new OperationInvalide ("Une catégorie ne peut pas etre son propre parent");
    		
    	}
    	
    	if (id_categ <= 1) {
    		throw new OperationInvalide ("L'identifiant de la categorie doit etre supérieur ou égal à 1");
    		
    	}
    	
    	// DÉLIER SI LE PARENT DEVIENT NULL
        if (id_parent == 0) {
        	
            model.delierCateg(id_categ, id_parent);
            return;
        }  
        
        
        model.lierCateg(id_categ, id_parent);
    }
    
    
    // LIER DELIER PRODUIT
    public void lierDelierProduit(int id_produit, int id_categ) throws SQLException, OperationInvalide {
    	
    	if( id_produit <= 1) {
    		throw new OperationInvalide("L'identifiant du produit doit etre supérieur ou égal à 1");
    		
    	}
    	
    	
    	if(id_categ == 0) {  // relier le produit a aucune categorie = impossible
    		throw new OperationInvalide("L'identifiant de la catégorie liée ne peut pas etre null");
    		
    	}
    	
    	model.lierProduit(id_produit, id_categ);
    }
}



