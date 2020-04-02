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
    
    <title>请假详情</title>
    
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
				url:"<%=basePath%>holiday/getHolidayMessage.do",
				data:{
					"QJId":$("#id").val(),
				},
				success:function(msg){
				var holidayMessage  = $.parseJSON(msg);	
				console.log(holidayMessage);
				var cdate = holidayMessage.createTime;
				var resultTable = $("#resultTable");
					resultTable.html($("table tr")[0]);
			
					var notr = $("<tr><td>" +'请假编号：'+"</td><td>QJ"+holidayMessage.id+"</td></tr>");
					var natr = $("<tr><td>"+'     姓名：'+"</td><td>"+holidayMessage.username+"</td></tr>");
					var loctr = $("<tr><td>"+'请假类型：'+"</td><td>"+holidayMessage.type+" </td></tr>");
					var bz = $("<tr><td>"+'请假事由：'+"</td><td>"+holidayMessage.bz+" </td></tr>");
					var stimetr = $("<tr><td>"+'开始时间：'+"</td><td>"+holidayMessage.startTime+"</td></tr>");
					var etimetr = $("<tr><td>"+'结束时间：'+"</td><td>"+holidayMessage.endTime+" </td></tr>");
					var gtimetr = $("<tr><td>"+'申请时间：'+"</td><td>"+(cdate.year+1900)+"年"+(cdate.month+1)+"月"+(cdate.day)+"日"+" </td></tr>");
					var emNtr = $("<tr><td>"+'申请状态：'+"</td><td>"+holidayMessage.status+" </td></tr>");
				
					resultTable.append(notr).append(natr).append(loctr).append(bz).append(stimetr).append(etimetr).append(gtimetr).append(emNtr);
			
			
			}
			
			});
		
  			
  		}
	
	</script>
	
	
	
  </head>
   
  <body>
    <%
 	String str = request.getParameter("qjdid");
 	request.setAttribute("qjdid",str);
 %>
<input id = "id" value=${qjdid } type="hidden">
   
   <div align="center">
 			<table  id="resultTable" class="deptInfo">
 			<tr class="titleRow">请假详情</tr>
 			</table>
	<a href="<%=basePath%>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
	</div>
  </body>
</html>
