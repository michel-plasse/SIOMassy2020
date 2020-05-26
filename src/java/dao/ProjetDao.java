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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.Personne;
import modele.Projet;



/**
 *
 * @author maxim
 */

public class ProjetDao {
    
      public static final String GET_BY_ID_SESSION
          = "SELECT * "
          + "FROM projet "
          + "WHERE id_session_formation=?"
          + ")";

    public static List<Projet> getByIdSessionFormation(int idSession) throws SQLException {
        List<Projet> result = new ArrayList<Projet>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(GET_BY_ID_SESSION);
        stmt.setInt(1, idSession);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Projet projet;
            projet = new Projet(
                    rs.getInt("id_projet"),
                    rs.getInt("id_session_formation"),
                    rs.getInt("id_createur"),
                    rs.getString("titre"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    rs.getString("description"));
      result.add(projet);
    }
    return result;
  
          
    }
    
    public void insert(Projet projet) throws SQLException {

        try {
            Connection db = Database.getConnection();
            
            String sql = "INSERT INTO projet(id_projet, titre, date_debut, date_fin, id_session_formation, id_createur, description)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = db.prepareStatement(sql);
            stmt.setInt(1, projet.getId());
            stmt.setString(2, projet.getTitre());
            stmt.setDate(3, new java.sql.Date(projet.getDate_Debut().getTime()));
            stmt.setDate(4, new java.sql.Date(projet.getDate_Fin().getTime()));
            stmt.setInt(5, projet.getId_session_formation());
            stmt.setInt(6, projet.getId_createur());
            stmt.setString(7, projet.getDescription());
            
            System.out.println(projet);
            stmt.executeUpdate();
            // Recuperer le id
            sql = "SELECT MAX(id_projet) AS id from projet";
            Statement lecture = db.createStatement();
            ResultSet rs = lecture.executeQuery(sql);
            rs.next();

            projet.setId(rs.getInt("id"));
            System.out.println(projet);
            
            System.out.println("Projet ajouté.");
            
            } catch (SQLException exc) {
            exc.printStackTrace();
            System.out.println("poof.");
            throw exc;
            }
    }
    
    public void delete(int id) throws SQLException {
        Connection db = Database.getConnection();
        Statement stmt = db.createStatement();
        stmt.executeUpdate("DELETE FROM projet WHERE id_projet =" + id + ";");
    }
    
    public void update(int id_projet, Projet nouveau) throws SQLException {
        Connection db = Database.getConnection();
        Statement stmt = db.createStatement();
        
        int sessionFormation = nouveau.getId_session_formation();
        String titre = nouveau.getTitre();
        Date date_Debut = nouveau.getDate_Debut();
        Date date_Fin = nouveau.getDate_Fin();
        String description = nouveau.getDescription();

        stmt.executeUpdate("UPDATE projet SET(" + titre + ",'" + date_Debut + ",'" + date_Fin + ",'" + sessionFormation + ",'" + description + "WHERE id_projet =" + id_projet + ";");
        
    }
}
		
