package modele;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.util.converter.LocalDateTimeStringConverter;

public class Personne {
	private int id;
	private String prenom, nom, email, mdp, urlPhoto, jeton; 
        private boolean estFormateur, estAdministration;
        private LocalDateTime dateButoirJeton;
        private LocalDateTime dateInscription;
        //private Timestamp dateButoirJeton;
        //private estActif;
        //private Timestamp dateInsertion;
        
	public Personne() {
		id = 0;
		prenom = null;
		nom = null;
		email = null;
	}

        public Personne(String prenom, String nom, String email, String mdp, String jeton) {
            this.prenom = prenom;
            this.nom = nom;
            this.email = email;
            this.mdp = mdp;
            this.jeton = jeton;
            this.dateButoirJeton= LocalDateTime.now().plusHours(24);
            //this.dateButoirJeton = new Timestamp(System.currentTimeMillis());
            //LocalDateTime butoir = LocalDateTime.now();
            //this.dateButoirJeton= butoir.plusHours(24);
            //DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //this.dateButoirJeton=LocalDateTime.parse(today.format(F));
            //this.estActif=false;
            //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //this.dateInsertion=timestamp;
        }
        
        public void setdateButoirJeton(LocalDateTime dateButoirJeton) {
                    this.dateButoirJeton = dateButoirJeton;
            }

	public LocalDateTime getdateButoirJeton() {
		return dateButoirJeton;
	}

        public Personne(String prenom, String nom, String email, String mdp) {
            this.prenom = prenom;
            this.nom = nom;
            this.email = email;
            this.mdp = mdp;
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

        public String getMdp() {
            return mdp;
        }

        public void setMdp(String mdp) {
            this.mdp = mdp;
        }

        public String getUrlPhoto() {
            return urlPhoto;
        }

        public void setUrlPhoto(String urlPhoto) {
            this.urlPhoto = urlPhoto;
        }

        public String getJeton() {
            return jeton;
        }

        public void setJeton(String jeton) {
            this.jeton = jeton;
        }

        public boolean isEstFormateur() {
            return estFormateur;
        }

        public void setEstFormateur(boolean estFormateur) {
            this.estFormateur = estFormateur;
        }

        public boolean isEstAdministration() {
            return estAdministration;
        }

        public void setEstAdministration(boolean estAdministration) {
            this.estAdministration = estAdministration;
        }

        
}
