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
    <link href="<%=request.getContextPath()%>/template/myCss.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/jquery/jquery-3.5.1.min.js">
    <script type="text/javascript" src="<%=request.getContextPath()%>/template/jquery/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<!-- MENU AND HEADER -->
        <jsp:include page="menu.jsp"></jsp:include>
        
        <!-- BUTTON ACTION -->
        <div class="buttonCustomer">
	        <button class="btnAddCustomer" onclick="btnAddCustomer()">Thêm</button>
	        <button class="btnUpdateCustomer" onclick="btnUpdateCustomer()">Sửa</button>
	        <button class="btnDeleteCustomer" onclick="btnDeleteCustomer()">Xoá</button>
        </div>
        
        <div class="page-container">
            <!-- MAIN CONTENT-->
            <div style="min-height: 100vh;">
            	<div class="cus_pad">
                <div class="section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div style="flex: 0 0 100%; max-width: 100%;">
                                <div class="table-responsive table--no-card m-b-30">
                                <form>
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
                                        <tbody class="tbody"></tbody>
                                    </table>
                                </form>
                                </div>
                            </div>
                            <div class="pagination"></div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</body>

<jsp:include page="customer/addCustomer.jsp"></jsp:include>
<jsp:include page="customer/updateCustomer.jsp"></jsp:include>
<jsp:include page="customer/deleteCustomer.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function () {
	    loadForm();
	});

	function loadForm(page){
		$.ajax({
			type: "GET",
			url: "/Business_Management/loadData",
			data: {
				page: page
			},
			contentType: "application/json",
			dataType: "json",
			success: function(result){
				var html = '';
				$.each(result, function(key, item){
					html += '<tr>';
					html += '<td><input type="checkbox" class="selectCus" value="' + item.ps_ID + '"></td>';	
		            html += '<td>' + item.ps_ID + '</td>';
		            html += '<td>' + item.ps_Name + '</td>';
		            html += '<td>' + item.ps_Email + '</td>';
		            html += '<td>' + item.ps_Address + '</td>';
		            html += '<td>' + item.ps_PhoneNumber + '</td>';
		            html += '<td>' + item.CT_Rank + '</td>';
					html += '</tr>';
				})
				$('.tbody').html(html);
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
		loadPage();
	}
	
	function loadPage(){
		$.ajax({
			type: "POST",
			url: "/Business_Management/Pagination",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			success: function(result){
				var rs = Number(result);
				var html = '';
				for(var i = 1; i <= result; i++){
					html += '<button onclick="loadForm('+ i +')">' + i + '</button>';
				}
				$('.pagination').html(html);
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
	}
	
	function btnAddCustomer(){
		document.getElementById('addCustomerDialog').style.display = "block";
	}
	
	function addCustomer(){
		var cus = {
				nameCus : document.getElementById('nameAddCustomer').value,
				emailCus : document.getElementById('emailAddCustomer').value,
				passCus : document.getElementById('passAddCustomer').value,
				addressCus : document.getElementById('addressAddCustomer').value,
				phoneNumberCus : document.getElementById('phoneNumberAddCustomer').value,
				rankCus : document.getElementById('rankAddCustomer').value
			};
			$.ajax({
				type: "POST",
				url: "/Business_Management/addCustomer",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
				data: cus,
				success: function(){
					alert("Thêm thành công");
				},
				error: function(e){
					console.log("error: " + e);
				}
			});
			document.getElementById('addCustomerDialog').style.display = "none";
			loadForm(1);
	}
	
	function btnUpdateCustomer(){
		if(funcCountCheck() == 0)
			alert('Bạn chưa chọn sản phẩm!');
		else {
			if(funcCountCheck() > 1)
				alert('Bạn chỉ sửa được một sản phẩm tại một thời điểm!');
			else {
				document.getElementById('updateCustomerDialog').style.display = "block";
				loadUpdate();
			}
		}
	}
	
	function loadUpdate(){
		var idUpdate = funcReturnCheck();
		$.ajax({
			type: "GET",
			url: "/Business_Management/loadUpdateCus",
			contentType: "application/json;charset=utf-8;",
			data: {
				idCusUpdate : idUpdate[0]
			},
			dataType: "json",
			success: function(result){
				document.getElementById('nameCusUpdate').value = result.ps_Name;
				document.getElementById('emailCusUpdate').value = result.ps_Email;
				document.getElementById('passCusUpdate').value = result.ps_Password;
				document.getElementById('addressCusUpdate').value = result.ps_Address;
				document.getElementById('phoneNumberCusUpdate').value = result.ps_PhoneNumber;
				if(result.CT_Rank == "Đồng")
					document.getElementById('rank1').selected = "selected";
				else {
					if(result.CT_Rank == "Bạc")
						document.getElementById('rank2').selected = "selected";
					else
						document.getElementById('rank3').selected = "selected";
				}
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
	}
	
	function updateCustomer(){
		var idUpdate = funcReturnCheck();
		var obj = {
			idCus: idUpdate[0],
			nameCus: document.getElementById('nameCusUpdate').value,
			emailCus: document.getElementById('emailCusUpdate').value,
			passCus: document.getElementById('passCusUpdate').value,
			addressCus: document.getElementById('addressCusUpdate').value,
			phoneCus: document.getElementById('phoneNumberCusUpdate').value,
			rankCus: document.getElementById('rankCusUpdate').value
		}
		$.ajax({
			type: "POST",
			url: "/Business_Management/updateCustomer",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: obj,
			success: function(result){
				alert("Cập nhật thành công!");
			},
			error: function(e){
				alert("error: " + e);
			}
		});
		document.getElementById('updateCustomerDialog').style.display = "none";
		loadForm(1);
	}
	
	function btnDeleteCustomer(){
		if(funcCountCheck() == 0)
			alert('Bạn chưa chọn sản phẩm!');
		else
			document.getElementById('deleteCustomerDialog').style.display = "block";
	}
	
	function deleteCustomer(){
		var idDelete = "";
		var listDelete = funcReturnCheck();
		for(var i = 0; i < listDelete.length; i++){
			idDelete += listDelete[i] + ',';
		}
		$.ajax({
			type: "POST",
			url: "/Business_Management/deleteCustomer",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: {
				idDeleteCus : idDelete
			},
			success: function(result){
				alert('Xoá thành công!');
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
		document.getElementById('deleteCustomerDialog').style.display = "none";
		loadForm(1);
	}
	
	function funcCountCheck(){
		var idSelect = document.getElementsByClassName('selectCus');
		var countSelect = 0;
		for(var i = 0; i < idSelect.length; i++){
			if(idSelect[i].checked){
				countSelect++;
			}
		}
		return countSelect;
		
	}
	
	function funcReturnCheck(){
		var idSelect = document.getElementsByClassName('selectCus');
		var idAction = [];
		for(var i = 0; i < idSelect.length; i++){
			if(idSelect[i].checked){
				idAction.push(idSelect[i].value);
			}
		}
		return idAction;
	}
	
	function funcBack(){
		document.getElementById('addCustomerDialog').style.display = "none";
		document.getElementById('updateCustomerDialog').style.display = "none";
		document.getElementById('deleteCustomerDialog').style.display = "none";
	}
</script>
</html>