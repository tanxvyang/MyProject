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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
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
		input {
	text-align: center;
}
	
	</style>
	
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
				var tddeptNo= $("#tdeptNo");
				var tddeptName=$("#tdeptName");
				var tddeptLoc=$("#tdeptLoc");
				var tddeptMaster=$("#tdeptMaster");
				var inputNo =$("<input id='deptNoPOST' name='deptNoPOST' disabled='disabled'  value="+deptMessage.deptNo+" >");
				var inputNa = $("<input id='deptName' name='deptName' value="+deptMessage.deptName+" >");
				var inputLo=  $("<input id='deptLoc' name='deptLoc' value="+deptMessage.deptLoc+" >");
				var inputMA = $("<input id='deptMaster' name='deptMaster' value="+deptMessage.deptManager+" >");
				tddeptNo.append(inputNo);
				tddeptName.append(inputNa);
				tddeptLoc.append(inputLo);
				tddeptMaster.append(inputMA);
			
			}
			
			});
		
		}
	
	
	function checkBeforeSubmit(){
   
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
				alert("部门位置格式错误：3到5位数字");
				return false;
			}
			
			return true;
		}
	
		
	</script>
	
	
  </head>
  
 <body>
 <%
 	String deptNo = request.getParameter("deptNo");
 	request.setAttribute("deptNo",deptNo);
 %>
 
   	<form  action="dept/updateDept.do"  method="post"  onsubmit="return checkBeforeSubmit()" >
   	
 <input id = "id" name="deptNo" value=${deptNo } type="hidden" >
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			部门编号:
   			</td>
   			<td id="tdeptNo">
   			</td>
   		</tr>
   		<tr>
   			<td>
   			部门名称:
   			</td>
   			<td id="tdeptName">
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td id="tdeptLoc">
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td id="tdeptMaster">
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
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