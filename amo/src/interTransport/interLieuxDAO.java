package interTransport;
import java.util.Collection;

import modelTransport.Lieux;
import erreur.TransportException;

public interface interLieuxDAO {
	public void supprimer(Lieux lieu)throws TransportException; // supprimer (transport, trajet, Itineraire, adresse...) 
	public void sauvegarde(Lieux lieu)throws TransportException;// sauvegarde (transport, trajet, Itineraire, adresse...) 
	public void miseAjour(Lieux lieu)throws TransportException;// mise à jour (transport, trajet, Itineraire, adresse...) 
	public Lieux chercher(String id)throws TransportException;//chercher  (transport, trajet, Itineraire, adresse...) 
	public Collection<Lieux> toutLieux()throws TransportException; // chercher  (transport, trajet, Itineraire, adresse...) 
}
