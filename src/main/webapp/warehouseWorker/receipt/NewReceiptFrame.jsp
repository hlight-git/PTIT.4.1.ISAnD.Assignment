<%@ page import="java.util.List" %>
<%@ page import="model.SparePart" %>
<%@ page import="model.Receipt" %>
<%@ page import="model.Supplier" %>
<%@ page import="model.SparePartInReceipt" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2022
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Receipt receipt = (Receipt) session.getAttribute("receipt");
  String supplierKeyword = (String) request.getAttribute("supplierKeyword");
  String sparePartKeyword = (String) request.getAttribute("sparePartKeyword");
  List<Supplier> suppliers = (List<Supplier>) request.getAttribute("suppliers");
  List<SparePart> spareParts = (List<SparePart>) request.getAttribute("spareParts");
  Supplier selectedSupplier = receipt.getSupplier();
%>
<html>
<head>
    <title>New Receipt Frame</title>
    <link rel="stylesheet" href="style2.css">
</head>
<body>
  <div class="navbar-cover">
    <nav class="navbar">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/warehouseWorker/">333Garage</a>

        <button class="button logout">Logout</button>
    </nav>
</div>
  <div class="page-body">
    <div class="left">
      <div class="supplier">
      <form class="nosubmit" action="<%=request.getContextPath()%>/warehouseWorker/receipt/new" method="get">
          <div
                  id="supplierSearch"
                  class="search" onclick="changeDisplay('suppliers')">
            <input 
            class="nosubmit" 
            type="search" 
            placeholder="Search for supplier" 
            name="supplierKeyword"
            id="supplierInput"
            value='<%=supplierKeyword == null?"":supplierKeyword%>'
            >
          </div>
      </form>
      <form action="<%=request.getContextPath()%>/warehouseWorker/receipt/new" method="post">
        <div class="dropdown-content"
             style="display: <%=supplierKeyword == null?"none":""%>"
             id="suppliers">
          <input id="selectedSupplier" name="selectedSupplier" hidden>
          <%for (Supplier s:suppliers){%>
            <button class="select" onclick="setSupplier(this.value)" value="<%=s.getId()%>"><%=s.getName()%></button>
          <%}%>
        </div>
      </form>
    </div>
      <hr>
    <div class="spare-part">
      <form class="nosubmit" action="<%=request.getContextPath()%>/warehouseWorker/receipt/new" method="get">
        <div
                id="sparePartSearch"
                class="search" onclick="changeDisplay('spareParts')">
          <input 
          class="nosubmit" 
          type="search" 
          placeholder="Search for spare part" 
          name="sparePartKeyword"
          id="sparePartSearchInput"
          value='<%=sparePartKeyword == null?"":sparePartKeyword%>'
          >
        </div>
    </form>
      <form action="<%=request.getContextPath()%>/warehouseWorker/receipt/new" method="post">
        <div class="dropdown-content"
             style="display: <%=sparePartKeyword == null?"none":""%>"
             id="spareParts">
          <input id="selectedSparePart" name="selectedSparePart" hidden required>
          <%for (SparePart s:spareParts){%>
          <a class="select" onclick="setSparePart('<%=s.getId()%>', '<%=s.getName()%>')"><%=s.getName()%></a>
          <%}%>
        </div>
        <div class="spare-part container">
          <span>Quantity:</span>
          <input class="spare-part quantity" name="quantity" type="number" step="1" min="1" required>
        </div>
        <div class="spare-part container">
          <span>Unit price:</span>
          <input class="spare-part price" name="price" type="number" step="0.01" min="0.01" required>
          <span>$</span>
        </div>
        <button class="button add">Add</button>
      </form>
    </div>
      <hr>
      <a class="button link" href="#">New supplier</a>
      <a class="button link" href="#">New spare part</a>
    </div>
    <div class="right">
      <div class="receipt">
        <div class="selected-supplier">
          <span>Supplier: <%=selectedSupplier == null?"":selectedSupplier.getName()%></span>
          <div class="container table">
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
        </div>
      </div>
      <div class="actions">
        <form action="<%=request.getContextPath()%>/warehouseWorker/receipt/saved" method="post">
          <button class="button action" name="action" value="cancel">Cancel</button>
          <button class="button action" name="action" value="save">Save</button>
        </form>
      </div>
    </div>
  </div>
  <script>
    function setSupplier(value){
        document.getElementById("selectedSupplier").value = value;
        changeDisplay("suppliers");
    }
    function setSparePart(id, name){
        document.getElementById("selectedSparePart").value = id;
        document.getElementById("sparePartSearchInput").value = name;
        changeDisplay("spareParts");
    }
    function changeDisplay(id){
        let cur = document.getElementById(id).style.display;
        if (cur == "block"){
          document.getElementById(id).style.display = "none";
        } else {
          document.getElementById(id).style.display = "block";
        }
    }
</script>
</body>
</html>
