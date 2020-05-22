
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- <link rel="stylesheet"type="text/css" href="./agriotes.css"> --%>
        <title>JSP Page</title>
        <script src="jquery-3.4.1.min.js" type="text/javascript"></script>
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
                        url: "canal?"+idCanalCourant,
                        dataType: 'HTML',
                        
                        success:function(data)
                        {
                           $("#droite").html(data);
                        },
                        error:function(xhr, message)
                        {
                            $("#canal"+idCanalCourant).html(xhr.status +"" + "" + message);
                        }
                    });
                
            }
        </script>
        <div id="gauche"> 
            <h1 align="center">Agriotes Tchat</h1>
            <hr>
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
        <div id="droite">
             
        </div>

            
            
            
            
            
            
            
            
            
            <div id="gauche"> 
            <h1 align="center">Messages</h1>
            <hr>
            nombre de message:
            ${messages.size()}
            <br>


            <ul>
                <c:forEach items="${messages}" var="message">
                    <li id="message${message.contenu}">
                        Date :     ${message.date_publication} <br>
                        Contenu :   ${message.contenu} <br>
                        NOM : ${message.id_messageCanal}
                        <hr>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </body>
</html>




</body>
</html>
