<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Step 1</title>
 <link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div class="topnav">
        <a href="#">Link</a>
        <a href="#">Link</a>
        <a href="#">Link</a>
    </div>
    <div class="content">
        <center><h1>Welcome to Sun Financial</h1></center>
        <div id="login-box">
			<table width="450px">
			
			<h3>Registration Step 1/2</h3>

			<tr>
			<td colspan="2">
				<label color="Red"><font color="red">${errormsg}</font></label>
			</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="Email">Email:</label>
				</td>
				<td valign="top">
					<input  type="text" name="Email" size="30"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="OTP">OTP:</label>
				</td>
				<td valign="top">
					<input  type="text" name="OTP" size="30"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
					<a href="registration2.jsp"><button class="button_reg1">Next</button></a>
				</td>
			</tr>
			</table>
		</div>
	</div>
	<div class="footer">
    	<p>Footer</p>
    </div>
</body>
</html>