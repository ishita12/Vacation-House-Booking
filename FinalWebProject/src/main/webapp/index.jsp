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
<center><h1>Welcome to Couch Surfing</h1></center>


<a href="user/register.htm">Register a new User</a><br/>

	<h2>Login</h2>
	<form action="${contextPath}/user/login" method="post">
	
		<table>
		<tr>
		    <td>User Name:</td>
		    <td><input name="userName" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		Login as:
		<select id="roletype" name="role">
            <option value="host">host</option>
            <option value="traveller">traveller</option>
            
        </select>
		
		
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form>



</body>
</html>