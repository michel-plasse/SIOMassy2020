/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.MessageDao.INSERT_MESSAGE_BY_ID;
import static dao.PersonneDao.GET_BY_ID_SESSION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modele.Canal;
import modele.Message;
import dao.Database;

/**
 *
 * @author akber
 */
public class CanalDao {
    
    /**
      public static final String INSERT_CANAL_BY_ID = 
          " INSERT INTO canal (id_canal, nom, date_creation, id_createur)"
        + " values (?, ?, ?, ?)";
      
    **/
      public static final String AFFICHER_CANAL = 
              " SELECT * FROM canal ORDER BY id_canal ";

  /**
   * Stagiaires d'une session de formation
   *
   * @param idSession id de la session
   * @return les stagiaires sous forme d'une List<Personne>
   * @throws SQLException
   */
      
       
    /**  
      public void insert(Canal canal) throws SQLException {
        Connection db = Database.getConnection();

        PreparedStatement stmt = db.prepareStatement(INSERT_CANAL_BY_ID);
        stmt.setInt(1, canal.getId_canal());
        stmt.setString(2, canal.getNom());
        stmt.setTimestamp(3, Timestamp.valueOf(canal.getDate_creation()));
         stmt.setInt(3, canal.getId_createur());
        stmt.executeUpdate();
    }
    **/ 
      
         
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
