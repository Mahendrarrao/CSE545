
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Step 2</title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="topnav">
        <a href="#">Link</a>
        <a href="#">Link</a>
        <a href="#">Link</a>
    </div>
    <div class="content">
        <center><h1>Welcome to Sun Financial</h1></center>
        <div id="login-box-reg">
		<form:form modelAttribute="userForm" name="frm" method="post"  >
		
			<table>
			
			<h3>Registration Step 2/2</h3>

			<tr>
			<td colspan="2">
			<div id="error">
				<!--  <label color="Red"><font color="red">${errormsg}</font></label> -->
				<span>${error}</span>
			</div>
			</td>
			</tr>
				<tr>
					<td valign="top">
						<label for="first_name">First Name *</label>
					</td>
					<td valign="top" colspan='2'>
						<form:input path="firstName"  type="text" name="firstName" size="30"/>
						<div id="error"><form:errors path = "firstName"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="last_name">Last Name *</label>
					</td>
					<td valign="top">
						<form:input path="lastName"  type="text" name="lastName" size="30"/>
							<div id="error"><form:errors path = "lastName"/></div>
					</td>
				</tr>
				
				<tr>
					<td valign="top">
						<label for="email">Email *</label>
					</td>
					<td valign="top">
						<form:input path="email"  type="text" name="email" size="30"/>
							<div id="error"><form:errors path = "email"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="user_name">User Name *</label>
					</td>
					<td valign="top">
						<form:input path="userId" type="text" name="userName" size="30"/>
							<div id="error"><form:errors path = "userId"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="password">Password *</label>
					</td>
					<td valign="top">
						<form:input path="password"  type="password" name="password" size="30"/>
							<div id="error"><form:errors path = "password"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="confirm">Confirm Password *</label>
					</td>
					<td valign="top">
						<form:input path="confirmPassword" type="password" name="password" size="30"/>
							<div id="error"><form:errors path = "confirmPassword"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="merchant">Is Merchant? *</label>
					</td>
					<td valign="top">
						Yes: <form:radiobutton path="customerType"  name="isMerchant" value="1"/>
						No: <form:radiobutton path="customerType" name="isMerchant" value="0"/>
						<div id="error"><form:errors path = "customerType"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="phone">Phone *</label>
					</td>
					<td valign="top">
						<form:input path="phone" type="text" name="phone" maxlength="10" size="30"/>
							<div id="error"><form:errors path = "phone"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="address">Address *</label>
					</td>
					<td valign="top">
						<form:input path="address" type="text" name="address" size="30"/>
							<div id="error"><form:errors path = "address"/></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label for="city">City *</label>
					</td>
					<td valign="top">
						<form:input path="city" type="text" name="city" size="30"/>
							<div id="error"><form:errors path = "city"/></div>
					</td>
				</tr>
				</table>

				<p><input type="submit" id="but_reg2" value="Submit"></p>
		</form:form>
		</div>
	</div>
	<div class="footer">
    	<p>Footer</p>
    </div>
</body>
</html>