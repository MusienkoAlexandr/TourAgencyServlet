<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<headerJSP>

    <nav class="orange lighten-1" role="navigation">
        <div class="nav-wrapper container">
            <a id="logo-container" href="#" class="brand-logo">Tour Agency</a>
            <ul class="right hide-on-med-and-down">
                <c:if test="${not empty sessionScope.login}">
                    <li><a href="personal"><i class="material-icons left">account_box</i><c:out value="${login}"/></a>
                    </li>
                    <li><a href="logout" class="waves-effect waves-light btn">Log out</a></li>
                </c:if>
                <c:if test="${empty sessionScope.login}">
                    <li><a href="login" class="waves-effect waves-light btn">Log in</a></li>
                    <li><a href="register" class="waves-effect waves-light btn">Register</a></li>
                </c:if>
                <li>
                    <a class='dropdown-trigger' href='#' data-target='dropdown1'>
                        <i class="material-icons">language</i>
                    </a>

                    <ul id='dropdown1' class='dropdown-content'>
                        <li><a href="#!">English</a></li>
                        <li><a href="#!">Українська</a></li>

                    </ul>
                </li>
            </ul>
            <ul class="right hide-on-med-and-down">
                <li><a href="main">Main</a></li>
                <li><a href="tours">Tours</a></li>
                <c:if test="${sessionScope.role == 'MANAGER'}">
                    <li><a href="orders">Orders</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li><a href="orders">Orders</a></li>
                    <li><a href="users">Users</a></li>
                </c:if>
            </ul>

            <ul id="nav-mobile" class="sidenav">
                <li><a href="#">Navbar Link</a></li>
            </ul>
            <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <c:if test="${not empty sessionScope.error}">
                <div class="section">
                    <div class="col s3 card-panel red lighten-1">
                        <span class="white-text"> <c:out value="${sessionScope.error}"/></span>
                    </div>
                </div>
                <c:remove var="error" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.success}">
                <div class="section">
                    <div class="col s3 card-panel green lighten-1">
                        <span class="white-text"> <c:out value="${sessionScope.success}"/></span>
                    </div>
                </div>
                <c:remove var="success" scope="session"/>
            </c:if>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var elems = document.querySelectorAll('.dropdown-trigger');
            var instances = M.Dropdown.init(elems, {});
        });
    </script>
</headerJSP>