<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="updateCategoryDialog" id="updateCategoryDialog">
	<div class="updateCategoryForm">
		<h3>CẬP NHẬT DANH MỤC</h3>
		<div>Tên danh mục</div>
		<input id="nameUpdateCategory">
		<div class="btnFormUpdateCategory">
			<button onclick="updateCategory()">Cập nhật</button>
			<button onclick="funcBack()">Quay lại</button>
		</div>
	</div>
</div>