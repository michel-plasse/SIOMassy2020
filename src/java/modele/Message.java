/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Objects;

/**
 *
 * @crist
 */
public class Message {
    int id_messageCanal, id_auteur, id_canal;
    String contenu, date_publication;
    
    
    public Message(){
        id_messageCanal = 0;
        id_auteur = 0;
        id_canal = 0;
        contenu = null;
        date_publication = null;
    }

    public Message(int id_messageCanal, int id_auteur, int id_canal, String contenu, String date_publication) {
        this.id_messageCanal = id_messageCanal;
        this.id_auteur = id_auteur;
        this.id_canal = id_canal;
        this.contenu = contenu;
        this.date_publication = date_publication;
    }

    public int getId_messageCanal() {
        return id_messageCanal;
    }

    public int getId_auteur() {
        return id_auteur;
    }

    public int getId_canal() {
        return id_canal;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public void setId_messageCanal(int id_messageCanal) {
        this.id_messageCanal = id_messageCanal;
    }

    public void setId_auteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }

    public void setId_canal(int id_canal) {
        this.id_canal = id_canal;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    @Override
    public String toString() {
        return "Message{" + "id_messageCanal=" + id_messageCanal + ", id_auteur=" + id_auteur + ", id_canal=" + id_canal + ", contenu=" + contenu + ", date_publication=" + date_publication + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id_messageCanal;
        hash = 41 * hash + this.id_auteur;
        hash = 41 * hash + this.id_canal;
        hash = 41 * hash + Objects.hashCode(this.contenu);
        hash = 41 * hash + Objects.hashCode(this.date_publication);
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
        final Message other = (Message) obj;
        if (this.id_messageCanal != other.id_messageCanal) {
            return false;
        }
        if (this.id_auteur != other.id_auteur) {
            return false;
        }
        if (this.id_canal != other.id_canal) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.date_publication, other.date_publication)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
