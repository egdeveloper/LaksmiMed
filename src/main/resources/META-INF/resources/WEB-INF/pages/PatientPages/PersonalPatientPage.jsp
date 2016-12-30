<%@ page import="org.egdeveloper.data.model.MedicalTest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пациент ${patient.fullName}</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-datepicker/datepicker/css/datepicker.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/plotly.js"/>"></script>
    <style rel="stylesheet" type="text/css">
        .test-button{
            text-align: center;
        }
    </style>
</head>
<body style="padding-top: 60px;">
<c:url var="updatePatientEntry" value="/updatePatientEntry"/>
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
    <ul class="nav nav-tabs" role="tablist" style="margin-top:20px">
        <li class="active"><a href="#mainPatientInfo" data-toggle="tab">Основные сведения о пациенте</a></li>
        <li><a href="#medicalPatientInfo" data-toggle="tab">Медицинские сведения о пациенте</a></li>
        <li><a href="#medicalTests" data-toggle="tab">Медицинские анализы</a></li>
        <li><a href="#testDynamics" data-toggle="tab">Динамика показателей</a></li>
    </ul>
    <form:form id="patientInfoForm" cssClass="form-horizontal" role="form" modelAttribute="patient" action="${updatePatientEntry}">
        <div class="tab-content" style="width: 100%; padding-top: 20px; padding-bottom: 20px;">
            <div class="tab-pane active" id="mainPatientInfo" style="margin-top:10px;">
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">ФИО: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="fullName"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Дата рождения: </label>
                    <div class="col-sm-10">
                        <form:input id="birthdateInput" cssClass="form-control patientInfoField dateField" readonly="true" cssStyle="background-color: #ffffff" path="birthdate"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Пол: </label>
                    <div class="col-sm-10">
                        <form:select cssClass="form-control" path="gender" >
                            <form:option value="MALE">мужской</form:option>
                            <form:option value="FEMALE">женский</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Номер карты: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="cardNumber"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Email: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="email"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Телефон: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="phoneNumber"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Почтовый индекс: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="postIndex"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Страна: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="country"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Регион: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="region"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Населенный пункт: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="city"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Адрес проживания: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="address"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Паспортные данные: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="passport"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Страховой полис: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="OMICard"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">ИНН: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="TIN"/>
                    </div>
                </div>
                <%--<div class="form-group row">--%>
                    <%--<label class="col-sm-2 form-control-label">Профессиональная специальность: </label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="occupation"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Должность: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="jobPost"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Место работы: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="jobPlace"/>
                    </div>
                </div>
                <%--<div class="form-group row">--%>
                    <%--<label class="col-sm-2 form-control-label">Условия работы: </label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="jobConditions"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="row pull-right" style="margin-top: 20px;">
                    <button id="updatePatientInfoButton" class="btn btn-success" type="button">Обновить</button>
                    <button id="cancelButton" class="btn btn-warning" type="button">Отмена</button>
                </div>
            </div>
            <div class="tab-pane" id="medicalPatientInfo" style="margin-top:10px">
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Состояние пациента: </label>
                    <div class="col-sm-10">
                        <form:select cssClass="form-control" path="patientState">
                            <form:option value="HEALTHY">здоров</form:option>
                            <form:option value="FAIR">стабилен</form:option>
                            <form:option value="SERIOUS">болен</form:option>
                            <form:option value="CRITICAL">серьезно болен</form:option>
                            <form:option value="UNDERTERMINED">неопределенное</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Резус-фактор: </label>
                    <div class="col-sm-10">
                        <form:select cssClass="form-control" path="rh">
                            <form:option value="POSITIVE">Rh+</form:option>
                            <form:option value="NEGATIVE">Rh-</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Группа крови: </label>
                    <div class="col-sm-10">
                        <form:select cssClass="form-control" path="bloodGroup">
                            <form:option value="I">I</form:option>
                            <form:option value="II">II</form:option>
                            <form:option value="III">III</form:option>
                            <form:option value="IV">IV</form:option>
                        </form:select>
                    </div>
                </div>
                <%--<div class="form-group row">--%>
                    <%--<label class="col-sm-2 form-control-label">Группа здоровья: </label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<form:select cssClass="form-control" path="disability">--%>
                            <%--<form:option value="NONE">нет</form:option>--%>
                            <%--<form:option value="I">I</form:option>--%>
                            <%--<form:option value="II">II</form:option>--%>
                            <%--<form:option value="III">III</form:option>--%>
                        <%--</form:select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Жалобы при поступлении: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="complaints"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Сопутствующее заболевания: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="associatedDisease"/>
                    </div>
                </div>
                <%--<div class="form-group row">--%>
                    <%--<label class="col-sm-2 form-control-label">Вредные привычки: </label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="badHabits"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Описание камня до поступления: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="preUreaStoneDescription"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Длительность заболевания: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="diseaseDuration"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Лечение до поступления: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="premedication"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Назначения до поступления: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control patientInfoField" readonly="true" cssStyle="background-color: #ffffff" path="preMedicalSupplies"/>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="medicalTests" style="margin-top: 10px">
                <div class="row" style="padding-bottom: 15px;">
                    <div class="col-sm-6">
                        <div class="btn-group">
                            <button id="addTestButton" class="btn btn-secondary btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" type="button">Добавить анализ</button>
                            <ul class = "dropdown-menu" role = "menu">
                                <li>
                                    <h6 class="dropdown-header">Кровь</h6>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/bio_chem"/>">Биохимический анализ крови</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/common_blood"/>">Общий анализ крови</a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>
                                <li>
                                    <h6 class="dropdown-header">Моча</h6>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/common_urea"/>">Общий анализ мочи</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/daily_excreation"/>">Суточная экскреция</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/titration_test"/>">Титриметрия</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/urea_color"/>">Хроматография</a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>
                                <li>
                                    <h6 class="dropdown-header">Камень</h6>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/stone_in_vivo"/>">Камень In Vivo</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="<c:url value="/test/stone_in_vitro"/>">Камень In Vitro</a>
                                </li>
                           </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:choose>
                        <c:when test="${patient.findTests().size() > 0}">
                                <table class="table table-striped table-bordered" cellpadding="0" width="100%">
                                    <thead>
                                    <tr>
                                        <td>№</td>
                                        <td>Тип анализа</td>
                                        <td>Дата проведения</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="test" items="${patient.findTests()}" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>${test.retrieveName()}</td>
                                                <td>${test.testDate}</td>
                                                <td>
                                                    <button class="btn btn-success" type="button" onclick="location.href='<c:url value="/viewMedicalTest/${test.retrieveEntityID()}/${test.id}"/>'">
                                                        <span class="glyphicon glyphicon-pencil"></span>
                                                    </button>
                                                </td>
                                                <td>
                                                    <form>
                                                        <button class="btn btn-danger" type="button">
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-warning" role="alert">
                                У пациента нет еще ни одного прикрепленного анализа
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
            <div class="tab-pane" id="testDynamics" style="margin-top: 10px;">
                <div class="row">
                    <form>
                        <div class="col-md-4">
                            <select id="medicalTestTypeSelection" class="form-control">
                                <option disabled>Выберите тип анализа</option>
                                <option value="bioChem">Биохимический анализ крови</option>
                                <option value="commonBlood">Общий анализ крови</option>
                                <option value="commonUrea">Общий анализ мочи</option>
                                <option value="dailyExcreation">Суточная экскреция</option>
                                <option value="titration">Титриметрия</option>
                                <option value="ureaColor">Хроматография</option>
                                <option value="stoneInVivo">Камень In Vivo</option>
                                <option value="stoneInVitro">Камень In Vitro</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <button id="retrieveTestDynamicsButton" class="btn btn-success" type="button">Получить динамику</button>
                        </div>
                        <div class="col-md-4">
                            <button id="dynamics-report-button" class="btn btn-success" type="button">Экспорт</button>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <div id="testDynamicsCharts" style="padding-top: 30px;">
                    </div>
                </div>
            </div>
        </div>
    </form:form>
    <div id="addTestModalDialog" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Добавить медицинский анализ</h4>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="list-group">
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addCommonBloodTest"/>">Общий анализ крови</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addBiochemTest"/>">Биохимический анализ</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addCommonUreaTest"/>">Общий анализ мочи</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addDailyExcreationTest"/>">Суточная экскреция</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addTitrationTest"/>">Титриметрия</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addUreaColorTest"/>">Хроматография</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addStoneInVivoTest"/>">Камень In Vivo</a>
                            <a role="button" class="col-md-5 btn btn-primary test-button" href="<c:url value="/addStoneInVitroTest"/>">Камень In Vitro</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="#" id="dynamics-export-link" hidden download></a>
