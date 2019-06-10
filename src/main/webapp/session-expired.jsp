<%@ include file = "WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Session Timed Out</title>
</head>
<%@ include file = "WEB-INF/jsp/banner.jsp" %>
<body> <center>
<br><br> You session has expired. To log in again, click
<a href="<c:url value="/"/>">  here.

</center>
</body>
</html>
