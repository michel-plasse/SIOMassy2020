<%-- 
    Document   : changementMDP
    Created on : 1 avr. 2020, 14:54:37
    Author     : aline
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Modifier mon profil"/>
<h1>demander nouveau mot de passe</h1>

<c:if test='${sessionScope["user"] == null}'>
  <form id="demanderNouvMdpForm" action="rappelMdp" method="POST">
    Identifiant : <input type="text" name="login" value="${param["login"]}"/><br/>
    <button type="submit">valider</button>    
    <!--          Verify Code :  <input type="code" name="code"/><br/>-->
    <c:if test="${erreurLogin != null}">
      <div class="error">${erreurLogin}</div>
    </c:if>
  </form>
</c:if>

</body>
</html>
