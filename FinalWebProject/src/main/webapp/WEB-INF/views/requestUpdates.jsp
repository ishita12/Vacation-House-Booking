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

	<a href="${contextPath}/travellerhost/goback">GO BACK</a>

	  <h2> My request Updates</h2>
                <table content editable="true" border="1">
                    <thead>
                        <tr>
                        
                            <td>Traveller Name</td> 
                            <td>city</td> 
                            <td>start Date</td> 
                            <td>End Date</td>
                            <td>Status</td> 
                           
                        </tr>
                    </thead>
                      <tbody>
                    <c:forEach items="${tReq}" var="t"> 
                    
                    <tr>
                    <form:form action="${contextPath}/travellerhost/pay" commandName="tRequestD"
		method="post">
		
 	<td>            <input id="requestID" name="requestID" type="hidden" value="${t.requestID}"/></td> 
	<td>            <input id="city" name="city" type="hidden" value="${t.city}"/></td>
<%-- 	<td>            <input id="dateEnded" name="dateEnded" type="hidden" value="${t.dateEnded}"/></td> --%>
<%-- 	<td>            <input id="dateStarted" name="dateStarted" type="hidden" value="${t.dateStarted}"/></td> --%>
	<td> <input id="placeID" name="placeID" type="hidden" value="${t.placeID}"/></td>
<td> <input id="travelId" name="travelId" type="hidden" value="${t.travelId}"/></td>
<td> <input id="status" name="status" type="hidden" value="${t.status}"/></td>

<td> <input id="travellerName" name="travellerName" type="hidden" value="${t.travellerName}"/></td>

<%-- 	<td> <input id="dateStarted" name="dateStarted" type="hidden" value="${p.dateStarted}"/></td> --%>
<%-- 	<td> <input id="dateEnded" name="dateEnded" type="hidden" value="${p.dateEnded}"/></td> --%>
	
	
	
	
	  <tr>
                     <td> ${user.firstName}</td>
           
            <td> ${t.city}</td>
             <td> ${t.dateStarted}</td>
             
              <td> ${t.dateEnded}</td>
              <td>${t.status}</td>
           <td> 
           
           <c:if test="${t.status=='In progress'}">
           
           <td><input type="submit"  value="pay" name="r${t.requestID}"> </td>
           <td> <input type="submit"  value="cancel" name="p${t.requestID}"> </td>
           </c:if>
          
          <td>
           <c:if test="${t.status=='Reject'}">
           Your Request has been rejected
           </c:if>
          </td>
          
           <td>
           <c:if test="${t.status=='Cancelled'}">
           You have cancelled your request
           </c:if>
          </td>
          <td><c:if test="${t.status=='Approve'}">
           Your Request has been approved
            <td> <input type="submit"  value="cancel" name="p${t.requestID}"> </td>
          
           </c:if></td>
          
            </tr>
	
	
	
		  
			</form:form>
                    </c:forEach>
                    </tbody>
                </table>  
 


</body>
</html>