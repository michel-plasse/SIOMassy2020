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
  <span class=error>  
  Les deux mots de passe doivent identiques et contenir :
    <ul>
      <li> - un minimum de 8 caractères</li>
      <li> - une ou plusieures majuscules </li>
      <li> - une ou plusieures minuscules </li>
      <li> - un ou plusieurs chiffres </li> 
      <li> - un ou plusieurs caractères spéciaux dans la liste : @#$%^&+=</li>
      <li> - ne doit pas contenir des espaces</li>
    </ul>
  </span>
</c:if> 
