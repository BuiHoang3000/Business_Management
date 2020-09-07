<!-- HEADER -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 79px; padding: 5px;">
  <div style="float: left">
     <h1>My Site</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- User store in session with attribute: loginedEm -->
     Xin chào <b>${loginedEm.ps_Name}</b>
 
  </div>
</div>
<!-- MENU -->
<div class="page-wrapper">
        <aside class="menu-sidebar d-none d-lg-block">
        	<div class="logo">
                <a href="#">
                    <img src="<%=request.getContextPath()%>/template/Admin/images/icon/bm.png" alt="Cool Admin" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li>
                            <a href="<%=request.getContextPath() + "/product"%>" id="s1">
                                <i class="fas fa-calendar-alt"></i>Sản phẩm</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() + "/customer"%>" id="s2">
                                <i class="fas fa-calendar-alt"></i>Khách hàng</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() + "/employee"%>" id="s3">
                                <i class="fas fa-calendar-alt"></i>Nhân viên</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() + "/order"%>" id="s4">
                                <i class="fas fa-calendar-alt"></i>Hoá đơn</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() + "/order_detail"%>" id="s5">
                                <i class="fas fa-map-marker-alt"></i>Chi tiết hoá đơn</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() + "/supplier"%>" id="s6">
                                <i class="fas fa-map-marker-alt"></i>Nhà cung cấp</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() + "/categories"%>" id="s7">
                                <i class="fas fa-map-marker-alt"></i>Loại sản phẩm</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
    </div>