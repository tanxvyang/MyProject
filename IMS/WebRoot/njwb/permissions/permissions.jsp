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
    
    <title>权限管理</title>
    
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
			
			var result = window.confirm("确认要删除此权限吗?");
			if(true == result){
				$.ajax({
				type:"POST",
				url:"<%=basePath%>merge/removePermissions.do",
				data:{
					"deptNo":id,
				},
				success:function(){
						alert("删除成功");
						search(1);
				}
				});
			}
			}
	
	</script>
	<script type="text/javascript">
		var currentPage;
		var oldMenu = "";
		var oldRole = "";
  		function search(pageNo){
  			var menu = $("#menu").val();
  			var role = $("#role").val();
  			if(oldMenu != menu||oldRole != role){
  				pageNo = 1;
  			}
  			oldMenu = menu;
  			oldRole = role;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>merge/selectAll.do",
  				data:{"pageNo":pageNo,
  					  "menu": menu,
  					  "role": role},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  					var resultTable = $("#resultTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var empid = $(datas[i].id);
  						var empNo = $("<td>"+datas[i].roleId+"</td>") 
  						var name = $("<td>"+datas[i].roleName+"</td>") 
  						var sex = $("<td>"+datas[i].menuId+"</td>") 
  						var dept = $("<td>"+datas[i].menuName+"</td>") 
  						var util ="<td>"+
  							"<img id='"+datas[i].id +"' alt='' src='<%=basePath%>img/delete.png' class='operateImg'onclick='del(id)'>  "+
         					"<a href='njwb/permissions/permissionsEdit.jsp?empid="+datas[i].id+"' target='contentPage'>  "+
         					"<img alt='' src='<%=basePath%>img/edit.png' class='operateImg' >  "+"</a>"
  						+"</td>"
  						tr.append(empNo).append(name).append(sex).append(dept).append(util);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td></tr>");
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
				}
			}
		});
	}
  		
  		function search3(){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>merge/selectMenu.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var sel = $("#menu");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].menuName+" >"+datas[i].menuName+"</option>");
					sel.append(noTd);
				}
			}
		});
	}
  		
  		
  		
  		$(document).ready(function(){
  			search(1);
  			search2();
  			 search3();
  		});
	
	
	</script>
  </head>
  
  <body>
     <h1 class="title">首页  &gt;&gt;权限管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/permissions/permissionsAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加权限</a>
         	</div>
         	<br/>
         	<br/>
         	<div id = "d1">
	  		
	  		菜单：<select id="menu" name="menu">
	  					<option value="">请选择</option>
	  				</select>
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
         		<td>菜单ID</td>
         		<td>菜单名称</td>
         		<td>操作列表</td>
  			</tr>
  		</table>
  		<c:if test='${isError}'>
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>  
		</c:if>
  </body>
</html>
