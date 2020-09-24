<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="addCategoryDialog" id="addCategoryDialog">
	<div class="addCategoryForm">
		<h3>THÊM DANH MỤC</h3>
		<div>Tên danh mục</div>
		<input id="nameAddCategory">
		<div class="btnFormAddCategory">
			<button onclick="addCategory()">Thêm</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>