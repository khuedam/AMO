package transportDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modelTransport.Infos_Personnelles;
import interTransport.interInfos_Personnelles;
import erreur.TransportException;

public class InfosPersonnellesDAO implements interInfos_Personnelles {

	private JdbcTools jdbctool;
	private adresseDAO adrDAO;
	private entrepriseDAO entDAO;
	public InfosPersonnellesDAO(){

	}
	public InfosPersonnellesDAO(entrepriseDAO entDAO,adresseDAO adrDAO,String pilote, String url, String utilisateur, String motdepass) throws TransportException{
		jdbctool = new  JdbcTools(pilote,url,utilisateur,motdepass);
		try {
			jdbctool.init();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getMessage());
		}
		this.entDAO=entDAO;
		this.adrDAO=adrDAO;
	}


	public JdbcTools getJdbctool() {
		return jdbctool;
	}
	public void setJdbctool(JdbcTools jdbctool) {
		this.jdbctool = jdbctool;
	}
	public adresseDAO getAdrDAO() {
		return adrDAO;
	}
	public void setAdrDAO(adresseDAO adrDAO) {
		this.adrDAO = adrDAO;
	}
	public entrepriseDAO getEntDAO() {
		return entDAO;
	}
	public void setEntDAO(entrepriseDAO entDAO) {
		this.entDAO = entDAO;
	}
	@Override
	public void supprimer(Infos_Personnelles p) throws TransportException {
		// TODO Auto-generated method stub
		
		
	}


	@Override
	public void sauvegarde(Infos_Personnelles infop) throws TransportException {
		// TODO Auto-generated method stub
		try {
			jdbctool.executeUpdate("insert into Infos_Personnelles(id_personnel,id_entreprise,id_adresse,nom,prenom,date_naissance,travail,tel,email,siteweb) values(?,?,?,?,?,?,?,?,?,?)",infop.getId(),infop.getEntreprise().getId_entreprise(),infop.getAdresse().getId_adr(),infop.getNom(),infop.getPrenom(),infop.getDateNaissance(),infop.getTravail(),infop.getTel(),infop.getAdresseE(),infop.getSiteWEB());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}
	}


	@Override
	public void miseAjour(Infos_Personnelles infop) throws TransportException {
		// TODO Auto-generated method stub
		try {
			jdbctool.executeUpdate("update Infos_Personnelles set id_entreprise=?, id_adresse=?,nom=?,prenom=?,tel=?,date_naissance=?,travail=?,tel=?,email=?,siteweb=? where id_personnel=?",infop.getEntreprise().getId_entreprise(),infop.getAdresse().getId_adr(),infop.getNom(),infop.getPrenom(),infop.getDateNaissance(),infop.getTravail(),infop.getTel(),infop.getAdresseE(),infop.getSiteWEB(),infop.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}
		
	}

	public Infos_Personnelles  chercher(String id) throws TransportException {
		// TODO Auto-generated method stub
		Infos_Personnelles infop  = null;
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		String sql="select * from  Infos_Personnelles where id_personnel=?";
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,id);
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				infop = new Infos_Personnelles();
				infop.setId(rst.getString(1));
				infop.setEntreprise(entDAO.chercher(rst.getString(2)));
				infop.setAdresse(adrDAO.chercher(rst.getString(3)));
				infop.setNom(rst.getString(4));
				infop.setPrenom(rst.getString(5));
				infop.setTravail(rst.getString(6));
				infop.setDateNaissance(rst.getString(7));
				infop.setTel(rst.getString(8));
				infop.setAdresseE(rst.getString(9));
				infop.setSiteWEB(rst.getString(10));
			}
		} catch (SQLException e) {
			throw new TransportException(e.getErrorCode(),e.getMessage());
			// 5. construire l'exception DAO
			// 6. renvoyer cette exception
		} finally {
			// fermer PreparedStatement 
			if(rst!=null)
				try {
					rst.close();
					// 7. fermer la connexion
					jdbctool.quietClose(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new TransportException(e.getErrorCode(),e.getMessage());
				}
		}
		return infop;
		
	}


	@Override
	public Collection<Infos_Personnelles> toutInfos_Personnelles()
			throws TransportException {
		// TODO Auto-generated method stub
		ArrayList<Infos_Personnelles> infosp = new ArrayList<Infos_Personnelles>();
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement("select * from Infos_Personnelles");
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				Infos_Personnelles infop = new Infos_Personnelles();
				infop.setId(rst.getString(1));
				infop.setEntreprise(entDAO.chercher(rst.getString(2)));
				infop.setAdresse(adrDAO.chercher(rst.getString(3)));
				infop.setNom(rst.getString(4));
				infop.setPrenom(rst.getString(5));
				infop.setDateNaissance(rst.getString(6));
				infop.setTravail(rst.getString(7));
				infop.setTel(rst.getString(8));
				infop.setAdresseE(rst.getString(9));
				infop.setSiteWEB(rst.getString(10));
				
				infosp.add(infop);
			}

		} catch (SQLException e) {
			throw new TransportException(e.getErrorCode(),e.getMessage());
			// 5. construire l'exception DAO
			// 6. renvoyer cette exception
		} finally {
			// fermer PreparedStatement 
			if(rst!=null)
				try {
					rst.close();
					// 7. fermer la connexion
					jdbctool.quietClose(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new TransportException(e.getErrorCode(),e.getMessage());
				}
		}
		return infosp;
		
		
	}
	
	

	

}
