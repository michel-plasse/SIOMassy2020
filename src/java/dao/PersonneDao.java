package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

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
          ="Insert into personne (nom,prenom,email,mdp,jeton,est_Actif, date_insertion) VALUES(?,?,?,?,?,?,?) ";

  public static final String CHECK_BY_MAIL  
          ="SELECT * FROM personne WHERE email=?";
  
   public static final String CHECK_BY_ACTIF  
          ="SELECT * FROM personne WHERE email=? and est_Actif=true";
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
  

  
  public static void insert(Personne p) throws SQLException{
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(Insertion); //Insert into personne (nom,prenom,email,mdp,jeton,est_Actif, date_insertion) VALUES(?,?,?,?,?,?,?)
    stmt.setString(1, p.getNom());
    stmt.setString(2, p.getPrenom());
    stmt.setString(3, p.getEmail());
    stmt.setString(4, p.getMdp());
    stmt.setString(5, p.getJeton());
    stmt.setBoolean(6, p.getestActif());
    stmt.setTimestamp(7, p.getdateInsertion());
    
    stmt.executeUpdate();
    
      
  }
  
  public static void update(String prenom, String nom, String mail, String mdp, String jeton) throws SQLException{
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement("UPDATE personne SET prenom=? , nom=?, mdp=?, jeton=?, date_insertion=? where email=?");
    stmt.setString(1, prenom);
    stmt.setString(2, nom);
    stmt.setString(3, mdp);
    stmt.setString(4, jeton);
    stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
    stmt.setString(6, mail);
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

  public static boolean compteActif(String mail)throws SQLException{
    int cpt=0;
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(CHECK_BY_ACTIF); //"SELECT * FROM personne WHERE email=? and est_Actif=true"
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
              rs.getBoolean("est_formateur"),
              rs.getBoolean("est_administration"),
              rs.getBoolean("est_Actif")
      );
    }
    stmt.close();
    db.close();
    return result;
  }
  
  
   
}
