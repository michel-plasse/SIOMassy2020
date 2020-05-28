package modele;

import java.time.LocalDateTime;

public class Evaluation {

  private int idEvaluation;
  private LocalDateTime dateEffet;
  private int idCreateur;
  private int idSessionFormation;
  private int idModule;
  private String nomModule;
  private String nomSession;

  public Evaluation() {
  }

  public Evaluation(int idEvaluation, LocalDateTime dateEffet, int idCreateur, int idSessionFormation, int idModule) {
    this.idEvaluation = idEvaluation;
    this.dateEffet = dateEffet;
    this.idCreateur = idCreateur;
    this.idSessionFormation = idSessionFormation;
    this.idModule = idModule;
  }

  public Evaluation(int idModule, int idSesionFormation, LocalDateTime dateEffet) {
    this.idModule = idModule;
    this.idSessionFormation = idSesionFormation;
    this.dateEffet = dateEffet;

  }

  public Evaluation(LocalDateTime dateEffet, String nomModule, String nomSession) {
    this.dateEffet = dateEffet;
    this.nomModule = nomModule;
    this.nomSession = nomSession;
  }

  public int getIdEvaluation() {
    return idEvaluation;
  }

  public void setIdEvaluation(int idEvaluation) {
    this.idEvaluation = idEvaluation;
  }

  public LocalDateTime getDateEffet() {
    return dateEffet;
  }

  public void setDateEffet(LocalDateTime dateEffet) {
    this.dateEffet = dateEffet;
  }

  public int getIdCreateur() {
    return idCreateur;
  }

  public void setIdCreateur(int idCreateur) {
    this.idCreateur = idCreateur;
  }

  public int getIdSessionFormation() {
    return idSessionFormation;
  }

  public void setIdSessionFormation(int idSessionFormation) {
    this.idSessionFormation = idSessionFormation;
  }

  public int getIdModule() {
    return idModule;
  }

  public void setIdModule(int idModule) {
    this.idModule = idModule;
  }

  public String getNomModule() {
    return nomModule;
  }

  public void setNomModule(String nomModule) {
    this.nomModule = nomModule;
  }

  public String getNomSession() {
    return nomSession;
  }

  public void setNomSession(String nomSession) {
    this.nomSession = nomSession;
  }
}
