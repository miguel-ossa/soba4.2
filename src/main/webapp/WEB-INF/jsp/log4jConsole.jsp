<%@ include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html bgcolor='blue'>
<head>
<script language="javascript" src="init.js"></script>

<title>Log4J Console</title>
</head>
<%@ include file="banner.jsp"%>
<body bgcolor="F7FFCE" >
	<center>
		<security:authorize ifAnyGranted="ROLE_ADMIN">
			<table>
				<tr>
					<td>Admin: <security:authentication property="name" />
					</td>
					<td><a href="<c:url value="loginBroker"/>">Admin Console</a></td>
				</tr>
			</table>
		</security:authorize>
		<h2>Log4j Loggers</h2>

		<hr>

		<!-- reset log level for a given class -->
		Reset Log4j Logging Level at Runtime <br>
		<form name="setLogLevel" method="POST"
			action="<c:url value="/log4jConsole" />">
			<table border="2" CELLPADDING="2" CELLSPACING="2" BGCOLOR="#C6EFF7">
				<c:forEach var="column" items="Logger, New Level, Change">
					<th align="left" bgcolor="#00184A"><FONT COLOR="#FFFFFF">${column}
					</FONT></th>
				</c:forEach>
				<tr>
					<td><select name="loggerSelected">
							<c:forEach items="${myLoggers}" varStatus="status" var="myLogger">
								<option value="${myLogger.name}">${myLogger.name}</option>
							</c:forEach>
					</select></td>
					<td><select name="levelSelected">
							<option value="debug">DEBUG</option>
							<option value="info">INFO</option>
							<option value="warn">WARN</option>
							<option value="error" selected>ERROR</option>
							<option value="fatal">FATAL</option>
							<option value="off">OFF</option>
					</select></td>
					<td colspan="2" align="center">
					<input type="submit" value="Apply" /></td>
				</tr>
			</table>
		</form>
		<!--  display loggers -->
		<hr>
		<br> <a href="<c:url value="/log4jConsole.htm"/>"> Refresh </a> <br>
		<table border="2" CELLPADDING="2" CELLSPACING="2" BGCOLOR="#C6EFF7">
			<c:forEach var="column" items="Parent, Logger, Level ">
				<th align="left" bgcolor="#00184A"><FONT COLOR="#FFFFFF">${column}
				</FONT></th>
			</c:forEach>
			<c:forEach items="${myLoggers}" varStatus="status" var="myLogger">
				<tr>
					<td>${myLogger.parent}</td>
					<td>${myLogger.name}</td>
					<td>${myLogger.effectiveLevel}</td>
				</tr>
			</c:forEach>
		</table>

	</center>
</body>
</html>
