<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<Integer,String> proMap = new HashMap<Integer,String>();
proMap.put(1, "程序员");
proMap.put(2, "DBA");
proMap.put(3, "测试");
proMap.put(4, "实施");
proMap.put(5, "销售");
request.setAttribute("pros", proMap);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AddCard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form action="<%=basePath %>card/addOrModifyCard.do" method="post" enctype="multipart/form-data">
  		<input type="hidden" name="id" value="${card.id }" >
  		姓名：<input type="text" name="username" value="${card.username }"><br>
  		职业：
  		<select name="pro">
  			<c:forEach items="${pros }" var="pro">
	  			<option value="${pro.key }" 
		  			<c:if test="${card.pro == pro.key }">
		  				selected = "selected"
		  			</c:if>
	  			>${pro.value }</option>
  			</c:forEach>
  		</select><br>
  		固话：<input type="text" name="tele" value="${card.tele }"><br>
  		手机：<input type="text" name="iphone" value="${card.iphone }"><br>
  		邮箱：<input type="text" name="email" value="${card.email }"><br>
  		地址：<input type="text" name="addr" value="${card.addr }"><br>
  		图片：<input type="file" name="imgFile"><br>
  		<input type="submit" value="生成名片">
  		<c:if test="${card != null }">
  			原头像
  			<img height="30px" width="30px" src="<%=basePath %>img/${card.img}">
  		</c:if>
  	</form>
  </body>
</html>
