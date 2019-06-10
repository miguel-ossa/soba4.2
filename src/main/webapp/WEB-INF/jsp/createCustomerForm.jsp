<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
</head>
<%@ include file = "banner.jsp" %>
  <style>
    .error { color: red; }
  </style>  
</head>
<body> <center>
<h1><fmt:message key="createcustomer.heading"/></h1>
<div align = "center">
<form:form method="post" commandName="customer">

  <table align ="center" width="600" bgcolor="#94D6E7" border="3" cellspacing="10" cellpadding="2">

    <tr>
      <td align="right" width="100">First Name:</td>
        <td width="100">
          <form:input path="firstName"/>
        </td>
        <td width="400">
          <form:errors path="firstName" cssClass="error"/>
        </td>
    </tr>
        <tr>
      <td align="right" width="100">Last Name:</td>
        <td width="100">
          <form:input path="lastName"/>
        </td>
        <td width="400">
          <form:errors path="lastName" cssClass="error"/>
        </td>
    </tr>
            <tr>
      <td align="right" width="100">Phone:</td>
        <td width="100">
          <form:input path="phone"/>
        </td>
        <td width="400">
          <form:errors path="phone" cssClass="error"/>
        </td>
    </tr>
                <tr>
      <td align="right" width="100">Address:</td>
        <td width="100">
          <form:input path="address"/>
        </td>
        <td width="400">
          <form:errors path="address" cssClass="error"/>
        </td>
    </tr>
                    <tr>
      <td align="right" width="100">City:</td>
        <td width="100">
          <form:input path="city"/>
        </td>
        <td width="400">
          <form:errors path="city" cssClass="error"/>
        </td>
    </tr>
                    <tr>
      <td align="right" width="100">State:</td>
        <td width="100">
          <form:input path="state"/>
        </td>
        <td width="400">
          <form:errors path="state" cssClass="error"/>
        </td>
    </tr>
                    <tr>
      <td align="right" width="100">Zipcode:</td>
        <td width="100">
          <form:input path="zipcode"/>
        </td>
        <td width="400">
          <form:errors path="zipcode" cssClass="error"/>
        </td>
    </tr>
     <tr align="center">
      <td align="right" width="100">Email:</td>
        <td width="100">
          <form:input path="email"/>
        </td>
        <td width="400">
          <form:errors path="email" cssClass="error"/>
        </td>
    </tr>

  </table>
       
  <br>
  <input type="submit" value="Submit">
</form:form>
  </div>
<br> <br> <br> <hr>
<table align="center">
<tr>
    <td> Performance metric (server side):</td>
   <td> Page load time : <%=(System.currentTimeMillis() - beginPageLoadTime)%> milliseconds</td></tr>
</table>
</body>
</center>
</html>
