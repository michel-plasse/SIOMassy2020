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
  
  public static final String Insertion 
          ="Insert into personne (nom,prenom,email,mdp,jeton,est_Actif) VALUES(?,?,?,?,?,?) ";

  public static final String CHECK_BY_MAIL  
          ="SELECT * FROM personne WHERE email=?";
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
  
  /*public static void addPerson(String nom,String prenom,String email,String mdp) throws SQLException{
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(Insertion);
    stmt.setString(1, nom);
    stmt.setString(2, prenom);
    stmt.setString(3, email);
    stmt.setString(4, mdp);
    stmt.executeUpdate();
    
      
  }*/
  
  public static void insert(Personne p) throws SQLException{
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(Insertion);
    stmt.setString(1, p.getNom());
    stmt.setString(2, p.getPrenom());
    stmt.setString(3, p.getEmail());
    stmt.setString(4, p.getMdp());
    stmt.setString(5, p.getJeton());
    stmt.setBoolean(6, p.getestActif());
    stmt.executeUpdate();
    
      
  }
  public static boolean mailExist(String mail)throws SQLException{
      
    int cpt=0;
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(CHECK_BY_MAIL);
    stmt.setString(1, mail);
    ResultSet rs= stmt.executeQuery();
    while (rs.next()) {
        cpt++;
    }
    if(cpt == 1){
        return true;
    }
    
    
      return false;
      
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
    Connection db = Database.getConnection();
    Personne result = null;
    // Nous cherchons dans la vue membre, qui ajoute a personne le booleen est_formateur
    PreparedStatement stmt = db.prepareStatement(GET_BY_EMAIL_PASSWORD);
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
    db.close();
    return result;
  }
}
