<%-- 
    Document   : Projet
    Created on : 6 mai 2020, 10:09:51
    Author     : maxim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <h1>Creation du projet</h1>
        <form method="get">
              
        Titre du projet : <input type="text" name="titre" />
        
        Date de creation : <input type="date" name="dateCreation"/>

        Date de fin : <input type="date" name="dateFin"/>       

        Description : <textarea name="description" cols="60" rows="10"> </textarea>
        

        <input type="submit" value="Valider" />

        <input type="submit" value="Annuler" />
 
    </body>
</html>
