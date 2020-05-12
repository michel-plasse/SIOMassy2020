<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Création sondage</title>
  </head>
  <body>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <form method="post" action="sondage">
      <tr> Saisir une question:
       </tr>
       
        <tr> <input type="text" id="quest" name="quest" required minlengh="4" maxlengh="500" size="100">
       </tr>
        <br><br>
        <tr> Selectionner les membres:
       </tr><br><br>
       <div style="overflow:scroll;height:70px;background-color:white;width:200px;">
         <c:forEach items="${listePersonne}" var="unePersonne" varStatus="boucle">
                <input type="checkbox" name="${unePersonne.getId()}" value="${unePersonne.getId()}" id="c1"/>
                <label for="c1">${unePersonne.getPrenom()}</label><br />
               
                    
                
          </c:forEach>
        
       </div>   
       <br><br>
      <tr> Saisir une date butoire (sous format yyyy-mm-dd svp) :
       </tr>
        <tr> <input type="text" id="date" name="date" required minlengh="4" maxlengh="10" size="13">
       </tr>
       <br><br>
       <tr> Determiner la note maximale de votre sondage :
       </tr>
        <tr> <input type="text" id="note" name="note" required minlengh="4" maxlengh="2" size="1">
       </tr>
        <br><br><br>
        <input  type="submit" name="vegeta" value="envoyer">
        <br><br>
        </form>
        
  </body>
</html>