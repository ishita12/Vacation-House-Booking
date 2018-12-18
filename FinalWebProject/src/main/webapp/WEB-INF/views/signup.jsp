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

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register" commandName="user"
		method="post" enctype="multipart/form-data">

		<table>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" /><font color="red"><form:errors
							path="firstName" /></font>
					
					</td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" /><font color="red"><form:errors
							path="lastName" /></font>
					</td>
			</tr>
			
			
			<tr>
				<td>Phone Number:</td>
				<td><form:input path="phonenumber" size="30"
						type="number" /> <font color="red"><form:errors
							path="phonenumber" /></font>
							</td>
			</tr>

<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30"
						type="email" /><font color="red"><form:errors
							path="email" /></font> </td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td><form:input path="userName" size="30" 
				type="text" /><font color="red"><form:errors
							path="userName" /></font>
					</td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						/><font color="red"><form:errors
							path="password" /></font> </td>
			</tr>
			
			<tr>
				<td>Confirm Password:</td>
				<td><form:password path="confirmPassword" size="30"
						/><font color="red"><form:errors
							path="confirmPassword" /></font></td>
			</tr>
			
			<tr>
				<td>Upload Photo:</td>
				<td><form:input path="photo" size="30"
					type="file"	/> <font color="red"><form:errors
							path="photo" /></font></td>
			</tr>


<tr>
				<td>Register as:</td>
				<td><form:select path="role">
                        <form:option value="host">Host</form:option>
                        <form:option value="traveller">Traveller</form:option>
                    </form:select></td>
			</tr>
			

			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>

	</form:form>


</body>
</html>