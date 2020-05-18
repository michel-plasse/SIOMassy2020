/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modele.Questionnaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wowmi
 */
public class QuestionnaireDao {

    public List<Questionnaire> getLesQuestionnaires() throws SQLException {
        List<Questionnaire> questionnaires = new ArrayList<>();
        Connection connection = Database.getConnection();
        String requete = "SELECT * FROM questionnaire";
        PreparedStatement stmt = connection.prepareStatement(requete);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            Questionnaire q = new Questionnaire(
                    resultSet.getInt("id_questionnaire"),
                    resultSet.getString("titre"),
                    resultSet.getTimestamp("date_creation").toLocalDateTime(),
                    resultSet.getTime("duree"),
                    resultSet.getInt("id_auteur")
            );
            questionnaires.add(q);
        }

        return questionnaires;
    }

}
