<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "model.Product" %>
<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<form action="get">

<c:forEach var="product" items="${productUd}">
	${product.getPr_ID()},${product.getPr_CA_ID()},${product.getPr_Name()},${product.getPr_Price()},${product.getPr_Quantify()},${product.getPr_SU_ID()}
</c:forEach>

</form>
</body>
</html>