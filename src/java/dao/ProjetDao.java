/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PersonneDao.GET_BY_ID_SESSION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import dao.Database;
import modele.Projet;


/**
 *
 * @author akber
 */
public class ProjetDao {

    /**
     * public static final String INSERT_CANAL_BY_ID = " INSERT INTO canal
     * (id_canal, nom, date_creation, id_createur)" + " values (?, ?, ?, ?)";
     *
     *
     */
    public static final String AFFICHER_PROJET
            = " SELECT * FROM projet ";

    

    public static List<Projet> getAll() throws SQLException {
        List<Projet> result = new ArrayList<Projet>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_PROJET);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Projet projet = new Projet(
                    rs.getInt("id_projet"),
                    rs.getString("titre"),
                    rs.getTimestamp("date_debut").toLocalDateTime(),
                    rs.getTimestamp("date_fin").toLocalDateTime(),
                    rs.getInt("id_session_formation"),
                    rs.getInt("id_createur"));
            result.add(projet);
        }
        return result;
    }

    

}
