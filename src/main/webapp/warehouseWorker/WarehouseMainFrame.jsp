<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2022
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warehouse Main Frame</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
  <div class="navbar-cover">
    <nav class="navbar">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/warehouseWorker">333Garage</a>

        <button class="button logout">Logout</button>
    </nav>
</div>
<h1>
    Menu
</h1>
<div class="menu-cover">
    <a class="button link" href="<%=request.getContextPath()%>/warehouseWorker/receipt/new">Import spare parts</a>
    <a class="button link" href="#">Suppliers management</a>
</div>
</body>
</html>