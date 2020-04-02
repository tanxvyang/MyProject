<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
    <title>添加请假</title>
    
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
		function addHoliday(){
		var sel2 = $("#sel2").val();
		var bz = $("#bz").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
			if(sel2 == ""){
				alert("请假类型不能为空");
				return false;
			}
			if(bz == ""){
				alert("请假事由不能为空");
				return false;
			}
			
			
			//判断起止时间是否合理
			
			if(startTime != "" && endTime != ""){
			
			if(compareDate(startTime, endTime)){
			if(expStatus =="草稿"){
				var result = window.confirm("是否保存为草稿？");
			if(true == result){
				return true;
			}else{
				return false;
			}	
			}else{
			var result = window.confirm("是否提交？");
			if(true == result){
				return true;
			}else{
				return false;
				}
			}
			}else{
			return false;
			}
			
			}else{
			alert("请选择起止时间");
				return false;
			}
	
		}
		
		function compareDate(startTime, endTime) {
			  var arrStart = startTime.split("-");
			  var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
			  var startTimes = startTime.getTime();
			  var arrEnd = endTime.split("-");
			  var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
			  var endTimes = endTime.getTime();
			  if (endTimes<startTimes) {
			    alert("结束时间不能小于开始时间");
			    return false;
			  }else{
			  return true;
			  }
			}
		
		
	</script>
  </head>
  
  <body>
    <form action="holiday/addHoliday.do"   method="post" onsubmit="return addHoliday()">   	
    
    
    <!-- 
    
    	测试数据    
   		<input type="hidden" name="username" value="李雷" >
     -->
    	<input type="hidden" name="username" value=${user.empNa } >
   		<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			请假类型:
   			</td>
   			<td id = "type" >
   				<select id="sel2" name="type">
   					<option value="">请选择</option>
	  					<option value="事假">事假</option>
	  					<option value="婚假">婚假</option>
	  					<option value="年假">年假</option>
	  					<option value="调休">调休</option>
	  					<option value="病假">病假</option>
	  					<option value="丧假">丧假</option>
   				</select>
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td>
   			请假事由:
   			</td>
   			<td>
   				<input id="bz" name="bz" type ="text"/>
   			</td>
   		</tr>
   	
   		<tr class="titleRow">
   			<td>
   			开始时间:
   			</td>
   			<td>
   				<input id="startTime" name="startTime" type = "text" onclick="laydate()"/>
   			</td>
   		</tr>  
   		
   		<tr class="titleRow">
   			<td>
   			结束时间:
   			</td>
   			<td>
   				<input id="endTime" name="endTime" type = "text" onclick="laydate()"/>
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td colspan="2">
   			<select id="expStatus" name="status">
	  					<option value="草稿">草稿</option>
	  					<option value="提交">提交</option>
	  				</select>
   				<input type= "submit" value="保存" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
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