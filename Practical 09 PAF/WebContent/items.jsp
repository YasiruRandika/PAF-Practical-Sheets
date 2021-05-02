<%@page import="service.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	//Save---------------------------------
	if (request.getParameter("itemCode") != null) {
		Item itemObj = new Item();
		String stsMsg = "";
		//Insert--------------------------
		if (request.getParameter("hidItemIDSave") == "") {
			stsMsg = itemObj.insertItem(request.getParameter("itemCode"), request.getParameter("itemName"),
					request.getParameter("itemPrice"), request.getParameter("itemDesc"));
		} else//Update----------------------
		{
			stsMsg = itemObj.updateItem(Integer.parseInt(request.getParameter("hidItemIDSave")),
					request.getParameter("itemCode"), request.getParameter("itemName"),
					request.getParameter("itemPrice"), request.getParameter("itemDesc"));
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete-----------------------------
	if (request.getParameter("hidItemIDDelete") != null) {
		Item itemObj = new Item();
		String stsMsg = itemObj.deleteItem(Integer.parseInt(request.getParameter("hidItemIDDelete")));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>
<body>
	<h1>Items Management</h1>
	<form id="formItem" name="formItem" method="post" action="items.jsp">
		Item code: <input id="itemCode" name="itemCode" type="text"
			class="form-control form-control-sm"> <br> Item name: <input
			id="itemName" name="itemName" type="text"
			class="form-control form-control-sm"> <br> Item price: <input
			id="itemPrice" name="itemPrice" type="text"
			class="form-control form-control-sm"> <br> Item
		description: <input id="itemDesc" name="itemDesc" type="text"
			class="form-control form-control-sm"> <br> <input
			id="btnSave" name="btnSave" type="submit" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	<br>
	<%
		Item itemObj = new Item();
		out.print(itemObj.readItems());
	%>
</body>
</html>