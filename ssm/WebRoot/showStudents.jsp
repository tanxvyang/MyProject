<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showStudents.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table,th,td{
			border: solid 1px black;
		}
	</style>
  </head>
  
  <body>
  	<table>
  		<tr>
  			<th>id</th>
  			<th>name</th>
  			<th>city</th>
  			<th>age</th>
  		</tr>
	  	<c:forEach items="${students}" var="student">
	  		<tr>
	  			<td>${student.id }</td>
	  			<td>${student.name }</td>
	  			<td>${student.city }</td>
	  			<td>${student.age }</td>
	  		</tr>	
	  	</c:forEach>
  	</table>
  	<form action="<%=basePath%>student/modifyStudent.do">
  		修改学生信息<br>
  		编号：<input name="id"><br>
  		姓名：<input name="name"><br>
  		城市：<input name="city"><br>
  		年龄：<input name="age"><br>
  		<input type="submit">
  	</form>
  </body>
</html>
