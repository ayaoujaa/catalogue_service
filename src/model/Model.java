package model;

import java.sql.*;
import java.util.ArrayList;

public class Model {

    private static Connection con;
    private ArrayList<Categorie> listCateg;
    private ArrayList<Produit> listProduit;

    public Model() throws ClassNotFoundException, SQLException {

        String BDD = "gestioncateg";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, passwd);

        System.out.println("Co réussie !");
    }
    
    
    
    

    //  RECUPERA° DES DONNEES 

    public void getAll() throws SQLException {

        listCateg = new ArrayList<>();
        listProduit = new ArrayList<>();

        Statement stmtCateg = con.createStatement();
        Statement stmtProduit = con.createStatement();

        ResultSet categorieToList = stmtCateg.executeQuery("SELECT * FROM categorie");
        ResultSet produitToList = stmtProduit.executeQuery("SELECT * FROM produit");

        while (categorieToList.next()) {
        	Integer id_categ = categorieToList.getInt("id_categ");
            String nom = categorieToList.getString("nom");
            int id_parent = categorieToList.getInt("id_parent");
            String description = categorieToList.getString("description");

            Categorie categorie = new Categorie(id_categ,nom, description, id_parent);
            listCateg.add(categorie);
        }

        while (produitToList.next()) {
        	
        	int id_produit =produitToList.getInt("id_produit");
            String nom = produitToList.getString("nom");
            float prix = produitToList.getFloat("prix");
            int qtt_stock = produitToList.getInt("qtt_stock");
            int id_categ = produitToList.getInt("id_categ");

            Produit produit = new Produit(id_produit,nom, prix, qtt_stock, id_categ);
            listProduit.add(produit);
        }
    }

    
    
    
    // recup id 
    public void recupId(int id_categ) throws SQLException {
    	Statement stmt = con.createStatement();
    	
    	String sql = "SELECT * FROM categorie WHERE id_categ = " + id_categ;
    	stmt.execute(sql);
    	System.out.println("requete id exécutée avec succès !");
    }
    
    
    
    
    
    
    
    
    
    //  AJOUT CATEG

    public void addCategorie(String nom, String description, int id_parent) throws SQLException {

        Statement stmt = con.createStatement();

        String sql = "INSERT INTO categorie (nom, description) VALUES ('"
                + nom + "', '" + description + "')";

        stmt.executeUpdate(sql);
        System.out.println("Catégorie ajoutée avec succès !");
    }
    
    
    // 	AJOUT PRODUIT

    public void addProduit(String nom, float prix, int qtt_stock, int id_categ) throws SQLException {

        Statement stmt = con.createStatement();

        String sql = "INSERT INTO produit (nom, prix, qtt_stock, id_categ) VALUES ('"+ nom + "', '" + prix + "', " 
        		+ qtt_stock + ", " + id_categ + ")";

        stmt.executeUpdate(sql);
        System.out.println("Produit ajouté avec succès !");
    }
    
    
    
    
    
    

    //  SUPPRESSION PRODUIT

    public void suppProduit(int id_produit) throws SQLException {

        Statement stmt = con.createStatement();
        String sql = "DELETE FROM produit WHERE id_produit = " + id_produit;
        
        stmt.executeUpdate(sql);
        System.out.println("Produit supprimé !");
    }
    
    //	SUPPRESSION CATEG

    public void suppCateg(int id_categ) throws SQLException {

        Statement stmt = con.createStatement();
        String sql = "DELETE FROM categorie WHERE id_categ = " + id_categ;
        
        stmt.executeUpdate(sql);
        System.out.println("Catégorie supprimée !");
    }
    
    
    
    
    

    //  MAJ PRODUIT

    public void MajProduit(int id_produit, String nom, float prix, int qtt_stock, int id_categ)throws SQLException {

        Statement stmt = con.createStatement();

        String sql = "UPDATE produit SET nom = '" + nom + "', prix = " + prix + ", qtt_stock = " + qtt_stock + 
        		", id_categ = " + id_categ + " WHERE id_produit = " + id_produit;

        stmt.executeUpdate(sql);
        System.out.println("Produit modifié avec succès !");
    }
    
    
    //	MAJ CATEG

    public void MajCateg(int id_categ, String nom, String description, Integer id_parent)throws SQLException {

        Statement stmt = con.createStatement();

        String sql = "UPDATE categorie SET nom = '" + nom + "', description = '" + description + "', id_parent = "
        		+ id_parent + " WHERE id_categ = " + id_categ;

        stmt.executeUpdate(sql);
        
        System.out.println("Catégorie modifiée avec succès !");
        
    }
    
    
    
    // LIEE ET DELIER 
    
    public void lierCateg(int id_categ, Integer id_parent) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE categorie SET id_parent = " + id_parent +" WHERE id_categ = " + id_categ;
    	stmt.executeUpdate(sql);
    	System.out.println("Categorie liée avec succès");
    }
    
    public void delierCateg(int id_categ,  Integer id_parent) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "Update categorie SET id_parent = NULL  WHERE id_categ =" + id_categ;
    	stmt.executeUpdate(sql);
    	System.out.println("Categorie deliée avec succès");
    }
    
    
    
    public void lierProduit(int id_produit, int id_categ) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE produit SET id_categ = " + id_categ + " WHERE id_produit = " + id_produit;
    	stmt.execute(sql);
    	System.out.println("Produit lié avec succès !");
    	
    	
    }
   

    
    
  
    
    
    //  GET 

    public ArrayList<Categorie> getListCateg() {
        return listCateg;
    }

    public ArrayList<Produit> getListProduit() {
        return listProduit;
    }
}
