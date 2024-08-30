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
            background-color: #1e9a9a;
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
            color: #1e9a9a;
        }
    </style>
</head>
<body>
    <h2>Project Management</h2>

    <div class="button-container">
        <a href="/employee_management_assignment/employees/list" style="text-decoration:none;">
            <button type="button">View Employee</button>
        </a>
        <a href="/employee_management_assignment/projects/add" style="text-decoration:none;">
            <button type="button">Add Project</button>
        </a>
    </div>

    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Project Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.projectId}</td>
                    <td>${project.projectName}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <td class="action-icons">
                        <a href="/employee_management_assignment/projects/update?projectId=${project.projectId}"><i class="fas fa-edit"></i></a>
                        <a href="/employee_management_assignment/projects/delete?projectId=${project.projectId}"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
