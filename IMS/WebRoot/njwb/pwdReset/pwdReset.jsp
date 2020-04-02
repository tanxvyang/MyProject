<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if(session.getAttribute("user") == null){
		request.setAttribute("isError", true);
		request.setAttribute("errorMessage", "请您登录后访问");
		request.getRequestDispatcher("../login.jsp").forward(request, response);
		
	}

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
      <title>密码重置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 500px;
			margin: 20px auto;
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
	<script type="text/javascript">
		function updatePwd(){
			var empid = $("#empid").val();
			var oldPwd = $("#oldPwd").val();
			var newPwd = $("#newPwd").val();
			var surePwd = $("#surePwd").val();
			
			if(oldPwd == ""){
				alert("原密码不能为空");
				return false;
			}
			
			if(newPwd == ""){
				alert("新密码不能为空");
				return false;
			}
			
			if(newPwd.length > 6 ){
				alert("新密码格式错误：超长");
				return false;
			}
			if(!/^\w{1,6}$/.test(newPwd)){
				alert("新密码格式错误：六位以下数字、字母、下划线");
				return false;
			}
			if(newPwd != surePwd){
				alert("两次输入密码不同");
				return false;
			}
			
		}
		
		
	</script>
	
	
  </head>
  
 <body>
   	<form action="user/updatePwd.do"   method="post" onsubmit="return updatePwd()">
   	<input id = "empid" name = "empid" value=${user.empNo } type="hidden">
   	<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			原密码:
   			</td>
   			<td>
   				<input id = "oldPwd" name = "oldPwd"  >
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td>
   			新密码:
   			</td>
   			<td>
   				<input id = "newPwd" name = "newPwd"  type="password" >
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td>
   			确认密码:
   			</td>
   			<td>
   				<input id = "surePwd" name = "surePwd" type="password" >
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
   				<input type = "reset" value="重置"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
   	<c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>