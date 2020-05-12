package dao;

import modele.Questionnaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO Class

public class QuestionnaireDao {

    public List<Questionnaire> getQuestionnaireByFormateur(int idFormateur) throws SQLException{
        ArrayList<Questionnaire> questionnaireArrayList = new ArrayList<>();
        Connection connection = Database.getConnection();
        final String SQL = "SELECT * FROM questionnaire WHERE id_auteur = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idFormateur);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            questionnaireArrayList.add(new Questionnaire(
                    resultSet.getString("id_questionnaire"),
                    resultSet.getString("titre"),
                    resultSet.getTimestamp("date_creation"),
                    resultSet.getString("duree"),
                    resultSet.getString("id_auteur")
                    ));
        }
        return questionnaireArrayList;
    }

}
