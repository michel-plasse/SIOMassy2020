package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
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

  public static final String GET_BY_EMAIL_PASSWORD
          = "SELECT * FROM personne WHERE email=? AND mdp=?";

  public static final String INSERTION
          = "Insert into personne (nom,prenom,email,mdp,jeton,date_butoir_jeton) VALUES(?,?,?,?,?,?)";


// ajout SMA pour mise à jour des données du profil
  public static final String MAJ_BY_ID_PERSONNE
          = "UPDATE personne SET nom =?, prenom =?,email = ?, mdp =?  WHERE id_personne =? ";

// fin ajout SMA pour mise à jour des données du profil

  public static void insert(Personne p) throws SQLException {
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(INSERTION); //"Insert into personne (nom,prenom,email,mdp,jeton,date_butoir_jeton) VALUES(?,?,?,?,?,?)"
    stmt.setString(1, p.getNom());
    stmt.setString(2, p.getPrenom());
    stmt.setString(3, p.getEmail());
    stmt.setString(4, p.getMdp());
    stmt.setString(5, p.getJeton());
    stmt.setTimestamp(6, Timestamp.valueOf(p.getdateButoirJeton()));
    stmt.executeUpdate();

  }

  public static void deletePerson(String jeton) throws SQLException {
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(DELETE_BY_JETON); //"DELETE FROM personne WHERE jeton=? ";
    stmt.setString(1, jeton);
    stmt.executeUpdate();
  }

  public static void deletePersonBydate(Timestamp now) throws SQLException {
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(DELETE_BY_DATE_BUTOIR); //"DELETE FROM personne WHERE date_butoir_jeton <= ? "
    stmt.setTimestamp(1, now);
    stmt.executeUpdate();
  }

  public static final String CHECK_BY_ACTIF
          = "SELECT * FROM personne WHERE email=? and date_inscription IS NOT NULL";

  public static final String DELETE_BY_JETON
          = "DELETE FROM personne WHERE jeton=? ";

  public static final String DELETE_BY_DATE_BUTOIR
          = "DELETE FROM personne WHERE date_butoir_jeton <= ? ";

  /**
   * Stagiaires d'une session de formation
   *
   * @param idSession id de la session
   * @return les stagiaires sous forme d'une List&lt;Personne&gt;
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

  public static boolean estValide(String mail) throws SQLException {
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(CHECK_BY_ACTIF); //"SELECT * FROM personne WHERE email=? and date_inscription IS NOT NULL;"
    stmt.setString(1, mail);
    ResultSet rs = stmt.executeQuery();
    return rs.next();
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
    // Recuperer la ligne, qui peut etre null si pas d'enregistrement
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      // Enregistrement est trouve
      LocalDateTime dateInscription = (rs.getTimestamp("date_inscription") == null)
              ? null : rs.getTimestamp("date_inscription").toLocalDateTime();
      LocalDateTime dateButoirJeton = (rs.getTimestamp("date_butoir_jeton") == null)
              ? null : rs.getTimestamp("date_butoir_jeton").toLocalDateTime();
      String Jeton = (rs.getString("jeton") == null) ? "" : rs.getString("jeton");
      result = new Personne(
              rs.getInt("id_personne"),
              rs.getString("nom"),
              rs.getString("prenom"),
              rs.getString("email"),
              rs.getString("mdp"),
              Jeton,
              dateInscription,
              dateButoirJeton
      );
    }
    stmt.close();
    db.close();
    return result;
  }

// ajout SMA pour mise à jour des données du profil
    /**
   * Mise à jour des informations personnelles : nom, prénom, email et mot de passe de la personne connectée 
   *
   * @param idPersonne identifiant nimérique de la personne connectée 
   * @param nom Nom de la personne qui sera mis à jour 
   * @param prenom Prénom de la personne qui sera mis à jour
   * @param email de la personne qui sera mis à jour
   * @param mdp mot de passe de la personne qui sera mis à jour
   * @throws SQLException
   */

   public static void majByIdPersonne(int idPersonne, String nom, String prenom, String email, String mdp)throws SQLException {
        Connection connexion = null;
        Connection db = Database.getConnection();

        PreparedStatement stmt= db.prepareStatement(MAJ_BY_ID_PERSONNE);
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, email);
        stmt.setString(4, mdp);
        stmt.setInt(5, idPersonne);

        stmt.executeUpdate();
        stmt.close();
        db.close();
   }

// fin ajout SMA pour mise à jour des données du profil

}