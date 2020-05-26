package modele;

import java.sql.Time;
import java.time.LocalDateTime;

public class QuestionnairePasse {
    private int id;
    private String titre;
    private LocalDateTime dateCreation;
    private Time duree;
    private int idAuteur;
    private int nbrStagiaires;

    public QuestionnairePasse(int id, String titre, LocalDateTime dateCreation, Time duree, int idAuteur, int nbrStagiaires) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.duree = duree;
        this.idAuteur = idAuteur;
        this.nbrStagiaires = nbrStagiaires;
    }

    @Override
    public String toString() {
        return "QuestionnairePasse{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", dateCreation=" + dateCreation +
                ", duree=" + duree +
                ", idAuteur=" + idAuteur +
                ", nbrStagiaires=" + nbrStagiaires +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public int getNbrStagiaires() {
        return nbrStagiaires;
    }

    public void setNbrStagiaires(int nbrStagiaires) {
        this.nbrStagiaires = nbrStagiaires;
    }
}

















