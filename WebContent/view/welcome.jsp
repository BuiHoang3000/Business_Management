<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "model.Product" %>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>
	<meta charset="UTF-8">
    <link href="<%=request.getContextPath()%>/template/Admin/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/css/theme.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/css/product.css" rel="stylesheet" media="all">
</head>
<body>
	<div class="page-wrapper">
		<!-- MENU AND HEADER -->
        <jsp:include page="menu.jsp"></jsp:include>
        
        <div class="page-container">
            <!-- MAIN CONTENT-->
            <div style="min-height: 100vh;">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div style="flex: 0 0 100%; max-width: 100%;">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                                <th class="text-center">ID</th>
                                                <th class="text-center">Catergorie ID</th>
                                                <th class="text-center">Name</th>
                                                <th class="text-center">Price</th>
                                                <th class="text-center">Quantify</th>
                                                <th class="text-center">Supplier ID</th>
                                                <th class="text-center">Status</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="product" items="${productList}">
                                        	<tr>
                                        		<td>${product.getPr_ID()}</td>
                                        		<td>${product.getPr_CA_ID()}</td>
                                        		<td>${product.getPr_Name()}</td>
                                        		<td>${product.getPr_Price()}</td>
                                        		<td>${product.getPr_Quantify()}</td>
                                        		<td>${product.getPr_SU_ID()}</td>
                                        		<td>${product.getPr_Status()}</td>
                                        		<td>
									                <a href="editProduct?code=${product.getPr_ID()}">Edit</a>
									                <a href="deleteProduct?code=${product.getPr_ID()}">Delete</a>
									             </td>
                                        	</tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>