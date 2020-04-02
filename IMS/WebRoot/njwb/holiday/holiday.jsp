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
    
    <title>请假主页面</title>
    
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
	
	function del(id,name){
			if(name=="提交"){
				alert("已提交，无法删除！")
			}else{
				var result = window.confirm("确认要删除吗?");
			if(true == result){
				
				$.ajax({
				type:"POST",
				url:"<%=basePath%>holiday/removeHoliday.do",
				data:{
					"deptNo":id,
				},
				success:function(msg){
					if( msg =="true"){
						alert("删除成功");
						search(1);
					}else{
						alert("删除失败,该请假单已经提交");
					}
					 
				}
				});

			}
			}
			
		}
	
	
		var currentPage;
		var oldType = "";
		var oldName = "";
		var oldStatus = "";
  		function search(pageNo){
  			var type = $("#type").val();
  			var username = $("#username").val();
  			var name = $("#name").val();
  			var status = $("#status").val();
  			if(oldName != name||oldType != type||oldStatus != status){
  				pageNo = 1;
  			}
  			oldType = type;
  			oldName = name;
  			oldStatus = status;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>holiday/selectAllHoliday.do",
  				data:{"pageNo":pageNo,
  					  "type": type,
  					  "name": name,
  					  "username": username,
  					  "status": status},
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					currentPage = pager;
  					var datas = pager.list;
  					var resultTable = $("#resultTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var date =  datas[i].createTime;
  						var tr = $("<tr></tr>");
  						//------------------有错看      这里           找这里  ------------------------
  						var qjdid = $("<td>"+"QJ"+datas[i].id+"</td>");
  						var name = $("<td>"+datas[i].username+"</td>") 
  						var sex = $("<td>"+datas[i].type+"</td>") 
  						var dept = $("<td>"+datas[i].bz+"</td>") 
  						var start = $("<td>"+datas[i].startTime+"</td>") 
  						var end = $("<td>"+datas[i].endTime+"</td>") 
  						var status = $("<td>"+datas[i].status+"</td>") 
  						var entryTime = $("<td>"+(date.year+1900)+"年"+(date.month+1)+"月"+(date.day)+"日"+"</td>") 
  						var util ="<td>"+
  							"  <img id='"+datas[i].id +"' name='"+datas[i].status +"' alt='' src='<%=basePath%>img/delete.png' class='operateImg' onclick='del(id,name)'>    "+
         					"<a href='<%=basePath%>njwb/holiday/holidayEdit.jsp?qjdid="+datas[i].id+"' target='contentPage'>   "+
         					"<img alt='' src='<%=basePath%>img/edit.png' class='operateImg' >"+"</a>"+
         					"<a href='<%=basePath%>njwb/holiday/holidayDetail.jsp?qjdid="+datas[i].id+"' target='contentPage'>   "+
         					"<img alt='' src='<%=basePath%>img/detail.png' class='operateImg'>"+"</a>"
  						+"</td>"
  						tr.append(qjdid).append(name).append(sex).append(dept).append(start).append(end).append(status).append(entryTime).append(util);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
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
			var username = $("#username").val();
				$.ajax({
				type:"POST",
				url:"<%=basePath%>account/ShiFouYouInput.do",
				data:{
					"username":username,
				},
				success:function(msg){
					var div1 = $("#div1");
					 if(msg == "管理员"){
						 var sex = $("申请人：<input id='name' name = 'name' type='text' style='width: 150px'>"); 
						 div1.append(sex);
					 }else{
						 var sex =$("#username").val();
						 div1.append(sex);
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
  
  <!-- 
  当前用户名，用于只查看当前请假
  <input id="username" type="hidden" value="韩梅梅">
   -->
  <input id="username" type="hidden" value=${user.empNa }>
  
     <h1 class="title">首页  &gt;&gt;请假管理 </h1>
         	
         	<div class="add">
         		<a href="<%=basePath%>njwb/holiday/holidayAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加请假</a>
         	</div>
         	<br/>
         	<br/>
         	<br/>
         	<div id = "div1" name = "div1">
         		申请人：
         	</div>
	  		请假类型：<select id="type" name="type">
	  					<option value="">请选择</option>
	  					<option value="事假">事假</option>
	  					<option value="婚假">婚假</option>
	  					<option value="年假">年假</option>
	  					<option value="调休">调休</option>
	  					<option value="病假">病假</option>
	  					<option value="丧假">丧假</option>
	  				</select>
	  		申请状态：<select id="status" name="status">
	  					<option value="">请选择</option>
	  					<option value="提交">已提交</option>
	  					<option value="草稿">草稿</option>
	  				</select>
	  		<!-- 
	  		<input id="dapt" type="text" style="width: 150px">
	  		 -->
	  		<input type="button" value="搜索" onclick="search(1)">
	  		<br>
  		<table id="resultTable" class="deptInfo" style="width: 100%">
  			<tr class="titleRow">
  				<td>请假编号</td>
         		<td>申请人</td>
         		<td>请假类型</td>
         		<td>请假事由</td>
         		<td>开始时间</td>
         		<td>结束时间</td>
         		<td>申请状态</td>
         		<td>提交时间</td>
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