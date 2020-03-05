package modele;

public class Personne {
	private int id;
	private String prenom, nom, email;

	public Personne() {
		id = 0;
		prenom = null;
		nom = null;
		email = null;
	}

	public Personne(int id, String prenom, String nom, String email) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
