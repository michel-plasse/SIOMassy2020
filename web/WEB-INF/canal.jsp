<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" href="agriotes.css"/>
        <style>
            body {
                margin: 0 auto;
                padding: 0 20px;
            }
        </style>
    </head>
    <body>
        <c:forEach items="${messagesAffiche}" var="messages">
            <div class="container" id="msg${messages.id}">
                <strong>${messages.prenom}</strong>
                <em>${messages.dateFomattee}</em>
                ${messages.contenu}
            </div>
        </c:forEach>
        Votre message: <input type="text" placeholder="Not emplemented yet">
        <button>Envoyer</button>
    </body>
</html>