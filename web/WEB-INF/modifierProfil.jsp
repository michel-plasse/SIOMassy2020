<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agriotes - Modifier Profil</title>
    <link rel="stylesheet" href="agriotes.css"/>
  </head>
  <a:enTete titre="Modification des informations Personnelles"/>

  
  <% session.getAttribute("user");%>

  <c:if test="${user == null}">
    <p class="erreur">Vous devez vous connecter pour mettre à jour votre profil</p>
  </c:if>

  <c:if test="${user != null}"  >
    <br>
    <c:if test="${user.urlPhoto != null}"  >
      <img src="img/${user.urlPhoto}" />
    </c:if>
    <br>
    <c:if test="${user.estFormateur == true}"  >
      <p> FORMATEUR </p>
    </c:if>

    <c:if test="${user.estAdministration == true}"  >
      <p> ADMINISTRATEUR </p>
    </c:if>

    <c:if test="${user.estFormateur !=true } && ${user.estAdministration!=true}">
      <p> STAGIAIRE </p>
    </c:if>
    <hr>
    <form id="loginForm" action="modifierProfil" method="POST">
      <div align="left" style="padding-left: 200px">
        <label for="nom"> Nom </label> 
        <input type="text" name="nom" id="nom" value="${user.nom}" required="true"/>
        <br>
        <label for="prenom"> Prenom </label> <input type="text" name="prenom" id="prenom" value="${user.prenom}" required="true"/>
        <br>
        <label for="email"> Email </label> 
        <input type="text" name="email" id="email" value="${user.email}" required="true"/> 
        <a:erreurEmail test="${emailEstInvalide}"/>
        <br>
        <label for="mdp1"> Mot De Passe </label> 
        <input type="password" name="mdp1" id="mdp1" value="${user.mdp}" required="true"/> 
        <a:erreurMdp test="${mdpEstInvalide}"/>       
        <label for="mdp2"> Veuillez resaisir votre Mot De Passe en cas de modification </label> 
        <input type="password" name="mdp2" id="mdp2" value="${user.mdp}" required="true"/> 
        <a:erreurMdpDiff test="${mdpEstDifferent}"/>
      </div>
      <br>
      <hr>
      <button name = "bouton_valid" value = "Enregister" type = "submit">Mise à jour des informations personnelles </button>  
      <button name = "bouton_valid" value = "Abandonner" type = "submit" >Abandonner la mise à jour </button>
    </form>
    
  </c:if>
</html>
