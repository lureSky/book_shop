<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- header -->
	<div class="agileits_header">
		<div class="container">
			<div class="w3l_offers">
				<p>热门商品降价20％ . <a href="products.html">快来购物吧</a></p>
			</div>
			<div class="agile-login">
				<ul>
					<s:if test="#session.user==null">
					<li><a href="registered.jsp">注册</a></li>
					<li><a href="login.jsp">登录</a></li>
					</s:if>
					<s:if test="#session.user!=null">
					<li style="color:white"><s:property value="#session.user.username"/></li>
					<li><a href="${pageContext.request.contextPath }/UserAction_logout">注销</a></li>
					</s:if>
					<li><a href="contact.jsp">联系</a></li>
					
				</ul>
			</div>
			<div class="product_list_header">  
					<!-- 到购物车连接 -->
					<a href="${pageContext.request.contextPath }/checkout.jsp"> 
						<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>
					</a>  
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>

	<div class="logo_products">
		<div class="container">
		<div class="w3ls_logo_products_left1">
				<ul class="phone_email">
					<li><i class="fa fa-phone" aria-hidden="true"></i>联系方式：(+0123) 234 567</li>
					
				</ul>
			</div>
			<div class="w3ls_logo_products_left">
				<h1><a href="index.jsp">图书商城</a></h1>
			</div>
		<div class="w3l_search" style="width:300px;">
			<%-- <form action="${pageContext.request.contextPath }/ProductAction_SearchByPname " method="post">
				<input type="search" name="pname" placeholder="查找图书" onkeyup="searchWord(this)" id="search">
				
				<!-- ajax展示，获得异步查询结果 -->
				<div id="showDiv" style="display:none;position:absolute;width:290px;background-color:#fff;border:1px solid #ccc">
							
				</div>
				
				
				<div class="clearfix"></div>
			</form> --%>
			<form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath }/ProductAction_searchProductByPname" method="post" onsubmit="return check();">
					<div class="form-group" style="position:relative">
						<input type="text" class="form-control" placeholder="搜索图书" onkeyup="searchWord(this)" id="search" name="pname">
					</div>
					<div id="showDiv" style="display:none;position:absolute;width:230px;background-color:#fff;border:1px solid #ccc">
							
					</div>
					<button type="submit" class="btn btn-info">搜索</button>
				</form>
		</div>
		<!-- 利用ajax进行查询 -->
		<script type="text/javascript">
		
			//防止空提交
			function check(){
				if($("#search").val()==""){
					return false;
				}else{
					return true;
				}
			}
		
			function overFn(obj){
				$(obj).css("background","#DBEAF9");
			}
			function outFn(obj){
				$(obj).css("background","#fff");
			}
			
			function clickFn(obj){
				$("#search").val($(obj).html());
				$("#showDiv").css("display","none");
			}
			
		
			function searchWord(obj){
				//1、获得输入框的输入的内容
				var word = $(obj).val();
				//2、根据输入框的内容去数据库中模糊查询---List<Product>
				var content = "";
				$.post(
					"${pageContext.request.contextPath}/ProductAction_searchList",
					{"word":word},
					function(data){
						
						if(data.length>0){
							for(var i=0;i<data.length;i++){
								content+="<div style='padding:5px;font-size:10px;cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"+data[i]+"</div>";
							}
							$("#showDiv").html(content);
							$("#showDiv").css("display","block");
						}
						
					},
					"json"
				);
				
			}
		</script>
		
			<div class="clearfix"> </div>
		</div>
	</div>
<!-- //header -->
<!-- navigation -->
	<!-- <div class="test" style="margin-top:300px;"></div> -->
	<div class="navigation-agileits">
		<div class="container">
			<nav class="navbar navbar-default">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header nav_2">
								<button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
							</div> 
							<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
								<ul class="nav navbar-nav" id="categoryUl">
									
					
								</ul>
							</div>
							</nav>
			</div>
		</div>
		
<!-- //navigation -->
<!-- 利用ajax技术获得导航栏 -->
<script type="text/javascript">
//这种类别一般放入后不需要修改，所以我们可以放入redis中
//header.jsp加载完毕后 去服务器端获得所有的category数据
$(function(){
	var content = "";
	$.post(
		"${pageContext.request.contextPath}/NavAction_getCategory",
		function(data){
			//[{"cid":"xxx","cname":"xxxx"},{},{}]
			//动态创建<li><a href="#">${category.cname }</a></li>
			for(var i=0;i<data.length;i++){
				if(i==0){
					content+="<li style='width:100px;text-align:center;'><a href='${pageContext.request.contextPath}/index.jsp'>"+data[i].cname+"</a></li>";
				}else if(i==data.length-1){
					content+="<li style='width:100px;text-align:center;'><a href='${pageContext.request.contextPath}/contact.jsp'>"+data[i].cname+"</a></li>";
				}else{
				content+="<li style='width:100px;text-align:center;'><a href='${pageContext.request.contextPath}/ProductAction_getList?cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
				}
			}
			
			//将拼接好的li放置到ul中
			$("#categoryUl").html(content);
		},
		"json"
		);
	});
</script>