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
if (form.password.value!=form.password2.value)  
{  
alert("两次输入的密码不同！");  
return false;  
}  
else  if (form.password.value=="")  
{  
alert("用户密码不能为空！");  
return false;  
} 
else  if (form.nickname.value=="")  
{  
alert("用户昵称不能为空！");  
return false;  
}  
else  if (form.email.value=="")  
{  
alert("用户邮箱不能为空！");  
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
.nickname {
background-color: var(--color-grey);
	color: var(--color-dark);
	border: none;
	border-radius: 3px;
	padding: 15px 20px;
	width: 100%;
	outline: 0;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
</head>
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
					<form action="RegistServlet" method="post" onSubmit="return isValid(this);">
						<p>注册</p>
						<h1 class=bt>${requestScope.ContextRegister }</h1>
						<div class="lowin-group">
							<label>Username </label>
							<input type="text" autocomplete="text" name="username" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Password </label>
							<input type="password" autocomplete="current-password" name="password" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>CheckPassword </label>
							<input type="password" autocomplete="current-password" name="password2" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Nickname </label>
							<input type="text"name="nickname"  class="nickname" /></td><tr>
						</div>
						<div class="lowin-group password-group">
							<label>Email </label>
							<input type="email" name="email" autocomplete="email" class="lowin-input">
						</div>
						<button class="lowin-btn login-btn" >
							Sign Up
						</button>

						
					</form>
				</div>
			</div>

		
	
		<footer class="lowin-footer">
			
		</footer>
	</div>
</body>
</html>