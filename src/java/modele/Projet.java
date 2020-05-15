/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
(id_projet, titre, date_debut, date_fin, id_session_formation, id_createur)
 */
package modele;

import java.time.LocalDateTime;

/**
 *
 * @author ricar
 */
public class Projet {
    private int id;
    private String titre;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private int id_session_formation;
    private int id_createur;

    public Projet(int id, String titre, LocalDateTime date_debut, LocalDateTime date_fin, int id_session_formation, int id_createur) {
        this.id = id;
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_session_formation = id_session_formation;
        this.id_createur = id_createur;
    }

    public Projet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public int getId_session_formation() {
        return id_session_formation;
    }

    public void setId_session_formation(int id_session_formation) {
        this.id_session_formation = id_session_formation;
    }

    public int getId_createur() {
        return id_createur;
    }

    public void setId_createur(int id_createur) {
        this.id_createur = id_createur;
    }
    
    
}
