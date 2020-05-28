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
 * @author akber
 */
public class MessageAffiche {
    private int id;
    private String contenu, prenom, nom;
    private LocalDateTime datePublication;

    public MessageAffiche() {
        id = 0;
        contenu = null;
        datePublication = null;
        prenom = null;
        nom = null;
    }

    public MessageAffiche(int id, String contenu, LocalDateTime datePublication, String prenom, String nom) {
        this.id = id;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.prenom = prenom;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "MessageAffiche{" + "id=" + id + ", contenu=" + contenu + ", prenom=" + prenom + ", nom=" + nom + ", datePublication=" + datePublication + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.contenu);
        hash = 11 * hash + Objects.hashCode(this.prenom);
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.datePublication);
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
        final MessageAffiche other = (MessageAffiche) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.datePublication, other.datePublication)) {
            return false;
        }
        return true;
    }

    
    
}
