<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<%@ include file = "banner.jsp" %>
<body> <center>
<h1>SOBA::Transfer Between Accounts</h1>
<form:form method="post" commandName="transfer">
  <table width="600" align = "center" width="95%" bgcolor="f8f8ff" border="5" cellspacing="5" cellpadding="5">
      </tr>   
           <td align="right" width="20%">Amount:</td>
        <td width="20%">
          <form:input path="amount"/>
        </td>
        <td width="60%">
          <form:errors path="amount" cssClass="error"/>
        </td>
    </tr>  
    <!--
    <tr>
      <td align="right" width="20%">Transfer From:</td>
        <td width="20%">
          <form:input path="fromAccountId"/>
        </td>
        <td width="60%">
          <form:errors path="fromAccountId" cssClass="error"/>
        </td>
    </tr>
    -->
    <tr>
      <td align="right" width="20%">Transfer From:</td>
        <td width="20%">
          <select name="fromAccountId"> 
        <option value="dummy" selected> -- select an account -- </option>
    	<option value="Checking"> Checking</option>
    	<option value="Savings"> Savings</option>
    	</select>
        </td>
        <td width="60%">
          <form:errors path="fromAccountId" cssClass="error"/>
        </td>
    </tr>
        <tr>
      <td align="right" width="20%">Transfer To::</td>
        <td width="20%">
          <select name="toAccountId"> 
            <option value="dummy" selected> -- select an account -- </option>
    	<option value="Checking"> Checking</option>
    	<option value="Savings"> Savings</option>
    	</select>
        </td>
        <td width="60%">
          <form:errors path="toAccountId" cssClass="error"/>
        </td>
    </tr>
  
             <tr>
      <td align="right" width="20%">Description:</td>
        <td width="20%">
          <form:input path="description"/>
        </td>
        <td width="60%">
          <form:errors path="description" cssClass="error"/>
        </td>
 
             
  </table>
  <br>
  <input type="submit" value="Submit">
</form:form>
<br> <br> <br> <hr>
<table align="center">
<tr>
    <td> Performance metric (server side):</td>
   <td> Page load time : <%=(System.currentTimeMillis() - beginPageLoadTime)%> milliseconds</td></tr>
</table>
</center>
</body>
</html>
