<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="utf-8"%>
<%@ taglib prefix = "a" tagdir="/WEB-INF/tags"%>
<a:enTete titre="Questionnaires"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/agriotes.css"/>
    <style>

        table {
            border-spacing: 0;
            width: 300px;
            padding: 10px;
            border: 4px solid #bc13fe;
            background: #4deeea;
            margin: 10px 10px 10px 200px;
        }


        #details {
            width: 800px;
            height: 100px;
            position: fixed;
            left: 200px;
        }

        th {
            cursor: pointer;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }
        tr:nth-child(n+2):hover {
            background-color: #74ee15;
        }
    </style>
    <title>Questionnaires</title>
</head>
<body>
    <div id="bloc">
        <section id="contain">
            <table id="questionnaireTable">
                <tr>
                    <th onclick="sortTable(0)">Titre</th>
                    <th onclick="sortTable(1)">Date de Creation</th>
                    <th onclick="sortTable(2)">Duration</th>
                    <th onclick="sortTable(3)">Nbr Stagiaires</th>
                </tr>
                <c:forEach items="${questionnairesFeeder}" var="questionnaire">
                    <tr class="hoverTable">
                        <td>${questionnaire.titre}</td>
                        <td>${questionnaire.dateCreation}</td>
                        <td>${questionnaire.duree}</td>
                        <td>${questionnaire.nbrStagiaires}</td>
                    </tr>
                </c:forEach>
            </table>

        </section>
    </div >
    <div>
        <section id="questionDetails">
            <table id="questionTable">
                <tr>
                    <%--                    <th onclick="sortTable(0)">Question ID</th>--%>
                    <th>Text</th>
                    <%--                    <th onclick="sortTable(2)">Questionnaire ID</th>--%>
                </tr>
                <c:forEach items="${questionFeeder}" var="questions">
                    <tr>
                        <td>${questions.texte}</td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</body>
</html>

<div>
    <button id="details" >click</button>
</div>


<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $('#details').on('click', function(){
            $('#questionDetails').toggle();
        });
    });
</script>

<script>
    function sortTable(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("questionnaireTable");
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount ++;
            } else {
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
    function highlight_row() {
        var table = document.getElementById('questionnaireTable');
        var cells = table.getElementsByTagName('td');
        for (var i = 0; i < cells.length; i++) {
            var cell = cells[i];
            cell.onclick = function () {
                var rowId = this.parentNode.rowIndex;
                var rowsNotSelected = table.getElementsByTagName('tr');
                for (var row = 0; row < rowsNotSelected.length; row++) {
                    rowsNotSelected[row].style.backgroundColor = "";
                    rowsNotSelected[row].classList.remove('selected');
                }
                var rowSelected = table.getElementsByTagName('tr')[rowId];
                rowSelected.style.backgroundColor = "yellow";
                rowSelected.className += " selected";
                // msg = 'Questionnaire: ' + rowSelected.cells[0].innerHTML;
                // msg += '\nThis will show details pulled from the Questions Table : ' + this.innerHTML;
                // alert(msg);
            }
        }
    }
    window.onload = highlight_row;
</script>