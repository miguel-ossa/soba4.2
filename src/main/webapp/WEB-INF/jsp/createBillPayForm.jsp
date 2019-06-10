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
<h1><fmt:message key="createtx.heading"/></h1>
    
<form:form method="post" commandName="billPayment">
  <table width="600" align = "center" width="95%" bgcolor="f8f8ff" border="5" cellspacing="5" cellpadding="5">
<tr>
      <td align="right" width="20%">Pay From Account:</td>
        <td width="20%">
          <form:input path="fromAccount"/>
        </td>
        <td width="60%">
          <form:errors path="fromAccount" cssClass="error"/>
        </td>
    </tr> 
  <tr>
      <td align="right" width="20%">Receiver AccountID:</td>
        <td width="20%">
          <form:input path="accountId"/>
        </td>
        <td width="60%">
          <form:errors path="accountId" cssClass="error"/>
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
        <tr>
           <td align="right" width="20%">Amount:</td>
        <td width="20%">
          <form:input path="amount"/>
        </td>
        <td width="60%">
          <form:errors path="amount" cssClass="error"/>
        </td>
    </tr>   
        <tr>
      <td align="right" width="20%">Biller:</td>
        <td width="20%">
          <form:input path="biller"/>
        </td>
        <td width="60%">
          <form:errors path="biller" cssClass="error"/>
        </td>
    </tr>
            <tr>
      <td align="right" width="20%">Address:</td>
        <td width="20%">
          <form:input path="address"/>
        </td>
        <td width="60%">
          <form:errors path="address" cssClass="error"/>
        </td>
    </tr>
            <tr>
      <td align="right" width="20%">City:</td>
        <td width="20%">
          <form:input path="city"/>
        </td>
        <td width="60%">
          <form:errors path="city" cssClass="error"/>
        </td>
    </tr>   
                <tr>
      <td align="right" width="20%">State:</td>
        <td width="20%">
          <form:input path="state"/>
        </td>
        <td width="60%">
          <form:errors path="state" cssClass="error"/>
        </td>
    </tr>
                <tr>
      <td align="right" width="20%">Zipcode:</td>
        <td width="20%">
          <form:input path="zipcode"/>
        </td>
        <td width="60%">
          <form:errors path="zipcode" cssClass="error"/>
        </td>
    </tr>
  
  </table>
  <br>
  <input type="submit" value="Submit">
</form:form>

</center>
</body>
</html>
