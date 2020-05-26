<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>

<a:enTete titre="ACCUEIL"/>
<h1> Accueil Agriotes </h1>

<% session.getAttribute("majOK");%>
  <c:if test="${majOK != null}">
    <p> Mise à jour effectuée</p>
  </c:if>
    
<% session.getAttribute("abandon");%>
  <c:if test="${abandon != null}">
    <p> Mise à jour abandonnée</p>
  </c:if>
  

