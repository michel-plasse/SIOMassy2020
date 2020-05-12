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
import java.util.Date;
import modele.Projet;



/**
 *
 * @author maxim
 */

public class ProjetDao {
    
    public void insert(Projet projet) throws SQLException {

        try {
            Connection db = Database.getConnection();
            
            String sql = "INSERT INTO projet(id, id_session_formation, id_createur, titre, date_Debut, date_Fin)"
                    + " VALUES (?,?,?,?,?, ?)";
            PreparedStatement stmt = db.prepareStatement(sql);
            stmt.setInt(1, projet.getId());
            stmt.setInt(2, projet.getId_session_formation());
            stmt.setInt(3, projet.getId_createur());
            stmt.setString(4, projet.getTitre());
            stmt.setDate(5, new java.sql.Date(projet.getDate_Debut().getTime()));
            stmt.setDate(6, new java.sql.Date(projet.getDate_Fin().getTime()));
            System.out.println(projet);
            stmt.executeUpdate();
            // Recuperer le id
            sql = "SELECT MAX(id_projet) AS id from projet";
            Statement lecture = db.createStatement();
            ResultSet rs = lecture.executeQuery(sql);
            rs.next();

            projet.setId(rs.getInt("id"));
            System.out.println(projet);
            } catch (SQLException exc) {
            exc.printStackTrace();
            System.out.println("Rollback.");
            throw exc;
            }
    }
    
    public boolean delete(int id) throws SQLException {
        Connection db = Database.getConnection();
        Statement stmt = db.createStatement();
        stmt.executeUpdate("DELETE FROM projet WHERE id_projet =" + id + ";");
        return false;
    }
    
    public boolean update(int idAncien, Projet nouveau) throws SQLException {
        Connection db = Database.getConnection();
        Statement stmt = db.createStatement();
        
        int sessionFormation = nouveau.getId_session_formation();
        String sujet = nouveau.getTitre();
        Date date_Debut = nouveau.getDate_Debut();
        Date date_Fin = nouveau.getDate_Fin();

        stmt.executeUpdate("UPDATE projet SET(" + sessionFormation + "," + sujet + ",'" + date_Debut + ",'" + date_Fin + ",'");
        return false;
    }    
}
		
