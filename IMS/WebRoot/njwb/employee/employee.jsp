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
    
    <title>员工主页面</title>
    
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
		function del(id){
			
			var result = window.confirm("确认要删除"+id+"号员工吗?");
			if(true == result){
				$.ajax({
				type:"POST",
				url:"<%=basePath%>employee/removeEmployee.do",
				data:{
					"deptNo":id,
				},
				success:function(msg){
					if(msg =="true"){
						alert("删除成功");
						search(1);
					}else{
						alert("删除失败,该员工为部门经理");
					}
				}
				});
			}
			}
	
	</script>
	<script type="text/javascript">
		var currentPage;
		var oldEmpNo = "";
		var oldName = "";
		var oldDapt = "";
  		function search(pageNo){
  			var empNo = $("#empNo").val();
  			var name = $("#name").val();
  			var dapt = $("#dapt").val();
  			if(oldName != name||oldEmpNo != empNo||oldDapt != dapt){
  				pageNo = 1;
  			}
  			oldEmpNo = empNo;
  			oldName = name;
  			oldDapt = dapt;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>employee/selectAll.do",
  				data:{"pageNo":pageNo,
  					  "empNo": empNo,
  					  "name": name,
  					  "dapt": dapt},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  					var resultTable = $("#resultTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var empid = $(datas[i].id);
  						var empNo = $("<td>"+datas[i].empNo+"</td>") 
  						var name = $("<td>"+datas[i].name+"</td>") 
  						var sex = $("<td>"+datas[i].sex+"</td>") 
  						var dept = $("<td>"+datas[i].dept+"</td>") 
  						var entryTime = $("<td>"+datas[i].entryTime+"</td>") 
  						var util ="<td>"+
  							"<img id='"+datas[i].id +"' alt='' src='<%=basePath%>img/delete.png' class='operateImg'onclick='del(id)'>  "+
         					"<a href='njwb/employee/employEdit.jsp?empid="+datas[i].id+"' target='contentPage'>    "+
         					"<img alt='' src='<%=basePath%>img/edit.png' class='operateImg' >"+"</a>"+
         					"  <a href='njwb/employee/employDetail.jsp?noTd="+datas[i].id+" ' target='contentPage'>  "+
         					"<img alt='' src='<%=basePath%>img/detail.png' class='operateImg'>"+"</a>"
  						+"</td>"
  						tr.append(empNo).append(name).append(sex).append(dept).append(entryTime).append(util);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td></tr>");
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
				url:"<%=basePath%>dept/selectDept.do",
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				//var resultTable = $("#d1");
				var sel2 = $("#dapt");
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option value="+datas[i].deptName+" >"+datas[i].deptName+"</option>");
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
    <h1 class="title">首页  &gt;&gt;员工管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/employee/employAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加员工</a>
         	</div>
         	<br/>
         	<br/>
         	<br/>
	  		员工编号：<input id="empNo" type="text" style="width: 150px">
	        姓名：<input id="name" type="text" style="width: 150px">
	  		所属部门：<select id="dapt" name="dapt">
	  					<option value="">请选择</option>
	  				</select>
	  		<!-- 
	  		<input id="dapt" type="text" style="width: 150px">
	  		 -->
	  		<input type="button" value="搜索" onclick="search(1)">
  		<br>
  		<table id="resultTable" class="deptInfo">
  			<tr class="titleRow">
  				<td>员工编号</td>
         		<td>员工姓名</td>
         		<td>性别</td>
         		<td>所属部门</td>
         		<td>入职时间</td>
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
 