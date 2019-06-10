<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Description</title>
</head>

<body>
<h2>SOBA Online Services</h2>
<c:forEach items="${services}" var="service">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${service}"/><br />
</c:forEach>
<br /><br />
<a href="${flowExecutionUrl}&_eventId=next">More Info </a>
</body>
</html>
