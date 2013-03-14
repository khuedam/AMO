package modelTransport;


//
//  @ Project : pfe
//  @ File Name : Entreprise.java
//  @ Date : 11/03/2013
//  @ Author : Faten,Tania,Van,Idan,Xavier
//
//
public class Entreprise {
	public int id_entreprise;
	public String nom;
	public Adresse adresse;
	public Entreprise(){
		
	}
	public Entreprise(Integer id_entreprise, String nom, Adresse adresse) {
		super();
		this.id_entreprise = id_entreprise;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public int getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}
