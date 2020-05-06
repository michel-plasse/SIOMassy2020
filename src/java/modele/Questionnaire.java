package modele;

//make a stored function in the code to add parameters to the questionnaire table

public class Questionnaire {
    String id_questionnaire;
    String titre;
    String date_creation;
    String duree;
    String id_auteur;


    //constructor
    public Questionnaire(String id_questionnaire, String titre, String date_creation, String duree, String id_auteur) {
        this.id_questionnaire = id_questionnaire;
        this.titre = titre;
        this.date_creation = date_creation;
        this.duree = duree;
        this.id_auteur = id_auteur;
    }


    //getters
    public String getId_questionnaire() {
        return id_questionnaire;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public String getDuree() {
        return duree;
    }

    public String getId_auteur() {
        return id_auteur;
    }



    //setters
    public void setId_questionnaire(String id_questionnaire) {
        this.id_questionnaire = id_questionnaire;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setId_auteur(String id_auteur) {
        this.id_auteur = id_auteur;
    }
}
