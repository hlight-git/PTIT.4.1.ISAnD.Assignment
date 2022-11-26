<%@ page import="model.Receipt" %>
<%@ page import="model.SparePartInReceipt" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2022
  Time: 3:54 PM.,/
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Receipt receipt = (Receipt) request.getAttribute("savedReceipt");
%>
<html>

<head>
  <title>Saved Receipt Frame</title>
  <link rel="stylesheet" href="style3.css">
</head>

<body>
<div class="navbar-cover">
  <nav class="navbar">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/warehouseWorker/">333Garage</a>

    <button class="button logout">Logout</button>
  </nav>
</div>
<h1>Saved receipt</h1>
<div class="receipt-info">
  <div class="receipt-info left">
    <ul>
      <li><strong>Supplier</strong>: <%=receipt.getSupplier().getName()%></li>
      <li><strong>Created at</strong>: <%=new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy").format(receipt.getCreatedTime())%></li>
      <li><strong>Created by</strong>: <%=receipt.getCreator().getName()%></li>
    </ul>
  </div>
  <div class="receipt-info right">
    <img src="printer.png" onclick="window.print()">
  </div>
</div>
<div class="container">
  <ul class="responsive-table">
    <li class="table-header">
      <div class="col col-1">ID</div>
      <div class="col col-2">Name</div>
      <div class="col col-3">Unit price ($)</div>
      <div class="col col-4">Quantity</div>
    </li>
    <%for (SparePartInReceipt s:(List<SparePartInReceipt>)receipt.getSpareParts()){%>
    <li class="table-row">
      <div class="col col-1">
        <%=s.getSparePart().getId()%>
      </div>
      <div class="col col-2">
        <%=s.getSparePart().getName()%>
      </div>
      <div class="col col-3">
        <%=s.getUnitPrice()%>
      </div>
      <div class="col col-4">
        <%=s.getQuantity()%>
      </div>
    </li>
    <%}%>
    <li class="table-footer">
      <div class="col col-footer">Total price: <%=receipt.getTotalPrice()%>$</div>
    </li>
  </ul>
</div>
<a class="button link" href="<%=request.getContextPath()%>/warehouseWorker/">Back to home</a>
</body>

</html>