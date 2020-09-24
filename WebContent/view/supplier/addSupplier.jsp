<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="addSupplierDialog" id="addSupplierDialog">
	<div class="addSupplierForm">
		<h3>THÊM NHÀ CUNG CẤP</h3>
		<div>ID</div>
		<input id="idAddSupplier">
		<div>Tên nhà cung cấp</div>
		<input id="nameAddSupplier">
		<div>Số điện thoại</div>
		<input id="phoneNumberAddSupplier">
		<div>Địa chỉ</div>
		<input id="addressAddSupplier">
		<div class="btnFormAddSupplier">
			<button onclick="addSupplier()">Thêm</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>