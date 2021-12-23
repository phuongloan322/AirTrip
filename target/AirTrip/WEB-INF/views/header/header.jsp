<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%  Sachbo sbo = new Sachbo();
		Loaibo lbo = new Loaibo();
		Cartbo cbo = new Cartbo();
		ArrayList<Sachbean> Sachlist = sbo.getSach();
		ArrayList<Loaibean> Loailist = lbo.getLoai();
	%>   
	<%
		HashMap<String, Cartbean> icart = (HashMap<String, Cartbean>)session.getAttribute("Cart");
		if(icart!= null) {
			session.setAttribute("TotalQuanty", cbo.TotalQuanty(icart));
			session.setAttribute("TotalPrice", cbo.TotalPrice(icart));
		}
		else {
			session.setAttribute("TotalQuanty", 0);
			session.setAttribute("TotalPrice", 0);
		}
	%>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i> info@domain.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
								<li><a href="#"><i class="fab fa-twitter"></i></a></li>
								<li><a href="#"><i class="fab fa-linkedin-in"></i></a></li>
								<li><a href="#"><i class="fab fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fab fa-google-plus-g"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-3">
						<div class="logo pull-left">
							<a href="<%=request.getContextPath()%>/Sach_index"><img src="<%=request.getContextPath()%>/asset/image_sach/minhkhai-logo.png" alt="" width="200" height="50"/></a>
						</div>
						
					</div >
					<div class="col-sm-5 pull-left">
						<form action="<%=request.getContextPath()%>/Sach" method="post" width="100%">
							<div id="custom-search-input">
                            <div class="input-group">
                                <input type="text" class="  search-query form-control" placeholder="Tìm kiếm sản phẩm mong muốn..." name="txtsearch"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-danger" type="button">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                            </div>
                        </div>
						</form>
					</div>
					<div class="col-sm-4">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="<%=request.getContextPath()%>/Cart"><i class="fa fa-shopping-cart"></i><span class="quanty">${TotalQuanty }</span> &nbsp;&nbsp; Giỏ hàng</a></li>
							<%
								if(session.getAttribute("loginInfor") != null) {
									Accountbean acc = (Accountbean)session.getAttribute("loginInfor");
									%>
									<li class="dropdown"><a href="#"><i class="fa fa-user"></i><%=acc.getHoten() %><i class="fa fa-angle-down"></i></a>
                                    	<ul role="menu" class="sub-menu">
                                    		<li><a><i class='fas fa-user-circle'></i>Thông tin cá nhân</a></li>
                                    		<li><a href="<%=request.getContextPath() %>/DetailOrder""><i class="fas fa-shopping-bag"></i>Đơn hàng</a></li>
	                                        <li><a href="<%=request.getContextPath() %>/logout"><i class="fa fa-lock"></i> Đăng xuất</a></li>
	                                    </ul>
	                                </li> 
									<%
								}
								else {
										%>
										<li><a href="<%=request.getContextPath() %>/login"><i class="fa fa-lock"></i> Đăng nhập</a></li>
										<%
								}
								%>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu">
							<ul class="nav navbar-nav collapse navbar-collapse pull-left">
								<li><a href="index.html" class="active">Trang chủ</a></li>
								<li><a href="<%=request.getContextPath()%>/about">Giới thiệu</a></li>
								<li class="dropdown"><a href="<%=request.getContextPath()%>/Sach">Gian hàng<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        
                                        <%for(Loaibean loai : Loailist) 
                                        {%>
                                        	<li><a href=""><%=loai.getTenloai()%></a></li>
                                        <%
                                        }
                                        %>
                                       
                                    </ul>
                                </li> 
								<li><a href="<%=request.getContextPath()%>/Contact">Liên hệ</a></li>
							</ul>
							<ul class="nav navbar-nav collapse navbar-collapse pull-right hotro">
								<li><a href="index.html"><i class="fas fa-phone-alt"></i> Hotline: 19001089</a></li>
								<li>|</li>
								<li><a href="index.html"><i class="far fa-comments"></i> Hỗ trợ trực tuyến</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
	 --%>