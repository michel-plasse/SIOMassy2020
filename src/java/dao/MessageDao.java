package dao;

import static dao.PersonneDao.GET_BY_ID_SESSION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Message;
import modele.MessageAffiche;
import modele.Personne;

public class MessageDao {

    public static final String AFFICHER_MESSAGE
            = "SELECT *\n"
            + "FROM message_canal ms\n"
            + "INNER JOIN canal c\n"
            + "    on ms.id_canal = c.id_canal\n"
            + "INNER JOIN personne p\n"
            + "    on c.id_canal = p.id_personne\n"
            + "    WHERE c.id_canal IN ( SELECT c.id_canal FROM canal WHERE c.id_canal=?)";
    //  "SELECT * FROM message_canal WHERE id_canal IN ( SELECT id_canal FROM canal WHERE id_canal=? )";

    public static List<Message> getMessages(int idCanal) throws SQLException {
        List<Message> result = new ArrayList<Message>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_MESSAGE);
        stmt.setInt(1, idCanal);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Message message = new Message(
                    rs.getInt("id_message_canal"),
                    rs.getInt("id_auteur"),
                    rs.getInt("id_canal"),
                    rs.getString("contenu"),
                    rs.getString("date_publication"));
            result.add(message);
        }
        return result;

    }

    public static List<MessageAffiche> getMessagesAffiche(int idCanal) throws SQLException {
        List<MessageAffiche> result = new ArrayList<MessageAffiche>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_MESSAGE);
        stmt.setInt(1, idCanal);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            MessageAffiche messageAffiche = new MessageAffiche(
                    rs.getString("contenu"),
                    rs.getString("date_publication"),
                    rs.getString("prenom"),
                    rs.getString("nom"));
            result.add(messageAffiche);
        }
        return result;

    }
}
