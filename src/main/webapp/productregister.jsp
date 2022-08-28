<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Registeration/title>
</head>
<body>
<form action="ProductRegisterServlet" method="post">
      Product Name:<input type="text" name="product_name">
      Product Country of Origin:<input type="text" name="product_country_of_origin">
      ProductCategory:<input type="text" name="product_category">
      Product Status:<select name="product_status">
      	<option>John</option>
      	<option>Susan</option>
      	<option>David</option>
      	</select>
      	<input type="submit" value="Product Registeration">
</form>
</body>
</html>