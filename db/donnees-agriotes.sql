DELIMITER $$
DROP PROCEDURE IF EXISTS agriotes2020_reset$$
CREATE PROCEDURE agriotes2020_reset()
BEGIN
  -- supprimer les données
  SET FOREIGN_KEY_CHECKS = 0;
  TRUNCATE TABLE canal;
	TRUNCATE TABLE copie;
	TRUNCATE TABLE equipe;
	TRUNCATE TABLE evaluation;
	TRUNCATE TABLE membre_canal;
	TRUNCATE TABLE membre_equipe;
	TRUNCATE TABLE membre_session;
	TRUNCATE TABLE message_canal;
	TRUNCATE TABLE module;
	TRUNCATE TABLE passage_questionnaire;
	TRUNCATE TABLE personne;
	TRUNCATE TABLE projet;
	TRUNCATE TABLE question;
	TRUNCATE TABLE questionnaire;
	TRUNCATE TABLE reponse_donnee;
	TRUNCATE TABLE reponse_possible;
	TRUNCATE TABLE reponse_sondage;
	TRUNCATE TABLE session_formation;
	TRUNCATE TABLE sondage;
  SET FOREIGN_KEY_CHECKS = 1;
 
  INSERT INTO personne (id_personne, nom, prenom, email, mdp, est_formateur, est_administration) values 
  (1, 'Ricardo', 'Yáo', 'stagiaire1@gmail.com', 'azerty', 0, 0),
  (2, 'Kilyan', 'Melys', 'stagiaire2@gmail.com', 'azerty', 0, 0),
  (3, 'Baptiste', 'Thym', 'stagiaire3@gmail.com', 'azerty', 0, 0),
  (4, 'Reda', 'Geraldine', 'stagiaire4@gmail.com', 'azerty', 0, 0),
  (5, 'Kenzy', 'Ruo', 'stagiaire5@gmail.com', 'azerty', 0, 0),
  (6, 'Mohamed', 'Roux', 'stagiaire6@gmail.com', 'azerty', 0, 0),
  (7, 'Eskander', 'Skand', 'stagiaire7@gmail.com', 'azerty', 0, 0),
  (8, 'Felix', 'Cat', 'stagiaire8@gmail.com', 'azerty', 0, 0),
  (9, 'Shafiqullah', 'Chris', 'stagiaire9@gmail.com', 'azerty', 0, 0),
  (10, 'Michel', 'Ruo', 'formateur1@gmail.com', 'azerty', 1, 0),
  (11, 'Brigitte', 'Daniele', 'formateur2@gmail.com', 'azerty', 1, 0),
  (12, 'Adeline', 'Brun', 'administration1@gmail.com', 'azerty', 0, 1),
  (13, 'Enora', 'Marquis', 'administration2@gmail.com', 'azerty', 0, 1);

  INSERT INTO session_formation(id_session_formation, nom, date_debut, date_fin) values 
  (1,'BTS SIO 2019-2020','2019-09-02','2020-05-29'),
  (2,'BTS SIO 2018-2019','2018-06-01','2019-05-29');

  INSERT INTO membre_session (id_personne,id_session_formation, date_entree) values
  (1,1,'2019-09-02'),
  (2,1,'2019-09-02'),
  (3,1,'2019-09-02'),
  (4,1,'2019-09-02'),
  (5,1,'2019-09-02'),
  (6,2,'2018-09-05'),
  (7,2,'2018-09-06');


  -- canal etc
  INSERT INTO canal (id_canal, nom, date_creation, id_createur) values
  (1, 'CANAL_1', '2019-09-02', 1),
  (2, 'CANAL_2', '2019-09-02', 2),
  (3, 'CANAL_3', '2019-09-02', 3);
  
  INSERT INTO membre_canal (id_canal, id_personne) values
  (1, 1),
  (2, 2),
  (3, 3);

  INSERT INTO message_canal (id_message_canal, date_publication, contenu, id_auteur, id_canal) VALUES
  (1, '2019-09-02 00:00:00', 'canal 1 msg 1', 1, 1),
  (2, '2019-09-02 01:00:00', 'canal 1 msg 2', 2, 1),
  (3, '2019-09-02 00:00:00', 'Message_2', 2, 2),
  (4, '2019-09-02 00:00:00', 'Message_3', 3, 3);


  -- questionnaire etc
  INSERT INTO questionnaire (id_questionnaire, titre, date_creation, duree, id_auteur) VALUES
  (1, 'testQuestionnaire1', '2020-04-28 00:00:00', '00:06:42', 10),
  (2, 'testQuestionnaire2', '2020-05-06 00:02:00', '00:01:00', 10),
  (3, 'testQuestionnaire3', '2020-05-06 00:01:00', '00:02:00', 10);

  INSERT INTO question (id_question, texte, id_questionnaire)
  VALUES
  (1, 'What color is Zeus Thundercloud''s hat?', 2),
  (2, 'Who gave Shanks the scar on his eye?', 2);

  INSERT INTO reponse_possible(id_reponse_possible, texte, est_correcte, id_question) VALUES
  (1, 'Blue and white', 1, 1),
  (2, 'Black and white', 0, 1),
  (3, 'yellow and red', 0, 1);

  INSERT INTO passage_questionnaire (id_questionnaire, id_stagiaire, date_fin, date_debut) VALUES 
	(1, 1, '2020-05-06 09:04:23', '2020-05-06 09:16:00'),
	(1, 2, '2020-05-06 09:05:42', '2020-05-06 09:19:03'),
	(1, 3, '2020-05-06 09:15:22', '2020-05-06 09:23:13');

  INSERT INTO reponse_donnee (id_questionnaire, id_stagiaire, id_reponse_possible) VALUES
  (1, 1, 2),
  (1, 2, 3),
  (1, 3, 3);
END$$

CALL agriotes2020_reset() $$
