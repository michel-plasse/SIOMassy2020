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
    private double note;

    public ResultatQuestionnaire(String nom, String prenom, LocalDateTime dateDebut, LocalDateTime dateFin, double note) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.note = note;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
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