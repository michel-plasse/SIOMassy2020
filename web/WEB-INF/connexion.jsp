<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agriotes - connexion</title>
    <link rel="stylesheet" href="agriotes.css"/>
     <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  
  <style>
      
      body {
  font-family: "Lato", sans-serif;
}

.sidenav {
  height: 100%;
  width: 160px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  padding-top: 20px;
}

.sidenav a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.main {
  margin-left: 160px; /* Same as the width of the sidenav */
  font-size: 28px; /* Increased text to enable scrolling */
  padding: 0px 10px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
      
      .form-group{
           display:inline-block;
           margin: 0 auto;
           margin-left: 50%;
           transform: translateX(-50%);
      }
      .btn{
          display:inline-block;
          margin: 0 auto;
          margin-left: 50%;
          transform: translateX(-50%);
          margin-top: 20px;
      }
     
      
  </style>
  </head>
  <body>
    
    

    
    <c:if test='${sessionScope["user"] == null}'> 
    <div class="sidenav">
        <a href="index.jsp">Accueil</a>
        <hr>
        <a href="trombinoscope?idSession=1">Session 1</a> 
        <hr>
    </div>
    
    <div class="main">
                                  <p align="center">
          <font size="6">
  <span style='color: blue'>C</span>
  <span style='color: red'>O</span>
  <span style='color: yellow'>N</span>
  <span style='color: blue'>N</span>
  <span style='color: green'>E</span>
  <span style='color: red'>X</span>
  <span style='color: yellow'>I</span>
  <span style='color: green'>O</span>
  <span style='color: blue'>N</span>
            </font>
    </p>
    <hr>
        <form id="loginForm" action="connexion" method="POST">
    <div class="form-group">
        <label for="inputEmail">Identifiant :</label>
        <input class="form-control" type="text" name="login" value="${param["login"]}" placeholder="Email">
    </div>
    <div class="form-group">
        <label for="inputPassword">Mot de passe :</label>
        <input type="password" class="form-control" name="password" placeholder="Mot de passe">
    </div>
    <button type="submit" class="btn btn-dark">Connexion</button>
    </div>
        
     <!-- <form id="loginForm" action="connexion" method="POST">
        Identifiant :  <input type="text" name="login" value="${param["login"]}"/><br/>
        Mot de passe :  <input type="password" name="password"/><br/>
        <button type="submit">Connexion</button> -->
        <c:if test="${erreurLogin != null}">
          <div class="error">${erreurLogin}</div>
        </c:if>
      </form>
    </c:if>
    <c:if test='${sessionScope["user"] != null}'>
   
    <div class="sidenav">
      
        <a href="trombinoscope?idSession=1">Session 1</a> <hr>
        <a href="upload_form.jsp">Fichiers</a><hr>
      </div>
    <div class="main">
         
                     <p align="center">
          <font size="6">
  <span style='color: blue'>D</span>
  <span style='color: red'>E</span>
  <span style='color: yellow'>C</span>
  <span style='color: blue'>O</span>
  <span style='color: green'>N</span>
  <span style='color: red'>N</span>
  <span style='color: yellow'>E</span>
  <span style='color: green'>X</span>
  <span style='color: blue'>I</span>
  <span style='color: red'>O</span>
  <span style='color: green'>N</span>
            </font>
    </p>
    <hr>
         
         
      <form id="loginForm" action="deconnexion" method="POST">
        <button type="submit" class="btn btn-dark">Déconnecter ${sessionScope["user"].email}</button>
      </form></div>
    </c:if>
  </body>
</html>
