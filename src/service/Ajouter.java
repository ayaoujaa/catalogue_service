package service;

import java.sql.SQLException;

import exception.ChampInvalide;
import model.Model;

public class Ajouter {

	 private Model model;

	 public Ajouter(Model model) {
	     this.model = model;
	 }

	    // RECUP LES DONNEES

	 public void chargerDonnees() throws SQLException {
	     model.getAll();
	 }
	 
	 
	 
	    // AJOUT PRODUIT

	  public void ajouterProduit(String nom, float prix, int qtt_stock, int id_categ)
	    
	  throws SQLException, ChampInvalide {

		        if (nom == null) {
		        	throw new ChampInvalide("Nom du produit obligatoire");
		
		        }
		
		        if (prix <= 0) {
		        	throw new ChampInvalide("Le prix doit etre supérieur a 0");
		        }
		
		        if (qtt_stock <= 0) {
		        	throw new ChampInvalide("La quantité de stock doit etre supérieure à 0");
		        }

	        model.addProduit(nom, prix, qtt_stock, id_categ);
	  }
	    
	    
	    
	    
	    // AJOUT CATEGORIE

	  public void ajouterCategorie(String nom, String description, int id_parent)throws SQLException, ChampInvalide {

	        if (nom == null) {
	        	throw new ChampInvalide("Nom de la categorie obligatoire");
	        }

	        model.addCategorie(nom, description, id_parent);
	  }
	 

	 
}
