package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exception.ChampInvalide;
import model.Categorie;
import model.Model;
import model.Produit;

public class Arborescence {
	 private static Model model;

	 public Arborescence(Model model) {
	     this.model = model;
	 }  

	 
	 
	    // RECUP LES DONNEES

	 public void chargerDonnees() throws SQLException {
	     model.getAll();
	 }

	 
	 
	
	 
	 
	 
	 
	 public static String afficheArborescence(int id_categ, String indent) throws ChampInvalide, SQLException {//static car depend des objets

		    
		    if (id_categ <= 0) {
		        throw new ChampInvalide("l'id doit être supérieur ou égal à 1");
		    }

		    
		    // recup la categ entrée
		    ResultSet recupCateg = model.categorieId(id_categ);
		    if (recupCateg.next() == false) {
		    	
		        throw new ChampInvalide("la catégorie n'existe pas");
		    }
		    
		    
		    
		    String resultat = "";
		    resultat += indent + "   |__ C" 
		            + recupCateg.getInt("id_categ") 
		            + " : " + recupCateg.getString("nom") + "\n";
		    
		    
		    
		    //System.out.println(indent + "   |__ C" + recupCateg.getInt("id_categ") + " : " + recupCateg.getString("nom"));

		    
		    // produit de la categ 
		    ResultSet recupProd = model.ProduitParCateg(id_categ);
		    while (recupProd.next()) {
		    	 resultat += indent + "         |__ P" 
		    	            + recupProd.getInt("id_produit") 
		    	            + " : " + recupProd.getString("nom") + "\n";
		       // System.out.println(indent + "       |__ P" + recupProd.getInt("id_produit") + " : " + recupProd.getString("nom"));
		        
		    }

		    
		    // recup tt les sous categ
		    ResultSet recupSousCateg = model.sousCategorieId(id_categ);
		    while (recupSousCateg.next()) {
		    	
		        int sousCategId = recupSousCateg.getInt("id_categ");
		        resultat += afficheArborescence(sousCategId, indent + "    ");
		        // recursivité 
		        //afficheArborescence(sousCategId, indent + "    ");
		    }
		    return resultat;
		}

	
	
}
