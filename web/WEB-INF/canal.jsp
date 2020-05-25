<%-- 
    Document   : canal
    Created on : 10 mai 2020, 21:44:03
    Author     : Crist
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
       
            <ul>
                <c:forEach items="${messages}" var="message">
                    <li>
                        Contenu :   ${message.contenu}
                        <hr>
                    </li>
                </c:forEach>
            </ul>
