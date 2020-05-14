
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
        <form id="ajouter" action="ajouterMembresCanal" method="POST">
            Id Canal :  <input type="text" name="idCanal" value="${param["idCanal"]}"/><br/>
            Id personne :  <input type="text" name="idPersonne" value="${param["idPersonne"]}"/><br/>
            <button type="submit">Ajouter</button>

        </form>

        <p><a href="listerMembresCanal">Liste des membres d'un canal</a></p>


    </body>
</html>

<!--
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

        <form method = "POST" action="AjouterMembresCanal">
            <td>Edit</td>
        </form>

        <form method = "POST" action="SupprimerMembresCanal">
            <td>Delete</td>
        </form>            
    </tr>

</c:forEach>



</table>

<p><a><form method = "GET" action="MembresCanalServlet">Gerer les membres d'un canal</a></p>



</form>

</body>
</html>-->