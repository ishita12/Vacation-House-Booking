<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Welcome ${user.firstName}
${user.userID}


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<a href="${contextPath}/host/goback">Go Back</a><br/>
	

<a href="${contextPath}/host/enter">fill the details</a><br/>

	




<form method="post" action="${contextPath}/host/search">
	
	
	<input type="submit" value="get your requests">
	
	
	
	</form>



</body>
</html>