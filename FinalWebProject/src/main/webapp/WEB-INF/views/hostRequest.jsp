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
 <style>
        body{
            background-color: lightgray;
            
        }
        
    </style>
</head>
<body>
Welcome ${user.firstName}
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


        <h2> place details are</h2>
                <table content editable="true" border="1">
                    <thead>
                        <tr>
                        
                            <td>CITY</td> 
                            <td>START DATE</td> 
                            <td>END DATE</td> 
                            <td>TRAVELLER NAME</td>
                            <td>  PROCESS REQUEST   </td> 
                           <td>STATUS</td>
                        </tr>
                    </thead>
                      <tbody>
                    <c:forEach items="${travelRequest}" var="p"> 
                    
                    <tr>
                    <form:form action="${contextPath}/host/processRequest" commandName="travelRequest"
		method="post" enctype="multipart/form-data">
		
	<td>            <input id="placeID" name="placeID" type="hidden" value="${p.placeID}"/></td>
	<td>            <input id="requestID" name="requestID" type="hidden" value="${p.requestID}"/></td>
	<td>            <input id="city" name="city" type="hidden" value="${p.city}"/></td>
	
	<td>            <input id="travellerName" name="travellerName" type="hidden" value="${p.travellerName}"/></td>
	<td> <input id="status" name="status" type="hidden" value="${p.status}"/></td>
<%-- 	<td> <input id="dateStarted" name="dateStarted" type="hidden" value="${p.dateStarted}"/></td> --%>
<%-- 	<td> <input id="dateEnded" name="dateEnded" type="hidden" value="${p.dateEnded}"/></td> --%>
	<td> <input id="travelId" name="travelId" type="hidden" value="${p.travelId}"/></td>
	
	
	
	
	  <tr>
                     <td> ${p.city}</td>
           
            <td> ${p.dateStarted}</td>
             <td> ${p.dateEnded}</td>
             
              <td> ${p.travellerName}</td>
              <td>${p.status}</td>
           <td> <input type="submit"  value="process request" name="r${p.requestID}"> </td>
            </tr>
	
	
	
		  
			</form:form>
                    </c:forEach>
                    </tbody>
                </table>  
 

</body>
</html>