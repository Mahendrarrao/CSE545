<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
 	<link href="/resources/css/style.css" rel="stylesheet" type="text/css" />

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

			  $("#datepicker").datepicker({dateFormat:"yy-mm-dd"});
			});
    </script>
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
	<ol id="toc">
		  
	</ol>
	<div align="center">
	<h2>Schedule an appointment</h2>
	<span>${statusmsg}</span>
	<form:form action="help" modelAttribute="appointment" method="post">
	<table>
	
		<tr>
			<td style="width:33%;">Select date:</td>
			<td style="width:33%;">Select time slot:</td>
		</tr>
		<tr>
			<td><form:input id="datepicker" path="date" type="text"  /></td>
			<td>
				<div class="col-xs-6 form-group">
					<div class="input-group">
					<form:input type="text"  class="form-control" path="time" id="StartTime" />
					<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
					</div>
				</div>
			</td>
			<td style="width:18%;">
				<input id="schedule" type="submit" value="Schedule" />
			</td>
		</tr>


	</table>

</form:form>

	</div>
	</div>
</div>


</body>
</html>