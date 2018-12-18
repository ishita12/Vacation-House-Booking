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


                        <h2>Enter the details of places</h2>
                <table content editable="true">
                    <thead>
                        <tr>
                        
                            <td>CITY</td> 
                            <td>RENT</td> 
                            <td>FROM</td> 
                            <td>TO</td> 
                            <td>UPLOAD IMAGES</td> 
                            <td>SELECT IMAGES</td> 
                        </tr>
                    </thead>
                    <tbody>
                    
                    <tr>
                    <form:form action="${contextPath}/host/upload" commandName="place"
		method="post" enctype="multipart/form-data">
		
	
                  <td><form:input path="placeName" size="30" type="text"  />
					<font color="red"><form:errors path="placeName" /></font></td>
					<td><form:input path="rent" size="30" type="number"  min="1" step="0.01"/>
					<font color="red"><form:errors path="rent" /></font></td>
					<td><form:input path="startDate" 
						type="date" /> <font color="red"><form:errors
							path="startDate" /></font>
						
							</td>
							<td><form:input path="endDate" 
						type="date"  /> <font color="red"><form:errors
							path="endDate" /></font></td>
							<td><form:input path="file_name" size="30" 
				type="text"  required="required" />
					<font color="red"><form:errors path="file_name" /></font></td>
							<td><form:input path="photo" size="30" type="file" multiple="multiple" required="required" /><br/>
			
					<font color="red"><form:errors path="photo" /></font></td>
					<td>
					 <input type="submit"  value="submit"> 
					 </td>
					 </form:form> 
			</tr>
			
                   <form:form action="${contextPath}/logout/" method="post">
                   
                   
                   <input type="submit" value="logout">
                   </form:form>
                    </tbody>
                </table>  
                <br/>
               
                <h4>**All fields are required</h4>
           
	
</body>
</html>