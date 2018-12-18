<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h1>MY REQUESTS UPDATES</h1>

<h2> place details are</h2>
                <table content editable="true" border="1">
                    <thead>
                        <tr>
                        
                            <td>Traveller Name</td> 
                            <td>Card Number</td> 
                            <td>CVV</td> 
                            <td>Expiry Date</td>
                            <td>Payment date</td> 
                           
                        </tr>
                    </thead>
                      <tbody>
	<form:form action="${contextPath}/travellerhost/payRequest" commandName="payment"
		method="post">
		<td>            <form:hidden id="travellerID" path="travellerID" name="travellerID" value="${travellerID}"/></td>
	
	
	  <tr>
                     <td> ${user.firstName}</td>
           
           <td><form:input path="cardNumber" size="30" type="text" required="required" />
				 <td><form:input path="cvv" size="30" type="text" required="required" />
				
             <td><form:input path="expiryDate" size="30" type="date" required="required" />
				
              
               <td> <input type="submit"  value="confirm"> </td>
           
				

	</form:form>
</tbody>
</table>




</body>
</html>