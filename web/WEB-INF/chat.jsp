<%-- 
    Document   : Tchat
    Created on : 14 avr. 2020, 17:45:17
    Author     : Crist
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/agriotes.css">
        <script src="jquery-3.5.0.min.js" type="text/javascript"></script>

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

            .row:after {
                content: "";
                display: table;
                clear: both;
            }
        </style>


    </head>
    <body>
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
                    url: "canal?id_canal=" + idCanalCourant,
                    datatype: 'HTML',

                    success: function (data)
                    {
                        $("#droite").html(data);
                    },
                    error: function (xhr, message)
                    {
                        $("#canal" + idCanalCourant).html(xhr.status + "" + "" + message);
                    }



                });



            }
        </script>
        <div class="row">
            <h1 align="center">Bienvenue sur le chat Agriotes</h1>
            <div class="column" id="gauche" style="background-color:#aaa;"> 
                
                
                nombre de canaux:
                ${canaux.size()}
                <br>
                <ul>
                    <c:forEach items="${canaux}" var="canal">
                        <li id="canal${canal.idCanal}" onclick="setCanal()">   

                            ${canal.nom}

                        </li>
                    </c:forEach>           
                </ul>
            </div>



            
            
            

            <div class="column" id="droite" style="background-color:#bbb;">


            </div>

        </div>    
    </body>
</html>
