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
            <form method="post">
                
                <table>
                    
                    
                    
                    <tr>
                        <td>Titre du projet :</td>
                        <td><input type="text" name="titre" /></td>
                    </tr>${titre}
                    
                    <tr>
                        <td>Date Limite : </td>
                        <td><input type="date" name="date_Fin"/></td>
                    </tr>${date_Fin}
                    
                </table>

            <input type="submit" value="Valider" />

            <input type="submit" value="Annuler" />
            
        </head>
    <body>
    </body>
</html>
