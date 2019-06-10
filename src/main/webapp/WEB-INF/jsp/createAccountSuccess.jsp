<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Create Account Success</title>
</head>
<%@ include file = "banner.jsp" %>
<body> <center>

<br> <br>
Your account with your customer ID <c:out value="${model.customerId}"/> has been created successfully. <p>
Please <a href="<c:url value="/"/>">log off</a> and sign in to access your account(s).
<br> 
 <br>

    </center>
</body>
</html>
