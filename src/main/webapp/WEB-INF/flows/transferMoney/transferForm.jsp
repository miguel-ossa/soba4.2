<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Transfer Form</title>
 <style>
    .error { color: red; }
  </style> 
</head>

<body>
	<form:form commandName="transferRecord">
		<table>
			<tr>
				<td>From Account ID</td>
				<td><form:input path="fromAccountId" /></td>
			</tr>
			<tr>
				<td>To Account ID</td>
				<td><form:input path="toAccountId" /></td>
			</tr>

			<tr>
				<td>Amount</td>
				<td><form:input path="amount" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssClass="error"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="_eventId_proceed"
					value="Proceed" /> <input type="submit" name="_eventId_cancel"
					value="Cancel" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
