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
    
    <title>报销主页面</title>
    
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
		#d1{
			text-align: center;
		}
	</style>
	<script type="text/javascript">
		function del(id,name){
			if(name=="已提交"){
				alert("已提交，无法删除！")
			}else{
				var result = window.confirm("确认要删除吗?");
			if(true == result){
				$.ajax({
				type:"POST",
				url:"<%=basePath%>expense/removeExpense.do",
				data:{
					"expNo":id,
				},
				success:function(msg){
					if( msg =="true"){
						alert("删除成功");
						search(1);
					}else{
						alert("删除失败请核对");
					}
				
					 
				}
				});
			}
			}
			
		}
	</script>
	
	<script type="text/javascript">
		function edit(id){
			if(id=="已提交"){
				alert("已提交，无法修改！")
			//window.location.href = '<%=basePath%>expense/expense.jsp';
			//window.location.src = '';
			$(this).attr("src","#");     //阻止链接\
			//$(this).undelegate(this,src);
			}
			
		}
	</script>
	
	
	<script type="text/javascript">
		var currentPage;
		var oldType = "";
		var oldExpStatus = "";
  		function search(pageNo){
  			var empNo = $("#empNo").val();
  			var type = $("#type").val();
  			var expStatus = $("#expStatus").val();
  			if(oldType != type||oldExpStatus != expStatus){
  				pageNo = 1;
  			}
  			oldExpStatus = expStatus;
  			oldType = type;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>expense/selectAll.do",
  				data:{"pageNo":pageNo,
  					  "empNo": empNo,
  					  "type": type,
  					  "expStatus": expStatus},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  
  					console.log(datas);
  					var resultTable = $("#resultTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var date = datas[i].createTime;
  						var tr = $("<tr></tr>");
  						var empNo = $("<td>"+datas[i].expNo+"</td>") 
  						var username = $("<td>"+datas[i].username+"</td>") 
  						var type = $("<td>"+datas[i].type+"</td>") 
  						var money = $("<td>"+datas[i].money+"</td>") 
  						var entryTime = $("<td>"+(date.year+1900)+"年"+(date.month+1)+"月"+(date.day)+"日"+"</td>") 
  						var expStatus = $("<td>"+datas[i].expStatus+"</td>") 
  						var util ="<td>"+
  							"   <img id='"+datas[i].expNo +"'  name='"+datas[i].expStatus +"'   alt='' src='<%=basePath%>img/delete.png' class='operateImg' onclick='del(id,name)'>   "+
         					"  <a  href='njwb/expense/expenseEdit.jsp?expNo="+datas[i].expNo+"' target='contentPage' onclick='edit(id)'>    "+
         					"<img  id='"+datas[i].type +"' alt='' src='<%=basePath%>img/edit.png' class='operateImg' >"+"</a>    "+
         					"    <a href='<%=basePath%>njwb/expense/expenseDetail.jsp?expNo="+datas[i].expNo+" ' target='contentPage' >   "+
         					"<img alt='' src='<%=basePath%>img/detail.png' class='operateImg'>"+"</a>  "
  						+"</td>"
  						tr.append(empNo).append(username).append(type).append(money).append(entryTime).append(expStatus).append(util);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
  						resultTable.append(tr);
  					}
  					var startPage = $("#startPage");
  					var lastPage = $("#lastPage");
  					lastPage.attr("onclick","search("+(pager.pageNo - 1)+")");
  					var nextPage = $("#nextPage");
  					nextPage.attr("onclick","search("+(pager.pageNo + 1)+")");
  					var endPage = $("#endPage");
  					endPage.attr("onclick","search("+pager.totalPage+")");
					var state = false;  					
  					if(pager.pageNo == 1){
  						state = true;
  					}
  					startPage.prop("disabled",state);
  					lastPage.prop("disabled",state);
  					state = false;
  					if(pager.pageNo == pager.totalPage){
  						state = true;
  					}
  					nextPage.prop("disabled",state);
  					endPage.prop("disabled",state);
  				}
  			});
  		}
  	
  		$(document).ready(function(){
  			search(1);
  			
  		});
	
	</script>
	                                                                  
	
  </head>
  
  <body>
    <h1 class="title">首页  &gt;&gt;报销管理 </h1>
         	<br>
         	<br>
         	<div class="add">
         		<a href="<%=basePath%>njwb/expense/expenseAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加报销</a>
         	</div>
         
         	<div style="text-align: center;">
	  		报销类型：<select id="type" name="type">
	  					<option value="">请选择</option>
	  					<option value="差旅费">差旅费</option>
	  					<option value="招待费">招待费</option>
	  					<option value="办公费">办公费</option>
	  				</select>
	  		申请状态：<select id="expStatus" name="expStatus">
	  					<option value="">请选择</option>
	  					<option value="草稿">草稿</option>
	  					<option value="已提交">已提交</option>
	  				</select> &nbsp <input type="button" value="搜索" onclick="search(1)">	
         	</div>
         	
  		<table id="resultTable" class="deptInfo">
  			<tr class="titleRow">
  				<td>报销编号</td>
         		<td>申请人</td>
         		<td>报销类型</td>
         		<td>金额</td>
         		<td>申请时间</td>
         		<td>申请状态</td>
         		<td>操作列表</td>
  			</tr>
  		</table>
  		<div id = "d1">
	  		<input id="startPage" type="button" value="首页" onclick="search(1)">
	  		<input id="lastPage" type="button" value="上一页">
	  		<input id="nextPage" type="button" value="下一页">
	  		<input id="endPage" type="button" value="末页">
  		</div>
  		
  		
  		<!-- 

注意了这里是测试，完善后要修改



   		<input id = "empNo" name="empNo" value= type="hidden" >
 -->
    	<input id = "empNo" name="empNo" value=${user.empNo } type="hidden" >
  </body>
</html>
 