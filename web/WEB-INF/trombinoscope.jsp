<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Trombinoscope de la session ${idSession}</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

        <style>

            body {
                font-family: "Lato", sans-serif;
            }

            .sidenav {
                height: 100%;
                width: 160px;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: #111;
                overflow-x: hidden;
                padding-top: 20px;
            }

            .sidenav a {
                padding: 6px 8px 6px 16px;
                text-decoration: none;
                font-size: 25px;
                color: #818181;
                display: block;
            }

            .sidenav a:hover {
                color: #f1f1f1;
            }

            .main {
                margin-left: 160px; /* Same as the width of the sidenav */
                font-size: 28px; /* Increased text to enable scrolling */
                padding: 0px 10px;
            }

            @media screen and (max-height: 450px) {
                .sidenav {padding-top: 15px;}
                .sidenav a {font-size: 18px;}
            }

        </style>
    </head>

    <body>

        <div class="sidenav">
            <a href="index.jsp">Accueil</a><hr>
            <a href="connexion">Connexion</a>
        </div>

        <div class="main">
            <h1 align="center">Trombinoscope de la session ${idSession}</h1>
            <hr>
            <h1 align="center">${stagiaires.size()} stagiaires</h1>
            <hr>

            <ol>
                <c:forEach items="${stagiaires}" var="stagiaire">
                    <div>
                        <table class="table table-dark table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>  
                                    <th>NOM</th>
                                    <th>PRENOM</th>
                                    <th>EMAIL</th>
                                    <th>PHOTO</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${stagiaire.id}</td>
                                    <td>${stagiaire.nom}</td>
                                    <td>${stagiaire.prenom}</td>
                                    <td><a href="mailto:${stagiaire.email}">${stagiaire.email}</a></td>
                                    <td><img src="images/${stagiaire.id}.jpg" width="100" height="100"/></td>
                                </tr>
                            </tbody>
                        </table>
 
                    </div>
                </c:forEach>
            </ol>

        </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>  
    </body>
</html>