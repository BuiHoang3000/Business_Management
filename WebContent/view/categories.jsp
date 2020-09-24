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
    <link href="<%=request.getContextPath()%>/template/Admin/css/categories.css" rel="stylesheet" media="all">
    
    <link href="<%=request.getContextPath()%>/template/myCss.css" rel="stylesheet" media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/template/jquery/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<!-- MENU AND HEADER -->
        <jsp:include page="menu.jsp"></jsp:include>
        
        <div class="searchCa">
       		<input id="inputSearchCa">
       		<button class="btnSearchCa" onclick="loadForm(1)">Tìm kiếm</button>
       	</div>
       	<div class="btnCa">
       		<button class="btnAddCa" onclick="addCa()">Thêm</button>
       		<button class="btnUpdateCa" onclick="updateCa()">Cập nhật</button>
       		<button class="btnDeleteCa" onclick="deleteCa()">Xoá</button>
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
                                                <th class="text-center">Tên danh mục</th>
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

<jsp:include page="category/addCategory.jsp"></jsp:include>
<jsp:include page="category/updateCategory.jsp"></jsp:include>
<jsp:include page="category/deleteCategory.jsp"></jsp:include>

<script type="text/javascript">

	/* Load */
	$(document).ready(function(){
		loadForm();
	});
	
	function loadForm(page){
		var searchCa = document.getElementById('inputSearchCa').value;
		$.ajax({
			type: "POST",
			url: "/Business_Management/CategoryController",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: {
				searchCA : searchCa,
				page : page
			},
			dataType: "json",
			success: function(result){
				var html = '';
				$.each(result, function(key, item){
					html += '<tr>';
					html += '<td><input class="selectCa" type="checkbox" value="' + item.ca_ID + '"></td>';
					html += '<td>' + item.ca_ID + '</td>';
					html += '<td>' + item.ca_Name + '</td>';
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
			url: "/Business_Management/loadPageCa",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			dataType: "json",
			success: function(result){
				var page = Number(result);
				var pagination = '';
				for(var i = 1; i <= page; i++){
					pagination += '<button onclick="loadForm(' + i + ')">' + i + '</button>'
				}
				$('.pagination').html(pagination);
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
	}
	
	/* Add */
	function addCa(){
		document.getElementById('addCategoryDialog').style.display = "block";
	}
	
	function addCategory(){
		var objCa = {
				nameAddCa : document.getElementById('nameAddCategory').value
			};
		$.ajax({
			type: "POST",
			url: "/Business_Management/addCategory",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: objCa,
			success: function(result){
				alert("Thêm thành công!");
			},
			error: function(e){
				console.log("error: " + e);
			}
		});
		document.getElementById('addCategoryDialog').style.display = "none";
		loadForm();
	}
	
	/* Update */
	
	function updateCa(){
		if(checkUpdate() != null){
			document.getElementById('updateCategoryDialog').style.display = "block";
			var idUpdateCa = checkUpdate();
			$.ajax({
				type: "GET",
				url: "/Business_Management/loadUpdateCa",
				contentType: "application/json;",
				data: {
					idUpdateCa : idUpdateCa
				},
				dataType: "json",
				success: function(result){
					document.getElementById('nameUpdateCategory').value = result.ca_Name;
				},
				error: function(e){
					console.log("error: " + e);
				}
			});
		}
	}
	
	function updateCategory(){
		var objCa = {
			idUpdateCa : checkUpdate(),
			nameUpdateCa : document.getElementById('nameUpdateCategory').value
		};
		$.ajax({
			type: "POST",
			url: "/Business_Management/updateCategory",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: objCa,
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
	
	/* Delete */
	
	function deleteCa(){
		if(checkDelete() != null)
			document.getElementById('deleteCategoryDialog').style.display = "block";
	}
	
	function deleteCategory(){
		$.ajax({
			type: "POST",
			url: "/Business_Management/deleteCategory",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			data: {
				idDeleteCa : checkDelete()
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
	
	/* Check */
	
	function checkUpdate(){
		var selectCa = document.getElementsByClassName('selectCa');
		var count = 0;
		var idUpdateCa = "";
		for(var i = 0; i < selectCa.length; i++){
			if(selectCa[i].checked){
				count++;
				idUpdateCa = selectCa[i].value;
			}
		}
		if(count == 0)
			alert("Bạn chưa chọn danh mục!");
		else{
			if(count > 1)
				alert("Bạn chỉ sửa được một danh mục!");
			else
				return idUpdateCa;
		}
	}
	
	function checkDelete(){
		var selectCa = document.getElementsByClassName('selectCa');
		var count = 0;
		var idDeleteCa = "";
		for(var i = 0; i < selectCa.length; i++){
			if(selectCa[i].checked){
				count++;
				idDeleteCa += selectCa[i].value + ",";
			}
		}
		if(count == 0)
			alert("Bạn chưa chọn danh mục!");
		else{
			return idDeleteCa;
		}
	}
	
	/* Back */
	function funcBack(){
		document.getElementById('addCategoryDialog').style.display = "none";
		document.getElementById('updateCategoryDialog').style.display = "none";
		document.getElementById('deleteCategoryDialog').style.display = "none";
	}
</script>

</html>