/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author kenzy
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modele.Evaluation;
import modele.Personne;

public class EvaluationDao {

  public static final String INSERTION
          = "Insert into evaluation (date_effet,id_createur,id_session_formation,id_module) VALUES(?,?,?,?)";

  public static List<Personne> getEvaluationByFormateur() throws SQLException {
    Connection con = Database.getConnection();
    List<Personne> result = new ArrayList();
    String requete = "SELECT * FROM personne";
    PreparedStatement canal = con.prepareStatement(requete);
    //canal.setInt(1, idFormateur);
    ResultSet rs = canal.executeQuery();
    while (rs.next()) {
      /*Evaluation evaluation = new Evaluation(
                    rs.getInt("id_evaluation"), 
                    rs.getTimestamp("date_effet").toLocalDateTime(),                     
                    rs.getInt("id_createur"), 
                    rs.getInt("id_session_formation"),
                    rs.getInt("id_module"));
                    
            result.add(evaluation);*/
      Personne personne = new Personne(
              rs.getInt("id_personne"),
              rs.getString("prenom"),
              rs.getString("nom"),
              rs.getString("email"));

      result.add(personne);
    }
    return result;
  }

  public static void insertEval(Evaluation e, Personne p) throws SQLException {
    Connection db = Database.getConnection();
    PreparedStatement stmt = db.prepareStatement(INSERTION); //Insert into evaluation (date_effet,id_createur,id_session_formation,id_module) VALUES(?,?,?,?,?)
    stmt.setTimestamp(1, Timestamp.valueOf(e.getDateEffet()));
    stmt.setInt(2, p.getId());
    stmt.setInt(3, e.getIdSessionFormation());
    stmt.setInt(4, e.getIdModule());
    stmt.executeUpdate();
  }

}
