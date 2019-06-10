<%@ include file="include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html bgcolor='blue'>
<head>
<script language="javascript" src="init.js"></script>
<script>
function initAll(form) {
// Initialize all form controls
  with (form) {
	initRadio (period, "relative")
	initSelect (periodInDays, "30");
    initText(fromDate,"");
    initText(toDate,"");
  }
}
</script>

<title>Activities </title>
</head>
<%@ include file = "banner.jsp" %>
<body bgcolor="F7FFCE" onload="initAll(document.query)"> <center>
<!--  upper bar for actions -->
		 <c:set var="customerId" value ="${customerId}"/>
		 
		 <c:set var="accountId" value ="${accountId}"/>
<table>
<tr> 
<td > <a href="<c:url value="createAccountForm/customerId/${customerId}"/>">Open an Account</a> </td> <td> | </td>
<td > <a href="<c:url value="createTxForm/customerId/${customerId}/accountId/${accountId}"/>">Create a Tx</a> </td> <td> | </td>
<td > <a href="<c:url value="createAclTxForm/customerId/${customerId}/accountId/${accountId}"/>">Create an ACL Tx</a> </td> <td> | </td>
<td > <a href="<c:url value="createBillPayForm/customerId/${customerId}/accountId/${accountId}"/>">Bill Payment</a> </td> <td> | </td>
<td > <a href="<c:url value="transferForm/customerId/${customerId}"/>">Transfers</a> </td> <td> | </td>
<td > <a href="<c:url value="transferMoney.htm"/>">Transfers (flow-based)</a> </td> <td> | </td>
<td > <a href="<c:url value="transactionList.htm?customerId=${customerId}&accountId=${accountId}"/>">Dispute Transactions</a> </td> <td> | </td>
<!--
<td > <a href="<c:url value="manageTx.htm?customerId=${customerId}&accountId=${accountId}"/>">Dispute</a> </td> <td> | </td>
-->
<td > <a href="${ctx}/rest/rest.jsp">jQuery-ajax-test</a> </td> 
</tr>
</table>
<hr>

<table>
<tr> <td/> Logged in as: <security:authentication property="name" /> </td>
<security:authorize access="hasRole('ROLE_REP')">
<td> <a href="<c:url value="loginBroker"/>">Rep Console</a> </td> 
<td/>
<td> <a href="<c:url value="manageTx.htm?customerId=${customerId}&accountId=${accountId}"/>">Manage Transactions</a> </td> 
</security:authorize>
</table>

<!-- Account Activity -->
<h2>Customer Account Details </h2>

<table>
<c:forEach items="${activities}" varStatus="status" var="activity">

 <c:if test="${status.count == 1}">

<tr> <td width=150> Customer ID: </td> <td> ${activity.customerId}</td> </tr>
<tr> <td width=150> Account ID: </td> <td> ${activity.accountId}</td> </tr>
<tr> <td width=150> Account Name: </td> <td> ${activity.name}</td> </tr>
<tr> <td width=150> Account Type: </td> <td> ${activity.accountType} </td> </tr>
<tr> <td width=150> Account Balance: </td> <td> ${activity.balance} </td> </tr>
 <c:set var="customerId" value ="${activity.customerId}"/>
 <!-- <td><c:out value="${customerId}" /></td> -->
 </c:if>
<c:if test="${status.count > 1}">
 </c:if>
</c:forEach>

</table>

<!--  options -->
<hr>
<form name = "query" method="POST" action="<c:url value="/repConsole" />">
<input type="hidden" id="customerId" name="customerId" value="${customerId}"/>
<table width="550">
<c:forEach var="column" items="Account Name, Type, Range (date input format: yyyy-mm-dd)">
<th align="left" bgcolor="#00184A"><FONT COLOR="#FFFFFF">${column} </FONT></th>
</c:forEach>
<tr>
<td width = "100"> 
<select name="accountName">
	<option value='Premium Checking' selected>Premium Checking</option>
	<option value='Super Savings'> Super Savings</option>
</select>
</td>

<td width = "100"> 
<select name="accountType">
	<option value='Checking' selected>Checking</option>
	<option value='Savings'> Savings</option>
</select>
</td>

<td width = "350"> 
<input type="radio" name="period" value="relative" checked> Last 
<select name="periodInDays">
  	<option value='30' selected>30 days</option>
	<option value='60'>60 days</option>
	<option value='90'>90 days </option>
</select> <br>
<input type="radio" name="period" value="absolute">
    From: 
    <input maxlength="10" size="10" type="text" name="fromDate" />
    To: 
    <input maxlength="10" size="10" type="text" name="toDate" />
</td> 	
</tr>
<br>
<tr> <td/>
    <td colspan="2" align="center">
      <input type="submit" value="Get History" />
      <input type="reset" value="Reset" />
    </td>
  </tr>
</table>
</form>
<!-- list activities -->
<hr>
<table width="800">
<tr>

<c:forEach var="column" items="Date, Type, Description, Debit, Credit, Balance">
<th align="left" bgcolor="#00184A"><FONT COLOR="#FFFFFF">${column} </FONT></th>
</c:forEach>
</tr>

<c:forEach items="${activities}" var="activity">
<tr>
<!-- formatDate doesn't work  if yyyy-mm-dd. must be MM-->
 <td width=150><fmt:formatDate value="${activity.transDate}" pattern="yyyy-MM-dd"/> </td>

 <td width=200>${activity.txType} </td>
 <td width=500>${activity.description} </td>
 <c:choose>
 <c:when test="${activity.amount > 0.0}">
 <td width=100> </td>
 <td width=100>${activity.amount} </td>
 </c:when>
  <c:otherwise>
  
 <td width=100 ><FONT COLOR="#FF0000">${activity.amount} </FONT></td>
  <td width=100> </td>
  
 </c:otherwise>
 </c:choose>
 <td width=100>${activity.balance} </td>
</tr>
</c:forEach>

</table>
</center>
<br> <br> <br> <hr>
<table align="center">
<tr>
    <td> Performance metric (server side):</td>
   <td> Page load time : <%=(System.currentTimeMillis() - beginPageLoadTime)%> milliseconds</td></tr>
</table>
</body>
</html>
