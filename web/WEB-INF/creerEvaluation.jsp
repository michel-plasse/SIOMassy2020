<%-- 
    Document   : creerEvaluation
    Created on : 13 mai 2020, 17:50:00
    Author     : kenzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>

<a:enTete titre="Créer évaluation"/>

<h1>Créer une nouvelle évaluation</h1>
<form action="creerEvaluation" method="Post">
  Module : 
  <select name="idModule">
    <c:forEach items="${modules}" var="module">
      <option value="${module.key}">${module.value}</option>
    </c:forEach>
  </select>
  <br>
  <br>
  Session de formation :    
  <select name="idSesionFormation">
    <c:forEach items="${sessions}" var="session">
      <option value="${session.key}">${session.value}</option>
    </c:forEach>
  </select>
  <br>
  <br>
  Date d'effet : <input type="datetime-local"  name="dateEffet" min="2020-05-27T00:01"/>
  <br>
  <br>
  <input  type="submit" value="Valider" >
  <br/>
</form>
</body>
</html>