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
                <img src="img/${messages.id}.jpg" alt="Avatar" style="width:100%;">
                <h3>${messages.prenom}</h3><hr>
                <p>${messages.contenu}</p>
                <span class="time-right">${messages.datePublication}</span>
            </div>
        </c:forEach>
        Votre message: <input type="text">
        <button>Envoyer</button>
    </body>
</html>