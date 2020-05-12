/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Ronan
 */
public class Sondage {

    private int id_sondage;
    private String question;
    private int note_maximale;
    private int id_createur;
    private String date_butoir;

    public Sondage() {

    }

    public Sondage(int id_sondage, String question, int note_maximale, int id_createur,  String date_butoir) {
        
        this.id_sondage = id_sondage;
        this.question = question;
        this.note_maximale = note_maximale;
        this.id_createur = id_createur;
        this.date_butoir = date_butoir;
    }
    public Sondage( String question, int note_maximale, int id_createur,  String date_butoir) {
        
        this.question = question;
        this.note_maximale = note_maximale;
        this.id_createur = id_createur;
        this.date_butoir = date_butoir;
    }

    public int getId_sondage() {
        return id_sondage;
    }

    public void setId_sondage(int id_sondage) {
        this.id_sondage = id_sondage;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNote_maximale() {
        return note_maximale;
    }

    public void setBeaute(int note_maximale) {
        this.note_maximale = note_maximale;
    }

    public int getId_createur() {
        return id_createur;
    }

    public void setId_createur(int id_createur) {
        this.id_createur = id_createur;
    }

    public String getDate_butoir() {
        return date_butoir;
    }

    public void setDate_butoir(String date_butoir) {
        this.date_butoir = date_butoir;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id_sondage;
        hash = 37 * hash + this.note_maximale;
        hash = 37 * hash + this.id_createur;
        hash = 37 * hash + Objects.hashCode(this.question);
        hash = 37 * hash + Objects.hashCode(this.date_butoir);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sondage other = (Sondage) obj;
        if (this.id_sondage != other.id_sondage) {
            return false;
        }
        if (this.note_maximale != other.note_maximale) {
            return false;
        }
        if (this.id_createur != other.id_createur) {
            return false;
        }
        
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.date_butoir, other.date_butoir)) {
            return false;
        }
        return true;
    }

}
