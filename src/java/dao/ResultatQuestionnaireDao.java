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
import modele.ResultatQuestionnaire;

/**
 *
 * @author wowmi
 */
public class ResultatQuestionnaireDao {

    public static final String GET_BY_ID_SESSION
            = "SELECT nom, prenom, date_debut, date_fin FROM passage_questionnaire,personne WHERE id_personne = id_stagiaire AND id_questionnaire = ?";
            
           
            

    /**
     * Liste des élèves ayant répondu à un questionnaire
     *
     * @param idQuestionnaire identifiant du questionnaire
     * @return liste des stagiaires ayant répondu à un questionnaire sous forme
     * d'une List<ResultatQuestionnaire>
     * @throws SQLException
     */
    public static List<ResultatQuestionnaire> getresquest(int idQuestionnaire) throws SQLException {
        List<ResultatQuestionnaire> resquest = new ArrayList<ResultatQuestionnaire>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(GET_BY_ID_SESSION);
        stmt.setInt(1, idQuestionnaire);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ResultatQuestionnaire p = new ResultatQuestionnaire(
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getTimestamp("date_debut").toLocalDateTime(),
                    rs.getTimestamp("date_fin").toLocalDateTime());
            resquest.add(p);
        }

        return resquest;
    }

}
