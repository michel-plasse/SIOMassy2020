<%-- 
    Document   : newtag_file
    Created on : 12 mai 2020, 11:51:08
    Author     : sandr
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="test"%>
<c:if test="${test}">
  <div>  
  Le mot de passe :
    <ul>
      <li> - doit contenir au moins 8 caractères</li>
      <li> - doit au moins un chiffre</li> 
      <li> - doit contenir au moins un caractère spécial dans la liste : @#$%^&+=</li>
      <li> - ne doit pas contenir des espaces</li>
    </ul>
  </div>
</c:if> 
