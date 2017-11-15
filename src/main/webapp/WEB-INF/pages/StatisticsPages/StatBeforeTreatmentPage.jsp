<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика до лечения</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/plotly.js"/>"></script>
</head>
<body style="padding-top: 70px;">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <div class="navbar-brand">LaksmiMed</div>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/doctor/patients"/>">Главная</a></li>
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
        <div class="row">
            <div id="durationPanel" class="panel panel-default">
                <div class="panel-heading">
                    Статистика пациентов по признаку "Продолжительность заболевания"
                </div>
                <div class="panel-body">
                    <div id="durationStatBar" style="position: relative; width: 400px; height: 400px;"></div>
                </div>
            </div>
            <div id="genderPanel" class="panel panel-default">
                <div class="panel-heading">
                    Статистика пациентов по признаку "Пол"
                </div>
                <div class="panel-body">
                    <div id="genderStatBar" style="position: relative; width: 400px; height: 400px;"></div>
                </div>
            </div>
            <div id="agePanel" class="panel panel-default">
                <div class="panel-heading">
                    Статистика пациентов по признаку "Возраст"
                </div>
                <div class="panel-body">
                    <div id="ageStatBar" style="position: relative; width: 400px; height: 400px;"></div>
                </div>
            </div>
            <div id="badHabitsPanel" class="panel panel-default">
                <div class="panel-heading">
                    Статистика пациентов по признаку "Вредные привычки"
                </div>
                <div class="panel-body">
                    <div id="badHabitsStatBar" style="position: relative; width: 400px; height: 400px;"></div>
                </div>
            </div>
            <div id="regionPanel" class="panel panel-default">
                <div class="panel-heading">
                    Статистика пациентов по признаку "Регион проживания"
                </div>
                <div class="panel-body">
                    <div id="regionStatBar" style="position: relative; width: 400px; height: 400px;"></div>
                </div>
            </div>
            <div id="associatedDiseasePanel" class="panel panel-default">
                <div class="panel-heading">
                    Статистика пациентов по признаку "Сопутствующие заболевания"
                </div>
                <div class="panel-body">
                    <div id="associatedDiseaseStatBar" style="position: relative; width: 400px; height: 400px;"></div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/canvasjs/canvasjs.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/pathFactory.js"/>"></script>
    <script>
        String.prototype.isEmpty = function() {
            return (this.length === 0 || !this.trim());
        };
        function requestForBeforeTreatmentStat(requestURL, callback){
            $.getJSON(requestURL, callback);
        }
        function visualizeBeforeTreatmentStatData(statData){
            var volume = statData.volume;
            var genderData = [{
                y: Object.keys(statData.gender),
                x: Object.keys(statData.gender).map(function(key){
                    return statData.gender[key] / volume * 100
                }),
                orientation: 'h',
                type: 'bar'
            }];
            var durationData = [{
                y: Object.keys(statData.duration).map(function(key){
                    if(parseInt(key) >= 5)
                        return key.toString() + " лет";
                    if(parseInt(key) == 1)
                        return key.toString() + " год";
                    if(key.isEmpty() || parseInt(key) == 0)
                        return "не было";
                    return key.toString() + " года";
                }),
                x: Object.keys(statData.duration).map(function(key){
                    return statData.duration[key] / volume * 100;
                }),
                orientation: 'h',
                type: 'bar'
            }];
            var ageData = [{
                y: Object.keys(statData.age).map(function(key){
                    return key.toString() + "-" + (parseInt(key) + 9).toString();
                }),
                x: Object.keys(statData.age).map(function(key){
                    return statData.age[key] / volume * 100;
                }),
                orientation: 'h',
                type: 'bar'
            }];
            var associatedDisease = [{
                labels: Object.keys(statData.associatedDisease),
                values: Object.keys(statData.associatedDisease).map(function(key){
                    return statData.associatedDisease[key] / volume * 100;
                }),
                type: 'pie'
            }];
            var regionData = [{
                labels: Object.keys(statData.region),
                values: Object.keys(statData.region).map(function(key){
                    return statData.region[key] / volume * 100;
                }),
                type: 'pie'
            }];
            var badHabitsData = [{
                y: Object.keys(statData.badHabits).map(function(key){
                    if(key.isEmpty() == true)
                        return "нет";
                    else
                        return key;
                }),
                x: Object.keys(statData.badHabits).map(function(key){
                    return statData.badHabits[key] / volume * 100;
                }),
                orientation: 'h',
                type: 'bar'
            }];
            $("#genderStatBar").css({"width" : $("#genderPanel").width() * 0.8});
            $("#durationStatBar").css({"width" : $("#durationPanel").width() * 0.8});
            $("#ageStatBar").css({"width" : $("#agePanel").width() * 0.8});
            $("#regionStatBar").css({"width" : $("#regionPanel").width() * 0.8});
            $("#badHabitsStatBar").css({"width" : $("#badHabitsPanel").width() * 0.8});
            $("#associatedDiseaseStatBar").css({"width" : $("#associatedDiseasePanel").width() * 0.8});
            Plotly.newPlot('genderStatBar', genderData, {
                title: 'Пол пациента',
                autoSize: false,
                width: $("#genderPanel").width() * 0.7
            });
            Plotly.newPlot('durationStatBar', durationData, {
                title: 'Продолжительность заболевания',
                autoSize: false,
                width: $("#durationPanel").width() * 0.7
            });
            Plotly.newPlot('ageStatBar', ageData, {
                title: 'Возраст пациента',
                autoSize: false,
                width: $("#agePanel").width() * 0.7
            });
            Plotly.newPlot('regionStatBar', regionData, {
                title: 'Регион проживания',
                width: $("#regionPanel").width() * 0.7
            });
            Plotly.newPlot('badHabitsStatBar', badHabitsData, {
                title: 'Вредные привычки',
                autoSize: false,
                width: $("#badHabitsPanel").width() * 0.7
            });
            Plotly.newPlot('associatedDiseaseStatBar', associatedDisease, {
                title: 'Сопутствующие заболевания',
                autoSize: false,
                width: $("#associatedDiseasePanel").width() * 0.7
            });

        }
        $(document).ready(function(){
            requestForBeforeTreatmentStat(retrievePath("/stat-service/patients/statistics"), visualizeBeforeTreatmentStatData);
        });
    </script>
</body>
</html>
