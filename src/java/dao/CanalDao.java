/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PersonneDao.DELETE_BY_JETON;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Canal;
import modele.Personne;

/**
 *
 * @author Cissé
 */
public class CanalDao {

    public static final String AFFICHER_CANAL
            = "SELECT * FROM canal order by id_canal";

/*    public static final String AFFICHER_MEMBRE_CANAL
            = "SELECT * "
            + "FROM"
            + " membre_canal"
            + " WHERE id_canal=?"
            + ")";
*/
    public static List<Canal> getAll() throws SQLException {
        List<Canal> result = new ArrayList<Canal>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_CANAL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Canal canal = new Canal(
                    rs.getInt("id_canal"),
                    rs.getInt("id_createur"),
                    rs.getString("nom"),
                    rs.getTimestamp("date_creation").toLocalDateTime());
            result.add(canal);
        }
        return result;
    }


}
