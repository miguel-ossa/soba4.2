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
<h1><fmt:message key="createloginuser.heading"/></h1>
<form:form method="post" commandName="loginUser">
  <table width="95%" bgcolor="#94D6E7" border="3" cellspacing="10" cellpadding="2">
<tr>
      <td align="right" width="20%">Customer Id:</td>
        <td width="20%">
          <form:input path="customerId"/>
        </td>
        <td width="60%">
          <form:errors path="customerId" cssClass="error"/>
        </td>
    </tr>
    <tr>
      <td align="right" width="20%">Login Id (minimum length: 5):</td>
        <td width="20%">
          <form:input path="username"/>
        </td>
        <td width="60%">
          <form:errors path="username" cssClass="error"/>
        </td>
    </tr>
        <tr>
      <td align="right" width="20%">Password:</td>
        <td width="20%">
          <form:password path="password"/>
        </td>
        <td width="60%">
          <form:errors path="password" cssClass="error"/>
        </td>
    </tr>
                 
  </table>
  <br>
  <input type="submit" value="Submit">
</form:form>
</center>
</body>
</html>
