<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Hot Tours</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>		
		<link rel="stylesheet" href="<%=request.getContextPath() %>/asset/css/stylenew.css">
		<%@ include file="/WEB-INF/views/layouts/head2.jsp" %>
		<%@ include file="/WEB-INF/views/layouts/header.jsp" %>
		<script>$(document).ready(function(){
			$().UItoTop({ easingType: 'easeOutQuart' });
		});
		</script>
	</head>
	<body>
<!--==============================header=================================-->
		<header>
			<div class="i-head">
				<div class="row">
					<div class="col-sm-3">
						<div class="logo pull-left">
							<a href="<c:url value="/index" />"><b class="head3">AirPay</b></a>
						</div>
						
					</div >
					<div class="col-sm-5 pull-left">
						<form action="<c:url value="/places/pagination/1" />" method="post" width="100%">
							<div id="custom-search-input">
                            <div class="input-group">
                                <input type="text" name="search" class="search-query form-control" placeholder="Tìm kiếm nhà / phòng mong muốn..." />
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
								<li><a href="<c:url value="/become-a-host" />" class="i-chu">Trở thành chủ nhà</a></li>
									<c:if test="${accLogin != null }">
										<li class="dropdown"><a class="i-chu"><i class="fa fa-user"></i>${accLogin.name }<i class="fa fa-angle-down"></i></a>
	                                    	<ul role="menu" class="sub-menu">
	                                    		<li><a href="<c:url value="/manager-account" />">Thông tin cá nhân</a></li>
	                                    		<li><a href="<c:url value="/loveplace" />">Danh sách Yêu thích</a></li>
	                                    		<li><a href="<c:url value="/bookroom" />">Nhà / phòng đã đặt</a></li>
	                                    		<hr>
	                                    		<li><a href="<c:url value="/become-a-host/index" />">Chế độ chủ nhà</a></li>
		                                        <li><a href="<c:url value="/logout" />">Đăng xuất</a></li>
		                                    </ul>
		                                </li>
	                                </c:if> 
									<c:if test="${accLogin == null }">
										<li><a href="<c:url value="/login" />" class="i-chu"><i class="fa fa-lock"></i> Đăng nhập</a></li>
									</c:if> 	
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container_12">
				<div class="grid_12">
					<div class="menu_block">
						<nav class="horizontal-nav full-width horizontalNav-notprocessed">
							<ul class="sf-menu">
								<li><a href="<c:url value="/index" />">TRANG CHỦ</a></li>
								<li class="current"><a href="<c:url value="/places" />">KHÁM PHÁ</a></li>
								<li><a href="<c:url value="/bookroom" />">CHUYẾN ĐI</a></li>
								<li><a href="<c:url value="/index" />">BLOG</a></li>
								<li><a href="<c:url value="/index" />">LIÊN HỆ</a></li>
							</ul>
						</nav>
						<div class="clear"></div>
					</div>
				</div>
				<div class="grid_12">
					<h1>
						<a href="<c:url value="/places" />">
							<img src="<c:url value="/asset/images/logo.png" />" alt="Your Happy Family">
						</a>
					</h1>
				</div>
			</div>
		</header>
