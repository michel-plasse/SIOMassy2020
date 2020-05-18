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
            <a href="membresCanal?idCanal="${idCanal}>Membres du Canal 1</a> <hr>
        </div> 
        <h1>Les membres du canal ${idCanal}</h1>

        <hr>

        <table width = "300px" border="1">
            <tr>
                <th>ID canal</th>
                <th>ID personne</th>
                <th>Supprimer</th>
            </tr>
            <c:forEach items="${membres}" var="membre">
                <tr>  
                    <td>${membre.idCanal}</td>
                    <td>${membre.idPersonne}</td>
                    <td>
                        <form action="index" method="post">
                            <button type="submit">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a href="ajouterMembresCanal?idCanal=1">Ajouter</a><hr>                      
    </body>
</html>