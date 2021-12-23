<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | Minh Khai Shop</title>
    <link href="<%=request.getContextPath()%>/asset/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/asset/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/asset/css/prettyPhoto.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/asset/css/price-range.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/asset/css/animate.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/asset/css/main.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/asset/css/responsive.css" rel="stylesheet">
	     
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/asset/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getContextPath()%>/asset/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getContextPath()%>/asset/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getContextPath()%>/asset/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/asset/images/ico/apple-touch-icon-57-precomposed.png">
    
    
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
    <!-- <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
	<!-- <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet" /> -->
	
</head><!--/head-->

<body>
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4 panel panel-default">
					<div class="login-form"><!--login form-->
						<b class="login">ĐĂNG NHẬP</b> <br><br>
						<form action="<%=request.getContextPath()%>/login" method="post" class="has-error">
							<input type="text" placeholder="User name" name="username"/>
							<input type="password" placeholder="Password" name="password"/>
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<%
								String error = request.getParameter("msg");
								if(error != null && error.equals("ERROR")) {
									%>
									<span class="help-block textcenter">Thông tin đăng nhập không đúng!</span>
									<%
								}
							%>
							<button type="submit" class="btn btn-default btnlogin">Login</button>
							<a class="textcenter" href="#">Quên mật khẩu ?</a>
							<hr>
							<a href="<%=request.getContextPath() %>/Register" class="btn btn-default btnregister1 vertical-center">Tạo tài khoản mới</a>
							<br>
						</form>
					</div><!--/login form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
</body>
</html>