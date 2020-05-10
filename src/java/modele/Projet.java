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
    private int id;
    private String titre;
    private Date date_Debut,date_Fin;

    public Projet(int id, String titre, Date date_Debut, Date date_Fin) {
        this.id = id;
        this.titre = titre;
        this.date_Debut = date_Debut;
        this.date_Fin = date_Fin;
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
