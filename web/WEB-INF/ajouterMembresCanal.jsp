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
        <form action="ajouterMembresCanal" method="post" >
            <fieldset>
                <legend>Ajout de membres</legend>
                <label for="idCanal">Id Canal:</label>
                <input type="text" id="idCanal" name="idCanal" />
                <label for="idPersonne">Id Personne:</label>
                <input type="text" id="idPersonne" name="idPersonne" /><br><br>
                <button type="submit" value="Submit">Ajouter</button>
            </fieldset>
        </form>
        <c:forEach var="idCanal" begin="1" end="3">
            <br>${idCanal})<a href="membresCanal?idCanal=${idCanal}"> Liste des membres du Canal ${idCanal} </a><br>
        </c:forEach>
    </center>
</body>
</html>