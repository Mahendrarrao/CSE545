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
          	<link href="tabs.css" rel="stylesheet" type="text/css" /> 
    </head>
<body>
<div class="topnav">
<ol id="toc">
      <a href="profile"><span>Profile</span></a></li>
   
      <a href="<c:url value='/logout'/>"><span>Logout</span></a>  
	</ol>
</div>
<div class="content">
  <center><h1>Welcome to Sun Financial</h1></center>
  <div id="login-box-ext-usr">
	
	<div align="center">
<h2>Transactions</h2>
<form:form method="post" action="/emp1/transactions/action" modelAttribute="selectedTransactions">
		<table border="2" >
			<tr>
			
				<th> From </th>
				<th> To </th>
				<th> Amount </th>
				<th> Transaction Date </th>
			
			</tr>
			<c:forEach var="transaction" items="${transactionList}">
				<tr>
					<td>${transaction.fromAccount}</td>
					<td>${transaction.toAccount}</td>
					<td>${transaction.transactionValue}</td>
					<td>${transaction.transactionDate}</td>
					<td><form:checkbox path="transactions" value="${ transaction.id}" /> </td>
				
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
<div class="footer">
  <p>Footer</p>
</div>
<script>

</script>
</body>
</html>