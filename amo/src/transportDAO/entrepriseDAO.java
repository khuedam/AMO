package transportDAO;
import interTransport.interEntrepriseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelTransport.Entreprise;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import erreur.TransportException;

public class entrepriseDAO implements interEntrepriseDAO {

	private JdbcTools jdbctool;
	private adresseDAO adrDAO;
	public entrepriseDAO(){

	}

	public entrepriseDAO(adresseDAO adrDAO, String pilote, String url, String utilisateur, String motdepass) throws TransportException{
		jdbctool = new  JdbcTools(pilote,url,utilisateur,motdepass);
		try {
			jdbctool.init();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getMessage());
		}
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

	public void supprimer(Entreprise ent) throws TransportException {
		// TODO Auto-generated method stub
		
		try {
			jdbctool.executeUpdate("delete from entreprise where id_entreprise=?",ent.getId_entreprise());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}



	}

	@Override
	public void sauvegarde(Entreprise e) throws TransportException {
		try {
			jdbctool.executeUpdate("insert into Entreprise(id_entreprise,nom,adresse) values(?,?,?)",e.getId_entreprise(),e.getNom(),e.getAdresse().getId_adr());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			throw new TransportException(ex.getErrorCode(),ex.getMessage());
		}
	}

	@Override
	public void miseAjour(Entreprise e) throws TransportException {
		// TODO Auto-generated method stub
		try {
			jdbctool.executeUpdate("update Entreprise set id_entreprise=?, nom=?,adresse=? where id_entreprise=?",e.getNom(),e.getAdresse().getId_adr(),e.getId_entreprise());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			throw new TransportException(ex.getErrorCode(),ex.getMessage());
		}
	}

	public Entreprise chercher(String id) throws TransportException {
		Entreprise ent  = null;
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		String sql="select * from Entreprise where id_entreprise=?";
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,id);
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				ent = new Entreprise();
				ent.setId_entreprise(rst.getInt(1));
				ent.setNom(rst.getString(2));
				ent.setAdresse(adrDAO.chercher(rst.getString(3)));
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
		return ent;
	}

	@Override
	public Collection<Entreprise> toutEntreprise() throws TransportException {
		ArrayList<Entreprise> entreprises = new ArrayList<Entreprise>();
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement("select * from Entreprise");
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				Entreprise ent = new Entreprise();
				ent.setId_entreprise(rst.getInt(1));
				ent.setNom(rst.getString(2));
				ent.setAdresse(adrDAO.chercher(rst.getString(3)));
				entreprises.add(ent);
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
		return entreprises;

	}




}
