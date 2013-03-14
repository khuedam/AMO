package interTransport;
import java.util.Collection;

import modelTransport.Itineraire;
import erreur.TransportException;

public interface interItineraireDAO {
	public void supprimer(Itineraire i)throws TransportException; // supprimer (transport, trajet, Itineraire, adresse...) 
	public void sauvegarde(Itineraire i)throws TransportException;// sauvegarde (transport, trajet, Itineraire, adresse...) 
	public void miseAjour(Itineraire i)throws TransportException;// mise à jour (transport, trajet, Itineraire, adresse...) 
	public Itineraire chercher(String id)throws TransportException;//chercher  (transport, trajet, Itineraire, adresse...) 
	public Collection<Itineraire> toutItineraire()throws TransportException; // chercher  (transport, trajet, Itineraire, adresse...) 

}
