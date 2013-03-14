package modelTransport;
public  class Trajet {
	
    private String nom,date_heure_depart,date_heure_arrive,;
    private Itineraire itineraire;
    public Lieux getDepart() {
		return depart;
	}

	public Itineraire getItineraire() {
		return itineraire;
	}
	
	public void setItineraire(Itineraire itineraire) {
		this.itineraire = itineraire;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDate_heure_depart() {
		return date_heure_depart;
	}
	public void setDate_heure_depart(String date_heure_depart) {
		this.date_heure_depart = date_heure_depart;
	}
	public String getDate_heure_arrive() {
		return date_heure_arrive;
	}
	public void setDate_heure_arrive(String date_heure_arrive) {
		this.date_heure_arrive = date_heure_arrive;
	}
}
