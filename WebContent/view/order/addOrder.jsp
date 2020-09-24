<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="addOrderDialog" id="addOrderDialog">
	<div class="addOrderForm">
		<h3>THÊM HOÁ ĐƠN</h3>
		<div>ID</div>
		<input id="idAddOrder">
		<div>Tên khách hàng</div>
		<input id="nameCTAddOrder">
		<div>Tên nhân viên</div>
		<input id="nameEMAddOrder">
		<div>Ngày mua</div>
		<input id="orderDateAddOrder">
		<div class="btnFormAddOrder">
			<button onclick="addOrder()">Thêm</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>