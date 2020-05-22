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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import modele.Message;

/**
 *
 * @author akber
 */
public class MessageDao {
    

/**
      public static final String INSERT_MESSAGE_BY_ID = 
          " INSERT INTO message_canal (id_message_canal, date_publication, contenu, id_auteur, id_canal)"
        + " values (?, ?, ?, ?, ?)";
      
 */
    
      public static final String AFFICHER_MESSAGE = 
              " SELECT * FROM message_canal ORDER BY id_message_canal";

  /**
   * Stagiaires d'une session de formation
   *
   * @param idSession id de la session
   * @return les stagiaires sous forme d'une List<Personne>
   * @throws SQLException
   */
      
   /**   
  public void insert(Message message) throws SQLException {
        Connection db = Database.getConnection();

        PreparedStatement stmt = db.prepareStatement(INSERT_MESSAGE_BY_ID);
        stmt.setInt(1, message.getId_messageCanal());
        stmt.setInt(2, message.getId_auteur());
        stmt.setInt(3, message.getId_canal());
        stmt.setString(4, message.getContenu());
        stmt.setTimestamp(5, Timestamp.valueOf(message.getDate_publication()));
        stmt.executeUpdate();
    }
      
      
     */ 
      
      
  public static List<Message> getMessage(int idCanal) throws SQLException {
    List<Message> result = new ArrayList<Message>();
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(AFFICHER_MESSAGE);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      Message message = new Message(
              rs.getInt("id_message_canal"),
              rs.getInt("id_auteur"),
              rs.getInt("id_canal"),
              rs.getString("contenu"),
              rs.getString("date_publication"));
      result.add(message);
    }
    return result;
  
  }
   
  
  
}
