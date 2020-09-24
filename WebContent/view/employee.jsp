<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link href="<%=request.getContextPath()%>/template/Admin/css/employee.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/myCss.css" rel="stylesheet" media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/template/jquery/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<!-- MENU AND HEADER -->
        <jsp:include page="menu.jsp"></jsp:include>
        
        <div class="searchEm">
       		<input id="inputSearchEm">
       		<button class="btnSearchEm" onclick="loadForm(1)">Tìm kiếm</button>
       	</div>
       	<div class="btnEm">
       		<button class="btnAddEm" onclick="addEm()">Thêm</button>
       		<button class="btnUpdateEm" onclick="updateEm()">Cập nhật</button>
       		<button class="btnDeleteEm" onclick="deleteEm()">Xoá</button>
       	</div>
        <div class="page-container">
            <!-- MAIN CONTENT-->
            <div style="min-height: 100vh;">
                <div class="section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div style="flex: 0 0 100%; max-width: 100%;">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                            	<th class="text-center">Chọn</th>
                                                <th class="text-center">ID</th>
                                                <th class="text-center">Họ và tên</th>
                                                <th class="text-center">Email</th>
                                                <th class="text-center">Ngày sinh</th>
                                                <th class="text-center">Địa chỉ</th>
                                                <th class="text-center">Số điện thoại</th>
                                                <th class="text-center">Chức vụ</th>
                                            </tr>
                                        </thead>
                                        <tbody class="tbody"></tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="pagination"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<jsp:include page="employee/addEmployee.jsp"></jsp:include>
<jsp:include page="employee/updateEmployee.jsp"></jsp:include>
<jsp:include page="employee/deleteEmployee.jsp"></jsp:include>

