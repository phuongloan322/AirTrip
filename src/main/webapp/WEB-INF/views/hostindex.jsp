<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<title>Become-a-host</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>   
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>	
	<link rel="stylesheet" href="<c:url value="/asset/carousel/css/owl.carousel.min.css" />">
    <link rel="stylesheet" href="<c:url value="/asset/carousel/css/owl.theme.default.min.css" />">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.5.6/css/ionicons.min.css">
	<link rel="stylesheet" href="<c:url value="/asset/carousel/css/style.css" />">
	<link rel="stylesheet" href="<c:url value="/asset/carousel/css/main.css" />">
	
 	
  </head>
  <body>
		<section class="">
			<div class="">
				<div class="row">
					<div class="col-md-12">
						<form action="<c:url value="/become-a-host/addNew" />" method="post" id="myform" enctype="multipart/form-data">	
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img order-md-last"
											style="background-image: url(asset/carousel/images/work-1.jpg);"></div>
										<div
											class="text text-left p-4 px-xl-5 d-flex align-items-center">
											<div class="desc w-100">
												<h2>Trở thành Chủ nhà sau 10 bước dễ dàng</h2>
												<p4>Hãy tham gia cùng chúng tôi. Chúng tôi sẽ trợ giúp
												bạn qua từng bước của quy trình.</p4>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img"
											style="background-image: url(asset/images/i122.jpeg);"></div>
										<div
											class="text text-left p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<h2 class="mb-4">Bạn sẽ cho thuê loại chỗ nào?</h2>
												<br>
												<div class="category">
													<c:forEach var="item" items="${categoryList }">
														<input type="radio" name="category"
															value="${item.categoryId }">
														<label>${item.name }</label>
														<br>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img order-md-last"
											style="background-image: url(asset/images/anh1.jpg);"></div>
										<div
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<h2 class="mb-4">Điều nào sau đây mô tả chính xác nhất về
													nơi ở của bạn?</h2>
												<br>
												<div class="category">
													<c:forEach var="item" items="${litleList }">
														<div class="group-form">
															<input type="radio" name="litleCategory"
																value="${item.litleCategoryId }"> <label>${item.litleName }</label><br>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd"
											style="background-image: linear-gradient(to bottom right, purple, pink);">
											<h2 class="address">Chỗ ở của bạn nằm ở đâu?</h2>
										</div>
										<div class="text text-left d-flex align-items-center setaddcha">
											<iframe
												src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d61222.99452486902!2d107.54209360402065!3d16.453387542910498!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3141a115e1a7935f%3A0xbf3b50af70b5c7b7!2zVHAuIEh14bq_LCBUaOG7q2EgVGhpw6puIEh14bq_LCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1638974681583!5m2!1svi!2s"
												width="100%" height="100%" style="border: 0;"
												allowfullscreen="" loading="lazy"></iframe>
											<input class="setaddress" type="text" name="address"
												placeholder="Nhập địa chỉ của bạn..." />
										</div>
									</div>
								</div>
		
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd order-md-last"
											style="background-image: linear-gradient(to top right, blue, pink);">
											<h2 class="address">Bạn muốn chào đón bao nhiêu khách?</h2>
										</div>
										<div
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<table class="bnkhach">
													<tr>
														<td><b>Khách </b></td>
														<td><input type="number" name="khach" value="1"
															placeholder="Vui lòng nhập số khách" /></td>
													</tr>
													<tr>
														<td><b>Phòng ngủ </b></td>
														<td><input type="number" name="phongngu" value="1"
															placeholder="Vui lòng nhập số khách" /></td>
													</tr>
													<tr>
														<td><b>Giường </b></td>
														<td><input type="number" name="giuong" value="1"
															placeholder="Vui lòng nhập số khách" /></td>
													</tr>
													<tr>
														<td><b>Phòng vệ sinh </b></td>
														<td><input type="number" name="phongvs" value="1"
															placeholder="Vui lòng nhập số khách" /></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd"
											style="background-image: url(asset/images/anh5.jpg);">
											<h2 class="address">Cho khách biết chỗ ở của bạn có những
												gì?</h2>
										</div>
										<div
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="">
												<div>
													<h4>Bạn có tiện nghi nào nổi bật không?</h4>
													<br>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Bể bơi">
														<label for="tiennghi"> Bể bơi</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Bồn tắm nước nóng">
														<label for="tiennghi"> Bồn tắm nước nóng</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Lò sưởng trong nhà">
														<label for="tiennghi"> Lò sưởng trong nhà</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Thiết bị tập thể dục">
														<label for="tiennghi"> Thiết bị tập thể dục</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Bếp đốt lửa trại">
														<label for="tiennghi"> Bếp đốt lửa trại</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Khu vực ăn uống ngoài trời">
														<label for="tiennghi"> Khu vực ăn uống ngoài trời</label><br>
													</div>
												</div>
												<div>
													<h4 class="asa">Còn những tiện nghi yêu thích của khách
														sau đây thì sao?</h4>
													<br>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Wi-fi">
														<label for="tiennghi"> Wi-fi</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="TV">
														<label for="tiennghi"> TV</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Bếp">
														<label for="tiennghi"> Bếp</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Máy giặt">
														<label for="tiennghi"> Máy giặt</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Điều hòa nhiệt độ">
														<label for="tiennghi"> Điều hòa nhiệt độ</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Không gian riêng để làm việc">
														<label for="tiennghi"> Không gian riêng để làm việc</label><br>
													</div>
												</div>
												<div>
													<h4 class="asa">Bạn có tiện nghi nào trong số những tiện
														nghi đảm bảo an toàn này không?</h4>
													<br>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Bộ sơ cứu">
														<label for="tiennghi"> Bộ sơ cứu</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Bình chữa cháy">
														<label for="tiennghi"> Bình chữa cháy</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Máy báo khói">
														<label for="tiennghi"> Máy báo khói</label><br>
													</div>
													<div class="group-form f20">
														<input type="checkbox" name="tiennghi" value="Khóa cửa phòng ngủ">
														<label for="tiennghi"> Khóa cửa phòng ngủ</label><br>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd "
											style="background-image: linear-gradient(to top right, blue, pink);">
											<h2 class="address">Tiếp theo, hãy thêm 5 ảnh chụp nổi bật
												chỗ ở của bạn</h2>
										</div>
										<div style="background-image: url(asset/images/anh2.jpg);"
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<img id="imgSrc1" style="display:none"  width="200px" height="200px" src="<c:url value="" />" /> 
												<img id="imgSrc2" style="display:none"  width="200px" height="200px" src="<c:url value="" />" />
												<img id="imgSrc3" style="display:none"  width="200px" height="200px" src="<c:url value="" />" />
												<img id="imgSrc4" style="display:none"  width="200px" height="200px" src="<c:url value="" />" />
												<img id="imgSrc5" style="display:none"  width="200px" height="200px" src="<c:url value="" />" />
												<img id="imgSrc6" style="display:none"  width="200px" height="200px" src="<c:url value="" />" />
											
												<input type="file" name="fileimage" id="imageFile"
														onchange="chooseFile(this)"
														 accept="image/*"  multiple="multiple" max="5" min="1">
												
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd order-md-last" style="background-image: url(asset/images/i123.jpeg);">
											<h2 class="address">Hãy đặt tên cho chỗ ở của bạn</h2>
										</div>
										<div
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<h3>
													<b>Tạo tiêu đề</b>
												</h3>
												<br>
												<textarea name="tieude" rows="3" cols="27" class="ftd" maxlength="100"></textarea>
											</div>
										</div>
									</div>
								</div>	
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd"
											style="background-image: url(asset/images/i121.jpg);">
											<h2 class="address">Bây giờ, hãy mô tả chỗ ở của bạn</h2>
										</div>
										<div
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<h3>
													<b>Tạo phần mô tả</b>
												</h3>
												<br>
												<textarea name="detail" rows="4" cols="60" class="ftd" maxlength="500"
													placeholder="Bạn sẽ rất thích thời gian ở tại địa điểm nghỉ dưỡng tươi tắn này."></textarea>
												
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img chaadd"
											style="background-image: linear-gradient(to top right, violet, orange);">
											<h2 class="address">Bây giờ đến phần thú vị rồi đặt giá và ngày
												cho thuê nào</h2>
										</div>
										<div style="background-image: url(asset/images/anh2.jpg);"
											class="text text-left  p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5" style="margin: 0 auto">
												<h3>
													<b style="color: #fff">Hãy nhập giá phù hợp với chỗ ở của bạn</b>
												</h3>
												<br>
												<div class="buttons_added" style="text-align: center;">
													<input class="minus is-form" type="button" value="-">
													<input  class="input-qty" max="10000000"
														min="0" name="price" type="number" value="1">
													<input class="plus is-form" type="button" value="+">
												</div>
												<br>
												<table class="startdate">
													<tr>
														<td class="ngay"><b style="color: #fff">Ngày bắt đầu </b></td>
														<td><input type="date" name="startDay" 
															placeholder="Chọn ngày bắt đầu cho thuế" /></td>
													</tr>
													<tr>
														<td class="ngay "><b style="color: #fff">Ngày kết thúc </b></td>
														<td><input type="date" name="endDay" 
															placeholder="Chọn ngày kết thúc cho thuế" /></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="work-wrap d-md-flex">
										<div class="img"
											style="background-image: url(asset/images/anh4.jpg);"></div>
										<div
											class="text text-left p-4 px-xl-5 d-flex align-items-center">
											<div class="py-md-5">
												<h3>
													<b>Chào mừng bạn!</b>
												</h3>
												<h3>
													<b>Chỉ cần nhấn xác nhận là hoàn thành mục cho thuê mới</b>
												</h3>
												<button class="finish">Hoàn thành</button>
											</div>
										</div>
									</div>
								</div>	
								
							<!-- <div class="featured-carousel owl-carousel">	 -->
							<%--
								
								
								 --%>
									
							<!-- </div> -->
						 </form>			 	
					</div>
				</div>
			</div>
		</section>
	<script>
	  /*   function submitForm(){
	        document.getElementById('myform').submit();
	    } */
	</script>
	
	<script type="text/javascript">
	 function readURL(input) {
		  if (input.files && input.files[0]) {
		      var reader = new FileReader();
		      reader.onload = function(e) {
		             $('#imgSrc1').attr('src', e.target.result);
		      }
		      reader.readAsDataURL(input.files[0]); 
		 }
		  if (input.files && input.files[1]) {
		      var reader = new FileReader();
		      reader.onload = function(e) {
		             $('#imgSrc2').attr('src', e.target.result);
		      }
		      reader.readAsDataURL(input.files[1]); 
		 }
		  if (input.files && input.files[2]) {
		      var reader = new FileReader();
		      reader.onload = function(e) {
		             $('#imgSrc3').attr('src', e.target.result);
		      }
		      reader.readAsDataURL(input.files[2]); 
		 }
		  if (input.files && input.files[0]) {
		      var reader = new FileReader();
		      reader.onload = function(e) {
		             $('#imgSrc4').attr('src', e.target.result);
		      }
		      reader.readAsDataURL(input.files[3]); 
		 }
		  if (input.files && input.files[4]) {
		      var reader = new FileReader();
		      reader.onload = function(e) {
		             $('#imgSrc5').attr('src', e.target.result);
		      }
		      reader.readAsDataURL(input.files[4]); 
		 }
		  
	}
	$("#imageFile").change(function() {
		 readURL(this); 
		 $("#imgSrc1").show();
		 $("#imgSrc2").show();
		 $("#imgSrc3").show();
		 $("#imgSrc4").show();
		 $("#imgSrc5").show();
		 
	});
	</script>	
	
	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
			integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
			crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		function addNew() {
			const formData = new FormData();
			
			formData.append("litleCategory", $("input[name='litleCategory']").val());
			formData.append("address", $("input[name='address']").val());
			formData.append("phongngu", $("input[name='phongngu']").val());
			formData.append("khach", $("input[name='khach']").val());
			formData.append("giuong", $("input[name='giuong']").val());
			formData.append("phongvs", $("input[name='phongvs']").val());
			formData.append("tiennghi", $("input[name='tiennghi']").val());
			formData.append("tieude", $("textarea[name='tieude']").val());
			formData.append("detail", $("textarea[name='detail']").val());
			formData.append("price", $("input[name='price']").val());
			formData.append("startDay", $("input[name='startDay']").val());
			formData.append("endDay", $("input[name='endDay']").val());
			
			jQuery.each(jQuery("input[name='fileimage']")[0].files, function(i, file) {
			    formData.append('file-'+i, file);
			});
			for (var value of formData.values()) {
				   console.log(value);
			}
				$.ajax({
					type : "POST",
					enctype: 'multipart/form-data',
					cache: false,
				    contentType: false,
				    processData: false,
					url : "/AirTrip/become-a-host/add-new",
					data : formData,
					success : function(data) {
						console.log("SUCCESS: ");
						
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
		}
	</script>
	
	<script type="text/javascript">
	  $(document).ready(function(){
		  $('#goAdd').on('click', function() {
				var href = $(this).attr('href');
				jQuery.noConflict();
				$('#myform').attr('action', href);
			});
	  });
	  </script>
    <script src="asset/carousel/js/popper.js"></script>
    <script src="asset/carousel/js/bootstrap.min.js"></script>
    <script>//<![CDATA[
		$('input.input-qty').each(function() {
		  var $this = $(this),
		    qty = $this.parent().find('.is-form'),
		    min = Number($this.attr('min')),
		    max = Number($this.attr('max'))
		  if (min == 0) {
		    var d = 0
		  } else d = min
		  $(qty).on('click', function() {
		    if ($(this).hasClass('minus')) {
		      if (d > min) d += -50
		    } else if ($(this).hasClass('plus')) {
		      var x = Number($this.val()) + 1
		      if (x <= max) d += 50
		    }
		    $this.attr('value', d).val(d)
		  })
		})
		//]]></script>

  </body>
</html>