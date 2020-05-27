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

    public Personne(String prenom, String nom, String mail, String mdp, String jeton) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void setMdp(String mdp1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUrlPhoto(String photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean emailIsValid(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean mdpIsValid(String mdp1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
