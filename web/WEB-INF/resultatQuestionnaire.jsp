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
            </tr>
            <c:forEach items="${resquest}" var="resquest">
            <tr>
                
                <td>${resquest.prenom}</td>
                <td>${resquest.nom}</td>
                <td>${resquest.dateDebut}</td>
                <td>${resquest.dateFin}</td>
        
            </tr>
            </c:forEach>
        </table>
    </section>
</div >
</body>
</html>