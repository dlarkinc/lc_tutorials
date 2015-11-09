<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	<spring:message code="home.title" text="Default Title" />
</h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<p>
	<a href="${contextPath}/?lang=fr">French</a> | <a href="${contextPath}/?lang=en">English</a>
</p>
<P>  The time on the server is ${serverTime}. </P>
<p> One plus seven is ${1+7}. </p>
<p> True or False? ${true or false}</p>
<p> True and False? ${true && false}</p>
<p> Incoming query string: ${pageContext.request.queryString} </p>
<p> User agent: ${header["user-agent"]}</p>
<p> 2nd element in string array: ${stringArray[1]}</p>

<p>Full array list:</p>
<ul>
<c:forEach var="item" items="${stringArray}">
	<li>${item}</li>
</c:forEach>
</ul>


<p>Let's count!</p>
<% for (int i=1; i<=10; i++) { %>
<p> <%= i %> </p> 
<% } %>
<p>All done!</p> 

</body>
</html>
