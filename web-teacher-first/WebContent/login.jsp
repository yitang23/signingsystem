<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String tishi=request.getParameter("tishi");
    String a="";
    if(tishi==null||tishi.equals("")){
    	a="";
    }
    else if(Integer.parseInt(tishi)==2){
    	a="用户名或密码错误";
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/news/css/style.css"/>
<title>学生管理系统</title>
</head>
<body>
	<div class="login_title"><h2>学生管理系统</h2>
	</div>
	<div class="login_main">
		<div class="login_bg">
			<div class="login">
			<h2 >用户登录</h2>
  			<form action="loginServlet" method="post" id="frm">
  			<label>手机号:</label>
<!--   		<img src="images/phone.jpg" width="30" height="30"style="position:absolute; left:1000px; top:200px;"/>			 -->
  			<input type="text" name=tphone id="tphone" placeholder="输入手机号"></input>
  			<p>
  			<label>&nbsp&nbsp&nbsp密码:</label>
  			<input type="password" name="tpassword" id="tpassword"placeholder="输入密码"></input>
  			</p>
  			<p><a class="login_button" href="javascript:void(0)" onclick="post()">登录</a></p>
			<p><a class="login_button_2" href="register.jsp">注册</a></p>
			<p><span id="tishi" class="tishi"><%=a %></span></p>
			</form>
			</div>
		</div>
	</div>
	  <jsp:include page="admin/bottom.jsp"></jsp:include>
	 <script type="text/javascript">
	 function post(){
		 var tphone=document.getElementById("tphone").value;
		 var tpassword=document.getElementById("tpassword").value;
		  document.getElementById("tishi").innerHTML="";
		  if(tphone == "" || tpassword==""){
			  document.getElementById("tishi").innerHTML="请输入手机号或密码";
			  return;
		  }
		  document.getElementById("frm").submit();
	 }
	 </script>
</body>
</html>