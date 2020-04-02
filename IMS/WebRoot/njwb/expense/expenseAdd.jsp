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
    
    <title>报销添加</title>
    
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
		function checkBeforeSubmit(){
		var type = $("#type").val();
		var expStatus = $("#expStatus").val();
		var about = $("#about").val();
		var money = $("#money").val();
			if(type == ""){
				alert("请选择报销类型");
				return false;
			}
			
			if(about == ""){
				alert("摘要不能为空！请填写摘要");
				return false;
			}
			
			if(money == "" ){
				alert("金额不能为空！");
				return false;
			}else if( !/^[0-9]+(.[0-9]{1,2})?$/.test(money)){
				alert("金额格式错误！");
				return false;
			}
			
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
			
			return true;
		}
	</script>
	
	<script type="text/javascript">
		
		function search(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/selectDept.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var resultTable = $("#d1");
				var sel2 = $("#sel2");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].deptName+" >"+datas[i].deptName+"</option>");
					sel2.append(noTd);
  					resultTable.append(sel2);
				}
			}
		});
	}
		$(document).ready(function(){
  			
  		});
	
	</script>
  </head>
  
  <body>
	<form action="expense/addExpense.do"   method="post" onsubmit="return checkBeforeSubmit()" >   	
   		<table id = "deptEditTable" class="deptInfo">
   		
   		<tr class="titleRow">
   		<td>
   		报销类型：
   		</td>
   		<td>
   				<select id="type" name="type">
	  					<option value="">请选择</option>
	  					<option value="差旅费">差旅费</option>
	  					<option value="招待费">招待费</option>
	  					<option value="办公费">办公费</option>
	  				</select>
   		</td>
   		
   		</tr>
   		
   		
   		<tr class="titleRow">
   			<td>
   			摘要:
   			</td>
   			<td>
   			<textarea id="about" name="about" rows="3" cols=""  placeholder="例：2019年12月1日-12月30日，出差上海，车票400，住宿800，餐饮336.5" ></textarea>
   		
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td>
   			金额:
   			</td>
   			<td>
   				<input id="money" name="money" type ="text" placeholder="例：1536.5"/>
   			</td>
   		</tr>  

   		 
   		

   		
   		
   		<tr class="titleRow">
   			<td colspan="2">
   				<select id="expStatus" name="expStatus">
	  					<option value="草稿">草稿</option>
	  					<option value="提交">提交</option>
	  				</select>
   				<input type ="submit" value="保存" class="btn" onclick=""/>
   				<input type ="reset" value="重置" class="btn"/>
				<a href="<%=basePath%>njwb/expense/expense.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   		</table>

<!-- 

注意了这里是测试，完善后要修改



    		<input id = "empNo" name="empNo" value=${user.empNo } type="hidden" >
 -->
   		<input id = "empNo" name="empNo" value=${user.empNo } type="hidden" >
   	</form>
   	 <c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>  	
   	
   	
  </body>
</html>
