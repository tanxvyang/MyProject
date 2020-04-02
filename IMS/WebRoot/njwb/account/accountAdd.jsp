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
    
    <title>添加账户</title>
    
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
		function addAccount(){
		var num = $("#num").val();
		var empNo = $("#empNo").val();
		var name = $("#name").val();
		var sel1 = $("#sel1").val();
		var sel2 = $("#sel2").val();
			if(num == ""){
				alert("帐号不能为空");
				return false;
			}
			if(num.length > 6 ){
				alert("帐号格式错误：超长");
				return false;
			}
			if(!/^\w{1,6}$/.test(num)){
				alert("帐号格式错误：六位以下数字、字母、下划线");
				return false;
			}
			if(empNo == ""){
				alert("用户编码不能为空");
				return false;
			}
			
			if(name == ""){
				alert("用户姓名不能为空");
				return false;
			}
			if(name.length > 4 ){
				alert("用户姓名格式错误：超长");
				return false;
			}
			if(/^\w{1,4}$/.test(name)){
				alert("用户姓名格式错误：4位以下中文");
				return false;
			}
			
			if(sel1 == "" || sel1 == undefined){
				alert("账户状态未选择");
				return false;
			}
			if(sel2 == "" || sel2 == undefined){
				alert("账户角色未选择");
				return false;
			}
			return true;
		}
		
		
		function search2(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/selectRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel2 = $("#sel2");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].roleName+" >"+datas[i].roleName+"</option>");
					sel2.append(noTd);
  					//resultTable.append(sel2);
				}
			}
		});
	}
  		
  		$(document).ready(function(){
  			search2();
  		});
	</script>
	
  </head>
  
  <body>
	<form action="account/addAccount.do"   method="post" onsubmit="return addAccount()">   	
   		<table id = "deptEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			帐号:
   			</td>
   			<td>
   				<input id="num" name="num" type ="text"/>
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td>
   			员工编码:
   			</td>
   			<td>
   				<input id="empNo" name="empNo" type ="text"/>
   			</td>
   		</tr>  
   		
   		<tr class="titleRow">
   			<td>
   			员工姓名:
   			</td>
   			<td>
   				<input id="name" name="name" type ="text"/>
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td>
   			状态:
   			</td>
   			<td>
   				<select id="sel1" name="sel1">
   					<option value="">请选择</option>
   					<option value="正常">正常</option>
   					<option value="注销">注销</option>
   				</select>
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td>
   			角色:
   			</td>
   			<td id = "d1" name = "d1">
   				<select id="sel2" name="sel2">
	   				<option value="">请选择</option>
   				</select>
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td colspan="2">
   				<input type= "submit" value="添加" class="btn"/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/account/account.jsp" target="contentPage"><input type="button" value="返回"></a>
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
