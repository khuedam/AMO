package modelTransport;
public class Adresse {
	
	private String id_adr;
	private String nom_rue;
	private int numero_rue;
	private String ville;
	private String code_postal;
	private String pays;
	
	public Adresse()
	{
	}
	public Adresse(String id_adresse, String nom_rue, int numero_rue,
			String ville, String code_postal, String pays) {
		super();
		this.id_adr = id_adresse;
		this.nom_rue = nom_rue;
		this.numero_rue = numero_rue;
		this.ville = ville;
		this.code_postal = code_postal;
		this.pays = pays;
	}
	
	public String getId_adr() {
		return id_adr;
	}
	public void setId_adr(String id_adr) {
		this.id_adr = id_adr;
	}
	public String getNom_rue() {
		return nom_rue;
	}
	public void setNom_rue(String nom_rue) {
		this.nom_rue = nom_rue;
	}
	public Integer getNumero_rue() {
		return numero_rue;
	}
	public void setNumero_rue(int numero_rue) {
		this.numero_rue = numero_rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
}
