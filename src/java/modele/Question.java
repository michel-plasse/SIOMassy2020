package modele;

public class Question {
    private int idQuestion;
    private String texte;
    private int id;

    @Override
    public String toString() {
        return "Question{" +
                "idQuestion=" + idQuestion +
                ", texte='" + texte + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Question(int idQuestion, String texte, int id) {
        this.idQuestion = idQuestion;
        this.texte = texte;
        this.id = id;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
