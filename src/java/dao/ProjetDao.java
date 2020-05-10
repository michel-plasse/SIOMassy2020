/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author maxim
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modele.Projet;


public class ProjetDao {
    
  public static final String GET_BY_ID_SESSION
          = "SELECT * "
          + "FROM projet "
          + "WHERE id_projet IN "
          + "("
          + "	SELECT id_personne"
          + "    FROM session_formation"
          + "    WHERE id_session_formation=?"
          + ")";    
    
    public static List<Projet> getByIdSessionFormation(int idSession) throws SQLException {
    List<Projet> result = new ArrayList<Projet>();
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(GET_BY_ID_SESSION);
    stmt.setInt(1, idSession);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      Projet projet = new Projet(
              rs.getInt("id_personne"),
              rs.getString("prenom"),
              rs.getDate("date_Debut"),
              rs.getDate("date_Fin"));
      result.add(projet);
    }
    return result;
  }
    
}
