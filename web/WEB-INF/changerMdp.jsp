<%-- 
    Document   : changerMdp
    Created on : 15 mai 2020, 16:19:41
    Author     : aline
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agriotes</title> 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="agriotes.css"/>
    </head>

    <body>
        <div class="sidenav">
            <a href="index.jsp">Retour à la page d'acceuil</a>
            <hr/>
        </div>
        <h1>Changer votre Mot de Passe</h1><hr>

        <form action="changerMdp" method="POST">
            <input type="hidden" name="jeton" value="${param['jeton']}">
            <table>
                <tr>
                    <th><label for="email"> Mail </label> </th> 
                    <td><input  class="agrandir" type="text" 
                                placeholder="Entrez votre e-mail ici" id="email" name="email" 
                                value="${param['email']}" /></td> 
                        <a:erreurEmail test="${emailEstInvalide}"/>          
                </tr>
                <tr>
                <th><label for="mdp"> Mot De Passe </label></th> 
                <td><input class="agrandir" type="password" name="mdp" id="mdp"
                           value="${param['mdp']}" required="true" /></td>
                    <a:erreurMdp test="${mdpEstInvalide}"/>  
                </tr>
                <tr>
                    <th><label  for="mdp2"> Veuillez confirmer votre Mot De Passe </label></th> 
                    <td><input class="agrandir" type="password" name="mdp2" id="mdp2" 
                               value="${param['mdp']}" required="true"/></td> 
                        <a:erreurMdpDiff test="${mdpEstDifferent}"/>
                </tr>
            </table>
            <button type = "submit">Confirmer </button> 
            <c:if test="${erreurLogin != null}">
                <div class="error" >${erreurLogin}</div> 
            </c:if>
        </form>
    </body>
</html>