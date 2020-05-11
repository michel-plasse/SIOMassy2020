<%-- 
    Document   : Projet
    Created on : 6 mai 2020, 10:09:51
    Author     : maxim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <head>        
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <h1>Creation du projet</h1>
            <form id="ProjetForm" method="get">

            <div>Titre du projet : <input type="text" name="titre" /></div>

            <div>Date de creation : <input type="date" name="date_Debut"/></div>

            <div>Date de fin : <input type="date" name="date_Fin"/></div>

            <input type="submit" value="Valider" />

            <input type="submit" value="Annuler" />
        </head>
    </body>
</html>
