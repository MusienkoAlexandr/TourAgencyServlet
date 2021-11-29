<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tours</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="resources/css/noUiSlider/nouislider.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/wnumb/1.2.0/wNumb.min.js"
            integrity="sha512-igVQ7hyQVijOUlfg3OmcTZLwYJIBXU63xL9RC12xBHNpmGJAktDnzl9Iw0J4yrSaQtDxTTVlwhY730vphoVqJQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="resources/js/noUiSlider/nouislider.min.js"></script>


    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
          integrity="sha512-UJfAaOlIRtdR+0P6C3KUoTDAxVTuy3lnSXLyLKlHYJlcSU8Juge/mjeaxDNMlw9LgeIotgz5FP8eUQPhX1q10A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"
            integrity="sha512-NiWqa2rceHnN3Z5j6mSAvbwwg3tiwVNxiAQaaSMSXnRRDh5C2mk/+sKQRw8qjV1vN4nf8iK2a0b048PnHbyx+Q=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>


    <link rel="stylesheet" href="resources/css/my.css">

</head>
<body>
<m:headerJSP/>
<c:set var="tours" value="${requestScope.tours}"/>
<c:set var="total_pages" value="${requestScope.total_pages}"/>
<c:set var="page" value='${(not empty param.page) ? param.page : "1"}'/>
<script>

    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('.materialboxed');
        M.Materialbox.init(elems, {});
        M.CharacterCounter.init(document.querySelector('.materialize-textarea'), {});

        let element = document.querySelector('.filter-action-btn');
        var instance_filter = M.FloatingActionButton.init(element, {hoverEnabled: false, direction: 'bottom'});
        document.body.removeEventListener('click', instance_filter._handleDocumentClickBound, true);
        instance_filter.el.removeEventListener('click', instance_filter._handleFABClickBound);
        instance_filter.open();
        element = document.querySelector('.sort-action-btn');
        var instance_sort = M.FloatingActionButton.init(element, {hoverEnabled: false, direction: 'bottom'});
        document.body.removeEventListener('click', instance_sort._handleDocumentClickBound, true);
        instance_sort.el.removeEventListener('click', instance_sort._handleFABClickBound);
        instance_sort.open();
    });


    function filterButtonOpen() {
        let btn = document.querySelector("#filter-button");
        let instance = M.FloatingActionButton['getInstance'](btn);
        instance.open();
    }

    function displayInput(element) {
        let card = element.parentElement.querySelector(".card-panel");
        if (card.classList.contains("invisible")) {
            card.classList.replace("invisible", "visible");
        } else {
            card.classList.replace("visible", "invisible");
        }
    }

    function sortButton(element) {
        let parent = element.parentElement;
        let sort_by_input = document.querySelector("#sort-by");
        let sort_order_input = document.querySelector("#sort-order");
        if (parent.classList.contains("sort-none")) {
            sort_by_input.setAttribute("value", element.getAttribute("input-value"));
            sort_order_input.setAttribute("value", "asc");
            parent.classList.replace("sort-none", "sort-asc");
        } else if (parent.classList.contains("sort-asc")) {
            sort_by_input.setAttribute("value", element.getAttribute("input-value"));
            sort_order_input.setAttribute("value", "desc");
            parent.classList.replace("sort-asc", "sort-desc");
        } else if (parent.classList.contains("sort-desc")) {
            sort_by_input.removeAttribute("value");
            sort_order_input.removeAttribute("value");
            parent.classList.replace("sort-desc", "sort-none");
        }

        parent.parentElement.querySelectorAll("li:not(#" + parent.id + ")")
            .forEach(value => {
                value.classList.replace("sort-asc", "sort-none");
                value.classList.replace("sort-desc", "sort-none")
            });
    }

    function searchPage(number) {
        let page_input = document.querySelector("#page");
        page_input.setAttribute("value", number);
        submitForm(page_input.form);
    }

    function submitForm(form) {
        let controls = form.elements;
        for (let i = 0, iLen = controls.length; i < iLen; i++) {
            controls[i].disabled = (controls[i].value === '' || controls[i].value === undefined);
        }
        form.submit();
    }

</script>
<form id="search" method="get">
    <input id="page" type="hidden" name="page">
    <input id="sort-by" type="hidden" name="sort-by">
    <input id="sort-order" type="hidden" name="sort-order">
