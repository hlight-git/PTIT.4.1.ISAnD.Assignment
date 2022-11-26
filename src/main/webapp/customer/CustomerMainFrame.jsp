<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2022
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Customer Main Frame</title>
    <link rel="stylesheet" href="style1.css">
</head>

<body>
    <div class="navbar-cover">
        <nav class="navbar">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/customer">333Garage</a>

            <button class="button logout">Logout</button>
        </nav>
    </div>
    <h1>
        Menu
    </h1>
    <div class="menu-cover">
        <a class="button link" href="<%=request.getContextPath()%>/customer/sellableObject/search">Searching services and spare parts</a>
        <a class="button link" href="#">Booking appointment online</a>
    </div>
</body>

</html>