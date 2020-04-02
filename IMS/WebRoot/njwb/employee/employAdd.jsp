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
    
    <title>员工添加</title>
    
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
		function addEmployee(){
		var No = $("#No").val();
		var name = $("#name").val();
		var time = $("#time").val();
			if(No == ""){
				alert("用户编号不能为空");
				return false;
			}
			if(No.length > 6 ){
				alert("用户编号格式错误：超长");
				return false;
			}undefined
			if(!/^\w{1,6}$/.test(No)){
				alert("用户编号格式错误：六位以下数字、字母、下划线");
				return false;
			}
			if(name == "" || name == undefined){
				alert("用户姓名不能为空");
				return false;
			}
			if(name.length > 4 ){
				alert("用户姓名格式错误：超长");
				return false;
			}
			if(/^\w{1,4}$/.test(name)){
				alert("用户姓名格式错误：4位以下中文");
				return false;
			}
			
			if(time == "" || time == undefined){
				alert("入职时间不能为空");
				return false;
			}
			return true;
		}
	</script>
	
	<script type="text/javascript">
		
		function search(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/selectDept.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var resultTable = $("#d1");
				var sel2 = $("#sel2");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].deptName+" >"+datas[i].deptName+"</option>");
					sel2.append(noTd);
  					resultTable.append(sel2);
				}
			}
		});
	}
		$(document).ready(function(){
  			search();
  		});
	
	</script>
  </head>
  
  <body>
	<form action="employee/addEmployee.do"   method="post" onsubmit="return addEmployee()">   	
   		<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input id="No" name="No" type ="text"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td>
   				<input id="name" name="name" type ="text"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
   				<select id="sel1" name="sel1">
   					<option value="男">男</option>
   					<option value="女">女</option>
   				</select>
   			</td>
   		</tr>  
   		<tr>
   			<td>
   			所在部门:
   			</td>
   			<td id = "d1" name = "d1">
   				<select id="sel2" name="sel2">
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td>
   				<input id="time" name="time" type = "text" onclick="laydate()"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type= "submit" value="添加" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/employee/employee.jsp" target="contentPage"><input type="button" value="返回"></a>
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
