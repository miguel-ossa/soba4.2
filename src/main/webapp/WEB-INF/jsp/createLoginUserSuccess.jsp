<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Create Online Login Access Success</title>
</head>
<%@ include file = "banner.jsp" %>
<body> <center> <br> <br>
Your online banking login ID <FONT FACE="Springfield, Extra Bold" COLOR="blue"><c:out value="${model.username}"/> 
</FONT>has been created successfully 
 for your customer ID <c:out value="${model.customerId}"/>.
<br> 
 <br>
     <a href="<c:url value="/createAccountForm/customerId/${model.customerId}"/>">Create</a> an account now.
     
    <br> <br>

<br></center>
</body>
</html>
