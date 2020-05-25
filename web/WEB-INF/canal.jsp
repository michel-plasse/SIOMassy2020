<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<ul>
    <c:forEach items="${messages}" var="message">
        <li>
            Contenu :   ${message.contenu} 
            <hr>
        </li>
    </c:forEach>
</ul>
