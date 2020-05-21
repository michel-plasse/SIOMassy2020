<%@tag description="En-tête de l'application Web agriotes2020" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Nous mettons ici tout ce qui est commun à nos JSP 
On peut y mettre des parties variables, comme le titre (ce qui est affiché
dans l'onglet du navigateur)
--%>
<%@attribute name="titre" required="true" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Agriotes - ${titre}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="agriotes.css"/>
  </head>
  <body>
    <c:if test="${user != null}"  >
      <nav class="sidenav">     
        <a href="trombinoscope?idSession=1">Trombinoscope de la session 1</a>  
        <a href="modifierProfil">Modifier profil</a>
        <a href="questionnaires">Questionnaires</a>
        <a href="creerQuestionnaire">Creer un questionnaire</a>
        <a href="evaluations">Evaluations</a>
        <a href="creerEvaluation">Creer une évaluation</a>
        <a href="projets">Projets</a>
        <a href="creerProjet">Creer un projet</a>
        <a href="sondages">Sondages</a>
        <a href="creerSondage">Creer un sondage</a>
        <a href="chat">Chat</a>
        <form action="deconnexion" method="POST">
          <button type="sumit">Déconnexion</button> 
        </form>
      </nav>
    </c:if>
    <c:if test="${user == null}"  >
      <div class="sidenav">
        <a href="inscription">Inscription</a> <hr>
        <a href="connexion">Connexion</a> <hr>
        <a href="rappelMdp">Modifier Mot de Passe</a> 
      </div>
    </c:if>
