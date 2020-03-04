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
        <div id="login-box">
            <h3> Forgot Password </h3>
            <form:form name="frm" action="sendtoemailforgotpassword"  method="post" onSubmit="return validateForm()">
            <table>           
                <tr>
                    <td>Username:</td>
                    <td><input type='text' id='userName' name='userName'></td>
                </tr>
                <tr>
                    <td colspan="2"> 
        				<input type="submit" id="sendotp" value="Send OTP" />
    				</td>              
                   
                </tr>
            </table>
            </form:form>
        </div>
    </div>
    <div class="footer">
        <p>Footer</p>
    </div>
</body>
</html>
    