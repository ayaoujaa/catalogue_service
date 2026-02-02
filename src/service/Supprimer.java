package service;

import java.sql.SQLException;

import exception.ChampInvalide;
import exception.OperationInvalide;
import model.Categorie;
import model.Model;
import model.Produit;

public class Supprimer {
	 private Model model;

	 public Supprimer(Model model) {
	     this.model = model;
	 }

	    // RECUP LES DONNEES

	 public void chargerDonnees() throws SQLException {
	     model.getAll();
	 }
	
	
	  
    // SUPPRESSION PRODUIT

    public void supprimerProduit(int id_produit) throws SQLException, ChampInvalide {

        if (id_produit <= 0) {
        	 throw new ChampInvalide("Identifiant du produit invalide car il ne doit pas etre inférieur ou égal à 0");
        }
            

        model.suppProduit(id_produit);
    }
    
    
    // SUPPRESSION CATEGORIE
    
    public void supprimerCategorie(int id_categ) throws SQLException, ChampInvalide, OperationInvalide {

        if (id_categ <= 0) {
            throw new ChampInvalide("Identifiant catégorie invalide car il ne doit pas être inférieur ou égal à 0");
        }
        
        
        // verif si sous categ
        for (Categorie c : model.getListCateg()) {
            if (c.getId_parent() == id_categ) {
               throw new OperationInvalide("Impossible de supprimer cette catégorie car elle a des sous-catégories");
            }
        }
        
        //verif si la categ a un produit relié 
        for ( Produit p : model.getListProduit()) {
        	if (p.getId_produit() == id_categ) {
        		throw new OperationInvalide("Impossible de supprimer cette catégorie car elle a des produits rattachés");
        	}
        }

        
        model.suppCateg(id_categ);
        	
    }


}
