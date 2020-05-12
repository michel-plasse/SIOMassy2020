<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agriotes - Modifier Profil</title>
    <link rel="stylesheet" href="agriotes.css"/>
  </head>

  <% session.getAttribute("user");%>

  <c:if test="${user == null}">
    <p class="erreur">Vous devez vous connecter pour mettre à jour votre profil</p>
  </c:if>

  <c:if test="${user != null}"
        >

    <h1>Modification de mes informations personnelles </h1>

    <br>
    <img src="img/${user.id}.jpg" />
    <hr>
    <form id="loginForm" action="modifierProfil" method="POST">
      <table>
        <input type="hidden" name="id" value="${user.id}" required="true">
        <tr>
          <th>
            Nom
          </th>

          <th>
            Prénom
          </th>
          <th>
            Email
          </th>
          <th>
            Mot De Passe
          </th>
        </tr>
        <td> 
          <input type="text" name="nom" value="${user.nom}" required="true"/>
        </td>
        <td> 
          <input type="text" name="prenom" value="${user.prenom}" required="true"/>
        </td>
        <td> 
          <input type="text" name="email" value="${user.email}" required="true"/>
        <a:erreurEmail test="${emailInvalide}"/>
        </td>
        <td> 
          <input type="text" name="mdp" value="${user.mdp}" required="true"/>
            <a:erreurMdp test="true"/>

        </td>

        </tr>
      </table>
      <br>
      <hr>
      <button name = "bouton_Valid" value = "Enregister" type = "submit">Mise à jour des informations personnelles </button>
      <button name = "bouton_Valid" value = "Abandonner" type = "submit" >Abandonner la mise à jour </button>

      
    </form>

  </c:if>



</html>
