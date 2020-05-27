<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <style>
            body {
                margin: 0 auto;
                padding: 0 20px;
            }

            .container {
                border: 2px solid #dedede;
                background-color: #f1f1f1;
                border-radius: 5px;
                padding: 10px;
                margin: 10px 0;
            }

            .darker {
                border-color: #ccc;
                background-color: #ddd;
            }

            .container::after {
                content: "";
                clear: both;
                display: table;
            }

            .container img {
                float: left;
                max-width: 60px;
                width: 100%;
                margin-right: 20px;
                border-radius: 50%;
            }

            .container img.right {
                float: right;
                margin-left: 20px;
                margin-right:0;
            }

            .time-right {
                float: right;
                color: #aaa;
            }

            .time-left {
                float: left;
                color: #999;
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