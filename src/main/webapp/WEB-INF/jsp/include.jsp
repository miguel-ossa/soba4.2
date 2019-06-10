<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<% long beginPageLoadTime = System.currentTimeMillis();%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--
<center>
<form STYLE="color: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #72A4D2;" >
<input type="button" value="Back to Previous Page" 
onClick="javascript: history.go(-1)">
</form>
</center>
-->