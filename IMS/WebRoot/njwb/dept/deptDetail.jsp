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
    
    <title>部门明细</title>
    
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
				url:"<%=basePath%>dept/getDeptMessage.do",
				data:{
					"deptNo":$("#id").val(),
				},
				success:function(msg){
				var deptMessage  = $.parseJSON(msg);	
				console.log(deptMessage);
				var date = deptMessage.createDate;
				var messageList = $("#messageList");
					var notr = $("<li>" +'部门号：'+ deptMessage.deptNo+"</li><br>");
					var natr = $("<li>"+'部门名称：'+deptMessage.deptName+"</li><br>");
					var loctr = $("<li>"+'部门位置：'+deptMessage.deptLoc+" </li><br>");
					var matr = $("<li>"+'部门经理：'+deptMessage.deptManager+" </li><br>");
					var timetr = $("<li>"+'成立时间：'+(date.year+1900)+"年"+(date.month+1)+"月"+(date.day)+"日"+" </li><br>");
					var emNtr = $("<li>"+'部门员工数：'+deptMessage.deptEmpNumber+" </li><br>");
					var createdate = deptMessage.createDate
  				messageList.append(notr).append(natr).append(loctr).append(matr).append(emNtr).append(timetr);
					
			
			
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
	<a href="<%=basePath%>njwb/dept/dept.jsp" target="contentPage"><input type="button" value="返回"></a>
</div>
	
  </body>
</html>