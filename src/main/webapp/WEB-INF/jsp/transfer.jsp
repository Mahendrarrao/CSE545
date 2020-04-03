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
      <h2>Transfer</h2>

       <form:form name="frm" action="transfer" method="post" modelAttribute="transfer">
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
        <td>Enter Account No/ Email/ Phone to Transfer: </td>
            <td> 
        
                 <form:input path="toAccount" type="text"/>      
            </td>
      
      </tr>
       <tr>
        <td>Enter Amount to transfer </td>
            <td> 
        
                 <form:input path="amount" type="number"/>      
            </td>
      
      </tr>
       
           
         <tr>
          <td><input type="submit" name="submit" value="Transfer by Account"/> </td>
               <td><input type="submit" name="submit" value="Transfer by Email"/> </td>
                 <td><input type="submit" name="submit" value="Transfer by Phone"/> </td>
           
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

</body>
</html>