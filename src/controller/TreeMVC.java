package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categorie;
import model.Model;
import model.Produit;

public class TreeMVC {
	
	
    
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Model model = new Model();
		model.getAll();
		
		List<Categorie> listCateg = model.getListCateg();
	    List<Produit> listProduit = model.getListProduit();
	
	     
	     
	     // categ racine id 1
		for ( Categorie c : listCateg) {
			if(c.getId_parent() == 0 ) {
				 arborescence(c, listCateg, listProduit, "   ");
			}
		}
		
	}
	
	
	public static void arborescence(Categorie categorie , List<Categorie> listCateg, List<Produit>listProduit, String indent) //indentation 
	{
		
		// affiche la categ ds l'arbre avec l'espace a chaque fois
		System.out.println(indent + "|__ C" + categorie.getId_categ()+ " : "+ categorie.getNom() );
		
		
		// affiche les produits lié a la categ
		for(Produit p:listProduit) {
			if(p.getId_categ() == categorie.getId_categ()) {
				 System.out.println(indent + "   |__ P" + p.getId_produit() + " : "+ p.getNom());
			}
		}
		
		
		// parcours les categ pour trouver des sous categ
		for(Categorie c2: listCateg) {
			if(c2.getId_parent() == categorie.getId_categ()) { 
				
				 arborescence(c2, listCateg, listProduit, indent+"      ");
			}
		}
	}
	
	
	

}
