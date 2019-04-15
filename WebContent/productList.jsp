<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>商品列表</title>
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
<script src="js/bootstrap.min.js"></script>
<!-- //js -->
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<style type="text/css">
	.box{
	height:20px;}
</style>

<!-- start-smoth-scrolling -->
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
				<li><a href="${pageContext.request.contextPath }/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
				<li class="active">${cname }</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<!--- beverages --->
	<div class="products">
		<div class="container" style="margin-top:-50px">
			
			<div class="col-md-12 products-right">
				
				<div class="agile_top_brands_grids">
				<c:forEach items="${pageBean.list }" var="product" varStatus="status" step="1">
					<div class="col-md-4 top_brand_left">
						<div class="hover14 column">
							<div class="agile_top_brand_left_grid">
							
								<div class="agile_top_brand_left_grid_pos">
									<img src="images/offer.png" alt=" " class="img-responsive">
								</div>
								
								<div class="agile_top_brand_left_grid1">
									<figure>
										<div class="snipcart-item block">
											<div class="snipcart-thumb">
												<a href="${pageContext.request.contextPath }/ProductAction_searchProductByPname?pname=${product.pname }"><img src="${pageContext.request.contextPath }/${product.pimage }" height="200px"></a>		
												<p>${product.pname }</p>
												<h4>￥${product.shop_price }</h4>
											</div>
											<div class="snipcart-details top_brand_home_details">
												<form action="${pageContext.request.contextPath }/CartAction_addProductToCart" method="post">
													<fieldset>
											
														<input type="hidden" name="pid" value="${product.pid }">
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
						<div class="box"></div>
						<div class="clearfix"></div>
					</div>
					
					</c:forEach>
					
				
				<!--分页 -->
				<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
					<ul class="pagination" style="text-align: center; margin-top: 10px;">
						<!-- 上一页 -->
						<c:if test="${pageBean.currentPage==1 }">
							<li class="disabled">
								<a href="javascript:void(0);" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:if test="${pageBean.currentPage!=1 }">
						<li>
							<a href="${pageContext.request.contextPath }/ProductAction_getList?cid=${cid }&currentPage=${pageBean.currentPage-1 }" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
						</c:if>
						
						<!-- 显示每一页 -->
						
						<c:forEach begin="1" end="${pageBean.totalPage }" var="page">
							<!-- 要每次点击的时候判断是否是当前页,是就选中状态，不是就可以点击 -->
							
							<c:if test="${page==pageBean.currentPage }">
								<!-- 当前页已经选中，不能在进行点击操作 -->
								<li class="active"><a href="javascript:void(0);">${page }</a></li>
							</c:if>
							<!-- 不是当前页时可以点击 -->
							<c:if test="${page!=pageBean.currentPage }">
								<!-- 当前页已经选中，不能在进行点击操作 -->
								<li><a href="${pageContext.request.contextPath }/ProductAction_getList?cid=${cid }&currentPage=${page }">${page }</a></li>
							</c:if>
						</c:forEach>
						<!-- 下一页 -->
						<c:if test="${pageBean.currentPage==pageBean.totalPage }">
						<li class="disabled">
							<a href="javascript:void(0);" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li> 
						</c:if>
						<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
						<li>
							<a href="${pageContext.request.contextPath }/ProductAction_getList?cid=${cid }&currentPage=${pageBean.currentPage+1 }" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li> 
						</c:if>
						
						
					</ul>
				</div>
				<!-- 分页结束 -->
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
<!--- beverages --->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>