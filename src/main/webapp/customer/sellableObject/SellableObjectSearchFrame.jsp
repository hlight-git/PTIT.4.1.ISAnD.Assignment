<%@ page import="model.SellableObject" %>
<%@ page import="java.util.List" %>
<%@ page import="model.SparePart" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2022
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Sellable Object Search</title>
  <link rel="stylesheet" href="style2.css">
</head>

<body>
<div class="navbar-cover">
  <nav class="navbar">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/customer/">333Garage</a>

    <button class="button logout">Logout</button>
  </nav>
</div>
<div class="search-bar">
  <form action="#" method="get">
    <input type="search"
           placeholder="Search..."
           name="keyword"
           value="<%=request.getAttribute("keyword") == null?"":request.getAttribute("keyword")%>"
    >
    <button type="submit">Search</button>
  </form>
</div>
<div class="container">
  <ul class="responsive-table">
    <li class="table-header">
      <div class="col col-1">Name</div>
      <div class="col col-2">Type</div>
      <div class="col col-3">Unit price ($)</div>
      <div class="col col-4">Calculation unit</div>
    </li>
    <%for (SellableObject so:(List<SellableObject>)request.getAttribute("sellableObjects")){%>
      <li class="table-row">
        <div class="col col-1">
          <a href="<%=request.getContextPath()%>/customer/sellableObject/detail?id=<%=so.getId()%>">
            <%=so.getName()%>
          </a>
        </div>
        <div class="col col-2">
          <%=so instanceof SparePart?"Spare part":"Service"%>
        </div>
        <div class="col col-3">
          <%=so.getUnitPrice()%>
        </div>
        <div class="col col-4">
          <%=so.getCalculationUnit()%>
        </div>
      </li>
    <%}%>
  </ul>
</div>
</body>

</html>