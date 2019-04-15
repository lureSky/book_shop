<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>订单</title>
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
				<li class="active">订单</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<!-- checkout -->
	
	<div class="checkout">
		<div class="container">
			<h2>订单详情</h2>
			<div class="checkout-right">
				<table class="timetable_sub">
					<thead>
						<tr class="warning">
							<th colspan="5">订单编号：${order.oid }</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品名称</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
					</thead>
					
					<!-- 由于我们的cart是map集合，键是pid，值是cartItems -->
					<c:forEach items="${order.orderItems }" var="orderItem">
					<tr class="rem1">
						<td class="invert-image" width="20%"><img src="${pageContext.request.contextPath }/${orderItem.product.pimage }" height="100px" /></td>
						<td class="invert" width="20%">${orderItem.product.pname }</td>
						<td class="invert" width="20%">￥${orderItem.product.shop_price }</td>
						<td class="invert" width="20%">
								<div><span>${orderItem.count }</span></div>
						</td>
						<td class="invert" width="20%" id="subTotal" >￥${orderItem.subTotal }</td>
						
					</tr>
					
					</c:forEach>
					
								
				</table>
			</div>
			<div style="text-align: right; margin-right: 50px;margin-top: 50px;">
				
				<span style="font-size:22px">商品金额: <strong style="color: #ff6600;">￥:${order.total }</strong></span>
			</div>
			
		</div>
		<div style="margin-top: 5px; margin-left: 200px;">
			<hr />
			<form id="orderForm" class="form-horizontal" style="margin-top: 5px; margin-left: 150px;" 
					action="${pageContext.request.contextPath }/OrderAction_confirmOrder" method="post">
				<input type="hidden" name="oid" value="${order.oid }">
				<div class="form-group">
					<label for="address" class="col-sm-1 control-label">地址</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="address" name="address" placeholder="请输入收货地址">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="name" name="name" value="${user.name }" placeholder="请输入收货人姓名">
					</div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-1 control-label">电话</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="telephone" name="telephone"
							placeholder="请输入联系方式" value="${user.telephone }">
					</div>
				</div>
			

				<hr />

				<div style="margin-top: 5px; margin-left: 50px;">
					
					<p style="text-align: right; margin-right:400px">
						<a href="javascript:void(0);" onclick="confirmOrder()">
							<img src="./images/finalbutton.gif" width="204" height="51"
							border="0" />
						</a>
					</p>
					<hr />
	
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	function confirmOrder(){
		//利用js提交表单
		if(confirm("确认提交订单！")){
			$("#orderForm").submit();
		}
	}
</script>

	<!-- //footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>