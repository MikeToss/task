<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="https://www.flaticon.com/svg/static/icons/svg/1126/1126202.svg">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Department</title>
    <style>
        <%@include file="css/department_style.css" %>
    </style>

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/create_department">Create new Department<span
                            class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>

<div class="container text-center" style="width: 500%">
    <div class="card border-0 shadow my-5">
        <div class="card-body p-5">
            <h1>Departments</h1><br>
            <table class="table center">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Amount of Employees</th>
                    <th>View Employees</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td><c:out value="${department.getId()}"/></td>
                        <td><c:out value="${department.getTitle()}"/></td>
                        <td><c:out value="${department.getDepartmentEmployees().size()}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/view_employee?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-success">View</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/update_department?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-info">Update</button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/delete_department?id=<c:out value='${department.getId()}'/>">
                                <button type="button" class="btn btn-outline-danger">Delete</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
