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
    
    <title>角色</title>
    
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
		#d1{
			text-align: center;
		}
	</style>
	<script type="text/javascript">
		function del(id){
			
			var result = window.confirm("确认要删除"+id+"号角色吗?");
			if(true == result){
				$.ajax({
				type:"POST",
				url:"<%=basePath%>role/removeRole.do",
				data:{
					"deptNo":id,
				},
				success:function(msg){
						if(msg =="true"){
						alert("删除成功");
						search(1);
					}else{
						alert("删除失败,该角色下有员工");
					}
				}
				});
			}
			}
	
	</script>
	<script type="text/javascript">
		var currentPage;
		var oldRole = "";
  		function search(pageNo){
  			var role = $("#role").val();
  			if(oldRole != role){
  				pageNo = 1;
  			}
  			oldRole = role;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>role/roleAll.do",
  				data:{"pageNo":pageNo,
  					  "role": role},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  					var resultTable = $("#resultTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var empNo = $("<td>"+datas[i].id+"</td>") 
  						var name = $("<td>"+datas[i].roleName+"</td>") 
  						var util ="<td>"+
  							"<img id='"+datas[i].id +"' alt='' src='<%=basePath%>img/delete.png' class='operateImg'onclick='del(id)'>  "+
         					"<a href='njwb/role/roleEdit.jsp?empid="+datas[i].id+"' target='contentPage'>   "+
         					"<img alt='' src='<%=basePath%>img/edit.png' class='operateImg' >   "+"</a>"
  						+"</td>"
  						tr.append(empNo).append(name).append(util);
  						resultTable.append(tr);
  					}
  					for(;i<2;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td></tr>");
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
  		
  		//查询有什么角色，放在下拉框里
  		function search2(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>role/selectRole.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel2 = $("#role");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].roleName+" >"+datas[i].roleName+"</option>");
					sel2.append(noTd);
  					//resultTable.append(sel2);
				}
			}
		});
	}
  		
  		
  		
  		$(document).ready(function(){
  			search(1);
  			search2();
  		});
	
	
	</script>
  </head>
  
  <body>
     <h1 class="title">首页  &gt;&gt;角色管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/role/roleAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加角色</a>
         	</div>
         	<br/>
         	<br/>
         	<br/>
         	<div id = "d1">
	  		角色：<select id="role" name="role">
	  					<option value="">请选择</option>
	  				</select>
	  		<input type="button" value="搜索" onclick="search(1)">
         	</div>
  		<br>
  		<table id="resultTable" class="deptInfo">
  			<tr class="titleRow">
  				<td>角色ID</td>
         		<td>角色名称</td>
         		<td>操作列表</td>
  			</tr>
  		</table>
  		<div id = "d1">
	  		<input id="startPage" type="button" value="首页" onclick="search(1)">
	  		<input id="lastPage" type="button" value="上一页">
	  		<input id="nextPage" type="button" value="下一页">
	  		<input id="endPage" type="button" value="末页">
  		</div>
  		<c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>
