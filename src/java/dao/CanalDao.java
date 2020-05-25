/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Canal;

/**
 *
 * @author akber
 */
public class CanalDao {

    public static final String AFFICHER_CANAL
            = " SELECT * FROM canal ORDER BY id_canal ";


    
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
