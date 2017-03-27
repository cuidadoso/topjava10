<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<%= request.getAttribute("meals") %>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table id="table" class="sortable">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Exceed</th>
    </tr>
    <c:forEach items="${param.meals}" var="meal">
        <tr>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.exceed}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
