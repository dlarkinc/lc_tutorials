<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Applicant</title>
</head>
<body>
	<h2>Applicant Details:</h2>
	<table>
		<tr>
			<td>Name:</td>
			<td>${applicant.name}</td>
		</tr>
		<tr>
			<td>Age:</td>
			<td>${applicant.age}</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td>${applicant.email}</td>
		</tr>
	</table>

</body>
</html>