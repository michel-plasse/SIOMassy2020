<%-- 
    Document   : creerEvaluation
    Created on : 13 mai 2020, 17:50:00
    Author     : kenzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Créer évaluation</title>
    </head>
    <body>
        <div>
        <h1>Créer une nouvelle évaluation</h1>
        <form action="creerEvaluation" method="Post">
            Module :
            <select name="idModule">
            </select>
            <br/>          
            Session de formation :
            <select name="idSesionFormation">
            </select>
            <br/>            
            Date et heure:
            <br/>
            <input type= "datetime"  name = "dateEtHeure"/>
            <br/>
            Duree:
            <br/>
            <input type="number" name = "duree"/>
            <br/>
            
            <br/>
            <p></p>
            <input  type="submit" value="Valider" >
            <br/>
        </form>
    </div>

    </body>
</html>