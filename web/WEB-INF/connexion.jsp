<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="connexion"/>
<h1>Connexion</h1>
<c:if test='${sessionScope["user"] == null}'>

  <form id="loginForm" action="connexion" method="POST">
    Identifiant :  <input type="text" name="login" value="${param["login"]}"/><br/>
    Mot de passe :  <input type="password" name="password"/><br/>
    <button type="submit">Connexion</button>    

    <c:if test="${erreurLogin != null}">
      <div class="error">${erreurLogin}</div>
    </c:if>
  </form>
  <c:if test='${sessionScope["user"] != null}'>
    <form id="loginForm" action="deconnexion" method="POST">
      <button type="submit">Déconnecter ${sessionScope["user"].email}</button>
    </form>
  </c:if>  
</c:if>



</body>
</html>
