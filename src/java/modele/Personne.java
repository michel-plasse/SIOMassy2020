package modele;

public class Personne {
	private int id;
	private String prenom, nom, email;
  private boolean estFormateur, estAdministration;

	public Personne() {
		id = 0;
		prenom = null;
		nom = null;
		email = null;
	}

	public Personne(int id, String prenom, String nom, String email) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
	}

  public Personne(int id, String prenom, String nom, 
          String email, boolean estFormateur, boolean estAdministration) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
    this.estFormateur = estFormateur;
    this.estAdministration = estAdministration;
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

    public void setEstFormateur(boolean estFormateur) {
        this.estFormateur = estFormateur;
    }

    public void setEstAdministration(boolean estAdministration) {
        this.estAdministration = estAdministration;
    }

    public boolean isEstFormateur() {
        return estFormateur;
    }

    public boolean isEstAdministration() {
        return estAdministration;
    }

}
