package service;

import java.sql.SQLException;

import exception.ChampInvalide;
import model.Model;

public class Modifier {
	
	 private Model model;

	 public Modifier(Model model) {
	     this.model = model;
	 }

	    // RECUP LES DONNEES

	 public void chargerDonnees() throws SQLException {
	     model.getAll();
	 }
	
	
	
	
	

    // MAJ PRODUIT

    public void modifierProduit(int id_produit, String nom, float prix,int qtt_stock, int id_categ)throws SQLException, ChampInvalide {

        if (id_produit <= 0) {
        	throw new ChampInvalide("Identifiant produit invalide car il ne peut pas etre inférieur ou égal a 0");
           
        }

        model.MajProduit(id_produit, nom, prix, qtt_stock, id_categ);
    }
    
    
    
    //  MAJ CATEG

    public void modifierCategorie(int id_categ, String nom,String description, Integer id_parent)throws SQLException, ChampInvalide {
    	
        if (id_categ <= 0) {
        	throw new ChampInvalide("Identifiant categorie invalide car il est inférieur ou égal à 0");
            
        }

        model.MajCateg(id_categ, nom, description, id_parent);
    }
    

}
