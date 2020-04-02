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
    
    <title>部门主页</title>
    
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
	
	
	</style>
	
	<script type="text/javascript">
		
		function del(id){
			
			var result = window.confirm("确认要删除部门"+id+"吗?");
			if(true == result){
				$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/removeDept.do",
				data:{
					"deptNo":id,
				},
				success:function(msg){
					if( msg =="true"){
						alert("删除成功");
						search(1);
					}else{
						alert("删除失败,该部门已删除或有员工");
					}
				
					 
				}
				});
			}
			
			
			
			
		}
		
	
		
		
		var currentPage;
		function search(pageNo){
			$.ajax({
				type:"POST",
				url:"<%=basePath%>dept/getUserByPage.do",
				data:{
					"pageNo":pageNo
				},
				success:function(msg){
				var pager  = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager.list;
				var resultTable = $("#resultTable");
				resultTable.html($("table tr")[0]);
				
				for(var i=0;i<datas.length;i++){
					var tr = $("<tr></tr>");
					var noTd =$("<td>"+datas[i].deptNo+"</td>");
					var naTd =$("<td>"+datas[i].deptName+"</td>");
					var locTd =$("<td>"+datas[i].deptLoc+"</td>");
					var maTd =$("<td>"+datas[i].deptManager+"</td>");
					var uilTd ="<td>"+
  							"  <img id='"+datas[i].deptNo +"'  alt='' src='<%=basePath%>img/delete.png' class='operateImg' onclick='del(id)'>    "+
         					"  <a href='<%=basePath%>njwb/dept/deptEdit.jsp?deptNo="+datas[i].deptNo+" 'target='contentPage'> "+
         					"  <img alt='' src='<%=basePath%>img/edit.png' class='operateImg' >   "+" </a>  "+
         					"  <a href='njwb/dept/deptDetail.jsp?noTd="+datas[i].deptNo+" ' target='contentPage'>  "+
         					"    <img alt='' src='<%=basePath%>img/detail.png' class='operateImg'>   "+"</a>  "
  						+"</td>"
				tr.append(noTd).append(naTd).append(locTd).append(maTd).append(uilTd);
  				resultTable.append(tr);
					
				}
				for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td></tr>");
  						resultTable.append(tr);
  					}
				var startPage = $("#startPage");
				var lastPage =$("#lastPage");
				var nextPage =$("#nextPage");
				var endPage =$("#endPage");
				
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
         	<h1 class="title">首页  &gt;&gt;部门管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/dept/deptAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加部门</a>
         	</div>
         	
         	<table id="resultTable" class="deptInfo">
         		<tr class="titleRow">
         			<th>部门编号</th>
         			<th>部门名称</th>
         			<th>部门位置</th>
         			<th>部门负责人</th>
         			<th>操作列表</th>
         		</tr>      		     		         	
         	</table>
         	<div align="center">
    <input id="startPage" type="button" value="首页"onclick="search(1)">
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