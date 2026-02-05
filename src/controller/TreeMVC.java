package controller;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.ChampInvalide;
import model.Categorie;
import model.Model;
import model.Produit;
import service.Arborescence;

public class TreeMVC {
	
	
    
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ChampInvalide {
		// TODO Auto-generated method stub
		Model model = new Model();
		model.getAll();
		
		
		Arborescence arborescence = new Arborescence(model);
		
		List<Categorie> listCateg = model.getListCateg();
	    List<Produit> listProduit = model.getListProduit();
	    
	    
	   
	    
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Insérez un identifiant :");
	    int choixId = sc.nextInt();

	    //Arborescence arbo = new Arborescence(model);

	    // ici on affiche l'arborescence à partir de l'ID choisi
	    arborescence.afficherDepuisId(choixId);
	    
	    
	    
	    
		
	}
	
	
	
	
	
	

}
