<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if(session.getAttribute("user") == null){
		request.setAttribute("isError", true);
		request.setAttribute("errorMessage", "请您登录后访问");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
    
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

    <style type="text/css">
  	.hide{
  		display: none;
  	
  	}
  </style>
   <script type="text/javascript">
   	$(function(){
   		  //找到所有的li,且class=menu
   		  //alert($("li[class='menu']").length);
   		  
   		  
   		  //存在的问题：一级菜单和二级菜单能正常的显示与隐藏，但是当点击二级菜单，发现二级菜单也跟着隐藏
   		  
   		  //吸取一个经验：在网页元素排版时，要兼顾后期的js操作
   		  //一个合理的网页布局，会让js获取元素时非常遍历，否则就很痛苦
   		  /**
   		  $("li[class='menu']").each(function(){
   		  	  $(this).click(function(){
   		  	      $(this).children(".hide").slideToggle();
   		  	  });
   		  
   		  });
   		  */
   		  
   		  //对所有的span标签设置单击事件
   		  
   		  //alert($("span").length);  4个
   		  
   		  //alert($("li[class='menu'] span").length);
   		  
   		  $("li[class='menu'] span").each(function(){
   		  		$(this).click(function(){
   		  			  //this代表的是span
   		  			  $(this).siblings(".hide").slideToggle();
   		  		});
   		  
   		  });
   	
   	});
   
   </script> 
    
    <script type="text/javascript">
    	function search2(){
    		var empNa = $("#empNa").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>merge/selectMerge.do",
				data:{
					"empNa":empNa
				},
				
				success:function(msg){
				var pager = $.parseJSON(msg);	
				currentPage =pager;
				var datas = pager;
				var u1 = $("#u1");
				var u2 = $("#u2");
				var u3 = $("#u3");
				for(var i=0;i<datas.length;i++){
					if(datas[i].menuName == "部门管理" || datas[i].menuName == "员工管理" || datas[i].menuName == "请假管理"){
						if(datas[i].menuName == "部门管理"){
							var adress = "dept";
						}
						if(datas[i].menuName == "员工管理"){
							var adress = "employee";
						}
						if(datas[i].menuName == "请假管理"){
							var adress = "holiday";
						}
						var noTd =$("<li class='menu-sub' ><a href='<%=basePath%>/njwb/"+adress+"/"+adress+".jsp'  id='deptManager'   target='contentPage'>"+datas[i].menuName+"</a></li>");
						u1.append(noTd);
					}
					if(datas[i].menuName == "报销管理"){
						var adress = "expense";
						var noTd =$("<li class='menu-sub' ><a href='<%=basePath%>/njwb/"+adress+"/"+adress+".jsp'  id='deptManager'   target='contentPage'>"+datas[i].menuName+"</a></li>");
						u2.append(noTd);
					}
					if(datas[i].menuName == "账户维护" || datas[i].menuName == "角色管理" || datas[i].menuName == "权限管理"){
						if(datas[i].menuName == "账户维护"){
							var adress = "account";
						}
						if(datas[i].menuName == "角色管理"){
							var adress = "role";
						}
						if(datas[i].menuName == "权限管理"){
							var adress = "permissions";
						}
						var noTd =$("<li class='menu-sub' ><a href='<%=basePath%>/njwb/"+adress+"/"+adress+".jsp'  id='deptManager'   target='contentPage'>"+datas[i].menuName+"</a></li>");
						u3.append(noTd);
					}
				}
			}
		});
	}
  		
  		
  		
  		$(document).ready(function(){
  			search2();
  		});
  		
  		function exit(){
  			var result = window.confirm("确认退出系统");
  			if(result == true ){
  				session.invalidate();
  				return true;
  			}else{
  				return false;
  			}
  		}
    
    </script>
  </head>
  
   <body>
  	<div id = "mainDiv">
	  	<div id = "header">
	    	<div id = "logoDiv" class="lft">
	    		南京网博教育集团
	    	</div>
	    	<div id = "userDiv" class="rft">
	    		${user.empNa}
	    		<input id = "empNa" name = "empNa" value=${user.empNa } type="hidden">
	    	</div>
	    </div>
	    <div id = "welcomeDiv">
	    	欢迎使用网博管理系统
	    </div>
	    
	    
	    <div id = "contentDiv">
	    	<div id = "content-left" class="lft">
	    		<ul>
	    		
	    			<li class="menu">
	    				<span>人事管理</span>
	    				<ul id = "u1" name = "u1" class="hide">
	    				</ul>
	    			
	    			</li>
	    			
	    			<li class="menu">
	    				<span>财务管理</span>
	    				<ul id = "u2" name = "u2" class="hide">
	    				</ul>
	    			
	    			</li>	    			
	    		    <li class="menu">
	    				<span>系统管理</span>
	    				<ul id = "u3" name = "u3" class="hide">
	    					<li class="menu-sub"><a href="<%=basePath%>/njwb/pwdReset/pwdReset.jsp"  id="roleManager"   target="contentPage">密码重置</a></li>
	    					<li class="menu-sub"><a href="<%=basePath%>/njwb/login.jsp"  id="exitManager"    onclick="return exit()">系统退出</a></li>
	    				</ul>
	    			
	    			</li>

	    		</ul>
	    		
	    		
	    	</div>
	    	
	    	<div id = "content-right" class="rft">
	    		<iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
	    		</iframe>
	    	</div>
	    </div>
	    
	    <div id = "footer">
	    	<span>&copy;版权归属南京网博江北总部</span>
	    </div>
  	
  	</div>
   
  </body>
</html>
