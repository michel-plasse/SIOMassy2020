package modele;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//todo
//convert Time from seconds to minutes:seconds

//NO UNDERSCORES

public class Questionnaire {
    private int id;
    private String titre;
    private LocalDateTime dateCreation;
    private Time duree;
    private int idAuteur;

    public Questionnaire(int id, String titre, LocalDateTime dateCreation, Time duree, int idAuteur) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.duree = duree;
        this.idAuteur = idAuteur;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", dateCreation=" + dateCreation +
                ", duree=" + duree +
                ", idAuteur=" + idAuteur +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public int getId_questionnaire() {
        return id;
    }

    public void setId_questionnaire(int id_questionnaire) {
        this.id = id_questionnaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}

















