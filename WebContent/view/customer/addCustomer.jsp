<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="addCustomerDialog" id="addCustomerDialog">
	<div class="addCustomerForm">
		<h3>THÊM KHÁCH HÀNG</h3>
		<div>Tên khách hàng</div>
		<input id="nameAddCustomer">
		<div>Email</div>
		<input id="emailAddCustomer">
		<div>Mật khẩu</div>
		<input id="passAddCustomer" type="password">
		<div>Địa chỉ</div>
		<input id="addressAddCustomer">
		<div>Số điện thoại</div>
		<input id="phoneNumberAddCustomer">
		<div>Xếp hạng</div>
		<select id="rankAddCustomer">
			<option value="1">Đồng</option>
			<option value="2">Bạc</option>
			<option value="3">Vàng</option>
		</select>
		<div class="btnFormAddCustomer">
			<button onclick="addCustomer()">Thêm</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>