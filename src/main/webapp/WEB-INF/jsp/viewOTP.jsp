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
        <%--     <form:form name="frm" action="sendtoemailforgotpassword"  method="post">
            <table>           
                <tr>
                    <td>Email:</td>
                    <td><input type='text' id='email' name='email'></td>
                </tr>
                <tr>
                    <td>Confirm Email:</td>
                    <td><input type='text' id='confirmemail' name='confirmemail'></td>
                </tr>
                <tr>
                    <td colspan="2"> 
        				<input type="submit" id="sendotp" value="Send OTP" />
    				</td>              
                   
                </tr>
            </table>
            </form:form> --%>

		<form:form method="POST" modelAttribute="forgotPasswordForm"
			action="/otp">
			<table>           
              <tr>
              <span>
              ${message}</span>
              </tr>
                <tr>
                    <td>User Id:</td>
                    <td>	<form:input path="userId"  type="text" name="userId" size="30"/>
						<form:errors path = "userId"/></td>
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
   
</body>
</html>