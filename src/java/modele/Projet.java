/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;

/**
 *
 * @author maxim
 */
public class Projet {
    private int id,id_session_formation,id_createur;
    private String titre;
    private Date date_Debut,date_Fin;

    public Projet(int id, String titre, Date date_Debut, Date date_Fin) {
        this.id = id;
        this.titre = titre;
        this.date_Debut = date_Debut;
        this.date_Fin = date_Fin;
    }

    public Projet(int id, int id_session_formation, int id_createur, String titre, Date date_Debut, Date date_Fin) {
        this.id = id;
        this.id_session_formation = id_session_formation;
        this.id_createur = id_createur;
        this.titre = titre;
        this.date_Debut = date_Debut;
        this.date_Fin = date_Fin;
    }

    public int getId_session_formation() {
        return id_session_formation;
    }

    public int getId_createur() {
        return id_createur;
    }

    public void setId_session_formation(int id_session_formation) {
        this.id_session_formation = id_session_formation;
    }

    public void setId_createur(int id_createur) {
        this.id_createur = id_createur;
    }
    
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDate_Debut() {
        return date_Debut;
    }

    public Date getDate_Fin() {
        return date_Fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate_Debut(Date date_Debut) {
        this.date_Debut = date_Debut;
    }

    public void setDate_Fin(Date date_Fin) {
        this.date_Fin = date_Fin;
    }
    
    
}
