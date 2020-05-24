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
            width: 100%;
            padding: 10px;
            border: 4px solid #bc13fe;
            background: #4deeea;
            margin: 10px 10px 10px 200px;
        }

        th {
            cursor: pointer;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }


        .selected {
            color: #001eff;
        }

        tr:hover {background-color:#74ee15;}
    </style>
</head>
<body>
<div id="bloc">
    <section id="contain">

        <table id="questionnaireTable">
            <tr>
                <th onclick="sortTable(0)">ID</th>
                <th onclick="sortTable(1)">Titre</th>
                <th onclick="sortTable(2)">Date de Creation</th>
                <th onclick="sortTable(3)">Duration</th>
            </tr>
            <c:forEach items="${questionnaires}" var="questionnaire">
                <tr>
                    <td>${questionnaire.id}</td>
                    <td>${questionnaire.duree}</td>
                    <td>${questionnaire.titre}</td>
                    <td>${questionnaire.dateCreation}</td>
                </tr>
            </c:forEach>
        </table>
    </section>
</div >

</body>
</html>



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
            // Take each cell
            var cell = cells[i];
            // do something on onclick event for cell
            cell.onclick = function () {
                // Get the row id where the cell exists
                var rowId = this.parentNode.rowIndex;

                var rowsNotSelected = table.getElementsByTagName('tr');
                for (var row = 0; row < rowsNotSelected.length; row++) {
                    rowsNotSelected[row].style.backgroundColor = "";
                    rowsNotSelected[row].classList.remove('selected');
                }
                var rowSelected = table.getElementsByTagName('tr')[rowId];
                rowSelected.style.backgroundColor = "yellow";
                rowSelected.className += " selected";

                msg = 'Vegeta, allow me to achieve my final perfect form. As a Saiyan, you can appreciate the challenge.: ' + rowSelected.cells[0].innerHTML;
                msg += '\nThe cell value is: ' + this.innerHTML;
                alert(msg);
            }
        }

    } //end of function

    window.onload = highlight_row;
</script>