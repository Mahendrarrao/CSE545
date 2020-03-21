<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Sign Up</title>
<link href="resources/css/style.css rel="stylesheet" type="text/css" />
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
            <h3>Sign Up</h3>
            <form name='loginForm' action="${contextPath}/login" method="post">
            <table>
              <tr>
                  <td valign="top"> SSN: </td>
                  <td valign="top">
                    <input   type="password" name="ssn" placeholder="SSN" />
                    <span>${error}</span>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  </td>
              </tr>
              <tr>
                  <td valign="top"> Email: </td>
                  <td valign="top">
                      <input  type="text" name="email" size="20"/>
                  </td>
              </tr>
              <tr>
                  <td valign="top"> Confirm Email: </td>
                  <td valign="top">
                      <input  type="text" name="confirmemail" size="20"/>
                  </td>
              </tr>
              <tr>
        					<td>Password:</td>
        					<td>
        						<input   type="password" name="password" placeholder="Password" />
        						<span>${error}</span>
        						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        					</td>
				      </tr>
            </table>
            </form>
            <a href="registration1.jsp"><button class="button">Sign Up</button></a>
        </div>
    </div>
    <div class="footer">
    <p>Footer</p>
    </div>
</body>
</html>
