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
    
    <title>部门编辑</title>
    
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
			width: 350px;
			margin: 20px auto;
			
			
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
	<script type="text/javascript">
	function checkBeforeSubmit(){
    var deptNo = $("#deptNo").val();
			if(deptNo == ""){
				alert("部门号不能为空");
				return false;
			}
			if(deptNo.length > 5 ){
				alert("部门号格式错误：超长");
				return false;
			}
			if(!/^[A-Z]{1}\d{1,4}$/.test(deptNo)){
				alert("部门号格式错误：大写字母加数字，不超过六位");
				return false;
			}	
    var deptName = $("#deptName").val();
			
			if(deptName == ""){
				alert("部门名不能为空");
				return false;
			}
			
			if(/^\w{1,6}$/.test(deptName)){
				alert("部门名格式错误：六位以下数字、字母、下划线");
				return false;
			}
			
    var deptLoc = $("#deptLoc").val();
			if(deptLoc == ""){
				alert("部门位置不能为空");
				return false;
			}
			if(!/^\d{3,5}$/.test(deptLoc)){
				alert("部门位置格式错误：三到五位数字");
				return false;
			}
			
			return true;
		}
	</script>
	
  </head>
  
  <body>
   	<form action="dept/addDept.do" method="POST"  onsubmit="return checkBeforeSubmit()">
   	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			部门编号:
   			</td>
   			<td>
   				<input type = "text" name="deptNo" id="deptNo"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			部门名称:
   			</td>
   			<td>
   				<input type = "text" name="deptName" id="deptName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td>
   				<input type = "text" name="deptLoc" id="deptLoc"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td>
   				<input type = "text" name="deptMaster" id="deptMaster"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="添加"/>
   				<input type = "reset" value="重置"/>
				<a href="<%=basePath%>njwb/dept/dept.jsp" target="contentPage"><input type="button" value="返回"></a>
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