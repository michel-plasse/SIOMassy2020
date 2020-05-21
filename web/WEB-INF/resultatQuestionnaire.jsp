<%-- 
    Document   : questionnairesDuFormateur
    Created on : 18 mai 2020, 17:00:55
    Author     : wowmi
--%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des questionnaires passés</title>

</head>
<body>

<div>
    <section>
        <h1>Liste des stagiaires</h1>
        <table>
            <tr>
                <th>Nom du stagiaire</th>
                <th>Prénom du stagiaire</th>
                <th>Date de debut du questionnaire</th>
                <th>Date de fin du questionnaire</th>
                <th>Note du stagiaire</th>
            </tr>
            <c:forEach items="${questionnaires}" var="questionnaire">
            <tr>
                <td>${questionnaire.id}</td>
                <td>${questionnaire.titre}</td>
                <td>${questionnaire.dateCreation}</td>
                <td>${questionnaire.duree}</td>
                <td>${questionnaire.idAuteur}</td>
            </tr>
            </c:forEach>
        </table>
    </section>
</div >
</body>
</html>