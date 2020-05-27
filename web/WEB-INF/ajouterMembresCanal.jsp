<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Ajouter un membre du canal"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Agriotes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="canal.css"/>
    </head>
    <body>
        <center>
            <a href="membresCanal?idCanal=1">Liste des membres du canal ${membre.idCanal} </a>
            <h1>Ajout de membres</h1>
            <form action="ajouterMembresCanal" method="post" >
                <input type="hidden" name="idCanal" value="${membre.idCanal}" />
                Id personne :  <input type="text" name="idPersonne" />
                <button type="submit">Ajouter</button>
            </form>
        </center>
    </body>
</html>