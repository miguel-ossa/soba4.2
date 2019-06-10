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
<h2>Account ID: ${model.accountId}</h2>
<h3>To dispute a transaction, click the transaction ID.</h3>

<hr>
<table width="750">

	<c:forEach var="column"
		items="TxId (regular), TxId (rest), TxDate (yyyy-MM-dd), Description, Amount ($), Balance ($), Status">
		<th align="center" bgcolor="#00184A"><FONT COLOR="#FFFFFF">${column}
		</FONT></th>
	</c:forEach>


	<c:forEach items="${model.transactions}" var="transaction">
		<tr>
			<td><a
				href="<c:url value="/transactionList.htm?customerId=${model.customerId}&txId=${transaction.transactionId}"/>"><c:out
				value="${transaction.transactionId}" /></a></td>
			<td><a
				href="<c:url value="/restTx/txId/${transaction.transactionId}"/>"><c:out
				value="${transaction.transactionId}" /></a></td>
			<td><fmt:formatDate value="${transaction.transDate}"
				pattern="yyyy-MM-dd" /></td>
			<td><c:out value="${transaction.description}" /></td>
			<td><c:out value="${transaction.amount}" /></td>
			<td><c:out value="${transaction.balance}" /></td>
			<td><c:out value="${transaction.status}" /></td>
		</tr>
	</c:forEach>

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