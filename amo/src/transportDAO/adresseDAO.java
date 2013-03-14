package transportDAO;

import java.util.ArrayList;
import java.util.Collection;

import modelTransport.Adresse;
import erreur.TransportException;
import interTransport.interAdresseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class adresseDAO implements interAdresseDAO{
	private JdbcTools jdbctool;
	public adresseDAO(){

	}
	public JdbcTools getJdbctool() {
		return jdbctool;
	}
	public void setJdbctool(JdbcTools jdbctool) {
		this.jdbctool = jdbctool;
	}
	public adresseDAO(JdbcTools jdbctool)throws TransportException{
		this.jdbctool=jdbctool;
	}
	public adresseDAO(String pilote, String url, String utilisateur, String motdepass) throws TransportException{
		jdbctool = new  JdbcTools(pilote,url,utilisateur,motdepass);
		try {
			jdbctool.init();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getMessage());
		}
	}
	@Override
	public void supprimer(Adresse adr) throws TransportException {
		// TODO Auto-generated method stub
		try {
			jdbctool.executeUpdate("delete from Adresses where id_adresse=?",adr.getId_adr());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			throw new TransportException(ex.getErrorCode(),ex.getMessage());
		}

	}
	

	@Override
	public void sauvegarde(Adresse adr) throws TransportException {
		try {
			jdbctool.executeUpdate("insert into Adresses(id_adresse,numero_rue,nom_rue,ville,code_postal,pays) values(?,?,?,?,?,?)",adr.getId_adr(),adr.getNumero_rue(),adr.getNom_rue(),adr.getVille(),adr.getCode_postal(),adr.getPays());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}
	}

	@Override
	public void miseAjour(Adresse adr) throws TransportException {
		// TODO Auto-generated method stub
		try {
			jdbctool.executeUpdate("update Adresses set numero_rue=?, nom_rue=?,ville=?,code_postal=?,tel=?,adresseE=?,pays=? where id_adresse=?",adr.getNumero_rue(),adr.getNom_rue(),adr.getVille(),adr.getCode_postal(),adr.getVille(),adr.getId_adr());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e.getErrorCode(),e.getMessage());
		}
	}

	@Override
	public Adresse chercher(String id) throws TransportException {
		// TODO Auto-generated method stub
		Adresse adr  = null;
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		String sql="select * from Adresses where id_adresse=?";
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,id);
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				adr = new Adresse();
				adr.setId_adr(rst.getString(1));
				adr.setNumero_rue(rst.getInt(2));
				adr.setNom_rue(rst.getString(3));
				adr.setVille(rst.getString(4));
				adr.setCode_postal(rst.getString(5));
				adr.setCode_postal(rst.getString(6));
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
		return adr;
	}

	@Override
	public Collection toutAdresse() throws TransportException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Adresse> adresses = new ArrayList<Adresse>();
		Connection conn = null;
		PreparedStatement st  = null;	
		ResultSet rst =null;
		try {
			// 1. créer une connexion
			conn = jdbctool.newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement("select * from Adresses");
			rst = st.executeQuery();
			// 4. lire le résultat
			while(rst.next()){
				Adresse adr = new Adresse();
				adr.setId_adr(rst.getString(1));
				adr.setNumero_rue(rst.getInt(2));
				adr.setNom_rue(rst.getString(3));
				adr.setVille(rst.getString(4));
				adr.setCode_postal(rst.getString(5));
				adr.setCode_postal(rst.getString(6));
				adresses.add(adr);
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
		return adresses;

	}

}
