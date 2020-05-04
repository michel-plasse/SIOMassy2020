/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Cissé-LENOVO
 */
public class MembresCanal {
    private int idCanal;
    private int idPersonne;
    private String nom;
    
    public MembresCanal(){
        idCanal =0;
        idPersonne = 0;
        nom = null;
    }

    public MembresCanal(int idCanal, int idPersonne, String nom) {
        this.idCanal = idCanal;
        this.idPersonne = idPersonne;
        this.nom = nom;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
}
