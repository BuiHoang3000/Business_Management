<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "model.Product" %>
<%@ page import = "service.ProductService" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.SQLException" %>

<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
	<meta charset="UTF-8">
    <link href="<%=request.getContextPath()%>/template/Admin/css/font-face.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/css/theme.css" rel="stylesheet" media="all">
    <link href="<%=request.getContextPath()%>/template/Admin/css/product.css" rel="stylesheet" media="all">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/template/Login_v1/css/main.css">
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/template/Login_v1/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/Login_v1/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/Login_v1/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/Login_v1/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/Login_v1/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/Login_v1/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/Login_v1/css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/myCss.css">
</head>
<body>
	<div class="page-wrapper">
		<!-- MENU AND HEADER -->
        <jsp:include page="menu.jsp"></jsp:include>
        
        <div class="page-container">
            <!-- MAIN CONTENT-->
            <div style="min-height: 100vh;">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div style="flex: 0 0 100%; max-width: 100%;">
                                <div class="table-responsive table--no-card m-b-30">
                               	
                               	<form id="formExportToExcel" method="post" style="margin-top:16px;" action="<%=request.getContextPath()%>/exportToExcelProduct">
                               		<button id="btnExport" value="btnExport" type="submit" class="btn btn-outline-primary" style="float:right;">Xuất hoá đơn</button>
                               	</form>
                               	
                               	<button id="btnDetail" onclick="funcDetail()" class="btn btn-secondary" style="color:white;float:right;">Chi tiết</button>
                               	<button id="btnDelete" onclick="funcDelete()" class="btn btn-danger" style="color:white;float:right;">Xoá</button>
                                <button id="btnUpdate" onclick="funcUpdate()" class="btn btn-success" style="color:white;float:right;">Sửa</button>
                                <button id="btnAdd" onclick="funcAdd()" class="btn btn-primary" style="color:white;float:right;">Thêm</button>
                                
                                <form id="formPr" method="post" action="<%=request.getContextPath()%>/product">
                                	<input type="text" name="searchPr" id="searchPr" placeholder="Tìm kiếm" style="height:37;" />
                                	<button class="btn btn-warning" type="submit">Tìm kiếm</button>
                                    <table id="tableF" class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                            	<th class="text-center">Chọn</th>
                                                <th class="text-center">ID</th>
                                                <th class="text-center">Danh mục</th>
                                                <th class="text-center">Tên sản phẩm</th>
                                                <th class="text-center">Giá</th>
                                                <th class="text-center">Số lượng</th>
                                                <th class="text-center">Nhà cung cấp</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="product" items="${productList}">
                                        	<tr>
                                        		<td class="text-center"><input type="checkbox" value="${product.getPr_ID()}" class="selectPr"></td>
                                        		<td class="text-center">${product.getPr_ID()}</td>
                                        		<td class="text-center">${product.getPr_CA_ID()}</td>
                                        		<td class="text-center">${product.getPr_Name()}</td>
                                        		<td class="text-center">${product.getPr_Price()}</td>
                                        		<td class="text-center">${product.getPr_Quantify()}</td>
                                        		<td class="text-center">${product.getPr_SU_ID()}</td>
                                        	</tr>
                                        </c:forEach>                    
                                        </tbody>
                                    </table>
                                </form>
                                <div>
                                	<form method="post" action="<%=request.getContextPath()%>/product">
		                                <div class="rowPage">
		                                	<c:forEach var="j" begin="1" step="1" end="${numOfPages}">
		                                    	<c:if test="${page != j}">
		                                        	<a type="submit" class="page" href="<%=request.getContextPath()%>/product?page=${j}&size=${pageSize}">${j}</a> |
		                                        </c:if>
		                                        <c:if test="${page == j}">${j} |</c:if>
		                                    </c:forEach>
		                                </div>
		                                <div class="changeSize">
		                                	<input type="text" name="prNumberPerPage" value="${pageSize}" class="prNumberPerPage" />
	                                		<button class="btn btn-outline-success" type="submit">Thay đổi</button>
		                                </div>
	                                </form>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<dialog id="updateDialog">
  <form method="POST" action="<%=request.getContextPath()%>/updateProduct">
    <span class="login100-form-title p-b-59">
    	Cập Nhật Sản Phẩm
    </span>
    <div class="wrap-input100 validate-input">
        <span class="label-input100">ID</span>
        <input class="input100" type="text" name="id" id="idPrUpdate" readonly>
        <span class="focus-input100"></span>
    </div>
    
    <div class="wrap-input100 validate-input">
        <span class="label-input100">Danh mục</span>
        <select class="form-control" name="category" id="categoryPrUpdate">
        	<c:forEach var="category" items="${categoryList}">
            	<option id="category${category.getCa_ID()}PrUpdate" value="${category.getCa_ID()}">${category.getCa_Name()}</option>
        	</c:forEach>
        </select>

        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Tên sản phẩm</span>
        <input class="input100" type="text" name="name" id="namePrUpdate" value="${updatePr.getPr_Name()}">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Giá</span>
        <input class="input100" name="price" id="pricePrUpdate" value="${updatePr.getPr_Price()}" type="number" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Số lượng</span>
        <input class="input100" name="quantify" id="quantifyPrUpdate" value="${updatePr.getPr_Quantify()}" type="number" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Nhà cung cấp</span>
        <select class="form-control" name="supplier" id="supplierPrUpdate">
        	<c:forEach var="supplier" items="${supplierList}">
            	<option id="supplier${supplier.getSu_ID()}PrUpdate" value="${supplier.getSu_ID()}">${supplier.getSu_Name()}</option>
            </c:forEach>
      	</select>
        <span class="focus-input100"></span>
    </div>
    <div class="flex-m w-full p-b-33">
        <div class="contact100-form-checkbox">
        </div>
    </div>

    <div class="container-login100-form-btn">
        <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
            <button class="login100-form-btn" type="submit">Cập nhật</button>
        </div>
        <button type="button" onclick="funcBack()">Quay lại</button>
    </div>
  </form>
