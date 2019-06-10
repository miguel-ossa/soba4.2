<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<%@ include file = "banner.jsp" %>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body> <center>
<h1><fmt:message key="createaccount.heading"/></h1>

<form:form method="post" commandName="account">
  <table width="95%" bgcolor="#94D6E7"  border="3" cellspacing="10" cellpadding="2">
  
  <tr>
      <td align="right" width="20%">CustomerId:</td>
        <td width="20%">
          <form:input path="customerId"/>
        </td>
        <td width="60%">
          <form:errors path="name" cssClass="error"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">Name:</td>
        <td width="20%">
          <form:input path="name"/>
        </td>
        <td width="60%">
          <form:errors path="name" cssClass="error"/>
        </td>
    </tr>
        <tr>
      <td align="right" width="20%">Type:</td>
        <td width="20%">
          <select name="type"> 
    	<option value="Checking" selected> Checking</option>
    	<option value="Savings"> Savings</option>
    	</select>
        </td>
        <td width="60%">
          <form:errors path="type" cssClass="error"/>
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
    </tr>        
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
