<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>忘记密码</title>
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
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
</head>
	
<body>
	<jsp:include page="header.jsp"></jsp:include>

<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
				<li><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
				<li class="active">忘记密码</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<!-- login -->
	<div class="login">
		<div class="container">
			<c:if test="${forgotUser==null }">
			<h2>请填写基本信息</h2>
			</c:if>
			<c:if test="${forgotUser!=null }">
			<h2>请填新密码</h2>
			</c:if>
		
			<div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">
				<c:if test="${forgotUser==null }">
				<form action="${pageContext.request.contextPath }/UserAction_forgetPassword" method="post">
					<input type="email" placeholder="邮箱地址" required=" " id="email" name="email">
					<input type="text" placeholder="验证码" id="checkCode" name="checkCode">
					<input type="submit" value="提交">
				</form>
				</c:if>
				<c:if test="${forgotUser!=null }">
				<form action="${pageContext.request.contextPath }/UserAction_updatePassword" id="rePass" method="post">
					<input type="hidden" value="${forgotUser.email }" name="email">
					<input type="password" placeholder="请输入新密码" required=" " id="password" name="password">
					<input type="password" placeholder="确认密码" id="repassword" name="repassword">
					<input type="submit" value="提交">
				</form>
				<script type="text/javascript">
				$(function(){
					$("#rePass").validate({

					rules:{
						"password":{
							"required":true,
							"rangelength":[6,12]
						},
						"repassword":{
							"required":true,
							"rangelength":[6,12],
							"equalTo":"#password"
						}
					},
					messages:{
		
						"password":{
							"required":"密码不能为空",
							"rangelength":"密码长度为6-12位"
						},
						"repassword":{
							"required":"密码不能为空",
							"rangelength":"密码长度为6-12位",
							"equalTo":"两次密码不一致"
						}
					}
					});
				});
				</script>
				</c:if>
				
			</div>
		</div>
	</div>
<!-- //login -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>