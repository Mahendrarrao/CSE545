<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>External User</title>
      <link href="style.css" rel="stylesheet" type="text/css" />
      <link href="tabs.css" rel="stylesheet" type="text/css" /> 
   <script>
    function validateForm()
    {
      
        if(document.frm.phone.value=="" || document.frm.address.value=="" || document.frm.city.value=="" || document.frm.email.value=="")
        {
          alert("fields cannot be left blank");
        
          return false;
        }
        var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;  
        if(document.frm.phone.value.match(phoneno))  
        {    
        }  
        else  
        {  
            alert("invalid mobile number");  
            return false;  
        } 
        var email=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(!document.frm.email.value.match(email))
        {
          alert("invalid email address");  
          return false;
        }
       return true;
    }
  </script>
   
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
      <li  class="current"><a href="employeeRequest.jsp"><span>Employee Request</span></a></li>
      <li><a href="viewTransactions.jsp"><span>View Transactions</span></a></li>
      <li><a href="adminProfile.jsp"><span>Profile</span></a></li>
      <li><a href="accmgmtadmin.jsp"><span>Account Management</span></a></li>
      <li><a href="systemLogs.jsp"><span>System Logs</span></a></li>   
      <li><a href="help.jsp"><span>Help and Support Center</span></a></li>
      <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>  
  </ol>
   <div align="center">
      <h2>Employee Request</h2>
           
  </div>
  </div>
</div>
<div class="footer">
  <p>Footer</p>
</div>
</body>
</html>