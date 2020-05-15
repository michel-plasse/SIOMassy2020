/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modele.Membre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Personne;

/**
 *
 * @author Cissé-LENOVO
 */
public class MembresCanalDao {

   
    
    public static final String AFFICHER_MEMBRE_CANAL
            = "SELECT  mc.id_canal, mc.id_personne FROM membre_canal mc WHERE mc.id_canal = ?";
            //+ "JOIN personne p ON mc.id_personne = p.id_personne, p.nom, "
      
    
        public static final String AFFICHER_TOUS_LES_MEMBRES
            = "SELECT  * FROM membre_canal ";

    public static final String INSERER_MEMBRE_CANAL
            = "Insert into membre_canal(id_canal, id_personne) VALUES(?,?)";
    
    public static final String DELETE_MEMBRE_CANAL
          = "DELETE FROM membre_canal WHERE id_canal=? ";
     
    /**
     *
     * @param idCanal
     * @param mc
     * @return
     * @throws SQLException
     */
    public static List<Membre> getMembres(int idCanal) throws SQLException {
        List<Membre> result = new ArrayList<>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_MEMBRE_CANAL);
        stmt.setInt(1, idCanal);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Membre membres = new Membre(
                    rs.getInt("id_canal"),
                    rs.getInt("id_personne"));
            result.add(membres);
        }
        return result;
    }
    
        public static List<Membre> getAll() throws SQLException {
        List<Membre> result = new ArrayList<>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_TOUS_LES_MEMBRES);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Membre membres = new Membre(
                    rs.getInt("id_canal"),
                    rs.getInt("id_personne"));
            result.add(membres);
        }
        return result;
    }
//"Insert into membre_canal (id_canal, id_personne) VALUES(?,?)"
     public static void ajouterMembre(Membre mc) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(INSERER_MEMBRE_CANAL); 
        stmt.setInt(1, mc.getIdCanal());
        stmt.setInt(2, mc.getIdPersonne());
        stmt.executeUpdate();

    }
//"DELETE FROM membre_canal WHERE idCanal=? "; 
    public static void supprimerMembre(int idCanal) throws SQLException {
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(DELETE_MEMBRE_CANAL); 
        stmt.setInt(1, idCanal);
        stmt.executeUpdate();
    }

  

     
}
