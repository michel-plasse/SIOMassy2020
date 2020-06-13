<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Les membres du Canal"/>
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
        <h1>Les membres du canal ${idCanal}</h1>
        <table width = "300px" border="1">
            <tr>
                <th>Id canal</th>
                <th>Id personne</th>
                <th>Supprimer</th>
            </tr>
            <c:forEach items="${membres}" var="membre">
                <tr>  
                    <td>${membre.idCanal}</td>
                    <td>${membre.idPersonne}</td>
                    <td>
                        <form action="supprimerMembresCanal" method="post">
                            <input type="hidden" name="idCanal" value="${membre.idCanal}"/>
                            <input type="hidden" name="idPersonne" value="${membre.idPersonne}"/>
                            <button type="submit">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form action="ajouterMembresCanal" method="post" >
            <fieldset>
                <legend>Ajout de membres</legend>
                <input type="hidden" id="idCanal" name="idCanal" value="${param['idCanal']}"/>
                <label for="idPersonne">Id Personne:</label>
                <input type="text" id="idPersonne" name="idPersonne" /><br><br>
                <button type="submit" value="Submit">Ajouter un membre</button>
            </fieldset>
        </form>
    </center>

    <c:forEach var="idCanal" begin="1" end="3">
        <br>${idCanal})<a href="membresCanal?idCanal=${idCanal}"> Liste des membres du Canal ${idCanal} </a><br>
    </c:forEach>
</body>
</html>