<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="addEmployeeDialog" id="addEmployeeDialog">
	<div class="addEmployeeForm">
		<h3>THÊM NHÂN VIÊN</h3>
		<div>Tên nhân viên</div>
		<input id="nameAddEm">
		<div>Email</div>
		<input id="emailAddEm">
		<div>Mật khẩu</div>
		<input id="passAddEm" type="password">
		<div>Ngày sinh</div>
		<input id="birthDateAddEm">
		<div>Địa chỉ</div>
		<input id="addressAddEm">
		<div>Số điện thoại</div>
		<input id="phoneNumberAddEm">
		<div>Chức vụ</div>
		<select id="positionAddEm">
			<option value="1">Tổ trưởng</option>
			<option value="2">Nhân viên</option>
		</select>
		<div class="btnFormAddEmployee">
			<button onclick="addEmployee()">Thêm</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>