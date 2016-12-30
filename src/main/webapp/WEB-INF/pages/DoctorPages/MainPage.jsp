<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать!</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }

        body > .footer-container {
            padding: 60px 15px 0;
        }
        .footer-container .text-muted {
            margin: 20px 0;
        }
        .footer > .footer-container {
            padding-right: 15px;
            padding-left: 15px;
        }

        code {
            font-size: 80%;
        }
    </style>
</head>
<body style="padding-top: 70px;">
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <div class="navbar-brand">LaksmiMed</div>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/main"/>">Главная</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Сайт <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/about"/>">О сайте</a></li>
                        <li><a href="<c:url value="/help"/>">Помощь</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Статистика <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/pages/patients/statistics"/>">До лечения</a></li>
                        <li><a href="<c:url value="/pages/tests/stone-components"/>">Компоненты мочевых камней</a></li>
                        <li><a href="<c:url value="/pages/tests/indicator-stone"/>">Индикатор-камень</a></li>
                        <li><a href="<c:url value="/pages/tests/disease-stone"/>">Заболевание-камень</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/doctor/patients"/>">Пациенты</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/doctor/personal-page"/>">Личная страница</a></li>
                <li><a href="<c:url value="/logout"/>">Выйти</a></li>
            </ul>
        </div>
    </nav>
</div>
<footer class="footer">
    <div class="footer-container">
        <p class="text-muted">©Ярных Роман <a href="mailto:egdeveloper@mail.ru">egdeveloper@mail.ru</a></p>
    </div>
</footer>
</body>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
</html>
