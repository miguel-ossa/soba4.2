<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
    <h3>Accounts</h3>
    <c:forEach items="${model.accounts}" var="account">
      <c:out value="${account.accountId}"/> <i> <c:out value="${account.balance}"/></i><br><br>
    </c:forEach>
    <br>
    <a href="<c:url value="/"/>">Home</a>
    <br>
  </body>
</html>