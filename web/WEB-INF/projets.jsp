<%-- 
    Document   : projets
    Created on : 6 mai 2020, 11:49:04
    Author     : ricar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Agriotes Projets</h1>
            <hr>
            nombre de projets:
            ${projets.size()}
            <br>


            <ul>
                <c:forEach items="${projets}" var="projet">
                    <li>

                        ${projet.titre}
                    </li>
                </c:forEach>
            </ul>
    </body>
</html>
