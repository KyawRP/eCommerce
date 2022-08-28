<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Product Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/boot strap.min.css" integrity="sha384- ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
	<div>
		<a class="navbar-brand"> Product Management Application </a>
	</div>
	<ul class="navbar-nav">
		<li><a href="<%=request.getContextPath()%>/ProductServlet/dashboard"
				class="nav-link">Back to Dashboard</a>
		</li>
	</ul>
</nav>

<div class="container col-md-6">
	<div class="card">
		<div class="card-body">
			<c:if test="${product != null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${product == null}">
				<form action="insert" method="post">
			</c:if>
		<caption>
			<h2>
				<c:if test="${product != null}"> Edit Product
				</c:if>
				<c:if test="${product == null}"> Add New Product
				</c:if>
			</h2>
 		</caption>

 		<c:if test="${product != null}">
 			<input type="hidden" name="oriName" value="<c:out value='${product.product_name}' />" />
		</c:if>
		<fieldset class="form-group">
			<label>Product Name</label> <input type="text" value="<c:out value='${product.product_name}' />" class="form-control" name="product_name" required="required">
		</fieldset>
		<fieldset class="form-group">
			<label>Product Country Of Origin</label> <input type="text" value="<c:out value='${product.product_country_of_origin}' />" class="form-control" name="product_country_of_origin">
		</fieldset>
		<fieldset class="form-group">
			<label>Product Category</label> <input type="text" value="<c:out value='${product.product_category}' />" class="form- control" name="product_category">
		</fieldset>
		<fieldset class="form-group">
			<label>Product Status</label> <input type="text" value="<c:out value='${product.product_status}' />" class="form-control" name="product_status">
 		</fieldset>
 		<button type="submit" class="btn btn-success">Save</button>

		</form>
 	</div>
 </div>
</div>

</body>
</html>


