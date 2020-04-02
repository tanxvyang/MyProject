<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
      <title>角色名修改</title>
    
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
		function updateAccount(){
			var onlyId = $("#onlyId").val();
			var roleName = $("#roleName").val();
			if(roleName == ""){
				alert("角色名字不能为空");
				return false;
			}
			if(roleName.length > 6 ){
				alert("角色名格式错误：超长");
				return false;
			}
			if(/^\w{1,6}$/.test(roleName)){
				alert("角色名格式错误：六位以下中文");
				return false;
			}
			return true;
		}
		
	</script>
  </head>
  
 <body>
 <%
 	String str = request.getParameter("empid");
 	request.setAttribute("empid",str);
 %>
   	<form action="role/updateRole.do"   method="post" onsubmit="return updateRole()">
   	<input id = "empid" name = "empid" value=${empid } type="hidden" >
   	<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			角色名:
   			</td>
   			<td>
   				<input id = "roleName" name = "roleName"  >
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
   				<input type = "reset" value="重置"/>
				<a href="<%=basePath%>njwb/role/role.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   		<input id = "onlyId" name = "onlyId" value=${empid } type="hidden">
   	</form>
   	<c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>