</form>
<div class="custom_container">
    <div class="row">
        <div id="sort_panel" class="col s2">
            <div class="sort-action-btn fixed-action-btn left-align" style="position: relative; float: left">
                <a class="btn-floating btn-large blue">
                    <i class="large material-icons">sort</i>
                </a>
                <ul style="position: relative;  width: max-content; text-align: left; top: 0; padding-left: 7px;">
                    <li id="price_sort" class="sort-none">
                        <a class="btn-floating red" style="vertical-align: top" onclick="sortButton(this)"
                           input-value="price">
                            <i class="material-icons">payments</i>
                        </a>
                        <i id="price_up" class="small material-icons arrow-up">arrow_drop_up</i>
                        <i id="price_down" class="small material-icons arrow-down">arrow_drop_down</i>
                    </li>
                    <li id="date_sort" class="sort-none"><a class="btn-floating yellow darken-1"
                                                            style="vertical-align: top" onclick="sortButton(this)"
                                                            input-value="date"><i class="material-icons">event</i></a>
                        <i id="date_up" class="small material-icons arrow-up">arrow_drop_up</i>
                        <i id="date_down" class="small material-icons arrow-down">arrow_drop_down</i>
                    </li>
                    <li id="nights_sort" class="sort-none"><a class="btn-floating green" style="vertical-align: top"
                                                              onclick="sortButton(this)" input-value="nights"><i
                            class="material-icons">nightlight_round</i></a>
                        <i id="nights_up" class="small material-icons arrow-up">arrow_drop_up</i>
                        <i id="nights_down" class="small material-icons arrow-down">arrow_drop_down</i>
                    </li>
                </ul>
            </div>
            <a class="btn right" onclick="submitForm(document.querySelector('#search'))"><i class="material-icons left">search</i>Search</a>
        </div>

        <div id="tour_catalog" class="col s7">
            <c:if test="${empty tours}">
                <span>No tours found.</span>
            </c:if>
            <c:if test="${not empty tours}">
                <c:forEach var="tour" items="${tours}">
                    <div class="card horizontal">
                        <div class="card-image">
                            <img class="materialboxed"
                                 src="resources/img/${tour.id}_1.png"
                                 alt="Image"/>
                        </div>
                        <div class="card-stacked">
                            <div class="card-content">
                                <c:if test="${tour.burning}">
                                    <a class="btn-floating right waves-effect waves-light orange pulse"><i
                                            class="material-icons">local_fire_department</i></a>
                                </c:if>
                                <div class="custom_section"><span class="card-title">${tour.title}</span>
                                </div>
                                <div class="custom_section">
                                    <div class="valign-wrapper custom_section">
                                        <div class="valign-wrapper">
                                            <i class="material-icons-outlined prefix">payments</i>
                                            <p class="col center-align">$ ${tour.price}</p>
                                        </div>
                                        <span class="divider"></span>
                                        <div class="valign-wrapper">
                                            <i class="material-icons-outlined prefix">tour</i>
                                            <p class="col center-align">${tour.tourType}</p>
                                        </div>
                                        <span class="divider"></span>
                                        <div class="valign-wrapper">
                                            <i class="material-icons-outlined prefix">perm_identity</i>
                                            <p class="col center-align">${tour.persons}</p>
                                        </div>
                                    </div>
                                    <div class="valign-wrapper custom_section">
                                        <div class="valign-wrapper">
                                            <i class="material-icons-outlined prefix">home</i>
                                            <p class="col center-align">${tour.roomType}</p>
                                        </div>
                                        <div class="valign-wrapper">
                                            <i class="material-icons-outlined prefix">event</i>
                                            <p class="col center-align">${tour.startDate}</p>
                                        </div>
                                        <div class="valign-wrapper">
                                            <i class="material-icons-outlined prefix">nightlight_round</i>
                                            <p class="col center-align">${tour.nights}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="custom_section">
                                    <p>
                                            ${tour.description.substring(0, 140)}...
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <ul class="pagination center-align">
                    <li class="${(page == "1") ? 'disabled':'waves-effect'}"><a onclick="searchPage(${page - 1})"><i class="material-icons">chevron_left</i></a></li>
                    <c:forEach var="index" begin="1" end="${total_pages}">
                        <li class="${(page == index) ? "active" : "waves-effect"}"><a onclick="searchPage(this.innerHTML)">${index}</a></li>
                    </c:forEach>
                    <li class="${(page == total_pages) ? 'disabled' : 'waves-effect'}"><a onclick="searchPage(${page + 1})"><i class="material-icons">chevron_right</i></a></li>
                </ul>
            </c:if>
        </div>

        <div id="filter_panel" class="col s3">
            <div id="filter-button" class="filter-action-btn fixed-action-btn right-align"
                 style="position: relative; float: right;">
                <a class="btn-floating btn-large red">
                    <i class="large material-icons">filter_alt</i>
                </a>
                <div>
                    <ul class="right"
                        style="position: relative;  width: max-content; text-align: right; top: 0; padding-right: 7px;">

                        <li>
                            <div class="right" style="width: fit-content">
                                <div id="price_input" class="card-panel ${(param.containsKey("min_price") || param.containsKey("max_price")) ? 'visible' : 'invisible'}" style="width: 50%;">
                                    <div class="valign-wrapper">
                                        <div id="min_price_input" class="input-field">
                                            <input id="min_price" name="min_price" type="number" form="search" value="${(param.containsKey("min_price")) ? param.get("min_price") : ""}">
                                            <label for="min_price">Min Price</label>
                                        </div>
                                        <div id="max_price_input" class="input-field">
                                            <input id="max_price" name="max_price" type="number" form="search" value="${(param.containsKey("max_price")) ? param.get("max_price") : ""}">
                                            <label for="max_price">Max Price</label>
                                        </div>
                                    </div>
                                </div>
                                <a class="btn-floating red" style="vertical-align: top"
                                   onclick="displayInput(this)"><i
                                        class="material-icons">payments</i></a>
                            </div>

                        </li>
                        <li>
                            <div class="right" style="width: fit-content">
                                <div id="tour_type_input" class="card-panel ${(param.containsKey("tour_type")) ? 'visible' : 'invisible'}">
                                    <label>
                                        <input name="tour_type" value="vacation" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Vacation</span>
                                    </label>
                                    <label>
                                        <input name="tour_type" value="excursion" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Excursion</span>
                                    </label>
                                    <label>
                                        <input name="tour_type" value="shopping" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Shopping</span>
                                    </label>
                                </div>
                                <a class="btn-floating yellow darken-1" style="vertical-align: top"
                                   onclick="displayInput(this)">
                                    <i class="material-icons">tour</i></a>
                            </div>
                        </li>
                        <li>
                            <div class="right" style="width: fit-content">
                                <div id="persons_input" class="card-panel invisible" style="width: 50%;">
                                    <div class="valign-wrapper">
                                        <div id="min_persons_input" class="input-field">
                                            <input id="min_persons" name="min_persons" type="number" form="search">
                                            <label for="min_persons">Min Persons</label>
                                        </div>
                                        <div id="max_persons_input" class="input-field">
                                            <input id="max_persons" name="max_persons" type="number" form="search">
                                            <label for="max_persons">Max Persons</label>
                                        </div>
                                    </div>
                                </div>
                                <a class="btn-floating green" style="vertical-align: top"
                                   onclick="displayInput(this)"><i
                                        class="material-icons">perm_identity</i></a>
                            </div>
                        </li>
                        <li>
                            <div class="right" style="width: fit-content">
                                <div id="room_type_input" class="card-panel invisible">
                                    <label>
                                        <input name="room_type" value="single" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Single</span>
                                    </label>
                                    <label>
                                        <input name="room_type" value="double" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Double</span>
                                    </label>
                                    <label>
                                        <input name="room_type" value="studio" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Studio</span>
                                    </label>
                                    <label>
                                        <input name="room_type" value="president" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>President</span>
                                    </label>
                                    <label>
                                        <input name="room_type" value="apartment" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Apartment</span>
                                    </label>
                                    <label>
                                        <input name="room_type" value="villa" form="search" type="checkbox"
                                               class="filled-in">
                                        <span>Villa</span>
                                    </label>
                                </div>
                                <a class="btn-floating blue" style="vertical-align: top"
                                   onclick="displayInput(this)"><i
                                        class="material-icons">home</i></a>
                            </div>
                        </li>
                        <li>
                            <div class="right" style="width: fit-content">
                                <div id="title_input" class="card-panel invisible">
                                    <div id="title_input_field" class="input-field">
                                            <textarea id="title" name="title" form="search" class="materialize-textarea"
                                                      data-length="30"></textarea>
                                        <label for="title">Tour title</label>
                                    </div>
                                </div>
                                <a class="btn-floating purple" style="vertical-align: top"
                                   onclick="displayInput(this)"><i
                                        class="material-icons">text_fields</i></a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
