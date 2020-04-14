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
    <h1>Connexion2</h1>
    §§§§§§§§§§§§§
    <c:if test='${sessionScope["user"] == null}'>

    <form id="loginForm" action="connexion" method="POST">
            Identifiant :  <input type="text" name="login" value="${param["login"]}"/><br/>
            Mot de passe :  <input type="password" name="password"/><br/>
            <button type="submit">Connexion</button>    

        <c:if test="${erreurLogin != null}">
          <div class="error">${erreurLogin}</div>
        </c:if>
        </form>
           
            <form id="CHGMDPForm" action="rappelMdp.jsp" method="POST">
        <c:if test="${erreurLogin != null}"></c:if>
        <button>

         <a href="<%=request.getContextPath()+"/ChangerMDPServlet"%>">Mot de passe oublié</a>
            </boutton>
        
        
    </form>
    </c:if>
     
   
   
  </body>
</html>
