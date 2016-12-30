<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Банк записей о пациентах</title>
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
                        <li><a href="<c:url value="/pages/tests/disease-stone"/>"></a></li>
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
    <div class="row">
        <div class="col-sm-6">
            <button class="btn btn-success" onclick="location.href='<c:url value="/patientInfoEditor"/>'">Добавить пациента</button>
            <button class="btn btn-success" onclick="location.href='<c:url value="/patients/database" />'">Загрузить банк пациентов</button>
        </div>
    </div>
    <br/>
    <div class="row">
        <c:choose>
            <c:when test="${doctor.patients.size() > 0}">
                <table class="table table-striped table-bordered" cellpadding="0" style="width: auto;">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>ФИО</td>
                        <td>Пол</td>
                        <td>Номер карты</td>
                        <td>Адрес проживания</td>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="patient" items="${patients}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td><a href="<c:url value="/patients/${patient.id}"/>">${patient.fullName}</a></td>
                            <td>${patient.gender.gender2String()}</td>
                            <td>${patient.cardNumber}</td>
                            <td>${patient.postIndex}, ${patient.region}, ${patient.city}, ${patient.address}</td>
                            <td>
                                <button class="btn btn-success" onclick="location.href='<c:url value="/patients/${patient.id}"/>'">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                            </td>
                            <td>
                                <form id="deletePatientForm_${patient.id}" action="<c:url value="/deletePatientEntry/${patient.id}"/>" method="post">
                                    <button id="deletePatientButton_${patient.id}" class="btn btn-danger deletePatientButtonClass" type="button">
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
                    В базе данных нет ни одной записи о пациентах. Добавьте запись!
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootbox/bootbox.min.js"/>"></script>
<script type="text/javascript">
    /*
    $(".deletePatietnButtonClass").each(function(button){
        button.click(function(){
            bootbox.dialog({
                message: "Вы действительно хотите удалить запись о пациенте?",
                title: "Потверждение удаления",
                cancel:{
                    label: "Отмена",
                    className: "btn btn-primary"
                },
                remove:{
                    label: "OK",
                    className: "btn btn-success",
                    callback: function(){
                        button.closest().submit();
                    }
                }
            })
        });
    });
    */
    $(".deletePatientButtonClass").each(function(){
        var deleteForm = $(this).closest("form");
        $(this).click(function(){
            bootbox.dialog({
                message: "Вы дейстительно хотите удалить запись о пациенте?",
                buttons: {
                    no: {
                        label: "Нет",
                        className: "btn-danger",
                        callback: function() {

                        }
                    },
                    yes: {
                        label: "Да",
                        className: "btn-success",
                        callback: function() {
                            deleteForm.submit();
                        }
                    }
                }
            });
        })
    });
</script>
</html>
