
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les membres</title>
        <link rel="stylesheet" href="agriotes.css"/>
    </head>
    <body>
        <h1>Ajout de membres</h1>
        <form method="post" action="ajouterMembresCanal" >
            Id Canal :  <input type="text" name="idCanal" value="${membre.idCanal}"/>
            Id personne :  <input type="text" name="idPersonne" value="${membre.idPersonne}"/>
            <button type="submit">Ajouter</button>
      </form>

        <p>
            <a href="/">Liste des membres d'un canal</a>
        </p>
    </body>
</html>