</dialog>

<dialog id="detailDialog">
  <form action="">
    <span class="login100-form-title p-b-59">
    	Chi tiết sản phẩm
    </span>
    <div class="wrap-input100 validate-input">
        <span class="label-input100">ID</span>
        <input class="input100" type="text" name="id" id="idPrDetail" readonly>
        <span class="focus-input100"></span>
    </div>
    
    <div class="wrap-input100 validate-input">
        <span class="label-input100">Danh mục</span>
        <select class="form-control" name="category" id="categoryPrDetail">
        	<c:forEach var="category" items="${categoryList}">
            	<option id="category${category.getCa_ID()}PrDetail" value="${category.getCa_ID()}">${category.getCa_Name()}</option>
        	</c:forEach>
        </select>

        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Tên sản phẩm</span>
        <input class="input100" type="text" name="name" id="namePrDetail" value="${updatePr.getPr_Name()}">
        <span class="focus-input100"></span>
    </div>
    
    <img src="" id="imagePrDetail" class="ImagePrDetail" width="240" height="300"/>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Giá</span>
        <input class="input100" name="price" id="pricePrDetail" value="${updatePr.getPr_Price()}" type="number" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Số lượng</span>
        <input class="input100" name="quantify" id="quantifyPrDetail" value="${updatePr.getPr_Quantify()}" type="number" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Nhà cung cấp</span>
        <select class="form-control" name="supplier" id="supplierPrDetail">
        	<c:forEach var="supplier" items="${supplierList}">
            	<option id="supplier${supplier.getSu_ID()}PrDetail" value="${supplier.getSu_ID()}">${supplier.getSu_Name()}</option>
            </c:forEach>
      	</select>
        <span class="focus-input100"></span>
    </div>
    <div class="flex-m w-full p-b-33">
        <div class="contact100-form-checkbox">
        </div>
    </div>

    <div class="container-login100-form-btn">
        <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
        </div>
        <button type="button" onclick="funcBack()">Quay lại</button>
    </div>
  </form>
</dialog>

<dialog id="addDialog">
  <form method="POST" action="<%=request.getContextPath()%>/insertProduct">
    <span class="login100-form-title p-b-59">
    	Thêm Sản Phẩm
    </span>
    
    <div class="wrap-input100 validate-input">
        <span class="label-input100">Danh mục</span>
        <select class="form-control" name="categoryAdd" id="categoryPrAdd">
        	<c:forEach var="category" items="${categoryList}">
            	<option id="category${category.getCa_ID()}PrAdd" value="${category.getCa_ID()}">${category.getCa_Name()}</option>
        	</c:forEach>
        </select>

        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Tên sản phẩm</span>
        <input class="input100" name="nameAdd" type="text" id="namePrAdd" value="">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Giá</span>
        <input class="input100" name="priceAdd" id="pricePrAdd" type="number" value="" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Số lượng</span>
        <input class="input100" name="quantifyAdd" id="quantifyPrAdd" type="number" value="" step="any" pattern="[-+]?[0-9]*[.,]?[0-9]+">
        <span class="focus-input100"></span>
    </div>

    <div class="wrap-input100 validate-input">
        <span class="label-input100">Nhà cung cấp</span>
        <select class="form-control" name="supplierAdd" id="supplierPrAdd">
        	<c:forEach var="supplier" items="${supplierList}">
            	<option id="supplier${supplier.getSu_ID()}PrAdd" value="${supplier.getSu_ID()}">${supplier.getSu_Name()}</option>
            </c:forEach>
      	</select>
      
        <span class="focus-input100"></span>
    </div>
    <div class="flex-m w-full p-b-33">
        <div class="contact100-form-checkbox">
        </div>
    </div>

    <div class="container-login100-form-btn">
        <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
            <button class="login100-form-btn" type="submit">Thêm</button>
        </div>
        <button type="button" onclick="funcBack()">Quay lại</button>
    </div>
  </form>
