<%-- 
    Document   : creerEvaluation
    Created on : 13 mai 2020, 17:50:00
    Author     : kenzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Créer évaluation</title>
  </head>
  <body>
    <div>
      <h1>Créer une nouvelle évaluation</h1>
      <form action="creerEvaluation" method="Post">
        Module : 
        <select name="idModule">
          <c:forEach items="${modules}" var="module">
            <option value="${module.key}">${module.value}</option>
          </c:forEach>
        </select>
        <br>
        Session de formation :    
        <select name="idSesionFormation">
          <c:forEach items="${sessions}" var="session">
            <option value="${session.key}">${session.value}</option>
          </c:forEach>
        </select>
        <br>
        <!--Date d'effet : <input type= "datetime"  name = "dateEffet"/>
        <br>-->
        <input  type="submit" value="Valider" >
        <br/>
      </form>
    </div>
  </body>
</html>