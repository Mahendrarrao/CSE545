<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
        	<link href="style.css" rel="stylesheet" type="text/css" />
          	<link href="tabs.css" rel="stylesheet" type="text/css" /> 
    </head>
<body>
<div class="topnav">
  <a href="#">Link</a>
  <a href="#">Link</a>
  <a href="#">Link</a>
</div>
<div class="content">
  <center><h1>Welcome to Sun Financial</h1></center>
  <div id="login-box-ext-usr">
	<ol id="toc">
		<li><a href="empaccount.jsp"><span>Account</span></a></li>
		<li><a href="TranPayment.jsp"><span>Payment</span></a></li>
		<li><a href="Transfer.jsp"><span>Transfer</span></a></li>
		<li><a href="creditDebit.jsp"><span>Credit/Debit</span></a></li>
		<li><a href="employeeRequest.jsp"><span>Employee Request</span></a></li>
		<li><a href="viewTransactions.jsp"><span>View Transactions</span></a></li>
		<li><a href="adminProfile.jsp"><span>Admin Profile</span></a></li>
		<li class="current"><a href="accmgmtadmin.jsp"><span>Account Management</span></a></li>
		<li><a href="systemLogs.jsp"><span>System Logs</span></a></li>
		<li><a href="help.jsp"><span>Help and Support Center</span></a></li>
		<li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>  
	</ol>
	<div align="center">
	<h2>Select type of change</h2>
	<form:form  method="post" commandName="form" >
		<table>	
		<tr>
			<td>
				<input type="radio" id="createacc" name="modify" value="create"/>Create a new customer account
			</td>
			<td>
				<input type="radio" id="viewAccounts" name="modify" value="View"/>View a customer account
			</td>
			<td>
				<input type="radio" id="deleteacc" name="modify" value="delete"/>Select an account to delete				
			</td>
		</tr>
		
<!-- 		<tr>
			<td>
			  <form:select name="selectedAccount" path="account">
					<form:options items="${listOfAccounts}"></form:options>
	    		</form:select>
			</td>
			</tr> -->
		</table>
		<input type="submit" id="modifybtn" value="Submit Modification Request"><br><br>
	</form:form>
	</div>
   </div>
</div>
<div class="footer">
  <p>Footer</p>
</div>
</body>
</html>