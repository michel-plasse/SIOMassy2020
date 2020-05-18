/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author wowmi
 */
public class Questionnaire {

    private int id;
    private String titre;
    private LocalDateTime dateCreation;
    private Time duree;
    private int idAuteur;

    public Questionnaire(int id, String titre, LocalDateTime dateCreation, Time duree, int idAuteur) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.duree = duree;
        this.idAuteur = idAuteur;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }
}