</dialog>

<dialog id="deleteDialog">
  <form method="POST" action="<%=request.getContextPath()%>/deleteProduct">
    <label>Bạn có chắc muốn xoá sản phẩm không?</label>
    <input type="hidden" id="idDelete" name="idDelete">
    <div class="container-login100-form-btn">
        <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
            <button class="btn btn-danger" type="submit">Có</button>
        </div>
        <button class="btn btn-success" type="button"  onclick="funcBack()">Không</button>
    </div>
  </form>
</dialog>

<script type="text/javascript">
  
  function funcAdd(){
	  var addDialog = document.getElementById('addDialog');
	  addDialog.showModal();
  }
  
  function funcUpdate() {
	  var checkboxPr = document.getElementsByClassName("selectPr");
	  var sp = 0;
	  var idUpdate;
	  for(var i=0;i<checkboxPr.length;i++){
			if(checkboxPr[i].checked){
				idUpdate = checkboxPr[i].value;
				sp++;
			}
		}
	  if(sp == 0)
		  alert("Bạn chưa chọn sản phẩm nào");
	  else {
		  if(sp > 1)
			  alert("Bạn chỉ có thể sửa 1 sản phẩm");
		  else {
			  var xhttp;
			  xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	  var updateDialog = document.getElementById('updateDialog');
					  updateDialog.showModal();
					  
					  var str = this.responseText;
					  str = str.substring(1,str.length-1);
					  var pr = JSON.parse(str);
					  
					  var idPrUpdate = document.getElementById("idPrUpdate");
					  idPrUpdate.value = idUpdate;
					  var category1PrUpdate = document.getElementById("category1PrUpdate");
					  var category2PrUpdate = document.getElementById("category2PrUpdate");
					  var category3PrUpdate = document.getElementById("category3PrUpdate");
					  var category4PrUpdate = document.getElementById("category4PrUpdate");
					  var category5PrUpdate = document.getElementById("category5PrUpdate");
					  
					  switch(pr.pr_CA_ID){
					  	case "Áo":
					  		category1PrUpdate.selected="selected";
					  		break;
					  	case "Quần":
					  		category2PrUpdate.selected="selected";
					  		break;
					  	case "Mũ":
					  		category3PrUpdate.selected="selected";
					  		break;
					  	case "Giày":
					  		category4PrUpdate.selected="selected";
					  		break;
					  	case "Dép":
					  		category5PrUpdate.selected="selected";
					  		break;
					  }
					  
					  var namePrUpdate = document.getElementById("namePrUpdate");
					  namePrUpdate.value = pr.pr_Name;
					  var pricePrUpdate = document.getElementById("pricePrUpdate");
					  pricePrUpdate.value = pr.pr_Price;
					  var quantifyPrUpdate = document.getElementById("quantifyPrUpdate");
					  quantifyPrUpdate.value = pr.pr_Quantify;
					  var supplier1PrUpdate = document.getElementById("supplier1PrUpdate");
					  var supplier2PrUpdate = document.getElementById("supplier2PrUpdate");
					  var supplier3PrUpdate = document.getElementById("supplier3PrUpdate");
					  var supplier4PrUpdate = document.getElementById("supplier4PrUpdate");
					  var supplier5PrUpdate = document.getElementById("supplier5PrUpdate");
					  
					  switch(pr.pr_SU_ID){
					  	case "Công ty TNHH Hoàng Long":
					  		supplier1PrUpdate.selected="selected";
					  		break;
					  	case "Công ty Giày Da Yên Nam":
					  		supplier2PrUpdate.selected="selected";
					  		break;
					  	case "Công ty TNHH Minh An":
					  		supplier3PrUpdate.selected="selected";
					  		break;
					  	case "Công ty Cổ Phần LX":
					  		supplier4PrUpdate.selected="selected";
					  		break;
					  	case "Công ty May Mặc An Phát":
					  		supplier5PrUpdate.selected="selected";
					  		break;
					  }
			    }
			  };
			  xhttp.open("GET", "<%=request.getContextPath()%>/loadUpdate?id="+idUpdate, true);
			  xhttp.send();
		  }
	  }
  };
  
  function funcDelete(){
	  var checkboxPr = document.getElementsByClassName("selectPr");
	  var sp = 0;
	  var allIdDelete = "id:";
	  for(var i=0;i<checkboxPr.length;i++){
			if(checkboxPr[i].checked){
				allIdDelete += checkboxPr[i].value+',';
				sp++;
			}
		}
	  if(sp == 0)
		  alert("Bạn chưa chọn sản phẩm nào");
	  else {
		  var idDelete = document.getElementById("idDelete");
		  idDelete.value = allIdDelete;
		  var deleteDialog = document.getElementById('deleteDialog');
		  deleteDialog.showModal();
	  }
  };
  
  function funcDetail() {
	  var checkboxPr = document.getElementsByClassName("selectPr");
	  var sp = 0;
	  var idUpdate;
	  for(var i=0;i<checkboxPr.length;i++){
			if(checkboxPr[i].checked){
				idDetail = checkboxPr[i].value;
				sp++;
			}
		}
	  if(sp == 0)
		  alert("Bạn chưa chọn sản phẩm nào");
	  else {
		  if(sp > 1)
			  alert("Bạn chỉ có thể xem 1 sản phẩm");
		  else {
			  var xhttp;
			  xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	  var detailDialog = document.getElementById('detailDialog');
			    	  detailDialog.showModal();
					  
					  var str = this.responseText;
					  str = str.substring(1,str.length-1);
					  var pr = JSON.parse(str);
					  
					  var idPrDetail = document.getElementById("idPrDetail");
					  idPrDetail.value = idDetail;
					  var category1PrDetail = document.getElementById("category1PrDetail");
					  var category2PrDetail = document.getElementById("category2PrDetail");
					  var category3PrDetail = document.getElementById("category3PrDetail");
					  var category4PrDetail = document.getElementById("category4PrDetail");
					  var category5PrDetail = document.getElementById("category5PrDetail");
					  
					  switch(pr.pr_CA_ID){
					  	case "Áo":
					  		category1PrDetail.selected="selected";
					  		break;
					  	case "Quần":
					  		category2PrDetail.selected="selected";
					  		break;
					  	case "Mũ":
					  		category3PrDetail.selected="selected";
					  		break;
					  	case "Giày":
					  		category4PrDetail.selected="selected";
					  		break;
					  	case "Dép":
					  		category5PrDetail.selected="selected";
					  		break;
					  }
					  
					  var namePrDetail = document.getElementById("namePrDetail");
					  namePrDetail.value = pr.pr_Name;
					  var imagePrDetail = document.getElementById("imagePrDetail");
					  imagePrDetail.src = pr.pr_PD_Image;
					  var pricePrDetail = document.getElementById("pricePrDetail");
					  pricePrDetail.value = pr.pr_Price;
					  var quantifyPrDetail = document.getElementById("quantifyPrDetail");
					  quantifyPrDetail.value = pr.pr_Quantify;
					  var supplier1PrDetail = document.getElementById("supplier1PrDetail");
					  var supplier2PrDetail = document.getElementById("supplier2PrDetail");
					  var supplier3PrDetail = document.getElementById("supplier3PrDetail");
					  var supplier4PrDetail = document.getElementById("supplier4PrDetail");
					  var supplier5PrDetail = document.getElementById("supplier5PrDetail");
					  
					  switch(pr.pr_SU_ID){
					  	case "Công ty TNHH Hoàng Long":
					  		supplier1PrDetail.selected="selected";
					  		break;
					  	case "Công ty Giày Da Yên Nam":
					  		supplier2PrDetail.selected="selected";
					  		break;
					  	case "Công ty TNHH Minh An":
					  		supplier3PrDetail.selected="selected";
					  		break;
					  	case "Công ty Cổ Phần LX":
					  		supplier4PrDetail.selected="selected";
					  		break;
					  	case "Công ty May Mặc An Phát":
					  		supplier5PrDetail.selected="selected";
					  		break;
					  }
			    }
			  };
			  xhttp.open("GET", "<%=request.getContextPath()%>/productDetail?id="+idDetail, true);
			  xhttp.send();
		  }
	  }
  };
  
  function funcBack(){
	  var addDialog = document.getElementById('addDialog');
	  addDialog.close();
	  var updateDialog = document.getElementById('updateDialog');
	  updateDialog.close();
	  var deleteDialog = document.getElementById('deleteDialog');
	  deleteDialog.close();
	  var detailDialog = document.getElementById('detailDialog');
	  detailDialog.close();
  };

</script>
</body>
</html>