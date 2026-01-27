package model;

public class Produit {
	public Produit(int id_produit, String nom, float prix, int qtt_stock, int id_categ) {
		super();
		this.id_produit = id_produit;
		this.nom = nom;
		this.prix = prix;
		this.qtt_stock = qtt_stock;
		this.id_categ = id_categ;
	}
	public int getId_produit() {
		return id_produit;
	}
	
	//commentaire
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getQtt_stock() {
		return qtt_stock;
	}
	public void setQtt_stock(int qtt_stock) {
		this.qtt_stock = qtt_stock;
	}
	public int getId_categ() {
		return id_categ;
	}
	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}
	private int id_produit;
	private String nom;
	private float prix;
	private int qtt_stock ;
	private int id_categ;
	
	public String toString() {
		return "PRODUIT [nom=" + nom + ", prix=" + prix + ", quantité stock=" + qtt_stock + "categorie liée :"+ id_categ+ "]";
	}
}