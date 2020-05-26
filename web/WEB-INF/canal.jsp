<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<ul>
    <c:forEach items="${messages}" var="message">
        <li>
            ${message.id_auteur} <br>
            ${message.contenu} <br>
            ${message.date_publication} <br>
            ${stagiaire.size()} <br>
            ${idSession} <br>
        </li>
    </c:forEach>
</ul>
