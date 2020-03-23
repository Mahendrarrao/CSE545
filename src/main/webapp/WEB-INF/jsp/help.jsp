<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
        	<link href="style.css" rel="stylesheet" type="text/css" />
          	<link href="tabs.css" rel="stylesheet" type="text/css" /> 
          	<link rel="stylesheet" type="text/css" href="https://jonthornton.github.io/jquery-timepicker/jquery.timepicker.css">
          	<link rel="stylesheet" type="text/css" href="https://jonthornton.github.io/jquery-timepicker/lib/bootstrap-datepicker.css">

    </head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://jonthornton.github.io/jquery-timepicker/jquery.timepicker.js"></script>
	<script type="text/javascript" src="https://jonthornton.github.io/jquery-timepicker/lib/bootstrap-datepicker.js"></script>
    <script  type="text/javascript">
		$(function() {
			  $('#StartTime').timepicker({
			  	minTime: '10:00:00',
			  	maxTime: '17:30:00',
			  	
			  });
			});
    </script>
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
		<li><a href="account.jsp"><span>Account</span></a></li>
		<li><a href="TranPayment.jsp"><span>Payment</span></a></li>
		<li><a href="Transfer.jsp"><span>Transfer</span></a></li>
		<li><a href="creditDebit.jsp"><span>Credit/Debit</span></a></li>
		<li><a href="authorize.jsp"><span>Make Payment</span></a></li>
		<li><a href="viewTransactions.jsp"><span>View Transactions</span></a></li>
		<li><a href="profile.jsp"><span>Profile</span></a></li>
		<li><a href="accmgmt.jsp"><span>Account Management</span></a></li>
		<li class="current"><a href="help.jsp"><span>Help and Support Center</span></a></li>
		<li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>  
	</ol>
	<div align="center">
	<h2>Schedule an appointment</h2>
	<table>
		<tr>
			<td style="width:33%;">Select date:</td>
			<td style="width:33%;">Select time slot:</td>
		</tr>
		<tr>
			<td><input type="date" data-date-inline-picker="true" /></td>
			<td>
				<div class="col-xs-6 form-group">
					<div class="input-group">
					<input type="text" class="form-control" id="StartTime" />
					<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
					</div>
				</div>
			</td>
			<td style="width:18%;">
				<input id="schedule" type="submit" value="Schedule" />
			</td>
		</tr>



	</table>



	</div>
	</div>
</div>


</body>
</html>