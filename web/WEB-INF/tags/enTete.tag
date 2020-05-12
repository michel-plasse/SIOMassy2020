<%@tag description="En-tête de l'application Web agriotes2020" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Nous mettons ici tout ce qui est commun à nos JSP 
On peut y mettre des parties variables, comme le titre (ce qui est affiché
dans l'onglet du navigateur)
--%>
<%@attribute name="titre" required="true" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agriotes ${titre}</title>
    <link rel="stylesheet" type="text/css" href="agriotes.css"/>
  </head>
  <body>
    <!-- Menu principal -->
     <nav>
      <a href="connexion">Connexion</a>
      <a href="trombinoscope?idSession=1">Session 1</a>
    </nav>
