<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Customer Details</title>
</head>

<body>
<table border="1">
  <tr>
    <td>CustomerId</td>
    <td>${customer.customerId}</td>
  </tr>
  <tr>
    <td>Last Name</td>
    <td>${customer.lastName}</td>
  </tr>
  <tr>
    <td>Zipcode</td>
    <td>${customer.zipcode}</td>
  </tr>
  <tr>
    <td>Create Date</td>
    <td><fmt:formatDate value="${customer.createDate}" pattern="yyyy-MM-dd" /></td>
  </tr>
</table>
<a href="${flowExecutionUrl}&_eventId=newSearch">New Search</a>
</body>
</html>
