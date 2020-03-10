DELIMITER $$
DROP PROCEDURE IF EXISTS agriotes2020_reset$$
CREATE PROCEDURE agriotes2020_reset()
BEGIN
  -- supprimer du plus dependant au moins dependant
  DELETE FROM membre_session;
  DELETE FROM personne;
  DELETE FROM session_formation;
  INSERT INTO personne (id_personne, nom, prenom, email, mdp) values 
  (1, 'Ricardo', 'Yáo', 'stagiaire1@gmail.com', 'azerty'),
  (2, 'Kilyan', 'Melys', 'stagiaire2@gmail.com', 'azerty'),
  (3, 'Baptiste', 'Thym', 'stagiaire3@gmail.com', 'azerty'),
  (4, 'Reda', 'Geraldine', 'stagiaire4@gmail.com', 'azerty'),
  (5, 'Kenzy', 'Ruo', 'stagiaire5@gmail.com', 'azerty'),
  (6, 'Mohamed', 'Roux', 'stagiaire6@gmail.com', 'azerty'),
  (7, 'Eskander', 'Skand', 'stagiaire7@gmail.com', 'azerty'),
  (8, 'Felix', 'Cat', 'stagiaire8@gmail.com', 'azerty'),
  (9, 'Shafiqullah', 'Chris', 'stagiaire9@gmail.com', 'azerty'),
  (10, 'Michel', 'Ruo', 'formateur1@gmail.com', 'azerty'),
  (11, 'Brigitte', 'Daniele', 'formateur2@gmail.com', 'azerty');

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
END$$
CALL agriotes2020_reset() $$