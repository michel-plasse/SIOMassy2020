package modele;

import java.time.LocalDateTime;

public class QuestionnairePasse {
    private int id;
    private int idStagiaire;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @Override
    public String toString() {
        return "QuestionnairePasse{" +
                "id=" + id +
                ", idStagiaire=" + idStagiaire +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }

    public QuestionnairePasse(int id, int idStagiaire, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.id = id;
        this.idStagiaire = idStagiaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStagiaire() {
        return idStagiaire;
    }

    public void setIdStagiaire(int idStagiaire) {
        this.idStagiaire = idStagiaire;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
}
