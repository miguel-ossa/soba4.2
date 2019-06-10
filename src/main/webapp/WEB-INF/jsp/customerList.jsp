<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
    <h3>Customers</h3>
    <c:forEach items="${model.customers}" var="cust">
      <c:out value="${cust.customerId}"/> <i> <c:out value="${cust.createDate}"/></i><br><br>
    </c:forEach>
    <br>
    <a href="<c:url value="index.jsp"/>">Home</a>
    <br>
  </body>
</html>