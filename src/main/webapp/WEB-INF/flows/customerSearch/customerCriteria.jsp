<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Customer Criteria</title>
</head>

<body>
<form:form commandName="customerCriteria">
<table>
  <tr>
    <td>Zipcode</td>
    <td><form:input path="zipcode" /></td>
  </tr>
  <tr>
    <td>Last Name</td>
    <td><form:input path="lastName" /></td>
  </tr>
  <tr>
    <td colspan="2">
      <input type="submit" name="_eventId_search" value="Search" />
    </td>
  </tr>
</table>
</form:form>
</body>
</html>
