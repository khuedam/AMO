package interTransport;

import java.util.Collection;

import modelTransport.Adresse;

import erreur.TransportException;

public interface interAdresseDAO<interAdresseDAO> {
	public void supprimer(Adresse adr)throws TransportException; // supprimer (transport, trajet, Itineraire, adresse...) 
	public void sauvegarde(Adresse adr)throws TransportException;// sauvegarde (transport, trajet, Itineraire, adresse...) 
	public void miseAjour(Adresse adr)throws TransportException;// mise à jour (transport, trajet, Itineraire, adresse...) 
	public Adresse chercher(String id)throws TransportException;//chercher  (transport, trajet, Itineraire, adresse...) 
	public Collection<Adresse> toutAdresse()throws TransportException; // chercher  (transport, trajet, Itineraire, adresse...) 

}

