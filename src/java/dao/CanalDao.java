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
import modele.CanalAffiche;

public class CanalDao {

    public static final String AFFICHER_CANAL
            = " SELECT c.id_canal as idCanal, c.nom as nomCanal, p.nom as nomCreateur"
            + " FROM canal c "
            + "INNER JOIN personne p on c.id_createur = p.id_personne; ";

    public static List<CanalAffiche> CanalAffiche() throws SQLException { // juste pour test  
        List<CanalAffiche> result = new ArrayList<>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_CANAL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            CanalAffiche canalAffiche = new CanalAffiche(
                    rs.getInt("idCanal"),
                    rs.getString("nomCanal"),
                    rs.getString("nomCreateur"));
            result.add(canalAffiche);
        }
        return result;
    }
}
