<%@ include file = "../WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello jQuery for Spring REST</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="${ctx}/rest/js/rest.js"></script>
    </head>

    <body>
        <div>
            <p class="transactionId">The tx ID is </p>
            <p class="amount">The amount is </p>
        </div>
    </body>
</html>