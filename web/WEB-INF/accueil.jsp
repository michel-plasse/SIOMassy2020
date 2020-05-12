<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html>
  <head>
    <title>Agriotes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
    <h1>ACCUEIL AGRIOTES</h1>
    <nav>     
      <a href="trombinoscope?idSession=1">Trombinoscope de la session 1</a>  
      <a href="modifierProfil">Modifier profil</a>
      <a href="questionnaires">Questionnaires</a>
      <a href="evaluations">Evaluations</a>
      <a href="creerProjet">Creer un projet</a>
      <a href="deconnexion">Déconnexion</a>
    </nav>
  </body>
</html>
