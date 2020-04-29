<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Agriotes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">   
    
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


</style>

  </head>
  
  <body>
  
      <div class="sidenav">
        <a href="connexion">Connexion</a>
        <hr>
        <a href="trombinoscope?idSession=1">Session 1</a> <hr>
       
        
      </div>
      
      
      <%--  <nav> 
      <a href="connexion">Connexion</a>
      <a href="trombinoscope?idSession=1">Session 1</a>
      </nav>--%> 
      
      <%--b<h1 align="center">Agriotes</h1> --%>
      
      <p align="center" class="main">
          <font size="7">
  <span style='color: blue'>A</span>
  <span style='color: red'>G</span>
  <span style='color: yellow'>R</span>
  <span style='color: blue'>I</span>
  <span style='color: green'>O</span>
  <span style='color: red'>T</span>
  <span style='color: yellow'>E</span>
  <span style='color: green'>S</span>
            </font>
    </p>
      
  </body>
</html>
