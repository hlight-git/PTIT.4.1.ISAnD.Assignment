<%@ page import="model.SellableObject" %>
<%@ page import="model.SparePart" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2022
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%SellableObject sellableObject = (SellableObject) request.getAttribute("sellableObject");%>
<html>

<head>
  <title>Sellable Object Detail</title>
  <link rel="stylesheet" href="style3.css">
</head>

<body>

<div class="navbar-cover">
  <nav class="navbar">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/customer/">333Garage</a>

    <button class="button logout">Logout</button>
  </nav>
</div>
<div class="info">
  <div class="info description">
    <p><%=sellableObject.getDescription()%>
    </p>
  </div>
  <div class="info image">
    <div></div>
    <img src="sparePart.png">
  </div>
  <div class="info attributes">
    <ul>
      <li><strong>Name</strong>: <%=sellableObject.getName()%>
      </li>
      <li><strong>Price</strong>: <%=sellableObject.getUnitPrice()%>$
      </li>
      <li><strong>Calculation unit</strong>: <%=sellableObject.getCalculationUnit()%>
      </li>
      <%if (sellableObject instanceof SparePart){%>
        <li><strong>Quantity in stock:</strong>: <%=((SparePart) sellableObject).getQuantityInStock()%>
        </li>
      <%}%>
    </ul>
  </div>
</div>
<div style="display:flex;">
  <a class="button link" href="<%=request.getContextPath()%>/customer/sellableObject/search">Back</a>
  <a class="button link" href="#">Add to cart</a>
</div>
</body>

</html>