<%@ include file="include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Admin Console</title>
</head>
<%@ include file = "banner.jsp" %>
<body>
<center>
<h1> Admin Console</h1>
<br> 
 <br> 
     To change logging level, click <a href="<c:url value="log4jConsole"/>">here</a> <p>
     To manage perfBasic profiling, click <a href="<c:url value="perfBasicConsole"/>">here</a>  
    <br>
    <br>
    </center>
</body>
</html>