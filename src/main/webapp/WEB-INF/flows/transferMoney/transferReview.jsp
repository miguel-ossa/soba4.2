<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Transfer Review</title>
</head>

<body>
	<form method="POST">
		<table>
			<tr>
				<td>Transfer ID</td>
				<td>${transferRecord.id}</td>
			</tr>
			<tr>
				<td>Transfer Date</td>
				<td><fmt:formatDate value="${transferRecord.transferDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td>From Account ID</td>
				<td>${transferRecord.fromAccountId}</td>
			</tr>
			<tr>
				<td>To Account ID</td>
				<td>${transferRecord.toAccountId}</td>
			</tr>
			<tr>
				<td>From Tx ID</td>
				<td>${transferRecord.fromTxId}</td>
			</tr>
			<tr>
				<td>To Tx ID</td>
				<td>${transferRecord.toTxId}</td>
			</tr>
			<tr>
				<td>Initiator</td>
				<td>${transferRecord.initiator}</td>
			</tr>
			<tr>
				<td>Amount</td>
				<td>${transferRecord.amount}</td>
			</tr>
						<tr>
				<td>Description</td>
				<td>${transferRecord.description}</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="_eventId_confirm"
					value="Confirm" /> <input type="submit" name="_eventId_revise"
					value="Revise" /> <input type="submit" name="_eventId_cancel"
					value="Cancel" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
