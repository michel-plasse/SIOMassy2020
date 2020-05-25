
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Message;


 
public class MessageDao {

    
      public static final String AFFICHER_MESSAGE = 
            "SELECT * FROM message_canal WHERE id_canal IN ( SELECT id_canal FROM canal WHERE id_canal=? )";

      
  public static List<Message> getMessage(int idCanal) throws SQLException {
    List<Message> result = new ArrayList<Message>();
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(AFFICHER_MESSAGE);
    stmt.setInt(1, idCanal);
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
