<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>

<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-all.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-autocomplete.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-mobile.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-navigation.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-scramble.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-typing.min.js"></script>
<script src="resources/JS/jquery.keyboard.min.js"></script>
<script src="resources/JS/jquery.mousewheel.min.js"></script>
<script src="resources/JS/virtualkeyboard.js"></script> -->

<link rel="stylesheet" href="resources/css/style.css" />


</head>
<body onload='document.loginForm.username.focus();'>
	<div class="topnav">
	
	</div>
	<div class="content">
		<center><h1>Welcome to Sun Financial</h1></center>
		<div id="login-box">
			<h3>Welcome</h3> 
			<form name='loginForm'
			  action="${contextPath}/login" method="post"> 
			<table>
			<tr>
			<td>
			<span>${message}</span>
			<span>${error}</span>
			</td>
			</tr>
				<tr>
					<td>User ID:</td>
					<td>
					<input type="text" name="username" placeholder="Username"/>
					
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td>
						<input   type="password" name="password" placeholder="Password" />
						
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						
					</td>
				</tr>

				<tr>
					<td colspan='2'><input name="signin" id="signin" type="submit"
					  value="Sign In" /></td>
				</tr>
				<tr>						
			  </table>
			</form> 
			<a href="forgotPassword">Forgot username/password?></a><br>
			<a href="register">Not enrolled? Sign up now. ></a>
		</div> 
	</div>

	
	
</body>
</html>