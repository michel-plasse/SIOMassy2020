<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Trombinoscope de la session ${idSession}</title>
  </head>
  <body>
    <h1>Trombinoscope de la session ${idSession}</h1>
    <p>${stagiaires.size()} stagiaires</p>
    
      <table class="classique" border="0" width="90%" align="center">
      <c:forEach items="${stagiaires}" var="stagiaire">

               <td align="center" bgcolor="#F0F0F0" width="15%"> <img src="img/${stagiaire.id}.png" width="100" height="100"/><br> ${stagiaire.prenom} ${stagiaire.nom} <br> (<a href="mailto:${stagiaire.email}">${stagiaire.email}</a>)<br> </td>





      </c:forEach>
    
  </body>
</html>