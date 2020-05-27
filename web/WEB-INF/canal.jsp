<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<ul>
    <c:forEach items="${messagesAffiche}" var="messagesAffiche">
        <li>
            ${messagesAffiche.prenom} <br>
            ${messagesAffiche.contenu} <br>
            ${messagesAffiche.date_publication} <br>
            
            
        </li>
    </c:forEach>
</ul>
