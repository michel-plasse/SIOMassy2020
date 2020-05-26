<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<ul>
    <c:forEach items="${messages}" var="message">
        <li>
            Auteur :   ${message.id_auteur} <br>
            Contenu :   ${message.contenu} <br>
            Date :   ${message.date_publication}
            <hr>
        </li>
    </c:forEach>
</ul>
