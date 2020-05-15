<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Agriotes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   


    </head>

    <body>
        <div class="main">
            <h1 align="center">Les membres </h1>
            <h1>Les membres du canal ${idCanal}</h1>
<!--            <form method="post" action="ajouterMembresCanal" >-->

                <table width = "300px" border="1">
                    <tr>
                        <th>ID canal</th>
                        <th>ID personne</th>
                        <th>Supprimer</th>
                    </tr>

                    <c:forEach items="${membres}" var="membre">
                        <tr>  
                            <td>
                                ${membre.idCanal}
                            </td>

                            <td>${membre.idPersonne}</td>

                            <td>
                                <button type="submit">Supprimer</a>  
                            </td> 
                        </tr>
                    </c:forEach>
                </table>

<!--            </form>-->
            <a href="/ajouterMembresCanal.jsp">Ajouter</a>
    </body>
</html>
