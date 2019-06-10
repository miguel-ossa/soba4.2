<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Customer List</title>
</head>
<body>
<table border="1">
  <tr>
  	<th>customerId</th>
    <th>email</th>
    <th>Last Name </th>
    <th>First Name</th>
    <th>createDate</th>
    <th>zipcode</th>
  </tr>
  <c:forEach items="${customers}" var="customer">
  <tr>
    <td>
      <a href="${flowExecutionUrl}&_eventId=select&customerId=${customer.customerId}">
        ${customer.customerId}
      </a>
    </td>
    <td>${customer.email}</td>
    <td>${customer.lastName}</td>
    <td>${customer.firstName}</td>
    <td><fmt:formatDate value="${customer.createDate}" pattern="yyyy-MM-dd" /></td>
    <td>${customer.zipcode}</td>
  </tr>
  </c:forEach>
</table>
<a href="${flowExecutionUrl}&_eventId=newSearch">New Search</a>
</body>
</html>
