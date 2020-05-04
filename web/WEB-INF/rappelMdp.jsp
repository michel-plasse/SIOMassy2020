<%-- 
    Document   : changementMDP
    Created on : 1 avr. 2020, 14:54:37
    Author     : aline
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="css/style.css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agriotes - Changez de mot de passe</title>
        <link rel="stylesheet" href="agriotes.css"/>
    </head>

    <body>
        <h1>changementMDP</h1>

    <c:if test='${sessionScope["user"] == null}'>

        <form id="changeMDPForm" action="rappelMdp" method="POST">
            Identifiant :     <input type="text" name="mail" value="${param["mail"]}"/><br/>


            <c:if test="${erreurLogin != null}">
                <div class="error">${erreurLogin}</div>
            </c:if>
            <c:if test="${erreurLogin != null}">
            </c:if>



            <c:if test="${erreurLogin != null}">

            </c:if>

            <a href="/connexion.jsp" ><button >valider</button></a>

        </form>
    </c:if>

</body>
</html>
