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
    
      <title>报销编辑</title>
    
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
	
		#expenseEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 500px;
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
	
			function search(){
				var expNo = $("#onlyId").val();
				$.ajax({
					type:"POST",
						url:"<%=basePath%>expense/getExpenseMessage.do",
					data:{
						"expNo": expNo
					},
					success:function(msg){
						var expenseMessage  = $.parseJSON(msg);	
						console.log("页面 ="+expenseMessage);
						console.log(expenseMessage);
						var date = expenseMessage.createTime;
							var expNtd = $("#expNtd");
							var tdName = $("#Nametd");
							//var expStatus = $("#expStatustd");
							var typetd = $("#typetd");
							var abouttd = $("#abouttd");
							var moneytd = $("#moneytd");
							var expStatustd = $("#expStatustd");
							var submit = $("#submit");
							
						var notr = $("<input id='expNot' name='expNot' disabled='disabled'  value="+ expenseMessage.expNo+" >");
						expNtd.append(notr);
						var about = $("<textarea id='about' name='about' rows='3' cols=''>" + expenseMessage.about+"</textarea>");
						 abouttd.append(about);
						var money = $("<input id='money' name='money' value="+expenseMessage.money+" >");
						moneytd.append(money);
						var username = $("<input id='username' name='username' disabled='disabled' value="+expenseMessage.username+" >");
						tdName.append(username);
						var Status = $("<input id='username' name='username' value="+expenseMessage.expStatus+" >");
						
						if(expenseMessage.type=="差旅费"){
						var loctr = $("<option  value="+expenseMessage.type+">"+expenseMessage.type+" </option>");
						var loctr2 = $("<option  value='招待费'>招待费 </option>");
						var loctr3 = $("<option  value='办公费'>办公费 </option>");
						typetd.append(loctr).append(loctr2).append(loctr3);
						}
						if(expenseMessage.type=="招待费"){
						var loctr = $("<option  value="+expenseMessage.type+">"+expenseMessage.type+" </option>");
						var loctr2 = $("<option  value='差旅费'>差旅费</option>");
						var loctr3 = $("<option value='办公费'>办公费 </option>");	
						typetd.append(loctr).append(loctr2).append(loctr3);
						}
						
						 if(expenseMessage.type=="办公费"){
						var loctr = $("<option  value="+expenseMessage.type+">"+expenseMessage.type+" </option>");
						var loctr2 = $("<option value='差旅费'>差旅费 </option>");
						var loctr3 = $("<option value='招待费'>招待费 </option>");	
						typetd.append(loctr).append(loctr2).append(loctr3);
						}
						
				
						 
						var submitBt = $("<input type = 'submit' value='修改'/>");
						var resetBt = $("<input type = 'reset' value='重置'/>");
						var backBt = $("<a href='<%=basePath%>njwb/expense/expense.jsp' target='contentPage'><input type='button' value='返回'></a>");
						 
						if(expenseMessage.expStatus=="草稿"){
						var emNtr = $("<option  value='草稿'>草稿</option>");
						var emNtr2 = $("<option  value='提交'>提交</option>");
						expStatustd.append(emNtr).append(emNtr2);
						submit.append(submitBt).append(resetBt).append(backBt);
						}
						
						if(expenseMessage.expStatus=="已提交"){
						var emNtr2 = $("<option value=''>已提交</option>");
						expStatustd.append(emNtr2);
						submit.append(resetBt).append(backBt);
						}
						
			
			}
			
			});
		
		}
			
	$(document).ready(function(){
  			search();
  		});
	
		function checkBeforeSubmit(){
		var type = $("#typetd").val();
		var expStatus = $("#expStatustd").val();
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
			if(!/^[A-Za-z0-9]+$/.test(about) ){
				alert("摘要类型填写错误");
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
  </head>
  
 <body>
 <%
 	String str = request.getParameter("expNo");
 	request.setAttribute("expNo",str);
 %>

   	<form action="expense/updateExpense.do"   method="post" onsubmit="return checkBeforeSubmit()">
<input id = "expNo" name = "expNo" value=${expNo }  type="hidden"  >
<input id = "empNo" name = "empNo" value=${user.empNo } type="hidden"  >
<div style="text-align: center;">
   	<table id = "expenseEditTable" class="deptInfo">
   		<tr class="titleRow">
   			<td>
   			报销编号：
   			</td>
   			<td id="expNtd">
   			</td>
   		</tr>
   		<tr class="titleRow">
   			<td>
   			申请人：
   			</td>
   			<td id = "Nametd">
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td>
   			申请状态：
   			</td>
   			<td >
   				<select id = "expStatustd" name="expStatustd" >
   				</select>
   			</td>
   		</tr>  

   		<tr class="titleRow">
   			<td>
   			报销类型：
   			</td>
   			<td>
   				<select id="typetd" name="typetd">
   				</select>
   			</td>
   		</tr>  
   		
   		<tr class="titleRow">
   			<td>
   			明细：
   			</td>
   			<td id = "abouttd">
   			</td>
   		</tr>  
   		<tr class="titleRow">
   			<td>
   			金额：
   			</td>
   			<td id = "moneytd">
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2" id="submit">
   				
   			</td>
   		</tr>  	
   	</table>
   		</div>
   		<input id = "onlyId" name = "onlyId" value=${expNo } type="hidden"  >
   	</form>
   	 <c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
	</c:if>
   	
  </body>
</html>