package service;

import java.sql.SQLException;
import java.util.List;

import exception.ChampInvalide;
import model.Categorie;
import model.Model;
import model.Produit;

public class Arborescence {
	 private Model model;

	 public Arborescence(Model model) {
	     this.model = model;
	 }

	    // RECUP LES DONNEES

	 public void chargerDonnees() throws SQLException {
	     model.getAll();
	 }

	
	 public void recup(int id_categ) throws SQLException,ChampInvalide {
		
			 
			 if ( id_categ <=0) {
				 throw new ChampInvalide("l'id doit etre superieur ou egal a 1");
			 }
			 
		 
	        model.recupId(id_categ);
	    }
	 
	 
	 
	 
	 
	public static void arborescence(Categorie categorie, List<Categorie> listCateg, List<Produit>listProduit, String indent) //indentation 
	{
		
		// affiche la categ
		System.out.println(indent + "|__ C" + categorie.getId_categ()+" : "+ categorie.getNom() );
		
		
		// affiche les produits lié a la categ
		for(Produit p:listProduit) {
			if(p.getId_categ() == categorie.getId_categ()) {
				 System.out.println(indent + "   |__ P" + p.getId_produit() + " : "+ p.getNom());
			}
		}
		
		
		// parcours les categ pour trouver des sous categ de la categ mere
		for(Categorie c2: listCateg) {
			if(c2.getId_parent() == categorie.getId_categ()) { 
				
				 arborescence(c2, listCateg, listProduit, indent+"      ");
			}
		} 
	}
	
	
	
	public void afficherDepuisId(int idCateg) throws ChampInvalide {
        Categorie racine = null;

        for (Categorie c : model.getListCateg()) {
            if (c.getId_categ() == idCateg) {
                racine = c;
                break;
            }
        }

        if (racine == null) {
            throw new ChampInvalide("Catégorie inexistante !");
        }

        // Appel de ta méthode existante
        arborescence(racine, model.getListCateg(), model.getListProduit(), "   ");
    }

}
