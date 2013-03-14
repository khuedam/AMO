package transportDAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import erreur.TransportException;

public class JdbcTools {
	private String url;
	private String utilisateur, motdepass;
	private String pilote;

	public JdbcTools() {

	}

	public JdbcTools(String pilote, String url, String utilisateur, String motdepass) {
		this.url = url;
		this.utilisateur = utilisateur;
		this.motdepass = motdepass;
		this.pilote = pilote;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getMotdepass() {
		return motdepass;
	}

	public void setMotdepass(String motdepass) {
		this.motdepass = motdepass;
	}

	public String getPilote() {
		return pilote;
	}

	public void setPilote(String pilote) {
		this.pilote = pilote;
	}

	public void loadDriver() throws ClassNotFoundException {
		Class.forName(pilote);
	}

	public Connection newConnection() throws SQLException {
		// final String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr";
		Connection conn = (Connection) DriverManager.getConnection(url, utilisateur, motdepass);
		return conn;
	}

	public void init() throws TransportException {
		try {
			this.loadDriver(); 
		} catch (ClassNotFoundException e) {
			 throw new TransportException("Pilote JDBC introuvable : " + e.getMessage());
		} 
	}

	public void quietClose(Connection conn) throws  TransportException {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				 throw new TransportException(e.getMessage());
			}
	}
	public int executeUpdate(String query) throws SQLException, TransportException {
		Connection conn = null;
		int rst = 0;
		try {
			// créer une connexion
			conn = newConnection();
			Statement stmt = (Statement) conn.createStatement();
			rst = stmt.executeUpdate(query);
			ResultSet rs = stmt.getGeneratedKeys();
			int insertedKeyValue = rs.getInt(1); 
		} catch (SQLException e) {
			 throw new TransportException(e.getMessage());
			// renvoyer cette exception
		} finally {
			quietClose(conn);
			// fe
		}
		return rst;
	}

	public int executeUpdate(String sql, java.io.Serializable... parameters)throws SQLException, TransportException {
		Connection conn = null;
		int rst = 0;
		PreparedStatement st=null;
		try {
			// 1. créer une connexion
			conn = newConnection();
			// 2. préparer l'instruction
			st = (PreparedStatement) conn.prepareStatement(sql);
			// 3. exécuter la requête
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] instanceof Integer)
					st.setInt(i+1, (Integer) parameters[i]);
				else if (parameters[i] instanceof String)
					st.setString(i+1, (String) parameters[i]);
				else if (parameters[i] instanceof Boolean)
					st.setBoolean(i+1, (Boolean) parameters[i]);
				else if (parameters[i] instanceof Date)
					st.setDate(i+1, (java.sql.Date) parameters[i]);	
				else throw new IllegalArgumentException();
			}
			st.execute();
			// 4. lire le résultat
		} catch (SQLException e) {
			// 5. construire l'exception DAO
			 throw new TransportException(e.getMessage());
			// 6. renvoyer cette exception
		} finally {
			if(st!=null)
				st.close();
			// 7. fermer la connexion
			quietClose(conn);
		}
		// renvoyer le résultat
		return rst;
	}

}
