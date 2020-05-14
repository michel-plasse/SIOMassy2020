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
            <form id="ProjetForm" method="post">
                
                <table>
                    <tr>
                        <td>Titre du projet :</td>
                        <td><input type="text" name="titre" /></td>
                    </tr>
                    <tr>
                        <td>Date de creation :</td>
                        <td><input type="date" name="date_Debut"/></td>
                    </tr>
                    <tr>
                        <td>Date de fin : </td>
                        <td><input type="date" name="date_Fin"/></td>
                    </tr>
                </table>

            <input type="submit" value="Valider" />

            <input type="submit" value="Annuler" />
            
        </head>
    </body>
</html>
