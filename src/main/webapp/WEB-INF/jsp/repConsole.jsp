<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Rep Console</title>
<%@ include file = "banner.jsp" %>
<body>
<center>

<br> 
 <br>
<form method="POST" action="<c:url value="repConsole" />">
<div align = "center">
<table align="center" width="350" border="7" CELLPADDING="7" CELLSPACING="10" BGCOLOR="#C6EFF7">
<th colspan="2" bgcolor="#00184A"><FONT COLOR="#FFFFFF">Rep Console (Search for a Customer)</FONT></th>
  <tr>
    <td >Customer ID: </td>
    <td><input type="text" name="customerId" /></td>
  </tr>
  <tr>
      <td align="right" width="20%">Account Type:</td>
        <td width="20%">
          <select name="accountType"> 
    	<option value="Checking" selected> Checking</option>
    	<option value="Savings"> Savings</option>
    	</select>
        </td>
        <td width="60%">
          <form:errors path="type" cssClass="error"/>
        </td>
    </tr>
    <tr>
    <td >First Name: </td>
    <td><input type="text" name="firstName" /></td>
  </tr>
      <tr>
    <td >Last Name: </td>
    <td><input type="text" name="lastName" /></td>
  </tr>
  <tr>
    <td align="center">Email: </td>
    <td><input type="text" name="email" /></td>
  </tr>
    <tr>
    <td align="center">Address: </td>
    <td><input type="text" name="address" value="any place"/></td>
  </tr>
      <tr>
    <td align="center">Zipcode: </td>
    <td><input type="text" name="zipcode" value="99999"/></td>
  </tr>

  <tr>
    <td colspan="2" align="center">
      <input type="submit" value="Submit" />
      <input type="reset" value="Reset" />
    </td>
  </tr>
</table>

    <br>
</body>
</html>
