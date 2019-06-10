<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<%@ include file="banner.jsp"%>
<body bgcolor="F7FFCE"">
<center>
<h1><fmt:message key="heading" /></h1>
  
<p><fmt:message key="greeting" /> <c:out value="${model.now}" /></p>

<h3>Dispute the Transaction (${model.transaction.transactionId})</h3>
<p>Review the transaction, then click Cancel or Dispute below.

<hr>
<table width="650">
	<tr> <td> Transaction Account ID: <td><c:out value="${model.transaction.accountId}" /></td></tr>
	<tr> <td> Transaction Date: <td><c:out value="${model.transaction.transDate}" /></td></tr>
	<tr> <td> Transaction Description: <td><c:out value="${model.transaction.description}" /></td></tr>
	<tr> <td> Transaction Amount: <td><c:out value="${model.transaction.amount}" /></td></tr>
	<tr> <td> Transaction Status: <td><c:out value="${model.transaction.status}" /></td></tr>
	<tr/>
	<tr/>
	<td> <form><input type="button" value="Cancel" onClick="javascript: history.go(-1)"> </form></td>
	<td><form><input type="button" value="Dispute" ONCLICK="window.location.href='/soba/disputeTx.htm?txId=${model.transaction.transactionId}&accountId=${model.transaction.accountId}'"> 
</form></td>
	<!--
 <a href="<c:url value="manageTx.htm?customerId=${model.customerId}&accountId=${model.transaction.accountId}"/>"> Dispute</a></p>
-->
	<tr> </tr>
</table>
<br> <br> <br> <hr>
<table align="center">
<tr>
    <td> Performance metric (server side):</td>
   <td> Page load time : <%=(System.currentTimeMillis() - beginPageLoadTime)%> milliseconds</td></tr>
</table>
</body>
</center>
</html>