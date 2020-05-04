/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import modele.MembresCanal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Cissé-LENOVO
 */
public class MembresCanalDao {
    
    
    public static final String INSERER_MEMBRE_CANAL
            = "Insert into membre_canal (id_canal, id_personne, nom) VALUES(?,?,?)";
    
    public static final String DELETE_MEMBRE_CANAL
          = "DELETE FROM membre_canal WHERE nom=? ";

    /**
     *
     * @param mc
     * @throws SQLException
     */
    public static void insert(MembresCanal mc) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(INSERER_MEMBRE_CANAL); //"Insert into personne (id_canal, id_personne, nom) VALUES(?,?,?)"
        stmt.setInt(1, mc.getIdCanal());
        stmt.setInt(2, mc.getIdPersonne());
        stmt.setString(3, mc.getNom());
        stmt.executeUpdate();

    }

    public static void deleteMembreCanal(String nom) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(DELETE_MEMBRE_CANAL); //"DELETE FROM personne WHERE jeton=? "; 
        stmt.setString(1, nom);
        stmt.executeUpdate();
    }

   
    
}
