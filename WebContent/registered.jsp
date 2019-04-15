<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册</title>
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
<style type="text/css">
	.error{
		color:red;
	}
</style>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- top-header and slider -->
<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			*/
								
			$().UItoTop({ easingType: 'easeOutQuart' });
								
			});
	</script>
<!-- //here ends scrolling icon -->
<script src="js/minicart.min.js"></script>
<script>
	// Mini Cart
	paypal.minicart.render({
		action: '#'
	});

	if (~window.location.search.indexOf('reset=true')) {
		paypal.minicart.reset();
	}
</script>
<!-- main slider-banner -->
<script src="js/skdslider.min.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<link href="css/skdslider.css" rel="stylesheet">
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
<!-- 表单校验jq代码 -->
<script type="text/javascript">
	<!-- 用户ajax校验也属于校验的一种，所以我们要自定义校验方法，对于username -->
	$.validator.addMethod(
			//1.规则名称
			"checkEmail",
			//2.函数
			function(value,element,params){
				
				var flag = false;
				
				//value是输入的内容
				//element是输入的元素节点，这里是input
				//params是 规则对应的参数值     eg：required:true    冒号后的值
				//对输入的value进行ajax校验
				
				//这里赋值没附上的真正原因是线程原因，没附上值直接返回了
				$.ajax({
					"async":false,//这里改为同步即可，不要异步访问
					"url":"${pageContext.request.contextPath }/UserAction_checkEmail",
					"data":{"email":value}, 
					"type":"POST",
					"dataType":"json",
					"success":function(data){
						flag = data;	//赋值没赋上
					}
					
				}); 
				//能否通过，看方法的返回值  false 则不通过
				return !flag;
			}
	);
	$(function(){
		$("#register").validate({

			rules:{
				"username":{
					"required":true,
					"rangelength":[6,12]
				},
				"password":{
					"required":true,
					"rangelength":[6,12]
				},
				"repassword":{
					"required":true,
					"rangelength":[6,12],
					"equalTo":"#password"
				},
				"email":{
					"required":true,
					"email":true,
					"checkEmail":true
				},
				"name":{
					"required":true,
					"minlength":2
				},
				"telephone":{
					"required":true,
					"minlength":11
				},
				"sex":{
					"required":true
				},
				"birthday":{
					"required":true
				}
				
			},
			messages:{
				"username":{
					"required":"用户名不能为空",
					"rangelength":"用户名长度为6-12位"
				},
				"password":{
					"required":"密码不能为空",
					"rangelength":"密码长度为6-12位"
				},
				"repassword":{
					"required":"密码不能为空",
					"rangelength":"密码长度为6-12位",
					"equalTo":"两次密码不一致"
				},
				"email":{
					"required":"邮箱不能为空",
					"email":"邮箱格式不正确",
					"checkEmail":"邮箱已存在"
				},
				"name":{
					"required":"姓名不能为空!",
					"minlength":"姓名不得少于2个字符!"

				},
				"telephone":{
					"required":"联系电话不能为空!",
					"minlength":"格式不正确"

				},
				"birthday":{
					"required":"必须选择出生日期"
				}
				
			}
			
		});
		
	});
	
</script>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
				<li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
				<li class="active">注册页面</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<!-- register -->
	<div class="register">
		<div class="container">
			<h2>会员注册</h2>
			<div class="login-form-grids">
					<form class="form-horizontal" id="register" style="margin-top: 5px;" action="${pageContext.request.contextPath }/UserAction_register" method="post">
					<div class="form-group">
						<label for="username" class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-8">
						<!-- 这里需要ajax进行异步请求 -->
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="password" name="password"
								placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
					<label for="confirmpwd" class="col-sm-4 control-label">确认密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="confirmpwd" name="repassword"
								placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
					<label for="email" class="col-sm-4 control-label">电子邮件</label>
						<div class="col-sm-8">
							<input type="email" class="form-control" id="inputEmail3" name="email"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
					<label for="name" class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="usercaption" name="name"
								placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group">
					<label for="name" class="col-sm-4 control-label">联系电话</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="telephone" name="telephone"
								placeholder="请输入联系电话">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-4 control-label">性别</label>
						<div class="col-sm-8">
							<label class="radio-inline"> 
								<input type="radio" name="sex" id="sex1" value="male">男
							</label>
							 <label class="radio-inline">
							 	<input type="radio" name="sex" id="sex2" value="female">女
							</label>
							<!-- 以免错误信息乱码 -->
							<label class="error" for="sex" style="display: none">您没有第三种选择</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-4 control-label">生日</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" name="birthday">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>
			<div class="register-home">
				<a href="index.jsp">主页</a>
			</div>
		</div>
	</div>
<!-- //register -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>