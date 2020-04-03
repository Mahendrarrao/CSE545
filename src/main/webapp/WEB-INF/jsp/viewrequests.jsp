<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
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
<h2>Requests</h2>
<form:form method="post" action="/emp2/requests/action" modelAttribute="selectedRequests">
		<table border="2" >
			<tr>
			
				<th> UserId </th>
				<th> Request Type </th>
				
				<th> Request Date </th>
			
			</tr>
			<c:forEach var="request" items="${requestList}">
				<tr>
					<td>${request.userId}</td>
					<td>${request.requestType}</td>
			
					<td>${request.requestDate}</td>
					<td><form:checkbox path="requests" value="${ request.id}" /> </td>
				
				<tr>
				</c:forEach>
				<tr>
				<td>
				      <input id="makechanges" name="action" type="submit" value="Approve" />
				      <input id="makechanges" name="action" type="submit" value="Reject" />
				
				</td>
				</tr>
			</table>
			</form:form>	

	</div>
   </div>
</div>

<script>

</script>
</body>
</html>