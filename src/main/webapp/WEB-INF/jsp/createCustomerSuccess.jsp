<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<%@ include file="banner.jsp"%>
<title>Create Customer Success</title>
</head>

<body>
	<center>
		Your customer ID
		<c:out value="${model.customerId}" />
		has been created successfully. <br> <br> Use your customer
		ID to <a
			href="<c:url value="/createLoginUserForm/customerId/${model.customerId}"/>">
			create</a> your login ID for banking online. <br> <br>
	</center>
</body>
</html>
