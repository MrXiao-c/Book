<%@ page language="java" contentType="text/html; charset=utf-8"

    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<script language="javascript">  
function isValid(form)  
{  
if (form.username.value=="")  
 {  
 alert("用户名不能为空");  
 return false;  
 }  
else  if (form.password.value=="")  
{  
alert("用户密码不能为空！");  
return false;  
} 

else return true;  
}  
</script>  
<style type="text/css">
.bg{
height: 750px;
	width: 100%;
	background: url(bg.jpg);
	overflow: hidden;
	background-attachment: fixed;
}
.bt{
color: red;
font-size:16px;
}
</style>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Lowin</title>
	<link rel="stylesheet" href="css/auth.css">
</head>

<body>
	<div class="lowin">
		<div class="lowin-brand">
			<img src="kodinger.jpg" alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					<form action="${pageContext.request.contextPath}/LoginServlet" method="post" onSubmit="return isValid(this);">
						<p>登录</p>
						<h1 class=bt>${requestScope.Context }</h1>
						<div class="lowin-group">
							<label>Username <a href="#" class="login-back-link">登录?</a></label>
							<input type="text" autocomplete="text" name="username" class="lowin-input">
						</div>
						
						<div class="lowin-group password-group">
							<label>Password <a href="ResetPassword.jsp" class="forgot-link">忘记密码?</a></label>
							<input type="password" name="password" autocomplete="current-password" class="lowin-input">
						</div>
						<button class="lowin-btn login-btn" >
							Sign In
						</button>

						<div class="text-foot">
							你还没有账号? <a href="register.jsp" class="register-link">注册</a>
						</div>
						
					</form>
				</div>
			</div>

		
	
		<footer class="lowin-footer">
			
		</footer>
	</div>
</body>
</html>