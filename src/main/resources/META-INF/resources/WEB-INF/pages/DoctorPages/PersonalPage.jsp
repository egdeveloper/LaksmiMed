<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личная страница</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
</head>
<body style="padding-top: 60px;">
<c:url var="updatePersonalDoctorInfo" value="/updatePersonalDoctorInfo"/>
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
                        <li><a href="<c:url value="/beforeTreatmentStatVisualization"/>">До лечения</a></li>
                        <li><a href="<c:url value="/stoneComponentsStatPage"/>">Компоненты мочевых камней</a></li>
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
    <h1 class="page-header">Личный профиль доктора</h1>
    <div class="row">
        <div class="col-sm-6 col-md-4 col-xs-12">
            <div class="text-center">
                <img href="" class="img-responsive"/>
            </div>
            <input type="file" class="text-right center-block well well-sm"/>
        </div>
        <div class="col-sm-6 col-md-8 col-sx-12">
            <form:form id="personalDoctorInfoForm" cssClass="form-horizontal" role="form" modelAttribute="doctor" action="${updatePersonalDoctorInfo}">
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">ФИО: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control personalDoctorInfoField" cssStyle="background-color: #ffffff" readonly="true" path="fullName"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Email: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control personalDoctorInfoField" cssStyle="background-color: #ffffff" readonly="true" path="email"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Место работы: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control personalDoctorInfoField" cssStyle="background-color: #ffffff" readonly="true" path="jobPlace"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Должность: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control personalDoctorInfoField" cssStyle="background-color: #ffffff" readonly="true" path="jobPost"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 form-control-label">Телефон: </label>
                    <div class="col-sm-10">
                        <form:input cssClass="form-control personalDoctorInfoField" cssStyle="background-color: #ffffff" readonly="true" path="phoneNumber"/>
                    </div>
                </div>
                <div class="row pull-right">
                    <button id="updatePersonalDoctorInfoButton" class="btn btn-success" type="button">Обновить</button>
                    <button class="btn btn-warning" type="button">Отмена</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">
    var doctorInfoUpdated = false;
    var personalDoctorInfoFields = document.getElementsByClassName("personalDoctorInfoField");
    $("#updatePersonalDoctorInfoButton").click(function(){
        if(!doctorInfoUpdated){
            document.getElementById("updatePersonalDoctorInfoButton").innerHTML = "Сохранить";
            for(var i = 0; i < personalDoctorInfoFields.length; i++){
                personalDoctorInfoFields[i].readOnly = false;
            }
            doctorInfoUpdated = true;
        }
        else{
            $("#personalDoctorInfoForm").submit();
            document.getElementById("updatePersonalDoctorInfoButton").innerHTML = "Обновить";
            for(var i = 0; i < personalDoctorInfoFields.length; i++){
                personalDoctorInfoFields[i].readOnly = true;
            }
            doctorInfoUpdated = false;
        }
    });
</script>
</html>
