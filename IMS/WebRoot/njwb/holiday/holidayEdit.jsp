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
    
      <title>请假修改</title>
    
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
			width: 500px;
			margin: 20px auto;
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
	<script type="text/javascript">
	
			window.onload = function(){
				$.ajax({
					type:"POST",
						url:"<%=basePath%>holiday/getHolidayMessage.do",
					data:{
						"QJId":$("#id").val(),
					},
					success:function(msg){
						var holidayMessage  = $.parseJSON(msg);	
						console.log("页面 ="+holidayMessage);
						console.log(holidayMessage);
						var date = holidayMessage.createTime;
							var seltype = $("#seltype");//请假类型
							var expStatustd = $("#selstatus");//申请状态
							var bztd = $("#bztd");
							var std = $("#std");
							var etd = $("#etd");
							var submit = $("#submit");
							var bzabout = $("<textarea id='bz' name='bz' rows='3' cols=''>" + holidayMessage.bz+"</textarea>");
						bztd.append(bzabout);
							var stdinput = $("<input id='startTime' name='startTime' type = 'text'  onclick='laydate()' value = "+ holidayMessage.startTime+ ">");
							var etdinput = $("<input id='endTime' name='endTime' type = 'text'   onclick='laydate()' value = "+ holidayMessage.endTime+ ">");
							std.append(stdinput);
							etd.append(etdinput);
							
						var loctr = $("<option name='type' value="+holidayMessage.type+">"+holidayMessage.type+" </option>");
						seltype.append(loctr)
								.append($("<option value='事假'>事假</option>"))
								.append($("<option value='婚假'>婚假</option>"))
								.append($("<option value='年假'>年假</option>"))
								.append($("<option value='调休'>调休</option>"))
								.append($("<option value='病假'>病假</option>"))
								.append($("<option value='丧假'>丧假</option>"));
								
						
						 
						var submitBt = $("<input type = 'submit' value='修改'/>");
						var resetBt = $("<input type = 'reset' value='重置'/>");
						var backBt = $("<a href='<%=basePath%>njwb/holiday/holiday.jsp' target='contentPage'><input type='button' value='返回'></a>");
						 
						if(holidayMessage.status=="草稿"){
						var emNtr = $("<option  value='草稿'>草稿</option>");
						var emNtr2 = $("<option  value='提交'>提交</option>");
						expStatustd.append(emNtr).append(emNtr2);
						submit.append(submitBt).append(resetBt).append(backBt);
						}
						
						if(holidayMessage.status=="提交"){
						var emNtr2 = $("<option value='提交'>提交</option>");
						expStatustd.append(emNtr2);
						submit.append(resetBt).append(backBt);
						}
						
						
						
			
			}
			
			});
		
		}
	
		function checkBeforeSubmit(){
		var seltype = $("#seltype").val();
		var selstatus = $("#selstatus").val();
		var bz = $("#bz").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
			if(seltype == ""){
				alert("请假类型不能为空");
				return false;
			}
			if(selstatus == ""){
				alert("请假类型不能为空");
				return false;
			}
			if(bz == ""){
				alert("请假事由不能为空");
				return false;
			}
			
			//判断起止时间
			if(startTime != "" &&endTime != ""){
			compareDate(startTime, endTime);
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
   <%
 	String str = request.getParameter("qjdid");
 	request.setAttribute("qjdid",str);
 %>
   	<form action="holiday/updateHoliday.do"   method="post" onsubmit="return checkBeforeSubmit()">
<input id = "id" name ="QJId" value=${qjdid } type="hidden">
   <table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			请假类型:
   			</td>
   			<td id = "type" >
   				<select id="seltype" name="type">
   					
   				</select>
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td>
   			申请状态:
   			</td>
   			<td id = "status" >
   				<select id="selstatus" name="status">
   					
   				</select>
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td>
   			请假事由:
   			</td>
   			<td id="bztd">
   				
   			</td>
   		</tr>
   	
   		<tr class="titleRow">
   			<td>
   			开始时间:
   			</td>
   			<td id="std">
   				
   			</td>
   		</tr>  
   		
   		<tr class="titleRow">
   			<td >
   			结束时间:
   			</td>
   			<td id="etd">
   				
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td colspan="2" id="submit">
   				
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