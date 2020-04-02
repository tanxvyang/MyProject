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
    
      <title>员工编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
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
		function search(){
			var empid = $("#onlyId").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>employee/selectOneEmployee.do",
				data:{"empid":empid},
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var tdNo = $("#tdNo");
				var	tdName = $("#tdName");
				var tdSex = $("#tdSex");
				var tdTime = $("#tdTime");
				for(var i=0;i<datas.length;i++){
					var a =$("<input id='num' name = 'num' value="+datas[i].empNo+" disabled='disabled' >");
					tdNo.append(a);
					var b =$("<input id='name' name = 'name' value="+datas[i].name+" disabled='disabled' >");
					tdName.append(b);
					if(datas[i].sex == "男"){
						var c =$("<option value="+datas[i].sex+" >"+datas[i].sex+"</option>");
						var e =$("<option value='女'>女</option>");
						tdSex.append(c).append(e);
					}else{
						var c =$("<option value="+datas[i].sex+" >"+datas[i].sex+"</option>");
						var e =$("<option value='男'>男</option>");
						tdSex.append(c).append(e);
					}
					var d =$("<input id='time' name = 'time' value="+datas[i].entryTime+" >")
					tdTime.append(d);
				}
			}
		});
	}
		function search2(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/selectDept.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel2 = $("#dapt");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].deptName+" >"+datas[i].deptName+"</option>");
					sel2.append(noTd);
				}
			}
		});
	}
		$(document).ready(function(){
  			search();
  			search2()
  		});
	
		function updateEmployee(){
			var onlyId = $("#onlyId").val();
			
			
			var time = $("#time").val();
			var tdSex = $("#tdSex").val();
			var dapt = $("#dapt").val();
			
			if(time == "" || time == undefined){
				alert("入职时间不能为空");
				return false;
			}
			return true;
		}
		
	</script>
  </head>
  
 <body>
 <%
 	String str = request.getParameter("empid");
 	request.setAttribute("empid",str);
 %>
   	<form action="employee/updateEmployee.do"   method="post" onsubmit="return updateEmployee()">
   	<input id = "empid" name = "empid" value=${empid } type="hidden" >
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td id="tdNo" name = "tdNo">
   			</td>
   		</tr>
   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td id = "tdName" name = "tdName">
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td >
   				<select id ="tdSex" name = "tdSex">
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			所属部门:
   			</td>
   			<td>
   				<select id="dapt" name = "dapt">
   				</select>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td id = "tdTime" name = "tdTime">
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
   				<input type = "reset" value="重置"/>
				<a href="<%=basePath%>njwb/employee/employee.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   		<input id = "onlyId" name = "onlyId" value=${empid } type="hidden">
   	</form>
  </body>
</html>