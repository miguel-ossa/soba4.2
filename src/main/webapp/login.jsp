<%@ include file = "WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Login</title>
</head>
<%@ include file = "WEB-INF/jsp/banner.jsp" %>
<script language="javascript">
function focusOnUsername () {
	document.loginForm.username.focus();
}
</script>
<body onLoad="focusOnUsername ()"> <center>

<table> <tr> 
<td> Prospective Customers: <i> Don't have an account? </i></td>
<td> <a href="<c:url value="createCustomerForm.htm"/>"> Open Now. </a> </td> <tr>
<td> Established Customers: <i>Don't have a user ID or password? </i></td> <td> <a href="<c:url value="createLoginUserForm.htm"/>"> Register </a></td></tr>
</tr></table>
<hr>
    <br> <br>

<form name="loginForm" method="POST" action="<c:url value="/j_spring_security_check" />">
<div align = "center">
<table align="center" width="300" border="7" CELLPADDING="7" CELLSPACING="10" BGCOLOR="#C6EFF7">
<th colspan="2" bgcolor="#00184A"><FONT COLOR="#FFFFFF">Existing User Login </FONT></th>
  <tr>
    <td >Username: </td>
    <td><input type="text" name="username" /></td>
  </tr>
  <tr>
    <td align="center">Password: </td>
    <td><input type="password" name="password" /></td>
  </tr>
  <tr> 
    <td> <B><img src="${ctx}/images/arrow.jpg"/> </B></td><td>
    	<select name="signInRole"> 
    	<option value="customer" selected> An Established Customer</option>
    	<option value="rep"> A Rep</option>
    	<option value="admin"> A System Admin</option>
    	</select>
    	</td>
    	</tr>
  <tr>
    <td colspan="2" align="center">
      <input type="submit" value="Login" />
      <input type="reset" value="Reset" />
    </td>
  </tr>
</table>
</form>
 </div>
</center>
<br> <br> <br> <hr>
<table align="center">
<tr>
    <td> Performance metric (server side):</td>
   <td> Page load time : <%=(System.currentTimeMillis() - beginPageLoadTime)%> milliseconds</td></tr>
</table>
</body>
</html>
