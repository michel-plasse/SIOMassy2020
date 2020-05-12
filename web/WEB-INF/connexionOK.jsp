<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <title>Espace greta</title>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="agriotes.css"/>
    </head>

    <body>
        <div class="sidenav">
          <a href="trombinoscope?idSession=1">Session 1</a> <hr>
          <a href="trombinoscope?idSession=2">Session 2</a> <hr>
          <a href="listeEvaluations">Liste de mes évaluations</a> <hr>
          <a href="deconnexion">Déconnexion</a>
        </div>
        <h1 align="center"> ${nomprenom}</h1>
        
      <%--  <nav> 
      <a href="connexion">Connexion</a>
      <a href="trombinoscope?idSession=1">Session 1</a>
      </nav>--%> 

      
  </body>
</html>