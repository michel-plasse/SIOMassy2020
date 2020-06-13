<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Changer votre Mot de Passe"/>
<h1>Changer votre Mot de Passe</h1><hr>
<form action="changerMdp" method="POST">
    <input type="hidden" name="jeton" value="${param['jeton']}">
    <table>
        <tr>
            <th><label for="email"> Mail </label> </th> 
            <td><input  class="agrandir" type="text" 
                        placeholder="Entrez votre e-mail ici" id="email" name="email" 
                        value="${param['email']}" /></td> 
                <a:erreurEmail test="${emailEstInvalide}"/>          
        </tr>
        <tr>
            <th><label for="mdp"> Mot De Passe </label></th> 
            <td><input class="agrandir" type="password" name="mdp" id="mdp"
                       value="${param['mdp']}" required="true" /></td>
                <a:erreurMdp test="${mdpEstInvalide}"/>  
        </tr>
        <tr>
            <th><label  for="mdp2"> Veuillez confirmer votre Mot De Passe </label></th> 
            <td><input class="agrandir" type="password" name="mdp2" id="mdp2" 
                       value="${param['mdp']}" required="true"/></td> 
                <a:erreurMdpDiff test="${mdpEstDifferent}"/>
        </tr>
    </table>
    <button type = "submit">Confirmer </button> 
    <c:if test="${erreurLogin != null}">
        <div class="error" >${erreurLogin}</div> 
    </c:if>
</form>
</body>
</html>