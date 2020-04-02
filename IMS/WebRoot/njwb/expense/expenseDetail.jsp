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
    
    <title>报销详情</title>
    
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
				url:"<%=basePath%>expense/getExpenseMessage.do",
				data:{
					"expNo":$("#id").val(),
				},
				success:function(msg){
				var expenseMessage  = $.parseJSON(msg);	
				console.log(expenseMessage);
				var date = expenseMessage.createTime;
				var resultTable = $("#resultTable");
					resultTable.html($("table tr")[0]);
			
					var notr = $("<tr><td>" +'报销编号：'+"</td><td>"+ expenseMessage.expNo+"</td></tr>");
					var natr = $("<tr><td>"+'申请人：'+"</td><td>"+expenseMessage.username+"</td></tr>");
					var loctr = $("<tr><td>"+'报销类型：'+"</td><td>"+expenseMessage.type+" </td></tr>");
					var about = $("<tr><td>"+'明细：'+"</td><td>"+expenseMessage.about+" </td></tr>");
					var matr = $("<tr><td>"+'金额：'+"</td><td>"+expenseMessage.money+" </td></tr>");
					var timetr = $("<tr><td>"+'申请时间：'+"</td><td>"+(date.year+1900)+"年"+(date.month+1)+"月"+(date.day)+"日"+" </td></tr>");
					var emNtr = $("<tr><td>"+'申请状态：'+"</td><td>"+expenseMessage.expStatus+" </td></tr>");
				
					resultTable.append(notr).append(natr).append(loctr).append(about).append(matr).append(emNtr).append(timetr);
			
			
			}
			
			});
		
  			
  		}
	
	</script>
	
	

  </head>
  
   <body>
   <%
 	String str = request.getParameter("expNo");
 	request.setAttribute("expNo",str);
 %>
<input id = "id" value=${expNo } type="hidden">
   
   <div align="center">
 			<table  id="resultTable" class="deptInfo">
 			<tr class="titleRow">报销详情</tr>
 			</table>
	<a href="<%=basePath%>njwb/expense/expense.jsp" target="contentPage"><input type="button" value="返回"></a>
	</div>
  </body>
</html>
