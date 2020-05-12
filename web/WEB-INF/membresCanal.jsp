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
            <a href="trombinoscope?idSession=1">Session 1</a> <hr>
        </div> 

        <div class="main">
            <h1 align="center">Les membres </h1>
            <h1>Les membres du canal ${idCanal}</h1>

            <hr>

            <table width = "300px" border="1">


                <tr>
                    <th>ID canal</th>

                    <th>ID personne</th>

                    <th>Ajouter</th>

                    <th>Supprimer</th>


                </tr>

                <c:forEach items="${membres}" var="membre">


                    <tr>  

                        <td><center>${membre.idCanal}</center></td>

                        <td><center>${membre.idPersonne}</center></td>


                        <td><form method = "POST" action="SupprimerMembresServlet">Edit</a></td>
                        <td><form method = "POST" action="AjouterMembresServlet">Delete</a></td>
                    </tr>

                </c:forEach>



            </table>

            <p><form method = "GET" action="MembresCanalServlet">Gerer les membres d'un canal</a></p>



            </form>

    </body>
</html>
