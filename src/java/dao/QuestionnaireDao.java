package dao;

import modele.Questionnaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionnaireDao {
    public ArrayList<Questionnaire> getListByIdAuteur(int idAuteur) throws SQLException {
        ArrayList<Questionnaire> questionnaireArrayList = new ArrayList<>();
        Connection db = Database.getConnection();
        final String sql= "SELECT * FROM questionnaire WHERE id_auteur = 1";

        PreparedStatement statement = db.prepareStatement(sql);
        statement.setInt(idAuteur, 1);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            questionnaireArrayList.add( new Questionnaire (
                    resultSet.getString( "id_questionnaire" ),
                    resultSet.getString( "titre" ),
                    resultSet.getString( "date_creation" ),
                    resultSet.getString( "duree" ),
                    resultSet.getString( "id_auteur" )
            ));
        }
        return questionnaireArrayList;

    }
}
