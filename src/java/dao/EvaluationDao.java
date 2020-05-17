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
import modele.Evaluation;

/**
 *
 * @author azery
 */
public class EvaluationDao {

  public static List<Evaluation> getEvaluationByFormateur(int idFormateur) throws SQLException {
    Connection con = Database.getConnection();
    List<Evaluation> result = new ArrayList();
    String requete = "SELECT m.nom as nom_module, date_effet, s.nom as nom_session\n"
            + "FROM evaluation e INNER JOIN module m\n"
            + "ON e.id_module = m.id_module\n"
            + "INNER JOIN session_formation s \n"
            + "on s.id_session_formation =e.id_session_formation\n"
            + "WHERE id_createur = ( SELECT id_personne \n"
            + "                      FROM personne \n"
            + "                      WHERE id_personne = ? and est_formateur=1)";
    PreparedStatement eval = con.prepareStatement(requete);
    eval.setInt(1, idFormateur);
    ResultSet rs = eval.executeQuery();
    while (rs.next()) {
      Evaluation evaluation = new Evaluation(
              rs.getTimestamp("date_effet").toLocalDateTime(),
              rs.getString("nom_module"),
              rs.getString("nom_session"));
      result.add(evaluation);
    }
    return result;
  }

}
