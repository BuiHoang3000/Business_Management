<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="updateCustomerDialog" id="updateCustomerDialog">
	<div class="updateCustomerForm">
		<h3>CẬP NHẬT THÔNG TIN</h3>
		<div>Tên khách hàng</div>
		<input id="nameCusUpdate">
		<div>Email</div>
		<input id="emailCusUpdate">
		<div>Mật khẩu</div>
		<input id="passCusUpdate">
		<div>Địa chỉ</div>
		<input id="addressCusUpdate">
		<div>Số điện thoại</div>
		<input id="phoneNumberCusUpdate">
		<div>Xếp hạng</div>
		<select id="rankCusUpdate">
			<option id="rank1" value="1">Đồng</option>
			<option id="rank2" value="2">Bạc</option>
			<option id="rank3" value="3">Vàng</option>
		</select>
		<div class="btnFormUpdateCustomer">
			<button onclick="updateCustomer()">Cập nhật</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>