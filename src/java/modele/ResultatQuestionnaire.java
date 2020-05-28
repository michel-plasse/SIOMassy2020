/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Time;
import java.time.LocalDateTime;

/**
 *
 * @author wowmi
 */
public class ResultatQuestionnaire {

    private String nom;
    private String prenom;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int note;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note= note;
    }

    public ResultatQuestionnaire(String nom, String prenom, LocalDateTime dateDebut, LocalDateTime dateFin, int note) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.note = note;
      
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }


}
