package interTransport;
import java.util.Collection;
import modelTransport.Trajet;
import erreur.TransportException;
public interface interTrajetDAO {
	public void supprimer(Trajet t)throws TransportException; // supprimer (transport, trajet, Itineraire, adresse...) 
	public void sauvegarde(Trajet t)throws TransportException;// sauvegarde (transport, trajet, Itineraire, adresse...) 
	public void miseAjour(Trajet t)throws TransportException;// mise à jour (transport, trajet, Itineraire, adresse...) 
	public Trajet chercher(String id)throws TransportException;//chercher  (transport, trajet, Itineraire, adresse...) 
	public Collection<Trajet> toutTrajet()throws TransportException; // chercher  (transport, trajet, Itineraire, adresse...) 
}
