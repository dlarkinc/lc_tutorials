<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<style>
		error {
		    color: red;
		}
	</style>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<form:form action="${contextPath}/applicant" modelAttribute="applicant" method="post">
   <p>Name: <form:input path="name" />
   <form:errors path="name" cssclass="error"></form:errors></p>
   <p>Age: <form:input path="age" />
   <form:errors path="age" cssclass="error"></form:errors></p>
   <p>Email: <form:input path="email" />
   <form:errors path="email" cssclass="error"></form:errors></p>
   <p>
      <input type="submit" value="Submit" />
      <input type="reset" value="Reset" />
   </p>
</form:form> 


</body>
</html>