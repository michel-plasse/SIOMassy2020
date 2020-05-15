/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Cissé-LENOVO
 */
public class Membre {
    private int idCanal;
    private int idPersonne;
    
    public Membre(){
        idCanal =0;
        idPersonne = 0;
    }

    public Membre(int idCanal, int idPersonne) {
        this.idCanal = idCanal;
        this.idPersonne = idPersonne;
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

  

  
    
    private static final Logger LOG = Logger.getLogger(Membre.class.getName());

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

   

}