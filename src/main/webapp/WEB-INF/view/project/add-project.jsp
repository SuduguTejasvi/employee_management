<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>Add Project</title>
</head>
<body>
<h2>Add Project</h2>
<br><br>
<form:form action="${pageContext.request.contextPath}/projects/save" modelAttribute="projects">
    <form:hidden path="projectId"/>
    Project Name: <form:input path="projectName"/>
    <br><br>
    Start date: <form:input path="startDate" type="date"/>
    <br><br>
    End date: <form:input path="endDate" type="date"/>
    <br><br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
