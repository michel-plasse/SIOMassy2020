/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modele.MembresCanal;
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
            = "SELECT * "
            + "FROM personne "
            + "WHERE id_personne IN "
            + "("
            + "	SELECT id_personne"
            + "    FROM membre_canal"
            + "    WHERE id_canal=?"
            + ")";

    /* public static final String INSERER_MEMBRE_CANAL
            = "Insert into membre_canal (id_canal, id_personne, nom) VALUES(?,?,?)";
    
    public static final String DELETE_MEMBRE_CANAL
          = "DELETE FROM membre_canal WHERE nom=? ";
     */
    /**
     *
     * @param idCanal
     * @param mc
     * @return
     * @throws SQLException
     */
    public static List<MembresCanal> getByIdCanal(int idCanal) throws SQLException {
        List<MembresCanal> result = new ArrayList<>();
        Connection db = Database.getConnection();
        PreparedStatement stmt = db.prepareStatement(AFFICHER_MEMBRE_CANAL);
        stmt.setInt(1, idCanal);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            MembresCanal membres = new MembresCanal(
                    rs.getInt("id_canal"),
                    rs.getInt("id_personne"),
                    rs.getString("nom"));
            result.add(membres);
        }
        return result;
    }

    /*  public static void insert(MembresCanal mc) throws SQLException {
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

     */
}
