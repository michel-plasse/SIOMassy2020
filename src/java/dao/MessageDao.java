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
 * Crist
 */
public class MessageDao {
    

/**
      public static final String INSERT_MESSAGE_BY_ID = 
          " INSERT INTO message_canal (id_message_canal, date_publication, contenu, id_auteur, id_canal)"
        + " values (?, ?, ?, ?, ?)";
      
 */
    
      public static final String AFFICHER_MESSAGE = 
              "SELECT * FROM message_canal WHERE id_canal IN ( SELECT id_canal FROM canal WHERE id_canal=? )";
                                                               

 
      
      
  public static List<Message> getMessage(int id_canal) throws SQLException {
    List<Message> result = new ArrayList<Message>();
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(AFFICHER_MESSAGE);
    stmt.setInt(1, id_canal);
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

  
    
    

