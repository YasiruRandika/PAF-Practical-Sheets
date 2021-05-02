<%@page import="java.util.ArrayList"%>
<%@page import="service.ItemServiceImpl"%>
<%@page import="service.itemService"%>
<%@page import="model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
itemService itemService = new ItemServiceImpl();
Item itemModel = new Item();
boolean update = false;
String stsMsg = "";

if(request.getParameter("updateId") != null) {
    update = true;
	itemModel = itemService.getItemById(Integer.parseInt(request.getParameter("updateId")));
}

if(request.getParameter("dltId") != null) {
    stsMsg = itemService.deleteItem(Integer.parseInt(request.getParameter("dltId")));
}

 //Update an Item
 if (request.getParameter("itemId") != null) {
	 stsMsg = itemService.updateItem(
			 Integer.parseInt(request.getParameter("itemId")),
			 request.getParameter("itemCode"),
			 request.getParameter("itemName"),
			 request.getParameter("itemPrice"),
			 request.getParameter("itemDesc"));
	 
 } else if (request.getParameter("itemCode") != null)
 {
	 //Insert an Item
 stsMsg = itemService.insertItem(request.getParameter("itemCode"),
 request.getParameter("itemName"),
 request.getParameter("itemPrice"),
 request.getParameter("itemDesc"));
 }
 
 session.setAttribute("statusMsg", stsMsg);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PAF</title>
<link rel="stylesheet" href="CSS/bootstrap.css">
<link rel="stylesheet" href="CSS/index.css">
</head>
<body>
<h1 class="display-6">Items Management</h1>
<hr>
<br>
<div class="d-flex flex-lg-row flex-sm-column justify-content-around">
<div>
<%if(stsMsg != "") { %>
<div class="alert alert-primary" style="max-width:350px; margin:20px" role="alert">
  <%=stsMsg %>
</div>
<%} %>
<form class="inputForm" method="post" action="index.jsp">
 <%if(!update){ %>
 <div class="form-group">
    <label for="itemCode">Item Code</label>
    <input class="form-control" name="itemCode" type="text" required>
 </div>
 <div class="form-group">
    <label for="itemName">Item Name</label>
   <input class="form-control" name="itemName" type="text" required>
 </div>
  <div class="form-group">
    <label for="itemPrice">Price</label>
   <input class="form-control" name="itemPrice" type="text" required>
 </div>
  <div class="form-group">
    <label for="itemDesc">Description</label>
   <input class="form-control" name="itemDesc" type="text" required>
 </div>
 <button type="submit" class="btn btn-primary">Save</button>
 <%} else { %>
 <input name="itemId" value="<%=itemModel.getItemID()%>"hidden>
 <div class="form-group">
    <label for="itemCode">Item Code</label>
    <input class="form-control" value="<%=itemModel.getItemCode() %>" name="itemCode" type="text" required>
 </div>
 <div class="form-group">
    <label for="itemName">Item Name</label>
   <input class="form-control" value="<%=itemModel.getItemName() %>" name="itemName" type="text" required>
 </div>
  <div class="form-group">
    <label for="itemPrice">Price</label>
   <input class="form-control" value="<%=itemModel.getItemPrice() %>" name="itemPrice" type="text" required>
 </div>
  <div class="form-group">
    <label for="itemDesc">Description</label>
   <input class="form-control" value="<%=itemModel.getItemDesc() %>" name="itemDesc" type="text" required>
 </div>
 <button type="submit" class="btn btn-success">Update</button>
 <%} %>
</form>
</div>

<div>
<% ArrayList<Item> items = itemService.getItems(); 
if(items.size() > 0) {%>
<table class="table" border="1">
<thead class="thead-dark">
<tr>
<th scope="col">Item Code</th>
<th scope="col">Item Name</th>
<th scope="col">Price</th>
<th scope="col">Description</th>
<th></th>
<th></th>
</tr>
</thead>
<tbody>
<%
 for(Item item : items) {
%>
<tr class="trow">
<td><div ><%=item.getItemCode() %></div></td>
<td><div><%=item.getItemName() %></div></td>
<td><div ><%=item.getItemPrice() %></div></td>
<td><div><%=item.getItemDesc() %></div></td>
<td><div class="d-flex justify-content-center"><form action="index.jsp"><input name="dltId" value="<%=item.getItemID()%>"hidden><button  class="btn btn-danger" type="submit">Delete</button></form></div></td>
<td><div class="d-flex justify-content-center"><form action="index.jsp"><input name="updateId" value="<%=item.getItemID()%>"hidden><button class="btn btn-warning" type="submit">Update</button></form></div></td>
</tr>
<%} %>
</tbody>
</table>
<%} %>
</div>
</div>

</body>
</html>