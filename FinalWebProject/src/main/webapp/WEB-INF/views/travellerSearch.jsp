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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 70%;
      margin: auto;
  }
  
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
                        
                            <td>placeName</td> 
                            <td>startDate</td> 
                            <td>endDate</td> 
                            <td>rent</td> 
                           <td>Images</td>
                        </tr>
                    </thead>
                      <tbody>
                    <c:forEach items="${requestScope.travel}" var="p"> 
                    
                    <tr>
                    <form:form action="${contextPath}/travellerhost/request" commandName="place"
		method="post" enctype="multipart/form-data">
		
	<td>            <input id="placeID" name="placeID" type="hidden" value="${p.placeID}"/></td>
	<td>            <input id="placeName" name="placeName" type="hidden" value="${p.placeName}"/></td>
	<td>            <input id="rent" name="rent" type="hidden" value="${p.rent}"/></td>
	<td>            <input id="startDate" name="startDate" type="hidden" value="${p.startDate}"/></td>
		<td>            <input id="endDate" name="endDate" type="hidden" value="${p.endDate}"/></td>
<td>	<input id="travellerEmail" name="travellerEmail" type="hidden" value="${requestScope.temail}" /></td>
	  <tr>
                     <td> ${p.placeName}</td>
           
            <td> ${p.startDate}</td>
             <td> ${p.endDate}</td>
             
              <td> ${p.rent}</td>
             <c:forEach items="${p.images}" var="i">
           <td> <img height="150" width="150" src="${i.fileName}" /></td>
           </c:forEach>
           <td> <input type="submit"  value="request" name="r${p.placeID}"> </td>
             <td> <input type="submit"  value="cancel" name="c${p.placeID}"> </td>
            </tr>
	
	
	
		  
			</form:form>
                    </c:forEach>
                    </tbody>
                </table>  

</body>
</html>