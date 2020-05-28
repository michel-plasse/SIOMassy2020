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
/* Requête SQL à réécrire car elle ne donne pas le bon résultat */
    
    public static final String GET_BY_ID_SESSION
            = "SELECT nom, prenom, passage_questionnaire.id_questionnaire, passage_questionnaire.id_stagiaire, COUNT(est_correcte) AS nb_bonnes_reponse_possible, SUM(est_correcte) AS nb_bonnes_reponse_données, SUM(est_correcte) / COUNT(est_correcte) * 20 AS note, date_debut, date_fin\n" +
"FROM reponse_possible, question,passage_questionnaire, reponse_donnee, personne\n" +
"WHERE question.id_question=reponse_possible.id_question\n" +
"AND passage_questionnaire.id_stagiaire = personne.id_personne\n" +
"AND passage_questionnaire.id_questionnaire = ?\n" +
"AND passage_questionnaire.id_questionnaire= reponse_donnee.id_questionnaire\n" +
"AND passage_questionnaire.id_stagiaire = reponse_donnee.id_stagiaire\n" +
"AND reponse_donnee.id_reponse_possible = reponse_possible.id_reponse_possible\n" +
"GROUP BY passage_questionnaire.id_questionnaire, passage_questionnaire.id_stagiaire";

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
                    rs.getTimestamp("date_fin").toLocalDateTime(),
                    rs.getInt("note"));

            resquest.add(p);
        }

        return resquest;
    }

}
