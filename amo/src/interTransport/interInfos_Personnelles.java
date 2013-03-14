package interTransport;

import java.util.Collection;

import modelTransport.Infos_Personnelles;
import erreur.TransportException;

public interface interInfos_Personnelles {
	
	public void supprimer(Infos_Personnelles p)throws TransportException; // supprimer (transport, trajet, Itineraire, adresse...) 
	public void sauvegarde(Infos_Personnelles p)throws TransportException;// sauvegarde (transport, trajet, Itineraire, adresse...) 
	public void miseAjour(Infos_Personnelles p)throws TransportException;// mise à jour (transport, trajet, Itineraire, adresse...) 
	public Infos_Personnelles chercher(String id)throws TransportException;//chercher  (transport, trajet, Itineraire, adresse...) 
	public Collection<Infos_Personnelles> toutInfos_Personnelles()throws TransportException; // chercher  (transport, trajet, Itineraire, adresse...) 

}
