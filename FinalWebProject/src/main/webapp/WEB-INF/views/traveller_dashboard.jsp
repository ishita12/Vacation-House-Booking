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
welcome ${user.firstName}




<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	

<a href="${contextPath}">Go Back</a><br/>

<form> 
<input type="hidden" name="userid" value="${user}"> 
</form> 
 
 
<%-- 	<a href="${contextPath}/traveller/entry">Let the hosts know where do you want to go</a> --%>
	
	
	
	<form method="post" action="${contextPath}/traveller/search">
	
	Search place <input type="text" name="searchplace" />
	<input type="submit" value="search"/>
	
	
	
	</form>
	
	
	<a href="${contextPath}/travellerhost/updates">My Requests Updates</a>
	
	

	
	
	
	

</body>
</html>