package interTransport;

import java.util.Collection;
import modelTransport.Entreprise;
import erreur.TransportException;
public interface interEntrepriseDAO {
	public void supprimer(Entreprise e)throws TransportException; // supprimer (transport, trajet, Itineraire, adresse...) 
	public void sauvegarde(Entreprise e)throws TransportException;// sauvegarde (transport, trajet, Itineraire, adresse...) 
	public void miseAjour(Entreprise e)throws TransportException;// mise à jour (transport, trajet, Itineraire, adresse...) 
	public Entreprise chercher(String id)throws TransportException;//chercher  (transport, trajet, Itineraire, adresse...) 
	public Collection<Entreprise> toutEntreprise()throws TransportException; // chercher  (transport, trajet, Itineraire, adresse...) 
}
