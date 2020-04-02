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
    
    <title>添加角色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
	<script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>
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
			width: 350px;
			margin: 20px auto;
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
	<script type="text/javascript">
		function addRole(){
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
	<form action="role/addRole.do"   method="post" onsubmit="return addRole()">   	
   		<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			角色名:
   			</td>
   			<td>
   				<input id="roleName" name="roleName" type ="text"/>
   			</td>
   		</tr>

   		<tr class="titleRow">
   			<td colspan="2">
   				<input type= "submit" value="添加" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/role/role.jsp" target="contentPage"><input type="button" value="返回"></a>
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
