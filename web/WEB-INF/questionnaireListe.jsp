<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="agriotes.css"/>
    <title>Liste des questionnaires</title>

</head>
<body>

<div  id="bloc">



    <section id="contain">
        <h2>Liste des Questionnaires</h2>
        <table>

            <tr>
                <th>ID_questionnaire</th>
                <th>Titre</th>
                <th>date_creation</th>
                <th>duree</th>
                <th>id_auteur</th>
                <%--@elvariable id="QuestionnaireServlet" type="java.util.List"--%>
                <c:forEach items="${QuestionnaireServlet}" var="questionnaire">
            </tr>

            <tr>

                <td>What's this</td>
                <td>${questionnaire["id_questionnaire"]}</td>
                <td>${questionnaire["titre"]}</td>
                <td>${questionnaire["date_creation"]}</td>
                <td>${questionnaire["duree"]}</td>
                <td>${questionnaire["id_auteur"]}</td>
                <td>
                    <form action="noter-stagiaire" method="GET">
                        <input style="display: none;" type="text" name="id_evaluation" value="${evaluation['id_evaluation']}">
                        <button type="submit">Noter élèves</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>

    </section>

</div >
</body>


</html>
