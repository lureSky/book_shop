<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- js -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- top-header and slider -->

<!-- //here ends scrolling icon -->
<script src="js/minicart.min.js"></script>

<!-- main slider-banner -->
<script src="js/skdslider.min.js"></script>
<link href="css/skdslider.css" rel="stylesheet">
	<!-- 自动转画 -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			jQuery('#demo1').skdslider({'delay':5000, 'animationSpeed': 2000,'showNextPrev':true,'showPlayButton':true,'autoSlide':true,'animationType':'fading'});
						
			jQuery('#responsive').change(function(){
			  $('#responsive_wrapper').width(jQuery(this).val());
			});
			
		});
</script>	
<!-- start-smoth-scrolling -->
</head>
	
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<!-- main-slider -->
		<ul id="demo1">
			<li>
				<img src="${pageContext.request.contextPath }/images/photobook1.jpg" alt="" />
				<!--Slider Description example-->
				<!-- <div class="slide-desc">
					<h3>Buy Rice Products Are Now On Line With Us</h3>
				</div> -->
			</li>
			<li>
				<img src="${pageContext.request.contextPath }/images/photobook2.jpg" alt="" />
				  
			</li>
			
			<li>
				<img src="${pageContext.request.contextPath }/images/photobook3.jpg" alt="" />
			</li>
		</ul>
	<!-- //main-slider -->
	<!-- //top-header and slider -->
	<!-- top-brands -->

	<div class="top-brands">
		<div class="container">
		<h2>热销书籍</h2>
			<div class="grid_3 grid_5">
				<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
					<ul id="myTab"class="nav nav-tabs"  role="tablist">
						<li role="presentation" class="active"><a href="#expeditions" id="expeditions-tab" role="tab" data-toggle="tab" aria-controls="expeditions" aria-expanded="true">热销电子书</a></li>
						<li role="presentation"><a href="#tours" role="tab" id="tours-tab" data-toggle="tab" aria-controls="tours">热销计算机图书</a></li>
					</ul>
					
		
					<!-- 列表1 -->
					<div id="myTabContent" class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="expeditions" aria-labelledby="expeditions-tab">
							
							<div class="agile_top_brands_grids">
							
								<c:forEach items="${hotEbookList }" var="hotEbook">
								<div class="col-md-4 top_brand_left">
									<div class="hover14 column">
										<div class="agile_top_brand_left_grid">
											<div class="agile_top_brand_left_grid_pos">
												<img src="images/offer.png" alt=" " class="img-responsive" />
											</div>
											<div class="agile_top_brand_left_grid1">
												<figure>
													<div class="snipcart-item block" >
														<div class="snipcart-thumb">
															<a href="${pageContext.request.contextPath }/ProductAction_searchProductByPname?pname=${hotEbook.pname }"><img src="${pageContext.request.contextPath }/${hotEbook.pimage }" height="200px"/></a>		
															<p>${hotEbook.pname }</p>
															<div class="stars">
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star gray-star" aria-hidden="true"></i>
															</div>
															<h4>￥${hotEbook.shop_price }</h4>
														</div>
														<div class="snipcart-details top_brand_home_details">
															<form action="${pageContext.request.contextPath }/CartAction_addProductToCart" method="post">
																<fieldset>
																	<!-- <input type="hidden" name="cmd" value="_cart" />
																	<input type="hidden" name="add" value="1" />
																	<input type="hidden" name="business" value=" " />
																	<input type="hidden" name="item_name" value="Fortune Sunflower Oil" />
																	<input type="hidden" name="amount" value="20.99" />
																	<input type="hidden" name="discount_amount" value="1.00" />
																	<input type="hidden" name="currency_code" value="USD" />
																	<input type="hidden" name="return" value=" " />
																	<input type="hidden" name="cancel_return" value=" " / -->
																	<input type="hidden" name="pid" value="${hotEbook.pid }">
																	<input type="hidden" name="buyNum" value="1">
																	<input type="submit" name="submit" value="加入购物车" class="button" />
																</fieldset>
															</form>
														</div>
													</div>
												</figure>
											</div>
										</div>
									</div>
									<div class="box" style="height:20px"></div>
								</div>
								</c:forEach>
								<div class="clearfix"> </div>
							</div>
						</div>
						
						
						
						
						<!-- 列表2 -->
						<div role="tabpanel" class="tab-pane fade" id="tours" aria-labelledby="tours-tab">
							
							<div class="agile_top_brands_grids">
							
								<c:forEach items="${hotCbookList }" var="hotCbook">
								<div class="col-md-4 top_brand_left">
									<div class="hover14 column">
										<div class="agile_top_brand_left_grid">
											<div class="agile_top_brand_left_grid_pos">
												<img src="images/offer.png" alt=" " class="img-responsive" />
											</div>
											<div class="agile_top_brand_left_grid1">
												<figure>
													<div class="snipcart-item block" >
														<div class="snipcart-thumb">
															<a href="${pageContext.request.contextPath }/ProductAction_searchProductByPname?pname=${hotCbook.pname }"><img src="${pageContext.request.contextPath }/${hotCbook.pimage }" height="200px"/></a>		
															<p>${hotCbook.pname }</p>
															<div class="stars">
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star blue-star" aria-hidden="true"></i>
																<i class="fa fa-star gray-star" aria-hidden="true"></i>
															</div>
															<h4>￥${hotCbook.shop_price }</h4>
														</div>
														<div class="snipcart-details top_brand_home_details">
															<form action="${pageContext.request.contextPath }/CartAction_addProductToCart" method="post">
																<fieldset>
																	<input type="hidden" name="pid" value="${hotCbook.pid }" />
																	<input type="hidden" name="buyNum" value="1">
																	<input type="submit" name="submit" value="加入购物车" class="button" />
																</fieldset>
															</form>
														</div>
													</div>
												</figure>
											</div>
										</div>
									</div>
									<div class="box" style="height:20px"></div>
								</div>
								</c:forEach>
								
								
								<div class="clearfix"> </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- //top-brands -->
 
				
