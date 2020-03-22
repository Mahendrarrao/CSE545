<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Home</title>
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
                <li class="current"><a href="account.jsp"><span>Account</span></a></li>
                <li><a href="TranPayment.jsp"><span>Payment</span></a></li>
                <li><a href="Transfer.jsp"><span>Transfer</span></a></li>
                <li><a href="creditDebit.jsp"><span>Credit/Debit</span></a></li>
                <li><a href="authorize.jsp"><span>Make Payment</span></a></li>
                <li><a href="viewTransactions.jsp"><span>View Transactions</span></a></li>
                <li><a href="extProfile.jsp"><span>Profile</span></a></li>
                <li><a href="accmgmt.jsp"><span>Account Management</span></a></li> 
                <li><a href="help.jsp"><span>Help and Support Center</span></a></li>
                <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>       
            </ol>
            <div align="center">
            <h2>Account List</h2>
     
            <table border="1">
                <th>Account No</th>
                <th>user id</th>
                <th>balance</th>
                <c:forEach var="account" items="${listAccount}" varStatus="status">
                <tr>
                    <td>${account.accNo}</td>
                    <td>${account.exUserId}</td>
                    <td>${account.balance}</td>           
                </tr>
                </c:forEach>             
            </table>
        </div>
        </div>
    </div>
    <div class="footer">
        <p>Footer</p>
    </div>
    </body>
</html>