<!--==============================Content=================================-->
		<div class="content"><div class="ic">More Website Templates @ TemplateMonster.com - February 10, 2014!</div>
			<div class="">
				<br>
				<c:if test="${categoryList.size() > 0 }">
					<ul class="category"class="category">
					<c:forEach var="item" items="${categoryList }">
							<a href="<c:url value="/places/${item.categoryId }" />"><li >${item.name }</li></a>
					</c:forEach>
					<a href="<c:url value="/places/" />"><li >Tất cả</li></a>
					</ul>
					
				</c:if>
				
				<table class="table itable" style="max-width: 90%">
					<tr><c:if test="${msg != null && msg != \"\" }">
						<p style="float: right;margin-right: 100px;">${msg }</p>
					</c:if></tr>
					
					<c:choose>
						
						<c:when test="${placeByCategory.size() > 0 }">
							<c:forEach var="item" items="${placeByCategory }" varStatus="loop">
							<input id="ed" value="${ item.placeId }" hidden/>
								<td style="width:33%">
									<div class="iPlace">
										<div class="cha">
											<span class="con1">
												 <c:choose>
													<c:when test="${!Cart.containsKey(item.placeId) }">
														<a data-id="${item.placeId }" >
															<i class="fas fa-heart" id="item-${ item.placeId }" style="color:white"></i>
														</a>
													</c:when>
													<c:otherwise>
														<a data-id="${item.placeId }">
															<i class="fas fa-heart" id="item-${ item.placeId }" style="color:red" ></i>
														</a>
													</c:otherwise>
												</c:choose> 
											</span>
											<img src="<c:url value="/asset/images/${item.image[0] }" />" alt="" height="330px">
										</div>
										<div class="label1">
											<div class="float-left title-place1">
												<a href="<c:url value="/detailplace/${item.placeId }" />">
													<b>${item.name }</b>
												</a>
											</div>
											<div class="float-right">$ ${item.price } / đêm</div>
										</div>
										<div class="label1">
											<div class="float-left title-place2"><i>${item.address }</i></div>
											<c:if test="${item.startDay != null && item.endDay != null}">
												<c:if test="${item.startDay.split(\"-\")[1] == item.endDay.split(\"-\")[1]}">
													<div class="float-right day-place">
														Ngày ${item.startDay.split("-")[2]} - Ngày ${item.endDay.split("-")[2]} tháng ${item.startDay.split("-")[1]}
													</div>
												</c:if>
												<c:if test="${item.startDay.split(\"-\")[1] != item.endDay.split(\"-\")[1]}">
													<div class="float-right day-place">
														Ngày ${item.startDay.split("-")[2]} / ${item.startDay.split("-")[1]} - Ngày ${item.endDay.split("-")[2]} / ${item.endDay.split("-")[1]}
													</div>
												</c:if>
											</c:if>
										</div>
									</div>
								</td>
								<c:if test="${ (loop.index + 1) % 3 == 0 || (loop.index + 1) == placeList.size() }">
									</tr>
								</c:if>
							</c:forEach> 
						</c:when>
						
						<c:when test="${placeList.size() > 0 }">
							<c:forEach var="item" items="${placeList  }" varStatus="loop">
								<td style="width:33%">
									<div class="iPlace">
										<div class="cha">
											<span class="con1">
												 <c:choose>
													<c:when test="${!Cart.containsKey(item.placeId) }">
														<a data-id="${item.placeId }" >
															<i class="fas fa-heart" id="item-${ item.placeId }" style="color:white"></i>
														</a>
													</c:when>
													<c:otherwise>
														<a data-id="${item.placeId }">
															<i class="fas fa-heart" id="item-${ item.placeId }" style="color:red" ></i>
														</a>
													</c:otherwise>
												</c:choose> 
											</span>
											<img src="<c:url value="/asset/images/${item.image[0] }" />" alt="" height="330px">
										</div>
										<div class="label1">
											<div class="float-left title-place1">
												<a href="<c:url value="/detailplace/${item.placeId }" />">
													<b>${item.name }</b>
												</a>
											</div>
											<div class="float-right">$ ${item.price } / đêm</div>
										</div>
										<div class="label1">
											<div class="float-left title-place2"><i>${item.address }</i></div>
											<c:if test="${item.startDay != null && item.endDay != null}">
												<c:if test="${item.startDay.split(\"-\")[1] == item.endDay.split(\"-\")[1]}">
													<div class="float-right day-place">
														Ngày ${item.startDay.split("-")[2]} - Ngày ${item.endDay.split("-")[2]} tháng ${item.startDay.split("-")[1]}
													</div>
												</c:if>
												<c:if test="${item.startDay.split(\"-\")[1] != item.endDay.split(\"-\")[1]}">
													<div class="float-right day-place">
														Ngày ${item.startDay.split("-")[2]} / ${item.startDay.split("-")[1]} - Ngày ${item.endDay.split("-")[2]} / ${item.endDay.split("-")[1]}
													</div>
												</c:if>
											</c:if>
										</div>
									</div>
								</td>
								<c:if test="${ (loop.index + 1) % 3 == 0 || (loop.index + 1) == placeList.size() }">
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						
						<c:otherwise>
							<h5 style="text-align: center;"><b>Không có kết quả nào được tìm thấy</b></h5>
						</c:otherwise>
					</c:choose>
				
				</table>
			</div>
			<c:if test="${msg == null }">
				<div class="pagination">
			   <a href="#">«</a>
			    <c:forEach begin="1" end="${totalPageNumber }" varStatus="loop">
			   		<a href="<c:url value="/places/pagination/${loop.index }" />">${loop.index }</a>
			   </c:forEach> 
			   <a href="#">»</a>
			 </div>
			</c:if>
		</div>
<!--==============================footer=================================-->
		<footer>
			<div class="container_12">
				<div class="grid_12">
					<div class="socials">
						<a href="#" class="fa fa-facebook"></a>
						<a href="#" class="fa fa-twitter"></a>
						<a href="#" class="fa fa-google-plus"></a>
					</div>
					<div class="copy">
						Your Trip (c) 2014 | <a href="#">Privacy Policy</a> | Website Template Designed by <a href="http://www.templatemonster.com/" rel="nofollow">TemplateMonster.com</a>
					</div>
				</div>
			</div>
		</footer>
		

		<script>
		jQuery('.con1 a').click(function(evt) {
			  evt.preventDefault();

			  var placeId = jQuery(this).attr("data-id");
			  var item = 'item-'+placeId;
			  
			  $.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/AirTrip/AddLovePlace",
					data : {
						placeId : placeId,
					},
					timeout : 2000,
					success : function(data) {		
						console.log("SUCCESS: ");
						if(document.getElementById(item).style.color == "red")
							document.getElementById(item).style.color = "white";
						else document.getElementById(item).style.color = "red";
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			  
			});
		
			
		</script>
	</body>
</html>