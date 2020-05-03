/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDateTime;


public class Canal {
    
    private int id, idCreateur;
    private String nom;
    private LocalDateTime dateCreation;
    
    public Canal(){
        id = 0;
        idCreateur = 0;
        nom = null;
        dateCreation = null;
    }

    @Override
    public String toString() {
        return "Canal{" + "id=" + id + ", idCreateur=" + idCreateur + ", nom=" + nom + ", dateCreation=" + dateCreation + '}';
    }
    

    public Canal(int id, int idCreateur, String nom, LocalDateTime dateCreation) {
        this.id = id;
        this.idCreateur = idCreateur;
        this.nom = nom;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCreateur(int idCreateur) {
        this.idCreateur = idCreateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    
    
    
}
