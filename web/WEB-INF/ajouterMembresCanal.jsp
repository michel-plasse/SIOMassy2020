<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Agriotes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   


    </head>

    <body>

        <div class="sidenav">
            <a href="connexion">Connexion</a>
            <hr>
            <a href="membresCanal?idCanal=1">Liste des membres du canal ${membre.idCanal} </a>
        </div> 
        <h1>Ajout de membres</h1>
        <form method="post" action="ajouterMembresCanal" >
            Id Canal :  <input type="text" name="idCanal" />
            Id personne :  <input type="text" name="idPersonne"/>
            <button type="submit">Ajouter</button>
        </form>

    </body>
</html>
