<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib prefix="a" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="agriotes.css"/>
  </head>
  <a:enTete titre="Liste des evaluations"/>
  <% session.getAttribute("user");%>

  <c:if test="${user == null}">
    <p class="erreur">Votre session a expiré, veuillez vous reconnecter</p>
  </c:if>
  <body>
    <div align="center" style="padding-left: 200px">
      <br/>
      <h1>Liste des évaluations</h1>
      <c:set var = "now" value = "" />
      <p><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${now}" /></p>
      <table>
        <tr>
          <th class="agrandir">Nom du module</th>
          <th class="agrandir">Nom de la session</th>
          <th class="agrandir">date d'effet</th>
        </tr>
        <c:forEach items="${evaluation}" var="uneEvaluation">
          <tr>
            <td class="agrandir"> ${uneEvaluation.nomModule}</td>
            <td class="agrandir"> ${uneEvaluation.nomSession}</td>
            <fmt:parseDate value="${uneEvaluation.dateEffet}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <td class="agrandir"><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${ parsedDateTime }" /></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>