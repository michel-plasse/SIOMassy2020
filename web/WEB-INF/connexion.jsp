<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agriotes - connexion</title>
    <link rel="stylesheet" href="agriotes.css"/>
   </head>
   
  <body>
      <p></p>
      
          <h1><c:if test="${messageBienvenue != null}"> <div class="bleu">${messageBienvenue}</div> </c:if></h1>
    <h1 align='center'>Connexion</h1>
    <c:if test='${sessionScope["user"] == null}'>
        <form align='center' id="loginForm" action="connexion" method="POST">
           
            <table>
                <tr>
                    <td>Identifiant :</td>  
                    <td><input type="text" name="login" value="${param["login"]}"/><br/></td>
                </tr>
                
                <tr>
                    <td>Mot de passe :</td>  
                    <td><input type="password" name="password" value="${param["password"]}"/><br/></td>
                </tr>
            </table>
                
            <button type="submit">Connexion</button>
                
            <c:if test="${erreurLogin != null}"> <div class="error">${erreurLogin}</div> </c:if>
        </form>
    </c:if>
   
    <c:if test='${sessionScope["user"] != null}'>
      <form id="loginForm" action="deconnexion" method="POST">
        <button type="submit">Déconnecter ${sessionScope["user"].email}</button>
      </form>
    </c:if>
  </body>
</html>
