
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Gstion des membres </h1>



        <form method="POST" action="/GererMembresServlet.java">
            <table width = "300px" border="1">
                <tr>
                    <td colspan ="2"><h1>Gérer les membres </h1></td>
                </tr>


                <tr>
                    <td>ID du canal</td>
                    <td><input type ="text" name ="idCanal" id="idCanal"></td>
                </tr>

                <tr>
                    <td>ID de la personne</td>                    
                    <td><input type ="text" name ="idPersonne" id="idPersonne"></td>
                </tr>

                <td>
                    <form action="supprimerMembreCanal" method="POST">
                        <input type="hidden" name="idCanal" value="${membre.idCanal}"/>
                        <input type="hidden" name="idMembre" value="${membre.idPersonne}"/>
                        <button type="submit" >Supprimer</button>
                </td>

            </table>

        </form>
        <p><a href="membresCanal">Liste des membres d'un canal</a></p>
    </body>
</html>
