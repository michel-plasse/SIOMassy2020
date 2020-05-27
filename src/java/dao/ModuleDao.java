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
public class ModuleDao {

//  public static List<Module> getModule() throws SQLException {
//    Connection con = Database.getConnection();
//    List<Module> result = new ArrayList();
//    String requete = "SELECT * FROM module ORDER BY nom ASC";
//    PreparedStatement stmt = con.prepareStatement(requete);
//    //canal.setInt(1, idFormateur);
//    ResultSet rs = stmt.executeQuery();
//    while (rs.next()) {
//      Module m = new Module(
//              rs.getString("nom"));
//      result.add(m);
//    }
//    return result;
//  }

  public Map<Integer, String> getModule() throws SQLException {
    Map<Integer, String> result = new HashMap<>();
    Connection con = Database.getConnection();
    String requete = "SELECT* FROM module ORDER BY nom ASC";
    PreparedStatement stmt = con.prepareStatement(requete);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.put(rs.getInt("id_module"), rs.getString("nom"));
    }
    return result;
  }

}
