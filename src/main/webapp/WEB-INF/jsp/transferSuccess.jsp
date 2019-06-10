<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Transfer Success</title>
</head>
<%@ include file = "banner.jsp" %>
<body> <center>
<br> 
    <br>
Transfer has been completed successfully for the customer with ID <c:out value="${model.customerId}"/>.
<br> 
    <br>
            <br>

     <a href="<c:url value="/transferForm/${model.customerId}"/>">Initiate another transfer</a>
    <br> <br>
    <br><p> or
        <br>
            <br>

     <a href="<c:url value="/activityList.htm?customerId=${model.customerId}"/>">View Account Activity</a>
    <br> <br>
</center>
</body>
</html>
