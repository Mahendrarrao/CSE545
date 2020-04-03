<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Employee Profile</title>
      <link href="/resources/css/style.css" rel="stylesheet" type="text/css" />
    
  
   
</head>
<body>
<div class="topnav">
 <ol id="toc">
      <a href="home"><span>Home</span></a>
   
      <a href="#" onclick="document.getElementById('logout-form').submit();"><span>Logout</span></a>  
	</ol>
	


<form id="logout-form" action="<c:url value="/logout"/>" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</div>
<div class="content">
  <center><h1>Welcome to Sun Financial</h1></center>
  <div id="login-box-ext-usr">

   <div align="center">
      <h2>Modify User Account</h2>

       <form:form name="frm" action="user" method="post" modelAttribute="user">
        <table>
        <tr>
        <td colspan="2"><label color="Red"><font color="red">${statusmsg}</font></label><td>
        </tr>
         <tr>
          <td>Full Name: </td>
            <td> <form:input path="fullName"  />
            <form:errors path = "fullName"/></td>
        </tr>
        
        <tr>
          <td>Phone: </td>
            <td> <form:input path="phone"  />
            <form:errors path = "phone"/></td>
        </tr>
          <tr>
            <td>Address:</td>
              <td>  <form:input path="address"  />
              <form:errors path = "address"/></td>
       
          <tr>
            <td>  Email:</td>
              <td><form:input path="email" />
              <form:errors path = "email"/></td>
        </tr>
        	<tr>
			<td>
 <form:radiobutton path="gender" value="male"/>  Male
			</td>
			<td>
		 <form:radiobutton path="gender" value="female"/>  Female
			</td>
		
        <form:hidden path="userId" />
        </tr>
         
        </table>
              
      <br/>    
        <input id="makechanges" type="submit" value="Make Changes" />
      </form:form>   
   
  </div>
  </div>
</div>


</body>
</html>