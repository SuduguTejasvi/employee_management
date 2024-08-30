<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            padding-top: 50px;
            background-color: #f5f8f8;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .button-container {
            text-align: center;
            margin-bottom: 20px;
        }

        button {
            background-color: #24babb;
            color: white;
            padding: 10px 20px;
            margin: 5px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
        }

        button:hover {
            background-color: #24babb;
        }

        table {
            width: 60%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #24babb;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        .action-icons a {
            color: #333;
            text-decoration: none;
            margin: 0 5px;
            font-size: 18px;
        }

        .action-icons a:hover {
            color: #24babb;
        }
    </style>
</head>
<body>
<h2>Employee Management</h2>

<div class="button-container">
    <a href="${pageContext.request.contextPath}/employees/add" style="text-decoration:none;">
        <button type="button">Add Employee</button>
    </a>
    <a href="${pageContext.request.contextPath}/projects/list" style="text-decoration:none;">
        <button type="button">View Project</button>
    </a>
</div>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Hire Date</th>
        <th>Projects</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.email}</td>
            <td>${employee.hireDate}</td>
            <td>${employee.projectResult}</td>
            <td class="action-icons">
                <a href="${pageContext.request.contextPath}/employees/update?employeeId=${employee.employeeId}"><i
                        class="fas fa-edit"></i></a>
                <a href="${pageContext.request.contextPath}/employees/delete?employeeId=${employee.employeeId}"><i
                        class="fas fa-trash-alt"></i></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
