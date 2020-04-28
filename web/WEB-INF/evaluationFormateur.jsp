<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Evaluations Formateur</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="agriotes.css"/>
    </head>

    <body>
        <h1>Liste des évaluations Formateur</h1>
        <br>
        <table>
            <tr>
                <th>id_evaluation</th>
                <th>date_effet</th>
                <th>id_createur</th>
                <th>id_session_formation</th>
                <th>id_module</th>
            </tr>

            <c:forEach items="${evaluation}" var="uneEvaluation">
                <tr>
                    <td><c:out value="" /></td>
                    <td><c:out value="" /></td>
                    <td><c:out value="" /></td>
                    <td><c:out value="" /></td>
                    <td><c:out value="" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>