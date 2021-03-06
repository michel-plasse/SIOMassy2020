<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
  <a:enTete titre="Modifier mon profil"/>
  <h1> Modifier mon profil </h1>
  <% session.getAttribute("user");%>
  <c:if test="${user == null}">
    <p class="erreur">Vous devez vous connecter pour mettre à jour votre profil</p>
  </c:if>
  <c:if test="${user != null}"  >
    <c:if test="${user.urlPhoto != null}"  >
      <img src="img/${user.urlPhoto}" />
    </c:if>
    <c:if test="${user.estFormateur == true}"  >
      <p> FORMATEUR </p>
    </c:if>
    <c:if test="${user.estAdministration == true}"  >
      <p> ADMINISTRATEUR </p>
    </c:if>
    <c:if test="${user.estFormateur == false } && ${user.estAdministration == false}">
      <p> STAGIAIRE </p>
    </c:if>
    <body>
      <form id="loginForm" action="modifierProfil" method="POST">
        <div style="padding-top:10px">
          <table>
            <tr>
              <th><label for="nom"> Nom </label></th> 
              <td><input type="text" name="nom" id="nom" value="${user.nom}" required="true"/></td>
            </tr>
            <tr>
              <th> <label for="prenom"> Prenom </label></th>
              <td><input type="text" name="prenom" id="prenom" value="${user.prenom}" required="true"/> </td>
            </tr>
            <tr>
              <th><label for="email"> Mail </label> </th> 
              <td><input type="text" name="email" id="email" value="${user.email}" required="true"/> </td> 
                <a:erreurEmail test="${emailEstInvalide}"/>
            </tr>
            <tr>
              <th><label for="mdp1"> Mot De Passe </label></th> 
              <td class="tdModifProfil"><input type="password" name="mdp1" id="mdp1" value="${user.mdp}" required="true"/></td> 
                <a:erreurMdp test="${mdpEstInvalide}"/>       
              <th><label < for="mdp2"> Veuillez confirmer votre Mot De Passe </label></th> 
              <td><input type="password" name="mdp2" id="mdp2" value="${user.mdp}" required="true"/></td> 
              <td><a:erreurMdpDiff test="${mdpEstDifferent}"/></td>
            </tr>
          </table>
        </div>
        <br>
        <hr>
        <button name = "bouton_valid" value = "Enregister" type = "submit">Mise à jour des informations personnelles </button>  
        <button name = "bouton_valid" value = "Abandonner" type = "submit" >Abandonner la mise à jour </button>
      </form>
    </c:if>
</html>
