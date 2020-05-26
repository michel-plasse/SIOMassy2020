package dao;

import modele.QuestionnairePasse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDao {
    //add formateur variable
    public List<QuestionnairePasse> getQuestionnaires() throws SQLException{
        ArrayList<QuestionnairePasse> questionnairePasseArrayList = new ArrayList<>();
        Connection connection = Database.getConnection();
        final String SQL = "SELECT qa.id_questionnaire, qa.titre, qa.date_creation, qa.duree, qa.id_auteur, COUNT(pq.id_stagiaire) as nbr_stagiaires\n" +
                "FROM questionnaire qa\n" +
                "LEFT JOIN passage_questionnaire pq ON qa.id_questionnaire = pq.id_questionnaire\n" +
                "WHERE id_auteur = 10\n" +
                "GROUP BY qa.id_questionnaire;\n";

        PreparedStatement statement = connection.prepareStatement(SQL);
//        statement(set formateur variable here)

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            questionnairePasseArrayList.add(new QuestionnairePasse(
                    resultSet.getInt("id_questionnaire"),
                    resultSet.getString("titre"),
                    resultSet.getTimestamp("date_creation").toLocalDateTime(),
                    resultSet.getTime("duree"),
                    resultSet.getInt("id_auteur"),
                    resultSet.getInt("nbr_stagiaires")
                    ));
        }
        return questionnairePasseArrayList;
    }
}
