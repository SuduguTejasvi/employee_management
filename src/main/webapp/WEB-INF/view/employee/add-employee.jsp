<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
<h2>Add Employee</h2>
<form:form action="${pageContext.request.contextPath}/employees/save" method="post" modelAttribute="employee">
    <form:hidden path="employeeId"/>

    First Name: <form:input path="firstName"/>
    <form:errors path="firstName" cssClass="error"/>
    <br><br>

    Last Name: <form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error"/>
    <br><br>

    Email: <form:input path="email"/>
    <form:errors path="email" cssClass="error"/>
    <br><br>

    Hire Date: <form:input path="hireDate" type="date"/>
    <form:errors path="hireDate" cssClass="error"/>
    <br><br>

    <label>Projects:</label><br>
    <c:forEach var="project" items="${projects}">
        <input type="checkbox" name="projectIds" value="${project.projectId}"
               <c:if test="${employeeProjectIds != null && employeeProjectIds.contains(project.projectId)}">checked</c:if>/>
        ${project.projectName}<br>
    </c:forEach>
    <form:errors path="projectIds" cssClass="error"/>
    <br>

    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
