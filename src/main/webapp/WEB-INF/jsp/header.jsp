<%--
  Created by IntelliJ IDEA.
  User: Neo
  Date: 25/05/2021
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <!-- Fonts and icons -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <!-- Black Dashboard CSS -->
    <link href="<c:url value="/assets/css/black-dashboard.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/css/nucleo-icons.css" />" rel="stylesheet">
    <link href="<c:url value="/assets/css/style.css" />" rel="stylesheet">

    <!-- Core JS Files -->
    <script src="<c:url value="/assets/js/core/jquery.min.js"/>"></script>
    <script src="<c:url value="/assets/js/core/popper.min.js"/>"></script>
    <script src="<c:url value="/assets/js/core/bootstrap.min.js"/>"></script>
</head>
<body>
<nav class="navbar sticky-top navbar-expand-lg bg-primary">
    <div class="container">
        <span>K</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        &#9660;
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Mes informations personnelles</a>
                        <a class="dropdown-item" href="/deconnexion">Déconnexion</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
