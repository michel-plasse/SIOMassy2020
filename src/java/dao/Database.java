package dao;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Représente la base de données. Fournit une connexion à cette base (via
 * <code>getConnection()</code>.
 *
 * ATTENTION : ajouter dans cette classe tous les codes erreur prévus par les
 * déclencheurs écrits par nous dans MySQL (après FOREIGN_KEY_NOT_FOUND).
 *
 * @author plasse
 */
public class Database {

  /**
   * Code erreur MySQL quand le serveur est inaccessible
   */
  public static final int SERVER_OFF = 0;

  /**
   * Code erreur MySQL pour "duplicate entry" (doublons)
   */
  public static final int DOUBLON = 1062;

  /**
   * Code erreur MySQL pour "Cannot delete or update a parent row: a foreign key
   * constraint fails "
   */
  public static final int ROW_IS_REFERENCED = 1451;

  /**
   * Code erreur MySQL pour "Cannot add or update a child row: a foreign key
   * constraint fails"
   */
  public static final int FOREIGN_KEY_NOT_FOUND = 1452;

  /**
   * Pilote MySQL (bibliothéque contenant les implémentations de jdbc)
   */
  protected static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
  protected static final String DB_NAME = "agriotes2020";
  protected static final String USER = "agriotes_user";
  protected static final String PASSWORD = "agriotes_pwd";

  /**
   * Chaine de connexion (adresse TCP/IP de la base de données
   */
    protected static String URL = "jdbc:mysql://localhost/" + DB_NAME + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

  // La chaine de connexion différe d'un SGBD à l'autre.
  // Pour Oracle : "jdbc:oracle:oci8:@localhost:1521:XE/" + DB_NAME
  // Pour Derby (BD en mémoire en Java) : "jdbc:derby://localhost:1527/" + DB_NAME
  // Pour MySQL : "jdbc:mysql://localhost/" + DB_NAME;

  // Bloc d'initialisation pour les variables static, ne s'exécute qu'une fois
  static {
    try {
      Class.forName(DRIVER_NAME).newInstance();
      System.out.printf("*** Driver %s loaded.\n", DRIVER_NAME);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
      // Depuis java 1.7, on peut rassembler ainsi les exceptions
      exc.printStackTrace();
      throw new RuntimeException("Pilote pas chargé");
    }
  }

  public enum SortOrder {
    ASC, DESC;
  }

  /**
   * Fournit une connexion à la base de données. Ne fait pas appel à un pool de
   * connexion, méme si cela est envisageable plus tard (ne changerait rien à
   * l'appel de la méthode)
   * <br><strong>Requiert</strong> que le pilote soit chargé
   *
   * @throws java.sql.SQLException
   */
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  /**
   * Réinitialise la base é la date passée en paramétre. Si ce paramétre vaut
   * null, prend l'instant courant.
   */
  public static void reset(LocalDateTime date) throws SQLException {
    Connection con = Database.getConnection();
    CallableStatement stmt = con.prepareCall("CALL agriotes2020_reset(?)");
    Timestamp ts = null;
    if (date != null) {
      ts = Timestamp.valueOf(date);
    }
    stmt.setTimestamp(1, ts);
    stmt.execute();
    stmt.close();
    con.close();
  }
}
