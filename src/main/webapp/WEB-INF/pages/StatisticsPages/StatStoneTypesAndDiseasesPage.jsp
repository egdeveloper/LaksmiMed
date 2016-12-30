<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Компоненты мочевого камня</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/plotly.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/canvasjs/canvasjs.min.js"/>"></script>
</head>
<body style="padding-top: 80px;">
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
                <li><a href="<c:url value="/personalPage"/>">Личная страница</a></li>
                <li><a href="<c:url value="/logout"/>">Выйти</a></li>
            </ul>
        </div>
    </nav>
    <div class="row">
        <div class="col-md-4">
            <select id="treatmentNumberSelection" class="form-control">
                <option disabled selected>Выберите стадию лечения</option>
                <option value="NONE">не было</option>
                <option value="I">лечение 1</option>
                <option value="II">лечение 2</option>
                <option value="III">лечение 3</option>
                <option value="IV">лечение 4</option>
                <option value="V">лечение 5</option>
                <option value="VI">лечение 6</option>
                <option value="VII">лечение 7</option>
                <option value="VIII">лечение 8</option>
                <option value="IX">лечение 9</option>
                <option value="X">лечение 10</option>
                <option value="XI">лечение 11</option>
                <option value="XII">лечение 12</option>
            </select>
        </div>
        <div class="col-md-4">
            <button id="updateStat" class="btn btn-success" type="button">Обновить статистику</button>
        </div>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div id="diseasesStat"></div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootbox/bootbox.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pathFactory.js"/>"></script>
<script>
    String.prototype.capitalizeFirstLetter = function(){
        return this.charAt(0).toUpperCase() + this.slice(1);
    };

    $("#updateStat").click(function(){
        if($("#treatmentNumberSelection").val() != null){
            $.getJSON("/stat-service/tests/disease-stone/" + $("#treatmentNumberSelection").val(), function (data)
            {
                var stat = {};
                stat.oxalates = {};
                stat.phosphates = {};
                stat.urates = {};
                var ox_m = 0;
                var ph_m = 0;
                var ur_m = 0;
                var inds = Object.keys(data);
                for(var i = 0; i < inds.length; i++){
                    ox_m += data[inds[i]].oxalates;
                    ph_m += data[inds[i]].phosphates;
                    ur_m += data[inds[i]].urates;
                }
                Object.keys(data).forEach(function (disease, i, arr) {
                    stat.oxalates[disease] = data[disease].oxalates / ox_m * 100;
                    stat.phosphates[disease] = data[disease].phosphates / ph_m * 100;
                    stat.urates[disease] = data[disease].urates / ur_m * 100;
                });
                $("#diseasesStat").empty();
                $("#diseasesStat").append("<div class='panel panel-default'>" +
                        "<div class='panel-heading'>Сопутствующие заболевания</div>" +
                        "<div class='panel-body'>" +
                        "<div id='diseasesStatBar'></div>" +
                        "</div>" +
                        "</div>");
                Plotly.newPlot("diseasesStatBar",
                        [
                            {
                                x: Object.keys(data),
                                y: Object.keys(data).map(function(key){
                                    return data[key].oxalates_corr * 100
                                }),
                                type: "bar",
                                name: "Оксалаты"
                            },
                            {
                                x: Object.keys(data),
                                y: Object.keys(data).map(function(key){
                                    return data[key].phosphates_corr * 100
                                }),
                                type: "bar",
                                name: "Фосфаты"
                            },
                            {
                                x: Object.keys(data),
                                y: Object.keys(data).map(function(key){
                                    return data[key].urates_corr * 100
                                }),
                                type: "bar",
                                name: "Ураты"
                            }
//                            {
//                                x: Object.keys(stat.phosphates),
//                                y: Object.keys(stat.phosphates).map(function(key){
//                                    return stat.phosphates[key];
//                                }),
//                                type: "bar",
//                                name: "Ураты"
//                            },
//                            {
//                                x: Object.keys(stat.urates),
//                                y: Object.keys(stat.urates).map(function(key){
//                                    return stat.urates[key];
//                                }),
//                                type: "bar",
//                                name: "Фосфаты"
//                            }
                        ],
                        {
                            title: "Сопутствующие заболевания",
                            font: {
                                size: 17
                            },
                            titlefont:{
                                size: 20
                            },
                            barmode: "group",
                            xaxis:{
                                titlefont: {
                                    size: 17
                                }
                            },
                            yaxis:{
                                title: "Корреляция, %",
                                titlefont: {
                                    size: 17
                                }
                            }
                        }
                );
            });
        }
        else{
            bootbox.alert("Выберите стадию лечения!");
        }
    });
</script>
</body>
</html>

