package dao;

import modele.Questionnaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDao {

    public List<Questionnaire> getQuestionnaires() throws SQLException{
        ArrayList<Questionnaire> questionnaireArrayList = new ArrayList<>();
        Connection connection = Database.getConnection();
        final String SQL = "SELECT * FROM questionnaire";

        PreparedStatement statement = connection.prepareStatement(SQL);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            questionnaireArrayList.add(new Questionnaire(
                    resultSet.getInt("id_questionnaire"),
                    resultSet.getString("titre"),
                    resultSet.getTimestamp("date_creation").toLocalDateTime(),
                    resultSet.getTime("duree"),
                    resultSet.getInt("id_auteur")
                    ));
        }
        return questionnaireArrayList;


    }






    
}
