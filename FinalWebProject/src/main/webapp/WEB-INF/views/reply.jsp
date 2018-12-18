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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

                        <h2>REPLY</h2>
                <table content editable="true">
                    <thead>
                        <tr>
                        
                            
                            <td>TO</td> 
                            <td>FROM</td> 
                            <td>STATUS</td> 
                            <td>MESSAGE</td> 
                          
                        </tr>
                    </thead>
                    <tbody>
                   
                    <tr>
                    <form:form action="${contextPath}/host/reply" commandName="reply"
		method="post" enctype="multipart/form-data">
		
	
                  <td><input value="${sessionScope.travellerName}" size="30" type="text" required="required" readonly />
					<td><input value="${user.firstName}" size="30" required="required" />
					</td><br />
					<td><form:radiobutton path="status" value="Reject" checked="checked"/>Reject
<form:radiobutton path="status" value="Approve"/>Approve
						
							</td><br />
							<td><form:textarea path="message" rows="5" cols="30" /> <font color="red"><form:errors
							path="message" required="required" /></font></td><br />
							<td>           <input id="hostName" name="hostName" type="hidden" value="${user.firstName}"/></td>
	<td>            <input id="travellerName" name="travellerName" type="hidden" value="${reply.travellerName}"/></td><br />
	
					 <input type="submit"  value="reply"> </td>
					 </form:form> 
			</tr>
			
                    
                    </tbody>
                </table>  
               
               
           













</body>
</html>