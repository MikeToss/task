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

    <title>New product addition</title>
    <style>
        <%@include file="css/department_style.css" %>
    </style>
    <style>
        body{
            background: url("https://dotvox.com/wp-content/uploads/2017/04/AdobeStock_100514450_crp2.jpg");
        }
    </style>

</head>
<body class="bg-light text-dark"><br>

<div class="py-md-1" style="text-align: center;">
    <h1 style="color: white">Update ${title} </h1>
</div>

<div class="mx-auto text-danger py-md-1" >
    <h4>
        <strong>${departmentAlreadyExistsMessage}</strong>
        <strong>
            <div class="registrationFormAlert" id="divCheckPasswordMatch1"></div>
        </strong>
    </h4>
</div>

<div class="mx-auto text-danger py-md-1">
    <h4 >
        <strong>${isTitleLengthInvalid}</strong>
        <strong>
            <div class="registrationFormAlert" id="divCheckPasswordMatch2"></div>
        </strong>
    </h4>
</div>


<form class="needs-validation" method="post" novalidate>
    <div class="mx-auto" style="width: 400px;">

        <div class="form-group px-md-5">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="${title}" required>
            <div class="invalid-feedback">This field is mandatory.</div>
        </div>

    </div>
    <div class="container" style="text-align: center">
        <button type="submit" class="btn btn-success">Update</button>
        <a href="${pageContext.request.contextPath}/departments">
            <button type="button" class="btn btn-primary">Return</button>
        </a>
    </div>

</form>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"></script>
</body>
</html>