<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Help and Support</title>
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
        <div id="support-box">
            <h3> Schedule an appointment </h3>
            <form:form name="frm" action="sendtoemailcalendarinvite"  method="post" onSubmit="return validateSchedule()">
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
        						<input type="submit" id="sendotp" value="Send Calendar" />
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
