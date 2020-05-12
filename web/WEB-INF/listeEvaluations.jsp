<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>La liste de mes evaluations</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="agriotes.css"/>
    </head>

    <body>
        <div class="sidenav">
          <a href="trombinoscope?idSession=1">Session 1</a> <hr>
          <a href="trombinoscope?idSession=2">Session 2</a> <hr>
          <a href="listeEvaluations">Liste de mes évaluations</a> <hr>
          <a href="deconnexion">Déconnexion</a>
        </div>
        <br/>
        <table>
            <tr>
                <th>Mes evaluations</th>
                
            </tr>

            <c:forEach items="${evaluation}" var="uneEvaluation">
                <tr>
                    <td> ${uneEvaluation.email}</td>
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>