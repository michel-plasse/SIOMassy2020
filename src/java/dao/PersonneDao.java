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
    public static final String GET_BY_EMAIL
            = "SELECT * FROM personne WHERE email=? ";
    public static final String GET_BY_EMAIL_DATE
            = "SELECT * FROM personne WHERE email=? AND date_inscription IS NOT NUL";
    public static final String INSERTION
            = "Insert into personne (nom,prenom,email,mdp,jeton,date_butoir_jeton) VALUES(?,?,?,?,?,?)";
    public static final String SET_JETON
            = "UPDATE personne SET jeton=? WHERE email=? ";
    public static final String MAJ_BY_ID_PERSONNE
            = "UPDATE personne SET nom =?, prenom =?,email = ?, mdp =?  WHERE id_personne =? ";
    public static final String MAJ_BY_MAIL_PERSONNE
            = "UPDATE personne SET  mdp =?  WHERE email = ? ";
    public static final String MAJ_BY_JETON_PERSONNE
            = "UPDATE personne SET  mdp =?  WHERE email = ? AND jeton = ? ";
    public static final String GET_BY_MAIL_JETON
            = "SELECT jeton FROM personne WHERE email = ? ";
    public static final String GET_BY_JETON
            = "SELECT * FROM personne WHERE jeton=? ";
    public static final String CHECK_BY_ACTIF
            = "SELECT * FROM personne WHERE email=? and date_inscription IS NOT NULL";

    public static final String DELETE_BY_JETON
            = "DELETE FROM personne WHERE jeton=? ";

    public static final String DELETE_BY_DATE_BUTOIR
            = "DELETE FROM personne WHERE date_butoir_jeton <= ? ";

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

    public static void updatePersonByMdp(String mail) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(SET_JETON); //"update mdp FROM personne WHERE email= ? "
        //stmt.setTimestamp(1, now);
        stmt.executeUpdate();
    }

    /**
     * Stagiaires d'une session de formation
     *
     * @param idSession id de la session
     * @return les stagiaires sous forme d'une Liste
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
     * être crypté quand il sera crypté dans la table personne (ce qu'il faut
     * pour des raisons de sécurité).
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
            // Enregistrement est trouve
            LocalDateTime dateInscription = (rs.getTimestamp("date_inscription") == null)
                    ? null : rs.getTimestamp("date_inscription").toLocalDateTime();
            LocalDateTime dateButoirJeton = (rs.getTimestamp("date_butoir_jeton") == null)
                    ? null : rs.getTimestamp("date_butoir_jeton").toLocalDateTime();
            String jeton = (rs.getString("jeton") == null) ? "" : rs.getString("jeton");
            result = new Personne(
                    rs.getInt("id_personne"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("mdp"),
                    rs.getString("url_photo"),
                    rs.getBoolean("est_Administration"),
                    rs.getBoolean("est_Formateur"),
                    jeton,
                    dateInscription,
                    dateButoirJeton
            );
        }
        stmt.close();
        con.close();
        return result;
    }

    /**
     * Positionne le jeton d'une personne en fonction de son email. La personne
     * doit avoir validé son inscription (date_inscription IS NOT NULL). Renvoie
     * le nombre de lignes affectées : 1 si ok (email trouvé et date_inscription
     * pas NULL), ou 0 sinon.
     *
     * @param email
     * @param jeton
     * @return
     * @throws SQLException
     */
    public static int setJeton(String email, String jeton) throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(SET_JETON);
        stmt.setString(1, jeton);
        stmt.setString(2, email);
        return stmt.executeUpdate();
    }

    public static int getByJeton(String email, String jeton) throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(GET_BY_JETON);
        stmt.setString(1, jeton);
        stmt.setString(2, email);
        return stmt.executeUpdate();
    }

    public static void majByIdPersonne(Personne personne) throws SQLException {

        try (Connection db = Database.getConnection()) {
            try (PreparedStatement stmt = db.prepareStatement(MAJ_BY_ID_PERSONNE)) {
                stmt.setString(1, personne.getNom());
                stmt.setString(2, personne.getPrenom());
                stmt.setString(3, personne.getEmail());
                stmt.setString(4, personne.getMdp());
                stmt.setInt(5, personne.getId());
                stmt.executeUpdate();
            }
            db.close();
        }
    }

    public static void changerMdp(String mdp, String email, String jeton) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(MAJ_BY_JETON_PERSONNE);
        stmt.setString(1, mdp);
        stmt.setString(2, email);
        stmt.setString(3, jeton);
        stmt.executeUpdate();
    }

    public boolean jetonEstTrouve(String jeton) throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(GET_BY_JETON);
        stmt.setString(1, jeton);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean jetonEstValide(String jeton) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(GET_BY_JETON); //"SELECT * FROM personne WHERE email=? and date_inscription IS NOT NULL;"
        stmt.setString(1, jeton);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static void majByMailPersonne(Personne personne) throws SQLException {
        try (Connection db = Database.getConnection()) {
            try (PreparedStatement stmt = db.prepareStatement(MAJ_BY_MAIL_PERSONNE)) {
                stmt.setString(4, personne.getMdp());
                stmt.executeUpdate();
            }
            db.close();
        }
    }
//public static Personne getByToken(String jeton) throws SQLException {
//        Connection con = Database.getConnection();
//        Personne result = null;
//        PreparedStatement stmt = con.prepareStatement(GET_BY_JETON);
//        stmt.setString(1, jeton);
//        ResultSet rs = stmt.executeQuery();
//        if (rs.next()) {
//            // Enregistrement est trouve
//           
//           
//            result = new Personne(
//                    rs.getInt("id_personne"),
//                    rs.getString("nom"),
//                    rs.getString("prenom"),
//                    rs.getString("email"),
//                    rs.getString("mdp"),
//                    rs.getString("url_photo"),
//                    rs.getBoolean("est_Administration"),
//                    rs.getBoolean("est_Formateur"),
//                    jeton
//            );
//        }
//        stmt.close();
//        con.close();
//        return result;
//} 

    public Personne personneJeton(String jeton) {
        Personne personne = new Personne();

        try {
            Connection con = Database.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_BY_MAIL_JETON);
            stmt.setString(1, jeton);

            ResultSet result = stmt.executeQuery();
            if (result.first()) {
                personne = new Personne(
                        result.getString("jeton")
                );
            }

        } catch (SQLException e) {

        }
        return personne;
    }
}
