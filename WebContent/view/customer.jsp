<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
	<meta charset="UTF-8">
    <link href="<%=request.getContextPath()%>/template/Admin/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/css/theme.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/css/customer.css" rel="stylesheet" media="all">
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
                                <form action="POST">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                            	<th class="text-center">Chọn</th>
                                                <th class="text-center">ID</th>
                                                <th class="text-center">Tên khách hàng</th>
                                                <th class="text-center">Email</th>
                                                <th class="text-center">Địa chỉ</th>
                                                <th class="text-center">Số điện thoại</th>
                                                <th class="text-center">Xếp hạng</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="customer" items="${customerList}">
									          <tr>
									          	 <td class="text-center"><input type="checkbox" class="selectCus" value="${customer.getPs_ID()}"></td>
									             <td class="text-center">${customer.getPs_ID()}</td>
									             <td class="text-center">${customer.getPs_Name()}</td>
									             <td class="text-center">${customer.getPs_Email()}</td>
									             <td class="text-center">${customer.getPs_Address()}</td>
									             <td class="text-center">${customer.getPs_PhoneNumber()}</td>
									             <td class="text-center">${customer.getCT_Rank()}</td>
									          </tr>
									        </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
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