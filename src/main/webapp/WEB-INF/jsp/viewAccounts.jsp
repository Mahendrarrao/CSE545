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
      <link href="/resources/css/tabs.css" rel="stylesheet" type="text/css" /> 
  
   
</head>
<body>
<div class="topnav">
 <ol id="toc">
       <a href="home"><span>Home</span></a>
      <a href="profile"><span>Profile</span></a>

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
      <h2>View Accounts</h2>

       <form:form name="frm" action="accounts" method="post" modelAttribute="account">
        <table>
        <tr>
        <td colspan="2"><label color="Red"><font color="red">${statusmsg}</font></label><td>
        </tr>
      
      <tr>
        <td>Select Account : </td>
            <td> 
            <form:select path="accountId">
                      <form:option value="" label="Select Account" />
                      <form:options items="${accountsList}" />
                       </form:select>
                       
            </td>
      
      </tr>
       
           
         <tr>
          <td><input type="submit" name="submit" value="Make as  Default Account"/> </td>
               <td><input type="submit" name="submit" value="View Account"/> </td>
                 <td><input type="submit" name="submit" value="Generate Statement"/> </td>
           
        </tr>
        </table>
              
   
      </form:form>   
      <div>
      <c:if test="${info != null}">
          <form:form  modelAttribute="info">
          <table>
          <tr>
          <td>
         Balance: <form:input path="balance" type="number" disabled="true"/>
          </td>
          </tr>
           <tr>
          <td>
         Account Status: <form:input path="accountStatus" type="text" disabled="true"/>
          </td>
          </tr>
          </table>
           </form:form>
           </c:if>
      </div>
      
  </div>
  </div>
</div>
<div class="footer">
  <p>Footer</p>
</div>
</body>
</html>