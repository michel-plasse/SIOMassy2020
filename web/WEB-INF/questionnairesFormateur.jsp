<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/agriotes.css"/>
    <title>Liste des questionnaires</title>

</head>
<body>

<div  id="bloc">
    <section id="contain">
        <h1>Les Questionnaires Actuelles</h1>
        <table style="border: 1px solid black;">
            <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Date de Creation</th>
                <th>Duration</th>
                <th>Auteur ID</th>
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