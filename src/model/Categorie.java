package model;

public class Categorie {
	public Categorie(int id_categ, String nom, String description, Integer id_parent) {
		super();
		this.id_categ = id_categ;
		this.nom = nom;
		this.description = description;
		this.id_parent = id_parent;
	}
	public int getId_categ() {
		return id_categ;
	}
	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId_parent() {
		return id_parent;
	}
	public void setId_parent(Integer id_parent) {
		this.id_parent = id_parent;
	}
	private int id_categ;
	private String nom ;
	private String description ;
	private Integer id_parent ;

	// chaine de caractere 
	public String toString() {
			return "CATEGORIE [nom=" + nom + ", description=" + description + ", sous-categorie=" + id_parent + "]";
		}
}