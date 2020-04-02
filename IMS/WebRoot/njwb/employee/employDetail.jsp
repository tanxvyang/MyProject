<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>员工明细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	
	<script type="text/javascript">
		
		window.onload = function(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>employee/getEmployeeMessage.do",
				data:{
					"deptNo":$("#id").val(),
				},
				success:function(msg){
				var deptMessage  = $.parseJSON(msg);	
				var date = deptMessage.entryTime;
				var messageList = $("#messageList");
					var notr = $("<li>" +'员工编号：'+ deptMessage.empNo+"</li><br>");
					var natr = $("<li>"+'员工姓名：'+deptMessage.name+"</li><br>");
					var loctr = $("<li>"+'性别：'+deptMessage.sex+" </li><br>");
					var matr = $("<li>"+'所属部门：'+deptMessage.dept+" </li><br>");
					var timetr = $("<li>"+'入职时间：'+deptMessage.entryTime+" </li><br>");
  				messageList.append(notr).append(natr).append(loctr).append(matr).append(timetr);
			
			}
			
			});
		
  			
  		}
	
	</script>
  </head>
  
   <body>
	<%
 	String str = request.getParameter("noTd");
 	request.setAttribute("noTd",str);
 %>
<input id = "id" value=${noTd } type="hidden">
<div align="center">

  <ul id = "messageList" style="margin-top: 50px"></ul>
	<a href="<%=basePath%>njwb/employee/employee.jsp" target="contentPage"><input type="button" value="返回"></a>
</div>
	
  </body>
</html>
