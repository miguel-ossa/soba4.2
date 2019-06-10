<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Create ACL Tx Success</title>
</head>
<%@ include file = "banner.jsp" %>
<body> <center>
<br> 
    <br>
The Transaction has been created successfully for the customer with ID <c:out value="${model.customerId}"/>.
<br> 
    <br>
            <br>

     <a href="<c:url value="/createAclTxForm/${model.customerId}"/>">Create a New Transaction</a>
    <br> <br>
    <br><p> or
        <br>
            <br>

     <a href="<c:url value="/activityList.htm?customerId=${model.customerId}"/>">View Account Activity</a>
    <br> <br>
</center>
</body>
</html>
