<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Consulter résultats questionnaire"/>


<h1>Liste des stagiaires ayant participé au questionnaire</h1>
<table>
    <tr>
        <th>Nom du stagiaire</th>
        <th>Prénom du stagiaire</th>
        <th>Date de debut du questionnaire</th>
        <th>Date de fin du questionnaire</th>
        <th>Note</th>
    </tr>
    <c:forEach items="${resquest}" var="resultat">
        <tr>
            <td>${resultat.prenom}</td>
            <td>${resultat.nom}</td>
            <td>${resultat.dateDebut}</td>
            <td>${resultat.dateFin}</td>
            <td>${resultat.note}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
