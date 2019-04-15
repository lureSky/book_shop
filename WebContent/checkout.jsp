<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
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
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>

</head>
	
<body>
<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1">
				<li><a href="${pageContext.request.contextPath }/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
				<li class="active">购物车</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<!-- checkout -->
	<c:if test="${cart==null }">
	<div class="checkout" style="text-align:center">
					<img src="${pageContext.request.contextPath }/images/cart-empty.png" />
					<h4><a href="${pageContext.request.contextPath }/index.jsp">返回首页</a></h4>
	</div>
		</c:if>
	<c:if test="${cart!=null }">
	<div class="checkout">
		<div class="container">
			<h2>购物清单</h2>
			<div class="checkout-right">
				<table class="timetable_sub">
					<thead>
						<tr>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>移除商品</th>
						</tr>
					</thead>
					
					<!-- 由于我们的cart是map集合，键是pid，值是cartItems -->
					<c:forEach items="${cart.cartItems }" var="entry">
					<tr class="rem1">
						<td class="invert-image" width="20%"><a href="${pageContext.request.contextPath }/ProductAction_searchProductByPname?pname=${entry.value.product.pname }"><img src="${pageContext.request.contextPath }/${entry.value.product.pimage }" class="img-responsive" /></a></td>
						<td class="invert" width="20%">${entry.value.product.pname }</td>
						<td class="invert" width="10%">￥${entry.value.product.shop_price }</td>
						<td class="invert" width="20%">
							 <div class="quantity"> 
								<div class="quantity-select">                           
									<div class="entry value-minus" onclick="minProduct('${entry.value.product.pid }','${entry.value.buyNums }')">&nbsp;</div>
									<div class="entry value"><span>${entry.value.buyNums }</span></div>
									<div class="entry value-plus" onclick="maxProduct('${entry.value.product.pid }')">&nbsp;</div>
								</div>
							</div>
						</td>
						<td class="invert" width="20%" id="subTotal" >￥${entry.value.subTotal }</td>
						<td class="invert" width="10%">
							<div class="rem">
								<div class="close1" onclick="deleteProduct('${entry.value.product.pid }')"> </div>
							</div>
							
						</td>
					</tr>
					
					</c:forEach>
					
								
				</table>
			</div>
			 <div class="checkout-left">	
				<div class="checkout-left-basket">
					<ul>
						<li>总金额 <i>-</i> <span id="total">￥${cart.total }</span></li>
					</ul>
					
					<!-- order -->
					<a href="${pageContext.request.contextPath }/OrderAction_submitOrder"><h4>提交订单</h4></a>
					
				</div>
				<div class="checkout-right-basket">
					<!-- 清空购物车 -->
					<a href="javascript:void(0);" onclick="clearCart()"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>清空购物车</a>
					<!-- 继续购物 -->
					<a href="${pageContext.request.contextPath }/index.jsp"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>继续购物</a>
				</div>
				<div class="clearfix"> </div>
			</div> 
		</div>
	</div>
	</c:if>
	<script type="text/javascript">
		function deleteProduct(pid){
			if(confirm("是否要删除该项?")){
				location.href="${pageContext.request.contextPath }/CartAction_deleteProductFromCart?pid="+pid;
			}
		}
		function clearCart(){
			if(confirm("是否需要清空购物车？")){
				location.href="${pageContext.request.contextPath }/CartAction_clearCart";
			}
		}
   </script>
<!-- //checkout -->
<!-- //footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--quantity-->
	<script type="text/javascript">
	//按加减号改变数量
	function minProduct(pid,buyNum){
		
		if(buyNum>1){
			var del = -1;
			location.href="${pageContext.request.contextPath }/CartAction_addProductToCart?pid="+pid+"&buyNum="+del;
		}
		
		
	}
	function maxProduct(pid){
		var add = 1;
		location.href="${pageContext.request.contextPath }/CartAction_addProductToCart?pid="+pid+"&buyNum="+add;
	}
	
	</script>
<!--quantity-->
</body>
</html>