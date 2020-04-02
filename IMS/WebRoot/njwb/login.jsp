<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
     <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <style type="text/css">
   body{
   	 background-color: #0070A2;
   }
   </style>
<script type="text/javascript">
		

        function changeYZM() {
            var yzm = document.getElementById("loginYZM");
            yzm.src= "servlet/ImgServlet?num="+Math.random();
        }
        
        function checkBeforeSubmit(){
   			 var empNo = $("#empNo").val();
			if(empNo == ""){
				alert("用户名不能为空");
				return false;
			}
			if(empNo.length > 6 ){
				alert("用户名格式错误：超长");
				return false;
			}
			if(!/^\w{1,6}$/.test(empNo)){
				alert("用户名格式错误：六位以下数字、字母、下划线");
				return false;
			}	
			var password = $("#password").val();
			if(password == ""){
				alert("密码不能为空");
				return false;
			}
			if((password.length <4) || (password.length>12)){
				alert("密码格式错误：密码为5-12位");
				return false;
			}
			
			var verificationCode = $("#code").val();
			if(verificationCode == ""){
				alert("验证码不能为空");
				return false;
			}
			
			
			return true;
		}
    </script>

  </head>
  
  <body>
  
     <div id = "login">
     	  <div id = "title">
     	  		NJWB管理系统
     	  </div>
     	  <form action="user/login.do"  method="post" onsubmit="return checkBeforeSubmit()">
	     	  <table id="loginTable">
	     	  		<tr>
	     	  			<td>用户名:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "text" name = "empNo" id = "empNo"/>
	     	  			</td>
	     	  			<td>&nbsp;</td>
	     	  		</tr>
	     	  		<tr>
	     	  			<td>密&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "password" name = "password" id = "password"/>
	     	  			</td>
	     	  			<td>&nbsp;</td>
	     	  		</tr>
	      	  		<tr>
	     	  			<td>验证码:&nbsp;</td>
	     	  			<td>
	     	  				<input type= "text" name = "code" id = "code"/>
	     	  			</td>
	     	  			<td>
	     	  				<img id="loginYZM" src="servlet/ImgServlet" onclick="changeYZM()">
	     	  			</td>
	     	  		</tr>
	     	  		
	     	  		<tr>
	     	  			<td>&nbsp;</td>
	     	  			<td colspan="2">
	     	  				<input type= "submit" value="登&nbsp;录" class="btn"/>
	     	  			</td>
	     	  		</tr>
	     	  </table>
	     </form>
     </div>
     <c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>
