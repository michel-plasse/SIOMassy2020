
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery-3.4.1.min.js" type="text/javascript"></script>


        <style>
            * {
                box-sizing: border-box;
            }

            .column {
                float: left;
                width: 50%;
                padding: 10px;
                height: 300px; 
                text-align: center;
            }

            .main:after {
                content: "";
                display: table;
                clear: both;
            }
            ul{
                list-style-type: none;
            }
        </style>


    </head>
    <body>
        <div class="sidenav"><a:enTete titre="Chat"/></div>
        <script>
            var idCanalCourant = null;
            function setCanal() {
                idCanalCourant = event.target.getAttribute("id").substring(5);
                var canaux = document.querySelectorAll('*[id^="canal"]');
                for (var i = 0; i < canaux.length; i++) {
                    canaux[i].style.fontWeight = "normal";
                }
                document.getElementById("canal" + idCanalCourant).style.fontWeight = "bold";
                $.ajax({
                    type: "GET",
                    url: "canal?idCanal=" + idCanalCourant,
                    dataType: 'HTML',

                    success: function (data)
                    {
                        $("#droite").html(data);
                    },
                    error: function (xhr, message)
                    {
                        $("#canal" + idCanalCourant).html(xhr.status + "" + "" + message);
                    }
                });
                console.log();
            }
        </script>
        <div class="main">
            <h1 align="center">Agriotes Tchat</h1>
            <div class="column" id="gauche" style="background-color:#aaa;"> 
                nombre de canaux:
                ${canaux.size()}
                <br>
                <ul>
                    <c:forEach items="${canaux}" var="canal">
                        <li id="canal${canal.idCanal}" onclick="setCanal()">
                            ${canal.nom} <hr>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="column" id="droite" style="background-color:#bbb;">
            </div>
        </div>
    </body>
</html>

