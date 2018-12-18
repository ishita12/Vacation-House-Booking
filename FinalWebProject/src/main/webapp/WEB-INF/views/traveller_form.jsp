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


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form action="${contextPath}/traveller/entry" commandName="travel"
		method="post">

		<table>
			<tr>
				<td>Enter the city you want to travel:</td>
				<td><form:input path="city" size="30" type="text" required="required" />
					<font color="red"><form:errors path="city" /></font></td>
			</tr>

		
			
			
			<tr>
				<td>Available from:</td>
				<td><form:input path="sDate" size="30"
						type="date"   required="required" />
						 <font color="red"><form:errors 
 							path="sDate" /></font> 
						
							</td>
			</tr>

<tr>
				<td>To:</td>
				<td><form:input path="eDate" size="30"
						type="date" required="required" />
						 <font color="red"><form:errors 
						path="eDate" /></font></td> 
			</tr>
						

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr> 
		</table>

	</form:form> 


</body>
</html>