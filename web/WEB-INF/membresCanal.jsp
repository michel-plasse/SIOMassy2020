<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <h1>Les membres du canal ${idCanal}</h1>
        <p>${membresCanal.size()} membresCanal</p>
        <ol>
            <c:forEach items="${membresCanal}" var="membre">
                <li>
                    ${membre.idCanal} 
                    ${membre.idPersonne} 
                    ${membre.nom} 

                </li>
            </c:forEach>
        </ol>
    </body>
</html>
