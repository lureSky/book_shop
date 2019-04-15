<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="s" uri="/struts-tags" %>
   <%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<title>订单列表</title>
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
				<li class="active">订单列表</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<!-- checkout -->
	
	<div class="checkout">
		<div class="container">
			<h2>订单列表</h2>
			<div class="checkout-right">
				<table class="timetable_sub">
					<thead>
						
						<tr>
							<th>订单编号</th>
							<th>订单日期</th>
							<th>总金额</th>
							<th>收货人</th>
							<th>收货地址</th>
							<th>联系电话</th>
						</tr>
					</thead>
					<tbody>
					<!-- 由于我们的cart是map集合，键是pid，值是cartItems -->
					<s:iterator value="#AllOrderList" var="order">
					<tr class="rem1">
						<td class="invert" width="20%"><s:property value="#order.oid"/></td>
						<td class="invert" width="20%"><s:date name="#order.orderTime" format="yyyy年MM月dd日" /></td>
						<td class="invert" width="15%" id="subTotal" >￥<s:property value="#order.total"/></td>
						<td class="invert" width="10%"><s:property value="#order.name"/></td>
						<td class="invert" width="20%"><s:property value="#order.address"/></td>
						<td class="invert" width="15%"><s:property value="#order.telephone"/></td>
						
					</tr>
					
					</s:iterator>
					
					</tbody>			
				</table>
			</div>
			<div style="text-align: right; margin-right: 50px;margin-top: 50px;">
				
				<span style="font-size:22px"><strong style="color: #ff6600;">
				<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a>
				</strong></span>
			</div>
			
		</div>
		
	</div>
	

	<!-- //footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>