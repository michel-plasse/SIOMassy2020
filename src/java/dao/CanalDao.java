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
import modele.Membre;
import modele.Personne;

/**
 *
 * @author Cissé
 */
public class CanalDao {

    public static final String AFFICHER_CANAL
            = "SELECT * FROM canal order by id_canal";

 public static final String AFFICHER_MEMBRE_CANAL
            = "SELECT  mc.id_canal, mc.id_personne FROM membre_canal mc WHERE mc.id_canal = ?";

    public static final String AFFICHER_TOUS_LES_MEMBRES
            = "SELECT  * FROM membre_canal ";

    public static final String INSERER_MEMBRE_CANAL
            = "Insert into membre_canal  (id_canal, id_personne) VALUES(?, ?)";

    public static final String DELETE_MEMBRE_CANAL
            = "DELETE FROM membre_canal WHERE id_canal=? and id_personne=? ";


    /**
     *
     * @param idCanal
     * @param mc
     * @return
     * @throws SQLException
     */
    public static List<Membre> getMembres(int idCanal) throws SQLException {
        List<Membre> result = new ArrayList<>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_MEMBRE_CANAL);
        stmt.setInt(1, idCanal);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Membre membres = new Membre(
                    rs.getInt("id_canal"),
                    rs.getInt("id_personne"));
            result.add(membres);
        }
        return result;
    }

    public static List<Membre> AfficherTousLesMembres() throws SQLException {
        List<Membre> result = new ArrayList<>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_TOUS_LES_MEMBRES);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Membre membres = new Membre(
                    rs.getInt("id_canal"),
                    rs.getInt("id_personne"));
            result.add(membres);
        }
        return result;
    }
//"Insert into membre_canal (id_canal, id_personne) VALUES(?,?)"
    public static void ajouterMembre(int idCanal, int idPersonne) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(INSERER_MEMBRE_CANAL);
        stmt.setInt(1, idCanal);
        stmt.setInt(2, idPersonne);
        stmt.executeUpdate();
    }
//"DELETE FROM membre_canal WHERE idCanal=? "; 
    public static void supprimerMembre(int idCanal, int idPersonne) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(DELETE_MEMBRE_CANAL);
        stmt.setInt(1, idCanal);
        stmt.setInt(2, idPersonne);
        stmt.executeUpdate();
    }
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