<script type="text/javascript">
	
	// Khởi tạo
	$(document).ready(function(){
		loadForm(1);
	});

	// Load lên form + tìm kiếm
	function loadForm(page){
		var searchEm = document.getElementById('inputSearchEm').value;

		$.ajax({
			type: "POST",
			url: "/Business_Management/EmployeeController",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: {
				page : page,
				searchEm : searchEm
			},
			dataType: "json",
			success: function(result){
				var html = '';
				$.each(result, function(key, item){
					html += '<tr>';
					html += '<td><input type="checkbox" class="selectEm" value="' + item.ps_ID + '"></td>';
					html += '<td>' + item.ps_ID + '</td>';
					html += '<td>' + item.ps_Name + '</td>';
					html += '<td>' + item.ps_Email + '</td>';
					html += '<td>' + item.ps_BirthDate + '</td>';
					html += '<td>' + item.ps_Address + '</td>';
					html += '<td>' + item.ps_PhoneNumber + '</td>';
					html += '<td>' + item.em_Position + '</td>';
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
	
	// Phân trang
	function loadPage(){
		var searchEm = document.getElementById('inputSearchEm').value;
		$.ajax({
			type: "POST",
			url: "/Business_Management/paginationEm",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				searchEm : searchEm
			},
			dataType: "json",
			success: function(result){
				var rs = Number(result);
				var page = '';
				for(var i = 1; i <= rs; i++){
					page += '<button onclick="loadForm(' + i + ')">' + i + '</button>'
				}
				$('.pagination').html(page);
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
	}
	
	// Thêm
	function addEm(){
		document.getElementById('addEmployeeDialog').style.display = "block";
	}
	
	function addEmployee(){
		var objAddEm = {
			nameAddEm : document.getElementById('nameAddEm').value,
			emailAddEm : document.getElementById('emailAddEm').value,
			passAddEm : document.getElementById('passAddEm').value,
			birthDateAddEm : document.getElementById('birthDateAddEm').value,
			addressAddEm : document.getElementById('addressAddEm').value,
			phoneNumberAddEm : document.getElementById('phoneNumberAddEm').value,
			positionAddEm : document.getElementById('positionAddEm').value
		};
		$.ajax({
			type: "POST",
			url: "/Business_Management/addEmployee",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: objAddEm,
			success: function(result){
				alert("Thêm thành công!");
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
		funcBack();
		loadForm(1);
	}
	
	// Sửa
	function updateEm(){
		if(checkUpdate() != null){
			document.getElementById('updateEmployeeDialog').style.display = "block";
			loadUpdateEm();
		}
	}
	
	function loadUpdateEm(){
		var idEmUpdate = checkUpdate();
		$.ajax({
			type: "GET",
			url: "/Business_Management/loadUpdateEm",
			contentType: "application/json; charset=UTF-8;",
			data: {
				idEmUpdate : idEmUpdate
			},
			dataType: "json",
			success: function(result){
				console.log(result);
				document.getElementById('nameUpdateEm').value = result.ps_Name;
				document.getElementById('emailUpdateEm').value = result.ps_Email;
				document.getElementById('passUpdateEm').value = result.ps_Password;
				document.getElementById('birthDateUpdateEm').value = result.ps_BirthDate;
				document.getElementById('addressUpdateEm').value = result.ps_Address;
				document.getElementById('phoneNumberUpdateEm').value = result.ps_PhoneNumber;
				result.em_Position == "Tổ trưởng"? document.getElementById('position1').selected = "selected" : document.getElementById('position2').selected = "selected";
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
	}
	
	function updateEmployee(){
		var objEmUpdate = {
			idUpEm : checkUpdate(),
			nameUpEm : document.getElementById('nameUpdateEm').value,
			emailUpEm : document.getElementById('emailUpdateEm').value,
			passUpEm : document.getElementById('passUpdateEm').value,
			birthDateUpEm : document.getElementById('birthDateUpdateEm').value,
			addressUpEm : document.getElementById('addressUpdateEm').value,
			phoneNumberUpEm : document.getElementById('phoneNumberUpdateEm').value,
			positionUpEm : document.getElementById('positionUpdateEm').value
		};
		$.ajax({
			type: "POST",
			url: "/Business_Management/updateEmployee",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: objEmUpdate,
			success: function(result){
				alert("Cập nhật thành công!");
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
		funcBack();
		loadForm(1);
	}
	
	// Xoá
	function deleteEm(){
		if(checkDelete() != null)
			document.getElementById('deleteEmployeeDialog').style.display = "block";
	}
	
	function deleteEmployee(){
		var idEmDelete = checkDelete();
		$.ajax({
			type: "POST",
			url: "/Business_Management/deleteEmployee",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: {
				idEmDelete : idEmDelete
			},
			success: function(result){
				alert("Xoá thành công!");
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
		funcBack();
		loadForm(1);
	}
	
	// check select update
	function checkUpdate(){
		var selectUp = document.getElementsByClassName('selectEm');
		var count = 0;
		var idUpdate;
		for(var i = 0; i < selectUp.length; i++){
			if(selectUp[i].checked){
				count++;
				idUpdate = selectUp[i].value;
			}
		}
		
		if(count == 0)
			alert('Bạn chưa chọn nhân viên!');
		else{
			if(count > 1)
				alert('Bạn chỉ được chọn một nhân viên để sửa!');
			else
				return idUpdate;
		}
	}
	
	// check select delete
	function checkDelete(){
		var selectDe = document.getElementsByClassName('selectEm');
		var count = 0;
		var idEmDelete = '';
		for(var i =0; i < selectDe.length; i++){
			if(selectDe[i].checked){
				count++;
				idEmDelete += selectDe[i].value + ',';
			}
		}
		if(count == 0)
			alert('Bạn chưa chọn nhân viên!');
		else
			return idEmDelete;
	}
	
	function funcBack(){
		document.getElementById('addEmployeeDialog').style.display = "none";
		document.getElementById('updateEmployeeDialog').style.display = "none";
		document.getElementById('deleteEmployeeDialog').style.display = "none";
	}
</script>
</html>