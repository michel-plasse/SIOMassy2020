/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Crist
 */
public class CanalAffiche {
    
    String nomCanal, nomCreateur;
    int idCanal;

    public CanalAffiche() {
        
        idCanal = 0;
        nomCanal = null;
        nomCreateur = null;
        
    }

    public CanalAffiche(String nomCanal, String nomCreateur, int idCanal) {
        this.nomCanal = nomCanal;
        this.nomCreateur = nomCreateur;
        this.idCanal = idCanal;
    }

    public String getNomCanal() {
        return nomCanal;
    }

    public void setNomCanal(String nomCanal) {
        this.nomCanal = nomCanal;
    }

    public String getNomCreateur() {
        return nomCreateur;
    }

    public void setNomCreateur(String nomCreateur) {
        this.nomCreateur = nomCreateur;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    @Override
    public String toString() {
        return "CanalAffiche{" + "nomCanal=" + nomCanal + ", nomCreateur=" + nomCreateur + ", idCanal=" + idCanal + '}';
    }
    
    
    
    
    
}