<!-- new -->
	<div class="newproducts-w3agile">
		<div class="container">
			<h3>新书上市</h3>
				<div class="agile_top_brands_grids">
					<c:forEach items="${newBookList }" var="newBook">
					<div class="col-md-3 top_brand_left-1">
						<div class="hover14 column">
							<div class="agile_top_brand_left_grid">
								<div class="agile_top_brand_left_grid_pos">
									<img src="images/offer.png" alt=" " class="img-responsive">
								</div>
								<div class="agile_top_brand_left_grid1">
									<figure>
										<div class="snipcart-item block">
											<div class="snipcart-thumb">
												<a href="${pageContext.request.contextPath }/ProductAction_searchProductByPname?pname=${newBook.pname }"><img src="${pageContext.request.contextPath }/${newBook.pimage }" height="200px"></a>		
												<p>${newBook.pname }</p>
												<div class="stars">
													<i class="fa fa-star blue-star" aria-hidden="true"></i>
													<i class="fa fa-star blue-star" aria-hidden="true"></i>
													<i class="fa fa-star blue-star" aria-hidden="true"></i>
													<i class="fa fa-star blue-star" aria-hidden="true"></i>
													<i class="fa fa-star gray-star" aria-hidden="true"></i>
												</div>
													<h4>￥${newBook.shop_price }</h4>
											</div>
											<div class="snipcart-details top_brand_home_details">
												<form action="${pageContext.request.contextPath }/CartAction_addProductToCart" method="post">
													<fieldset>
														<input type="hidden" name="pid" value="${newBook.pid }">
														<input type="hidden" name="buyNum" value="1">
														<input type="submit" name="submit" value="加入购物车" class="button">
													</fieldset>
												</form>
											</div>
										</div>
									</figure>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
					
						<div class="clearfix"> </div>
				</div>
		</div>
	</div>
<!-- //footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>