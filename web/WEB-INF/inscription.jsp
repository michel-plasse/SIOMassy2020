<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>Agriotes</title> 
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="agriotes.css"/>
  </head>

  <body>
    <div class="sidenav">
      <a href="index.jsp">Retour à la page d'acceuil</a>
      <hr/>
    </div>
    <h1>INSCRIPTION</h1><hr>
    <p><strong>Inscrivez-vous</strong></p>
    <form action="inscription" method="post">
      <table>
        <tr>
          <td>Nom :</td>
          <td><input class="agrandir" type="text" 
                     placeholder="Entrez votre nom ici" id="nom" name="nom" 
                     value="${param['nom']}" /></td>
        </tr>
        <tr>
          <td>Prenom :</td>
          <td><input class="agrandir" type="text" 
                     placeholder="Entrez votre prenom ici" id="prenom" name="prenom" 
                     value="${param['prenom']}"  /></td>            
        </tr>
        <tr>
          <td>E-mail :</td>
          <td><input  class="agrandir" type="text" 
                      placeholder="Entrez votre e-mail ici" id="mail" name="mail" 
                      value="${param['email']}" /></td>           
        </tr>
        <tr>
          <td>Mot de passe :</td>
          <td><input  class="agrandir" type="password" 
                      placeholder="Choisir un mot de passe" id="mdp" name="mdp" 
                      value="${param['mdp']}"  /></td>
        </tr>
        <tr>
          <td>Confirmer le mot de passe :</td>
          <td><input  class="agrandir" type="password" 
                      placeholder="Confirmer le mot de passe" id="mdp2" name="mdp2" 
                      value="${param['mdp2']}"  /></td>
        </tr>
      </table>
      <input type="submit" value ="S'inscrire" class="agrandirbtn" />
      <c:if test="${erreurLogin != null}">
        <div class="error" >${erreurLogin}</div> 
      </c:if>
    </form>
  </body>
</html>