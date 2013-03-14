package transportDAO;

import interTransport.interLieuxDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelTransport.Lieux;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import erreur.TransportException;

public class lieuxDAO implements interLieuxDAO{

	
	private JdbcTools jdbctool;
	private adresseDAO adrDAO;
	public lieuxDAO(){

	}
	public lieuxDAO(JdbcTools jdbctool)throws TransportException{
		this.jdbctool=jdbctool;
	}
	public lieuxDAO(adresseDAO adrDAO,String pilote, String url, String utilisateur, String motdepass) throws TransportException{
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
	@Override
	public void supprimer(Lieux lieu) throws TransportException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauvegarde(Lieux lieu) throws TransportException {
		try {
			jdbctool.executeUpdate("insert into Lieux(lieu,id_Adresse,nom,coordonnées) values(?,?,?,?,?,?)",lieu.getId_lieu(),lieu.getAdr().getId_adr(),lieu.getNom(),lieu.getCoordonnees());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}
	}

	@Override
	public void miseAjour(Lieux lieu) throws TransportException {
		// TODO Auto-generated method stub
		try {
			jdbctool.executeUpdate("update Lieux set id_Adresse=?,nom?=,nocoordonnees=? where id_lieu=?",lieu.getAdr().getId_adr(),lieu.getNom(),lieu.getCoordonnees(),lieu.getId_lieu());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}
	}
	

	@Override
	public Lieux chercher(String id) throws TransportException {
		Lieux lieu  = null;
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		String sql="select * from Lieux where id_lieu=?";
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,id);
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				lieu = new Lieux();
				lieu.setId_lieu(rst.getString(1));
				lieu.setAdr(adrDAO.chercher(rst.getString(2)));
				lieu.setNom(rst.getString(3));
				lieu.setCoordonnees(rst.getString(4));
				
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
		return lieu;
	}

	@Override
	public Collection<Lieux> toutLieux() throws TransportException {
		ArrayList<Lieux> lieux = new ArrayList<Lieux>();
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement("select * from Lieux");
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				Lieux lieu = new Lieux();
				lieu.setId_lieu(rst.getString(1));
				lieu.setAdr(adrDAO.chercher(rst.getString(2)));
				lieu.setNom(rst.getString(3));
				lieu.setCoordonnees(rst.getString(4));
				
				lieux.add(lieu);
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
		return lieux;

	}
}
