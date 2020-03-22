<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>External User</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link href="tabs.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript">
function handlePayment(merchantAccountNumber,requestAmount, customerAccountNumber,reqID){
	// var fromAccount=$("#fromAccountSelect").val();
	// var toUser = $("#toAccount").val();
	// var amt=$("#amount").val();
	 //var requestURL = "transferService?customerA="+customerAcc;
	 /*$.get(requestURL,function(data){
		    alert(data);
		  });*/
	 $.post( "paymentService", { toAcc: merchantAccountNumber,fromAcc:customerAccountNumber, amount: requestAmount, reqid:reqID})
	  .done(function( data ) {
	    alert( data );
	    document.location.reload(true);
	  });  	  
}
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
        <li><a href="Account.jsp"><span>Account</span></a></li>
        <li><a href="payment.jsp"><span>Payment</span></a></li>
        <li><a href="Transfer.jsp"><span>Transfer</span></a></li>
        <li><a href="creditDebit.jsp"><span>Credit/Debit</span></a></li>
        <li class="current"><a href="authorize.jsp"><span>Make Payment</span></a></li>
        <li><a href="viewTransactions.jsp"><span>View Transactions</span></a></li>
        <li><a href="profile.jsp"><span>Profile</span></a></li>
        <li><a href="accountManagement.jsp"><span>Account Management</span></a></li>   
        <li><a href="help.jsp"><span>Help and Support Center</span></a></li>
        <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li> 
    </ol>
    <center><h2>View Payment Requests</h2></center>
    <table style="width: 100%;" border="1">
    <tr>
    <th>Requested from Merchant</th>
    <th>Amount Requested</th>
    <th>To be Paid from Account</th>
    <th></th></tr> 
    <c:forEach var="payment" items="${lstPayment}" varStatus="status">
    <tr>
        <td>
        ${payment.merchantName}
        </td>
        <td>
        ${payment.requestAmount}
        </td>
        <td>
        ${payment.customerAccountNumber}
        </td>
        <td><input type=button value="Make Payment" onclick="handlePayment(${payment.merchantAccountNumber},${payment.requestAmount},${payment.customerAccountNumber},${payment.reqID})"/><br>
        <input type=button value="Decline Payment" /></td>
     </tr>
     </c:forEach>
     </table>
    </div>
</div>
<div class="footer">
    <p>Footer</p>
</div>
</body>
</html>