</body>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-datepicker/datepicker/js/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pathFactory.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/stream/stream.js"/>"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $.getJSON(retrievePath("/stat-service/tests/norms", function (data) {
            window.testNorms = data;
        }));
    });

    Array.max = function(){
        return Math.max.apply( Math, this );
    };

    Array.min = function(){
        return Math.min.apply( Math, this);
    };

    $(document).ready(function(){
        $(".dateField").datepicker({
            format : "dd.mm.yyyy",
            autoclose: true,
            locale: 'ru'
        });
    });

    function retrieveTestDynamics(patientId, callback){
        $.getJSON(retrievePath("/stat-service/indicators/dynamics") + "/" + patientId.toString(), callback);
    }

    function visualizeTestDynamics(testType, testData){
        $("#testDynamicsCharts").empty();
        var testIndicators = testData[testType];
        var i = 1;
        Object.keys(testIndicators).map(function(indicatorKey){
            if($.isEmptyObject(testIndicators[indicatorKey]))
                return;
            var timePoints = Object.keys(testIndicators[indicatorKey]).map(function(timeKey){
                return timeKey;
            }).sort(function(a, b){
                return (new Date(a)) - (new Date(b));
            });
            var valuePoints = Object.keys(testIndicators[indicatorKey])
                .sort(function(a, b){
                    return (new Date(a)) - (new Date(b));
                })
                .map(function(timeKey){
                    return testIndicators[indicatorKey][timeKey];
                });
            var minValue = Stream(valuePoints).min().get();
            var maxValue = Stream(valuePoints).max().get();
            var shapes = null;
            var normMin = testData.testNorms[testType][indicatorKey].min;
            var normMax = testData.testNorms[testType][indicatorKey].max;
            var fromDate = Stream(timePoints).min().orElse();
            var toDate = Stream(timePoints).max().get();
            var annotations = null;
            if(minValue <= normMin){
                annotations = [
                    {
                        x: toDate,
                        y: normMin,
                        xref: 'x',
                        yref: 'y',
                        text: 'Мин. Норма = ' + normMin,
                        font: {
                            family: 'Courier New, monospace',
                            size: 16,
                            color: 'black'
                        },
                        showarrow: false,
                        align: 'center',
                        bordercolor: '#c7c7c7',
                        borderwidth: 2,
                        borderpad: 4,
                        bgcolor: '#ff7f0e',
                        opacity: 1
                    }
                ];
                shapes = [
                    {
                        type: 'line',
                        x0: fromDate,
                        y0: normMin,
                        x1: toDate,
                        y1: normMin,
                        line: {
                            color: 'rgb(250, 0, 0)',
                            width: 5,
                            dash: 'dashdot'
                        }
                    }
                ];
            }
            if(maxValue >= normMax){
                annotations.push({
                    x: toDate,
                    y: normMax,
                    xref: 'x',
                    yref: 'y',
                    text: 'Макс. Норма = ' + normMax,
                    font: {
                        family: 'Courier New, monospace',
                        size: 16,
                        color: 'black'
                    },
                    showarrow: false,
                    align: 'center',
                    bordercolor: '#c7c7c7',
                    borderwidth: 2,
                    borderpad: 4,
                    bgcolor: '#ff7f0e',
                    opacity: 1
                });
                shapes.push({
                    type: 'line',
                    x0: fromDate,
                    y0: normMax,
                    x1: toDate,
                    y1: normMax,
                    line: {
                        color: 'rgb(250, 0, 0)',
                        width: 5,
                        dash: 'dashdot'
                    }
                });
            }
            var chartData = [{
                x: timePoints,
                y: valuePoints,
                type: 'scatter'
            }];
            var containerID = testType.toString() + "Indicator" + i.toString();
            var containerTags = '<div class="panel panel-default" style="margin-bottom: 40px;"><div class="panel-heading">'
                + indicatorKey + '</div>'
                + '<div class="panel-body"><div id="' + containerID + '"></div></div></div>';
            var layout = {
                title: indicatorKey,
                xaxis: {title: "Дата проведения"},
                yaxis: {title: indicatorKey}
            };
            if(shapes){
                layout.shapes = shapes;
            }
            if(annotations){
                layout.annotations = annotations;
            }
            $("#testDynamicsCharts").append(containerTags);
            Plotly.newPlot(containerID, chartData, layout);
            i++;
        });
    }

    var patientInfoUpdated = false;
    var patientInfoFields = document.getElementsByClassName("patientInfoField");
    $("#updatePatientInfoButton").click(function(){
        if(!patientInfoUpdated){
            document.getElementById("updatePatientInfoButton").innerHTML = "Сохранить";
            for(var i = 0; i < patientInfoFields.length; i++){
                patientInfoFields[i].readOnly = false;
            }
            patientInfoUpdated = true;
        }
        else{
            $("#patientInfoForm").submit();
            document.getElementById("updatePatientInfoButton").innerHTML = "Обновить";
            for(var i = 0; i < patientInfoFields.length; i++){
                patientInfoFields[i].readOnly = true;
            }
            patientInfoUpdated = false;
        }
    });

    $("#retrieveTestDynamicsButton").click(function(){
        var testSelection = document.getElementById("medicalTestTypeSelection");
        var testType = testSelection.options[testSelection.selectedIndex].getAttribute("value");
        retrieveTestDynamics(${patient.id}, function(data){
            visualizeTestDynamics(testType, data);
        });
    });

    $("#dynamics-report-button").click(function (e) {
        var testSelection = document.getElementById("medicalTestTypeSelection");
        var testType = testSelection.options[testSelection.selectedIndex].getAttribute("value");
//        e.preventDefault();  //stop the browser from following
        $("#dynamics-export-link").attr("href", "/patient/"
            + ${patient.id}
            + "/test/"
            + testType
            + "/dynamics/report" +
            "?type=application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        $("#dynamics-export-link")[0].click();
    });
</script>
</html>