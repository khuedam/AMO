package modelTransport;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import erreur.TransportException;


public class  Infos_Personnelles {
	// déclarer les variable
	private String id="";// chaque perchon n'a que un numéro de id
	private String nom, prenom,dateNaissance,tel,adresseE, siteWEB,travail; // mp est mot de pass
	private Adresse adresse;
	private Entreprise entreprise;
	public Infos_Personnelles()
	{

	}
	public Infos_Personnelles(String id, String nom, String prenom, String dateNaissance,String travail,String tel, String adresseE, String siteWEB,Entreprise entreprise,Adresse adresse) throws TransportException {
		setNom(nom);
		setPrenom(prenom);
		setAdresseE(adresseE);
		setSiteWEB(siteWEB);
		setTel(tel);
		setDateNaissance(dateNaissance);
		setTravail(travail);
		setId(id);
		this.entreprise=entreprise;
		this.adresse=adresse ;
		
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public String getTravail() {
		return travail;
	}
	public void setTravail(String travail) throws TransportException {
		if(travail.equals(null)||travail=="")
			throw new TransportException("travail not null",4);
		this.travail = travail;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) throws TransportException {
		if(id.equals(null)||id=="")
			throw new TransportException("id not null",1);
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws TransportException {
		if(nom.equals(null)||nom=="")
	throw new TransportException("nom not null",2);
		this.nom = nom;
	}
	public String getPrenom(){
		return prenom;
	}
	public void setPrenom(String prenom) throws TransportException {
		if(prenom.equals(null)||prenom=="")
			throw new TransportException("prenom not null",3);
		this.prenom = prenom;
	}
	public String getAdresseE() {
		return adresseE;
	}
	public void setAdresseE(String adresseE) throws TransportException {
		boolean  mail =Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",adresseE);
		if(!mail)
			throw new TransportException("mail est pas correct",7);
		this.adresseE = adresseE;
	}
	public String getSiteWEB() {
		
		return siteWEB;
	}
	public void setSiteWEB(String siteWEB) throws TransportException {
		if(!siteWEB.equals(null)||siteWEB==""){
			boolean  web =Pattern.matches("^(([w]{3})(\\.[_a-z0-9-]+)(\\.[_a-z0-9-]+)+)|(([http://]+)([_a-z0-9-]+)(\\.[a-z0-9-]+)+)|(([https://]+)([_a-z0-9-]+)(\\.[a-z0-9-]+)+)$",siteWEB);
			if(!web)
				throw new TransportException("web est pas correct",8);
		}
		 this.siteWEB = siteWEB;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) throws TransportException {
		boolean  nb =Pattern.matches("^((([0-9]{4})?)[-. ]?([0-9]{8})|((01|02|03|04|05|06|07|08)[0-9]{8}))$",tel);
				if(!nb)
					throw new TransportException("C'est pas numéro de telephone",5);
		this.tel = tel;
	}
	public void setDateNaissance(String dateNaissance) throws TransportException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			 formatter.parse(dateNaissance.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getMessage(),11);
		}
		this.dateNaissance =dateNaissance;
	}


}
