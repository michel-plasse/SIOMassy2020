package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Personne;


public class PersonneDao {
	public static final String GET_BY_ID_SESSION =
			"SELECT * " + 
			"FROM personne " + 
			"WHERE id_personne IN " + 
			"(" + 
			"	SELECT id_personne" + 
			"    FROM membre_session" + 
			"    WHERE id_session_formation=?" +
			")";
	
  /** Stagiaires d'une session de formation
   * 
   * @param idSession id de la session
   * @return les stagiaires sous forme d'une List<Personne>
   * @throws SQLException 
   */
	public static List<Personne> getByIdSessionFormation(int idSession) throws SQLException {
		List<Personne> result = new ArrayList<Personne>();
		Connection db = Database.getConnection();
		PreparedStatement stmt = db.prepareStatement(GET_BY_ID_SESSION);
		stmt.setInt(1, idSession);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Personne personne = new Personne(
					rs.getInt("id_personne"),
					rs.getString("prenom"),
					rs.getString("nom"),
					rs.getString("email"));
			result.add(personne);
		}
		return result;
	}
}
