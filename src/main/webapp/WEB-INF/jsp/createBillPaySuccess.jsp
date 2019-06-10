<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Create Bill Pay Success</title>
</head>
<%@ include file = "banner.jsp" %>
<body> <center>
<br> 
    <br>
The bill pay has been created successfully for the customer with ID <c:out value="${model.customerId}"/>.
<br> 
    <br>
            <br>

     <a href="<c:url value="/createBillPayForm/customerId/${model.customerId}/accountId/${model.accountId}"/>">Create a new Bill Pay</a>
    <br> <br>
    <br><p> or
        <br>
            <br>

     <a href="<c:url value="/activityList.htm?customerId=${model.customerId}"/>">View Account Activity</a>
    <br> <br>
</center>
</body>
</html>
