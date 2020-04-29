/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author akber
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
    
    
    
    
}
