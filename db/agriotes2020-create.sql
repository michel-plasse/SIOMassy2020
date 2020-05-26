-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema agriotes2020
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `agriotes2020` ;

-- -----------------------------------------------------
-- Schema agriotes2020
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agriotes2020` DEFAULT CHARACTER SET utf8 ;
USE `agriotes2020` ;

-- -----------------------------------------------------
-- Table `personne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personne` (
  `id_personne` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `mdp` VARCHAR(255) NOT NULL,
  `est_formateur` TINYINT NOT NULL DEFAULT 0,
  `est_administration` TINYINT NOT NULL DEFAULT 0,
  `url_photo` VARCHAR(45) NULL,
  `jeton` VARCHAR(255) NULL,
  `date_inscription` DATETIME NULL,
  `date_butoir_jeton` DATETIME NULL,
  PRIMARY KEY (`id_personne`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `session_formation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `session_formation` (
  `id_session_formation` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `date_debut` DATE NOT NULL,
  `date_fin` DATE NOT NULL,
  PRIMARY KEY (`id_session_formation`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `membre_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `membre_session` (
  `id_personne` INT NOT NULL,
  `id_session_formation` INT NOT NULL,
  `date_entree` DATE NULL,
  PRIMARY KEY (`id_personne`, `id_session_formation`),
  INDEX `fk_personne_session_formation_session_formation1_idx` (`id_session_formation` ASC),
  INDEX `fk_personne_session_formation_personne_idx` (`id_personne` ASC),
  CONSTRAINT `fk_personne_session_formation_personne`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_session_formation_session_formation1`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projet` (
  `id_projet` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `date_debut` DATETIME NOT NULL,
  `date_fin` DATETIME NOT NULL,
  `id_session_formation` INT NOT NULL,
  `id_createur` INT NOT NULL,
  PRIMARY KEY (`id_projet`),
  INDEX `fk_projet_session_formation1_idx` (`id_session_formation` ASC),
  INDEX `fk_projet_personne1_idx` (`id_createur` ASC),
  CONSTRAINT `fk_projet_session_formation1`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projet_personne1`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `canal` (
  `id_canal` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL,
  `id_createur` INT NOT NULL,
  PRIMARY KEY (`id_canal`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC),
  INDEX `fk_canal_personne1_idx` (`id_createur` ASC),
  CONSTRAINT `fk_canal_personne1`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message_canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `message_canal` (
  `id_message_canal` INT NOT NULL AUTO_INCREMENT,
  `date_publication` DATETIME NOT NULL,
  `contenu` TEXT NOT NULL,
  `id_auteur` INT NOT NULL,
  `id_canal` INT NOT NULL,
  PRIMARY KEY (`id_message_canal`),
  INDEX `fk_message_canal_personne1_idx` (`id_auteur` ASC),
  INDEX `fk_message_canal_canal1_idx` (`id_canal` ASC),
  CONSTRAINT `fk_message_canal_personne1`
    FOREIGN KEY (`id_auteur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_canal_canal1`
    FOREIGN KEY (`id_canal`)
    REFERENCES `canal` (`id_canal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire` (
  `id_questionnaire` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL,
  `duree` TIME NOT NULL,
  `id_auteur` INT NOT NULL,
  PRIMARY KEY (`id_questionnaire`),
  INDEX `fk_questionnaire_personne1_idx` (`id_auteur` ASC),
  CONSTRAINT `fk_questionnaire_personne1`
    FOREIGN KEY (`id_auteur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `question` (
  `id_question` INT NOT NULL AUTO_INCREMENT,
  `texte` VARCHAR(65) NOT NULL,
  `id_questionnaire` INT NOT NULL,
  PRIMARY KEY (`id_question`),
  INDEX `fk_question_questionnaire1_idx` (`id_questionnaire` ASC),
  CONSTRAINT `fk_question_questionnaire1`
    FOREIGN KEY (`id_questionnaire`)
    REFERENCES `questionnaire` (`id_questionnaire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reponse_possible`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reponse_possible` (
  `id_reponse_possible` INT NOT NULL AUTO_INCREMENT,
  `texte` VARCHAR(45) NOT NULL,
  `est_correcte` TINYINT(1) NOT NULL,
  `id_question` INT NOT NULL,
  PRIMARY KEY (`id_reponse_possible`),
  INDEX `fk_reponse_possible_question1_idx` (`id_question` ASC),
  CONSTRAINT `fk_reponse_possible_question1`
    FOREIGN KEY (`id_question`)
    REFERENCES `question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `passage_questionnaire`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passage_questionnaire` (
  `id_questionnaire` INT NOT NULL,
  `id_stagiaire` INT NOT NULL,
  `date_debut` DATETIME NOT NULL,
  `date_fin` DATETIME NULL,
  INDEX `fk_passage_questionnaire_personne1_idx` (`id_stagiaire` ASC),
  INDEX `fk_passage_questionnaire_questionnaire1_idx` (`id_questionnaire` ASC),
  PRIMARY KEY (`id_questionnaire`, `id_stagiaire`),
  CONSTRAINT `fk_passage_questionnaire_personne1`
    FOREIGN KEY (`id_stagiaire`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passage_questionnaire_questionnaire1`
    FOREIGN KEY (`id_questionnaire`)
    REFERENCES `questionnaire` (`id_questionnaire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reponse_donnee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reponse_donnee` (
  `id_questionnaire` INT NOT NULL,
  `id_stagiaire` INT NOT NULL,
  `id_reponse_possible` INT NOT NULL,
  PRIMARY KEY (`id_questionnaire`, `id_stagiaire`, `id_reponse_possible`),
  INDEX `fk_passage_questionnaire_question_passage_questionnaire1_idx` (`id_questionnaire` ASC, `id_stagiaire` ASC),
  INDEX `fk_reponse_donnee_reponse_possible1_idx` (`id_reponse_possible` ASC),
  CONSTRAINT `fk_passage_questionnaire_question_passage_questionnaire1`
    FOREIGN KEY (`id_questionnaire` , `id_stagiaire`)
    REFERENCES `passage_questionnaire` (`id_questionnaire` , `id_stagiaire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reponse_donnee_reponse_possible1`
    FOREIGN KEY (`id_reponse_possible`)
    REFERENCES `reponse_possible` (`id_reponse_possible`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module` (
  `id_module` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_module`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evaluation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id_evaluation` INT NOT NULL AUTO_INCREMENT,
  `date_effet` DATETIME NOT NULL,
  `id_createur` INT NOT NULL,
  `id_session_formation` INT NOT NULL,
  `id_module` INT NOT NULL,
  PRIMARY KEY (`id_evaluation`),
  INDEX `fk_evaluation_personne1_idx` (`id_createur` ASC),
  INDEX `fk_evaluation_session_formation1_idx` (`id_session_formation` ASC),
  INDEX `fk_evaluation_module1_idx` (`id_module` ASC),
  CONSTRAINT `fk_evaluation_personne1`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evaluation_session_formation1`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evaluation_module1`
    FOREIGN KEY (`id_module`)
    REFERENCES `module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `copie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copie` (
  `id_personne` INT NOT NULL,
  `id_evaluation` INT NOT NULL,
  `note` DECIMAL(3,1) NULL,
  PRIMARY KEY (`id_personne`, `id_evaluation`),
  INDEX `fk_personne_evaluation_evaluation1_idx` (`id_evaluation` ASC),
  INDEX `fk_personne_evaluation_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_personne_evaluation_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_evaluation_evaluation1`
    FOREIGN KEY (`id_evaluation`)
    REFERENCES `evaluation` (`id_evaluation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipe` (
  `id_equipe` INT NOT NULL AUTO_INCREMENT,
  `id_projet` INT NOT NULL,
  PRIMARY KEY (`id_equipe`),
  INDEX `fk_equipe_projet1_idx` (`id_projet` ASC),
  CONSTRAINT `fk_equipe_projet1`
    FOREIGN KEY (`id_projet`)
    REFERENCES `projet` (`id_projet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `membre_equipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `membre_equipe` (
  `id_personne` INT NOT NULL,
  `id_equipe` INT NOT NULL,
  `est_createur` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_personne`, `id_equipe`),
  INDEX `fk_personne_equipe_equipe1_idx` (`id_equipe` ASC),
  INDEX `fk_personne_equipe_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_personne_equipe_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_equipe_equipe1`
    FOREIGN KEY (`id_equipe`)
    REFERENCES `equipe` (`id_equipe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sondage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sondage` (
  `id_sondage` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(45) NOT NULL,
  `note_maximale` INT NOT NULL,
  `id_createur` INT NOT NULL,
  `date_butoir` DATETIME NOT NULL,
  PRIMARY KEY (`id_sondage`),
  INDEX `fk_sondage_personne1_idx` (`id_createur` ASC),
  CONSTRAINT `fk_sondage_personne1`
    FOREIGN KEY (`id_createur`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reponse_sondage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reponse_sondage` (
  `id_personne` INT NOT NULL,
  `id_sondage` INT NOT NULL,
  `note` INT NULL,
  PRIMARY KEY (`id_personne`, `id_sondage`),
  INDEX `fk_personne_sondage_sondage1_idx` (`id_sondage` ASC),
  INDEX `fk_personne_sondage_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_personne_sondage_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_sondage_sondage1`
    FOREIGN KEY (`id_sondage`)
    REFERENCES `sondage` (`id_sondage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `membre_canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `membre_canal` (
  `id_canal` INT NOT NULL,
  `id_personne` INT NOT NULL,
  PRIMARY KEY (`id_canal`, `id_personne`),
  INDEX `fk_canal_personne_personne1_idx` (`id_personne` ASC),
  INDEX `fk_canal_personne_canal1_idx` (`id_canal` ASC),
  CONSTRAINT `fk_canal_personne_canal1`
    FOREIGN KEY (`id_canal`)
    REFERENCES `canal` (`id_canal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_canal_personne_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
