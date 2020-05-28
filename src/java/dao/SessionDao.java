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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kenzy
 */
public class SessionDao {

//  public static List<Session> getSession() throws SQLException {
//    Connection con = Database.getConnection();
//    List<Session> result = new ArrayList();
//    String requete = "SELECT * FROM session_formation ORDER BY nom ASC";
//    PreparedStatement stmt = con.prepareStatement(requete);
//    //canal.setInt(1, idFormateur);
//    ResultSet rs = stmt.executeQuery();
//    while (rs.next()) {
//      Session s = new Session(
//              rs.getString("nom"));
//      result.add(s);
//    }
//    return result;
//  }
  
  public Map<Integer, String> getSession() throws SQLException {
    Map<Integer, String> result = new HashMap<>();
    Connection con = Database.getConnection();
    String requete = "SELECT* FROM session_formation ORDER BY nom ASC";
    PreparedStatement stmt = con.prepareStatement(requete);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.put(rs.getInt("id_session_formation"), rs.getString("nom"));
    }
    return result;
  }

}
