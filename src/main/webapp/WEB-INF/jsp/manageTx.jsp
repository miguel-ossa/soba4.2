<%@ include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>Tx List</title>
</head>
<%@ include file="banner.jsp"%>
<body>
<center>
<h2>You are logged in as <i> <security:authentication
	property="name" /> </i> with the following authorities:</h2>

<security:authentication property="authorities" var="authorities" />
<ul>
	<c:forEach items="${authorities}" var="authority">
		<li>${authority.authority}</li>
	</c:forEach>
</ul>
<hr> <br>

 <br>
<hr>
<security:authorize access="hasRole('ROLE_REP') or hasRole('ROLE_CUST')">
 Back to <a href="<c:url value="loginBroker"/>"> Rep Console</a> 
</security:authorize>
<hr/>
<security:authorize access="hasRole('ROLE_REP') or hasRole('ROLE_CUST')">
	<table>
		<tr>

			<c:forEach var="column"
				items="Date, Type, Description, Debit, Credit, Balance, Tx ID, Action">
				<th align="left" bgcolor="#00184A"><FONT COLOR="#FFFFFF">${column}
				</FONT></th>
			</c:forEach>
		</tr>

		<c:forEach items="${txs}" var="tx">

			<tr>


				<!-- formatDate doesn't work  if yyyy-mm-dd. must be MM-->
				<td width=100><fmt:formatDate value="${tx.transDate}"
					pattern="yyyy-MM-dd" /></td>

				<td width=80>${tx.type}</td>
				<td width=300>${tx.description}</td>
				<c:choose>
					<c:when test="${tx.amount > 0.0}">
						<td width=100></td>
						<td width=100>${tx.amount}</td>
					</c:when>
					<c:otherwise>

						<td width=100><FONT COLOR="#FF0000">${tx.amount} </FONT></td>
						<td width=100></td>

					</c:otherwise>
				</c:choose>
				<td width=100>${tx.balance}</td>
				<td>${tx.transactionId}</td>
				
				<security:accesscontrollist domainObject="${tx}" hasPermission="ADMINISTRATION">
				<c:if test="${tx.amount != 0}">
				<td><a
					href="reverseTx.htm?txId=${tx.transactionId}&accountId=${tx.accountId}">Reverse
				</a></td>
				</c:if>
				</security:accesscontrollist>
				

			</tr>

		</c:forEach>

	</table>
</security:authorize> <br>
<br>
<br> <br> <br> <hr>
<table align="center">
<tr>
    <td> Performance metric (server side):</td>
   <td> Page load time : <%=(System.currentTimeMillis() - beginPageLoadTime)%> milliseconds</td></tr>
</table>
</body>
</html>
