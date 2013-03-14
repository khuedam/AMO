package modelTransport;
import java.awt.Image;
import java.util.Vector;
public class Itineraire {

	private String id_itineraire;
	private String nom;
	private int distance;
	private Image carte;
	private Vector<Arrete> arretes;
	public Itineraire(String id_itineraire,String nom,int distance,Image carte) {
		super();
		this.id_itineraire = id_itineraire;
		this.nom=nom;
		this.distance=distance;
		this.carte=carte;
		arretes=new Vector<Arrete>();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Vector<Arrete> getArretes() {
		return arretes;
	}
	public void setArretes(Vector<Arrete> arretes) {
		this.arretes = arretes;
	}
	public Image getCarte() {
		return carte;
	}

	public void setCarte(Image carte) {
		this.carte = carte;
	}
	public String getId_itineraire() {
		return id_itineraire;
	}

	public void setId_itineraire(String id_itineraire) {
		this.id_itineraire = id_itineraire;
	}
}
