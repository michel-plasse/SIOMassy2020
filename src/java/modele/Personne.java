package modele;

import java.time.LocalDateTime;

public class Personne {

  private int id;
  private String prenom, nom, email, mdp, urlPhoto, jeton;
  private boolean estFormateur, estAdministration;

  private LocalDateTime dateInscription;
  private LocalDateTime dateButoirJeton;

  public Personne() {
    id = 0;
    prenom = null;
    nom = null;
    email = null;
  }

  public Personne(String prenom, String nom, String email, String mdp, String jeton) { // utilisé pour le insert
    this.prenom = prenom;
    this.nom = nom;
    this.email = email;
    this.mdp = mdp;
    this.jeton = jeton;
    this.dateButoirJeton = LocalDateTime.now().plusHours(24);

  }
  public Personne(int id, String nom, String prenom, String email, String mdp, String urlPhoto, Boolean estAdministration, Boolean estFormateur, String jeton, LocalDateTime dateinscription, LocalDateTime dateButoirJeton) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.mdp = mdp;
    this.urlPhoto = urlPhoto;
    this.estAdministration = estAdministration;
    this.estFormateur = estFormateur;
    this.jeton = jeton;
    this.dateInscription = dateinscription;
    this.dateButoirJeton = dateButoirJeton;
    // Constructeur utilisé pour créer le USER dans ConnexionServlet
  }

  public void setdateButoirJeton(LocalDateTime dateButoirJeton) {
    this.dateButoirJeton = dateButoirJeton;
  }

  public LocalDateTime getdateButoirJeton() {
    return dateButoirJeton;
  }

  public void setdateInscription(LocalDateTime dateInscription) {
    this.dateInscription = dateInscription;
  }

  public LocalDateTime getdateInscription() {
    return dateInscription;
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
 public Personne(int id, String nom, String prenom, String email, String mdp,  String jeton, LocalDateTime dateinscription, LocalDateTime dateButoirJeton) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.mdp = mdp;
    this.jeton = jeton;
    this.dateInscription = dateinscription;
    this.dateButoirJeton = dateButoirJeton;}
 
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
