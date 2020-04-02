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
    
    <title>添加权限</title>
    
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
		function addMerge(){
		var role = $("#role").val();
		var menu = $("#menu").val();
		
			if(role == ""){
				alert("未选择角色");
				return false;
			}
			
			if(menu == ""){
				alert("未选择要添加的权限菜单");
				return false;
			}
			
			return true;
		}
		
		function search2(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/selectRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel2 = $("#role");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].roleName+" >"+datas[i].roleName+"</option>");
					sel2.append(noTd);
				}
			}
		});
	}
  		
  		function search3(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>merge/selectMenu.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel = $("#menu");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].menuName+" >"+datas[i].menuName+"</option>");
					sel.append(noTd);
				}
			}
		});
	}
  		
  		
  		
  		$(document).ready(function(){
  			search2();
  			 search3();
  		});
	</script>
	
  </head>
  
  <body>
	<form action="merge/addMerge.do"   method="post" onsubmit="return addMerge()">   	
   		<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			角色:
   			</td>
   			<td id = "d1" name = "d1">
   				<select id="role" name="role">
	   				<option value="">请选择</option>
   				</select>
   			</td>
   		</tr>  
   		
   		<tr class="titleRow">
   			<td>
   			菜单:
   			</td>
   			<td>
   				<select id="menu" name="menu">
   					<option value="">请选择</option>
   				</select>
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td colspan="2">
   				<input type= "submit" value="添加" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/permissions/permissions.jsp" target="contentPage"><input type="button" value="返回"></a>
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
