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
import modele.Personne;
import modele.Questionnaire;

/**
 *
 * @author wowmi
 */
public class ResultatQuestionnaireDao {

    public List<Personne> getStagiaire(int idQuestionnaire) throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        Connection connection = Database.getConnection();
        String requete = "SELECT nom, prenom, date_debut, date_fin\n"
                + "FROM personne p\n"
                + "INNER JOIN passage_questionnaire pq ON \n"
                + "p.id_personne = pq.id_stagiaire\n"
                + "WHERE pq.id_questionnaire = ?";
        PreparedStatement stmt = connection.prepareStatement(requete);
        stmt.setInt(2, idQuestionnaire);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            Personne p = new Personne(
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getTimestamp("date_debut").toLocalDateTime(),
                    resultSet.getTimestamp("date_fin").toLocalDateTime()
            );
            personnes.add(p);
        }

        return personnes;

    }
}
