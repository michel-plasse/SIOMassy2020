/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modele.Projet;



/**
 *
 * @author maxim
 */

public class ProjetDao {
    
    public static final String CREATE_PROJET = 
        "INSERT INTO projet (id_projet, titre, date_Debut, date_Fin)"
        +"VALUES(?, ?, ?, ?)";
    
//    public final String UPDATE_PROJET = 
//        "UPDATE projet SET titre = " + Projet.getTitre() + ","
//        +" date_Debut = '" + Projet.getDate_Debut() + "',"
//        +" date_Fin = '" + Projet.getDate_Fin() + "'"
//        +" WHERE dev_id = " + Projet.getId();
//    
//    public final String DELETE_PROJET = 
//        "DELETE FROM projet WHERE id_projet = " + Projet.getId();

}
		
