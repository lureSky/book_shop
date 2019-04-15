<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html class="x-admin-sm">
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.1</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/font.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xadmin.css">
    <script src="${pageContext.request.contextPath }/lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>	
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/xadmin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/cookie.js"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>会员列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="${pageContext.request.contextPath }/AdminAction_searchUserByUserName" method="post" onsubmit="return check();">
          <input type="text" name="username"  id="search" placeholder="请输入用户名" autocomplete="off" class="layui-input" onkeyup="searchWord(this)">
          <div id="showDiv" style="display:none;position:absolute;width:230px;background-color:#fff;border:1px solid #ccc">
							
		</div>
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
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
					"${pageContext.request.contextPath}/AdminAction_searchUserList",
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
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','${pageContext.request.contextPath }/admin/member-edit.jsp',600,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageBean.totalCount } 条</span>
      </xblock>
      <table class="layui-table x-admin">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>用户名</th>
            <th>姓名</th>
            <th>邮箱</th>
            <th>手机</th>
            <th>生日时间</th>
            <th>状态</th>
            <th>操作</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${pageBean.list }" var="user">
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${user.uid }</td>
            <td>${user.username }</td>
            <td>${user.name }</td>
            <td>${user.email }</td>
            <td>${user.telephone }</td>
            <td>${user.birthday }</td>
            <td class="td-status">
              <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td>
            <td class="td-manage">
              <a title="编辑"  onclick="x_admin_show('编辑','member-edit.jsp?uid=${user.uid }',600,400)" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,'${user.uid}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
     <!--分页 -->
		<div class="page">
		
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
					<a href="${pageContext.request.contextPath }/AdminAction_getUserList?currentPage=${pageBean.currentPage-1 }" aria-label="Previous">
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
						<li><a href="${pageContext.request.contextPath }/AdminAction_getUserList?currentPage=${page }">${page }</a></li>
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
					<a href="${pageContext.request.contextPath }/AdminAction_getUserList?currentPage=${pageBean.currentPage+1 }" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li> 
				</c:if>
				
				
			</ul>
		</div>
		<!-- 分页结束 -->

    </div>
    <script>

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              location.href="${pageContext.request.contextPath }/AdminAction_delUserByUid?uid="+id;
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
    
  </body>

</html>