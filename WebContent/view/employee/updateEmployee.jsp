<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="updateEmployeeDialog" id="updateEmployeeDialog">
	<div class="updateEmployeeForm">
		<h3>CẬP NHẬT THÔNG TIN</h3>
		<div>Tên nhân viên</div>
		<input id="nameUpdateEm">
		<div>Email</div>
		<input id="emailUpdateEm">
		<div>Mật khẩu</div>
		<input id="passUpdateEm" type="password">
		<div>Ngày sinh</div>
		<input id="birthDateUpdateEm">
		<div>Địa chỉ</div>
		<input id="addressUpdateEm">
		<div>Số điện thoại</div>
		<input id="phoneNumberUpdateEm">
		<div>Chức vụ</div>
		<select id="positionUpdateEm">
			<option id="position1" value="1">Tổ trưởng</option>
			<option id="position2" value="2">Nhân viên</option>
		</select>
		<div class="btnFormUpdateEmployee">
			<button onclick="updateEmployee()">Cập nhật</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>