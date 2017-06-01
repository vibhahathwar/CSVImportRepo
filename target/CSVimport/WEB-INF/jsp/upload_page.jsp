<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CSV Import Application</title>
</head>
<body>
<spring:url value="/doUpload" var="doUploadURL"/>
	<h3>Upload CSV File...</h3>
	<form:form method="post" modelAttribute="formupload" action="${doUploadURL}" enctype="multipart/form-data">
	<form:input path="files" type="file" multiple="multiple"/></br></br>
	<form:errors path="files"/></br>
	<button type="submit"> upload</button>
	</form:form>
	
</body>
</html>