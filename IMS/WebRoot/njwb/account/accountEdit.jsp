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
    
      <title>账户修改</title>
    
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
				url:"<%=basePath%>account/selectOneAccount.do",
				data:{"empid":empid},
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var num = $("#num");
				var	username = $("#username");
				var status = $("#status");
				var role = $("#role");
				for(var i=0;i<datas.length;i++){
					var a =$("<input id='nums' name='nums' value="+datas[i].accountNum+" disabled='disabled' >");
					num.append(a);
					var b =$("<input id='usernames' name='usernames' value="+datas[i].name+" disabled='disabled' >");
					username.append(b);
					
					if(datas[i].status == "正常"){
						var c =$("<option value="+datas[i].status+" >"+datas[i].status+"</option>");
						var e =$("<option value='注销'>注销</option>");
						status.append(c).append(e);
					}else{
						var c =$("<option value="+datas[i].status+" >"+datas[i].status+"</option>");
						var e =$("<option value='正常'>正常</option>");
						status.append(c).append(e);
					}
					var d =$("<option value="+datas[i].role+" >"+datas[i].role+"</option>");
					role.append(d);
				}
			}
		});
	}
		function search2(){
			
			var empid = $("#onlyId").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>account/selectOneNo.do",
				data:{"empid":empid},
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				console.log(datas);
				var number = $("#number");
				for(var i=0;i<datas.length;i++){
					var a =$("<input id='number' name = 'number' value="+datas[i].empNo+" disabled='disabled' >");
					number.append(a);
				}
			}
		});
	}
		
		function search3(){
			var role = $("#role").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/selectRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel2 = $("#role");
				for(var i=0;i<datas.length;i++){
					if(role != datas[i].roleName){
						var noTd =$("<option value="+datas[i].roleName+" >"+datas[i].roleName+"</option>");
						sel2.append(noTd);
					}
  					//resultTable.append(sel2);
				}
			}
		});
	}
  		
		$(document).ready(function(){
  			search();
  			search2()
  			search3()
  		});
	
		
		function updateAccount(){
			var onlyId = $("#onlyId").val();
			var status = $("#status").val();
			var role = $("#role").val();
			return true;
		}
		
	</script>
  </head>
  
 <body>
 <%
 	String str = request.getParameter("empid");
 	request.setAttribute("empid",str);
 %>
   	<form action="account/updateAccount.do"   method="post" onsubmit="return updateAccount()">
   	<input id = "empid" name = "empid" value=${empid } type="hidden" >
   	<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow" >
   			<td>
   			帐号:
   			</td>
   			<td id="num" name = "num">
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td>
   			员工编码:
   			</td>
   			<td id = "number" name = "number">
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td>
   			员工姓名:
   			</td>
   			<td id = "username" name = "username">
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td>
   			状态:
   			</td>
   			<td >
   				<select id ="status" name = "status">
   				</select>
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td>
   			角色:
   			</td>
   			<td>
   				<select id="role" name="role">
   				</select>
   			</td>
   		</tr>  
   		
   		<tr class="titleRow">
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
   				<input type = "reset" value="重置"/>
				<a href="<%=basePath%>njwb/account/account.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   		<input id = "onlyId" name = "onlyId" value=${empid } type="hidden">
   	</form>
  </body>
</html>