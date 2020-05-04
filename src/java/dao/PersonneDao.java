package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Personne;

public class PersonneDao {

  public static final String GET_BY_ID_SESSION
          = "SELECT * "
          + "FROM personne "
          + "WHERE id_personne IN "
          + "("
          + "	SELECT id_personne"
          + "    FROM membre_session"
          + "    WHERE id_session_formation=?"
          + ")";
  
    public static final String GET_BY_EMAIL_PASSWORD = 
          "SELECT * FROM personne WHERE email=? AND mdp=?";
    public static final String GET_BY_EMAIL = 
          "SELECT * FROM personne WHERE email=? ";
  /**
   * Stagiaires d'une session de formation
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

  /**
   * Personne de login et mot de passe passés en paramètre, ou null si pas
   * trouvée. Le mot de passe est pour l'instant passé en clair, mais il devra
   * être crypté quand il sera crypté dans la table personne (ce qu'il faut pour
   * des raisons de sécurité).
   *
   * @param login
   * @param password
   * @return
   */
  public static Personne getByLoginPassword(String login, String password) throws SQLException {
    Connection con = Database.getConnection();
    Personne result = null;
    // Nous cherchons dans la vue membre, qui ajoute a personne le booleen est_formateur
    PreparedStatement stmt = con.prepareStatement(GET_BY_EMAIL_PASSWORD);
    stmt.setString(1, login);
    stmt.setString(2, password);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      result = new Personne(
              rs.getInt("id_personne"),
              rs.getString("nom"),
              rs.getString("prenom"),
              rs.getString("email"),
//              rs.getString("tel"),
//              rs.getString("adresse"),
//              rs.getString("code_postal"),
//              rs.getString("ville"),
              rs.getBoolean("est_administration"),
              rs.getBoolean("est_formateur"));
    }
    stmt.close();
    con.close();
    return result;
  }

    /**
     *
     * @param login
     * @return email
     * @throws SQLException
     */
    public static Personne getByLogin(String login) throws SQLException {
    Connection con = Database.getConnection();
    Personne result = null;
    // Nous cherchons dans la vue membre, qui ajoute a personne le booleen est_formateur
    PreparedStatement stmt = con.prepareStatement(GET_BY_EMAIL);
    stmt.setString(1, login);
    
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      result = new Personne(
              rs.getInt("id_personne"),
              rs.getString("nom"),
              rs.getString("prenom"),
              rs.getString("email"),
//              rs.getString("tel"),
//              rs.getString("adresse"),
//              rs.getString("code_postal"),
//              rs.getString("ville"),
              rs.getBoolean("est_administration"),
              rs.getBoolean("est_formateur"));
    }
    stmt.close();
    con.close();
    return result;
  }
    
    /** Positionne le jeton d'une personne en fonction de son email.
     * La personne doit avoir validé son inscription (date_inscription IS NOT NULL).
     * Renvoie le nombre de lignes affectées : 1 si ok (email trouvé
     * et date_inscription pas NULL), ou 0 sinon.
     * @param email
     * @param jeton
     * @return
     * @throws SQLException 
     */
  public static int setJeton(String email, String jeton) throws SQLException {
    int result = 0;
    // A implementer par toi-même
    // WHERE email=? AND date_inscription IS NOT NULL
    // METS LE DIAGRAMME DE SEQUENCE A JOUR (nom = setJeton)
    return result;
  }
}