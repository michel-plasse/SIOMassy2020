package modele;

//make a stored function in the code to add parameters to the questionnaire table



import java.sql.Timestamp;


//domain classgit

public class Questionnaire {
    private String id_questionnaire;
    private String titre;
    private Timestamp date_creation;
    private String duree;
    private String id_auteur;

    public Questionnaire(String id_questionnaire, String titre, Timestamp date_creation, String duree, String id_auteur) {
        this.id_questionnaire = id_questionnaire;
        this.titre = titre;
        this.date_creation = date_creation;
        this.duree = duree;
        this.id_auteur = id_auteur;
    }





    public String getId_questionnaire() {
        return id_questionnaire;
    }

    public void setId_questionnaire(String id_questionnaire) {
        this.id_questionnaire = id_questionnaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(String id_auteur) {
        this.id_auteur = id_auteur;
    }
}

















