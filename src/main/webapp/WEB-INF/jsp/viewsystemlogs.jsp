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
<h2>System logs</h2>
		<table border="2" >
			<tr>
			
				<th> user id </th>
				<th> comment </th>
				<th> timestamp </th>
			
			</tr>
			
			<c:forEach var = "i" begin = "1" end = "10">
				<tr>
					<td>${logList[i].userId}</td>
					<td>${logList[i].message}</td>
					<td>${logList[i].timestamp}</td>
				
				<tr>
				</c:forEach>
				
			<%-- </table>
			    <table border="1" cellpadding="5" cellspacing="5">
		        <tr>
		            <c:forEach begin="1" end="${noOfPages}" var="i">
		                <c:choose>
		                    <c:when test="${currentPage eq i}">
		                        <td>${i}</td>
		                    </c:when>
		                    <c:otherwise>
		                        <td><a href="employee.do?page=${i}">${i}</a></td>
		                    </c:otherwise>
		                </c:choose>
		            </c:forEach>
		        </tr>
		    </table> --%>	

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