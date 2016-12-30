<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>О сайте</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
</head>
<body style="padding-top: 70px;">
    <c:url var="registerAction" value="/register"/>
    <c:url var="login_action" value="/login"/>
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
                    <li><a href="<c:url value="/patients"/>">Пациенты</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<c:url value="/logout"/>">Выйти</a></li>
                </ul>
            </div>
        </nav>
        <div class="row">
            <h2>Цель проекта</h2>
        </div>
        <div class="row">
            <p class="lead">
                Наша задача заключается в создании удобного и мощного инструмента для врачей, нуждающихся в повышении эффективности
                каждодневной работы с медицинскими картами и учетом пациентов, статистической обработки
                медицинских обследований с целью выявления закономерностей в возникновении тех или иных заболеваний.
                Конечная цель нашего проекта - сделать диагностирование быстрым и безошибочным.
            </p>
        </div>
        <div class="row">
            <p class="lead">
                Данный веб-сайт выполнен в рамках курсовой работы и является одним из проектов единой
                системы интеграции медицины и информационных технологий
                <br/>
                Ссылки на другие проекты:
                <br/>
                Химия для медицины: <a href="http://chem-for-med.ru">http://chem-for-med.ru</a>
            </p>
        </div>
        <div class="row">
            <h2>Руководители проекта</h2>
        </div>
        <div class="row">
            <p class="lead">
                Кузьмичева Г.М. –– доктор химических наук, профессор, Лаборатория рентгеновских методов
                исследования, <a href="https://chemtech.mirea.ru/department/office-of-science-intensive-chemical-technologies/mitts/">Кафедра материаловедения и технологии функциональных материалов и структур</a>,
                <a href="https://www.mirea.ru">Московский технологический университете (МИРЭА, МГУПИ, МИТХТ)</a>
                <br/>
                Подбельский В.В. –– доктор физико-математических наук, профессор, Департамент программной инженерии,
                <a href="https://cs.hse.ru">Факультет компьютерных наук</a>, <a href="https://www.hse.ru">НИУ "Высшая школа экономики"</a>
            </p>
        </div>
        <div class="row">
            <h2>Разработка</h2>
        </div>
        <div class="row">
            <p class="lead">
                Ярных Р.В. –– студент 2 курса (БПИ141), <a href="https://cs.hse.ru">Факультет компьютерных наук</a>, <a href="https://www.hse.ru">НИУ "Высшая школа экономики"</a>
                <br/>
                Адрес электронной почты: <a href="mailto:egdeveloper@mail.ru">egdeveloper@mail.ru</a>
                <br/>
                Проект размещен в свободном доступе на github: <a href="https://github.com/egdeveloper/LaksmiMed">https://github.com/egdeveloper/LaksmiMed</a>
            </p>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
</body>
</html>
