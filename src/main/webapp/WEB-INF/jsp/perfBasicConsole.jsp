<%@page import="com.perfmath.spring.soba.util.PerfBasicUtil"%>
<%@ include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html bgcolor='blue'>
<head>
<title>PerfBasic Console</title>
</head>
<%@ include file="banner.jsp"%>
<body bgcolor="F7FFCE">
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
		<h2>PerfBasic profiling status: ${profilingStatus}</h2>
		<hr>
		Turn perfBasic profiling on/off at runtime <br>
		<form name="resetProfiling" method="POST"
			action="<c:url value="/perfBasicConsole" />">
			<select name="profilingStatus">
				<option value="enabled">ENABLE</option>
				<option value="disabled">DISABLE</option>
			</select> <input type="submit" value="Apply" />
			</td>
		</form>
	</center>
</body>
</html>
