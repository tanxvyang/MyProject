<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Card.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#cardDiv {
			display:inline-block;
			background-color: #72BAAD;
		}
		#cardTab{
			margin-top: 25px;
			margin-bottom: 25px;
			margin-left: 50px;
			color: white;
		}
		#cardName{
			font-size: 50px;
		}
		#pro{
			font-size: 25px;
		}
		li{
			font-size: 27px;
		}
	</style>
  </head>
  
  <body>
  	<div id="cardDiv">
  		<table id="cardTab" height="480px">
  			<tr>
  				<td width="480px">
  					<span id="cardName">${card.username }</span><br>
  					<span id="pro">
  						<c:choose>
  							<c:when test="${card.pro == 1 }">
  								程序员
  							</c:when>
  							<c:when test="${card.pro == 2 }">
  								DBA
  							</c:when>
  							<c:when test="${card.pro == 3 }">
  								测试
  							</c:when>
  							<c:when test="${card.pro == 4 }">
  								实施
  							</c:when>
  							<c:when test="${card.pro == 5 }">
  								销售
  							</c:when>
   						</c:choose>
  					</span>
  				</td>
  				<td rowspan="5"><img width="320px" src="img/${card.img }" alt=""></td>
  			</tr>
  			<tr>
  				<td>&nbsp;</td>
  			</tr>
  			<tr>
  				<td>&nbsp;</td>
  			</tr>
  			<tr>
  				<td>
	  				<ul>
	  					<li>${card.tele }</li>
	  					<li>${card.iphone }</li>
	  					<li>${card.email }</li>
	  					<li>${card.addr }</li>
	  				</ul>
  				</td>
  			</tr>
  		</table>
  	</div>
  </body>
</html>
