<%-- 
    Document   : Projet
    Created on : 6 mai 2020, 10:09:51
    Author     : maxim
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Creation d'un projet"/>
<h1>Creation d'un projet</h1>
<form method="post">
    <table>
        <tr>
            <td>Session_formation : </td>
            <td>
                <select name="id_session_formation">
                    <option value="1" selected="selected">BTS SIO 2019-2020</option>
                    <option value="2">BTS SIO 2018-2019</option>
                </select>${id_session_formation}
            </td>
        </tr>

        <tr>
            <td>Titre du projet :</td>
            <td><input type="text" name="titre" value="${param['titre']}"/></td>
        </tr>

        <tr>
            <td>Date Limite : </td>
            <td><input type="date" name="date_Fin"/></td>
        </tr>

        <tr>
            <td>Description</td>
            <td><textarea name="description" cols="60" rows="10">${param['description']}</textarea></td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Valider" />
                <input type="submit" value="Annuler" />
            </td>
        </tr>
    </table>
    <div class='error'>${messageErreurTitre}</div>
    <div class='error'>${messageErreurDateLimite}</div>
    <div>${message}</div>
</body>
</html>
