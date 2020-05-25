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
 * @author Crist
 */
public class Canal {
    
   private int idCanal,idCreateur;
   private String nom;
   private LocalDateTime dateCreation;
   
   public Canal(){
       
       idCanal = 0;
       idCreateur = 0;
       nom = null;
       dateCreation = null;
    
}
    public Canal(int idCanal,int idCreateur,String nom,LocalDateTime dateCreation){
        
        this.idCanal =idCanal;
        this.idCreateur=idCreateur;
        this.nom=nom;
        this.dateCreation=dateCreation;
        
    }
    
    public int getIdCanal(){
        return idCanal;
    }
    
     public void setId_Canal(int idCanal){
        this.idCanal=idCanal;
        
    }
    
    public int getIdCreateur(){
        return idCreateur;
    }
    
    public void setIdCreateur(int idCreateur){
        this.idCreateur=idCreateur;
    }
    
    public String getNom(){ 
        return nom;
    }
    
    public void setNom(String nom){
        this.nom=nom;
    }
    
    public LocalDateTime getDateCreation(){
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation){
        this.dateCreation=dateCreation;
    }

    @Override
    public String toString() {
        return "Canal{" + "idCanal=" + idCanal + ", idCreateur=" + idCreateur + ", nom=" + nom + ", dateCreation=" + dateCreation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.idCanal;
        hash = 83 * hash + this.idCreateur;
        hash = 83 * hash + Objects.hashCode(this.nom);
        hash = 83 * hash + Objects.hashCode(this.dateCreation);
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
        final Canal other = (Canal) obj;
        if (this.idCanal != other.idCanal) {
            return false;
        }
        if (this.idCreateur != other.idCreateur) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.dateCreation, other.dateCreation)) {
            return false;
        }
        return true;
    }

   
   
   
    
    
}




