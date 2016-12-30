<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика до лечения</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-slider/bootstrap-slider.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/plotly.js"/>"></script>
    <style>
        .col-centered{
            display: block;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
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
                        <li><a href="<c:url value="/pages/patients/statistics"/>">Статистика по пациентам</a></li>
                        <li><a href="<c:url value="/pages/tests/stone-components"/>">Компоненты мочевых камней</a></li>
                        <li><a href="<c:url value="/pages/tests/disease-stone"/>">Статистика по заболеваниям</a></li>
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
    <div class="panel panel-default">
        <div class="panel-body">
            <form id="chart-control-form">
                <div class="row">
                    <div class="col-md-6">
                        <label class="control-label" for="test-sel">Выберите тип анализа</label>
                        <select id="test-sel" class="form-control">
                            <option disabled selected>Выберите тип анализа</option>
                            <option value="bioChem">Биохимический анализ крови</option>
                            <option value="commonBlood">Общий анализ крови</option>
                            <option value="commonUrea">Общий анализ мочи</option>
                            <option value="dailyExcreation">Суточная экскреция</option>
                            <option value="titration">Титриметрия</option>
                            <option value="ureaColor">Хроматография</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="control-label" for="treatment-number-sel">Выберите стадию лечения</label>
                        <select id="treatment-number-sel" class="form-control">
                            <option disabled selected>Выберите стадию лечения</option>
                            <option value="NONE">не было</option>
                            <option value="I">после лечения №1</option>
                            <option value="II">до лечения №2</option>
                            <option value="III">после лечения №2</option>
                            <option value="IV">до лечения №3</option>
                            <option value="V">после лечения №3</option>
                            <option value="VI">до лечения №4</option>
                            <option value="VII">после лечения №4</option>
                            <option value="VIII">до лечения №5</option>
                            <option value="IX">после лечения №5</option>
                            <option value="X">до лечения №6</option>
                            <option value="XI">после лечения №6</option>
                            <option value="XII">до лечения №7</option>
                        </select>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <label class="col-md-4 control-label" for="corr-limit">Уровень корреляции, %</label>
                    <label class="col-md-4 control-label">Выбор параметра</label>
                    <label class="col-md-4 control-label">Параметры отображения</label>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <input id="corr-limit" type="text" data-provide="slider"
                        data-slider-min="0"
                        data-slider-max="100"
                        data-slider-step="5"
                        data-slider-value="0"
                        data-slider-tooltip="always"
                        <%--data-slider-ticks="[0.0, 0.5, 1.0]"--%>
                        <%--data-slider-ticks-labels='["нет связи", "высокий", "взаимосвязаны"]'--%>
                        >
                    </div>
                    <div class="col-md-4">
                        <label class="radio-inline">
                            <input name="par-sel" type="radio" value="ratio" checked>
                            доля
                        </label>
                        <label class="radio-inline">
                            <input name="par-sel" value="corr" type="radio">
                            корреляция
                        </label>
                    </div>
                    <div class="col-md-4">
                        <label class="radio-inline">
                            <input name="grview-sel" value="sep" type="radio" checked>
                            отдельным графиком
                        </label>
                        <label class="radio-inline">
                            <input name="grview-sel" value="one" type="radio">
                            одним графиком
                        </label>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-md-4 col-centered">

                    </div>
                    <div class="col-md-4 col-centered">
                        <button id="update-button" class="btn btn-success" type="button">Обновить статистику</button>
                    </div>
                    <div class="col-md-4 col-centered">

                    </div>
                </div>
            </form>
        </div>
    </div>
    <div id="ind-stat-panel">

    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-slider/bootstrap-slider.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/canvasjs/canvasjs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pathFactory.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/ion-range-slider/js/ion-range-slider.js"/>"></script>
<script>

    function round(d, p){
        return Math.round(d * p) / p;
    }

    function sDiv(a, b){
        if(b == 0 || b == 0.0)
                return 0;
        return a / b;
    }

    function fract(a, b, precision){
        precision = precision || 100;
        var pr = Math.round((a / b) * 100.0 * precision) / precision;
        return pr;
    }
    String.prototype.replaceAll = function(old_str, new_str){
        var content = this;
        return content.split(old_str).join(new_str);
    };
    String.prototype.format = function()
    {
        var content = this;
        for (var i=0; i < arguments.length; i++)
        {
            var replacement = '{' + i + '}';
            content = content.replaceAll(replacement, arguments[i]);
        }
        return content;
    };
    $('#update-button').click(function() {
        $.getJSON(retrievePath('/stat-service/tests/ind-devs-stone/'
                + $('#test-sel').find('option:selected').val()
                + '/' + $('#treatment-number-sel').find('option:selected').val()),
                function(resp){
                    $('#ind-stat-panel').empty();
                    if($("input[name='grview-sel']:checked").val() == "one"){
                        visOneGr(resp);
                    }
                    else{
                        visSepGrs(resp);
                    }
                });
    });
    function visSepGrs(resp){
        Object.keys(resp).forEach(function(key, i){
            if(key != "volume"){
                var ind_stat = resp[key];
                if(ind_stat.corr >= ($('#corr-limit').val() / 100)){
                    $('#ind-stat-panel').append(("<div id='ind_{0}-panel' class='panel panel-default'>" +
                    "<div class='panel-heading'>{1}</div>" +
                    "<div class='panel-body' id='ind_{0}-panel-body'>" +
                    "<div id='ind_{0}-bar'></div>" +
                    "<div id='ind_{0}-table'></div>" +
                    "<div>*, ** Данные коэффициенты показывают, какова роль показателя в образовании мочевых камней любого типа</div>" +
                    "</div>" +
                    "</div>").format(i, key));
                    if($("input[name='par-sel']:checked").val() == "ratio"){
                        var devs_total = ind_stat.devs.oxalates + ind_stat.devs.phosphates + ind_stat.devs.urates;
                        var normal_total = ind_stat.normal.oxalates + ind_stat.normal.phosphates + ind_stat.normal.urates;
                        var margin = devs_total + normal_total;
                        var ox_m = ind_stat.normal.oxalates + ind_stat.devs.oxalates;
                        var ph_m = ind_stat.normal.phosphates + ind_stat.devs.phosphates;
                        var ur_m = ind_stat.normal.urates + ind_stat.devs.urates;
                        var oxs = {
                            x: ['в нормe', 'за пределами нормы'],
                            y: [(ind_stat.normal.oxalates / ox_m) * 100, (ind_stat.devs.oxalates / ox_m) * 100],
                            name: 'Оксалаты',
                            type: 'bar'
                        };
                        var phs = {
                            x: ['в нормe', 'за пределами нормы'],
                            y: [(ind_stat.normal.phosphates / ph_m) * 100, (ind_stat.devs.phosphates / ph_m) * 100],
                            name: 'Фосфаты',
                            type: 'bar'
                        };
                        var urs = {
                            x: ['в нормe', 'за пределами нормы'],
                            y: [(ind_stat.normal.urates / ur_m) * 100, (ind_stat.devs.urates / ur_m) * 100],
                            name: 'Ураты',
                            type: 'bar'
                        };
                        Plotly.newPlot('ind_{0}-bar'.format(i), [oxs, phs, urs], {
                            title: key,
                            font: {
                                size: 17
                            },
                            legend: {
                                font: {
                                    size: 18
                                }
                            },
                            titleFont: {
                                size: 25
                            },
                            yaxis:{
                                title: "Доля, %",
                                titleFont: {
                                    size: 20
                                }
                            },
                            xaxis: {
                                titleFont: {
                                    size: 20
                                }
                            },
                            barmode: 'group'
                        });
                        $('#ind_{0}-table'.format(i)).append("<table class='table table-bordered table-hover'>" +
                                "<thead class='thead-inverse'>" +
                                "<tr>" +
                                ("<th>Значение показателя '{0}'</th>").format(key) +
                                "<th>Оксалаты</th>" +
                                "<th>Фосфаты</th>" +
                                "<th>Ураты</th>" +
                                "</tr>" +
                                "</thead>" +
                                "<tbody>" +
                                "<tr>" +
                                "<th>В норме</th>" +
                                "<td>{0}%</td>".format(fract(ind_stat.normal.oxalates, ox_m)) +
                                "<td>{0}%</td>".format(fract(ind_stat.normal.phosphates, ph_m)) +
                                "<td>{0}%</td>".format(fract(ind_stat.normal.urates, ur_m)) +
                                "</tr>" +
                                "<th>Вне нормы</th>" +
                                "<td>{0}%</td>".format(fract(ind_stat.devs.oxalates, ox_m)) +
                                "<td>{0}%</td>".format(fract(ind_stat.devs.phosphates, ph_m)) +
                                "<td>{0}%</td>".format(fract(ind_stat.devs.urates, ur_m)) +
                                "</tr>" +
                                "<tr>" +
                                "<th>Наблюдений</th>" +
                                "<td>{0}</td>".format(ox_m) +
                                "<td>{0}</td>".format(ph_m) +
                                "<td>{0}</td>".format(ur_m) +
                                "</tr>" +
                                "<tr>" +
                                "<th>Всего наблюдений</th>" +
                                "<td colspan='4'>{0}</td>".format(margin) +
                                "</tr>" +
                                "<tr>" +
                                "<th>Коэффициент Крамера*</th>" +
                                "<td colspan='4'>{0}%</td>".format(round(ind_stat.corr, 1000) * 100.0) +
                                "</tr>" +
                                "<tr>" +
                                "<th>Коэффициент сопряженности Пирсона**</th>" +
                                "<td colspan='4'>{0}%</td>".format(round(ind_stat.pearson_c, 1000) * 100.0) +
                                "</tr>" +
                                "</tbody>" +
                                "</table>");
                    }
                    else{
                        Plotly.newPlot('ind_{0}-bar'.format(i), [
                            {
                                x: ['Оксалаты', 'Фосфаты', 'Ураты'],
                                y: [ind_stat.stone_corr.oxalates_corr * 100, ind_stat.stone_corr.phosphates_corr * 100, ind_stat.stone_corr.urates_corr * 100],
                                type: 'bar'
                            }], {
                            title: key,
                            legend: {
                                font: {
                                    size: 16
                                }
                            },
                            titleFont: {
                                size: 20
                            },
                            yaxis:{
                                title: "Корреляция, %",
                                titleFont: {
                                    size: 16
                                }
                            },
                            barmode: 'group'
                        });
                        $('#ind_{0}-table'.format(i)).append("<table class='table table-bordered table-hover'>" +
                                "<thead class='thead-inverse'>" +
                                "<tr>" +
                                "<th></th>" +
                                "<th>Оксалаты, %</th>" +
                                "<th>Фосфаты, %</th>" +
                                "<th>Ураты, %</th>" +
                                "</tr>" +
                                "</thead>" +
                                "<tbody>" +
                                "<tr>" +
                                "<th>Корреляция между камнем и показателем</th>" +
                                "<td>{0}%</td>".format(round(ind_stat.stone_corr.oxalates_corr, 100) * 100) +
                                "<td>{0}%</td>".format(round(ind_stat.stone_corr.phosphates_corr, 100) * 100) +
                                "<td>{0}%</td>".format(round(ind_stat.stone_corr.urates_corr, 100) * 100) +
                                "</tr>" +
                                "<tr>" +
                                "<th>Корреляция*</th>" +
                                "<td colspan='4'>{0}%</td>".format(round(ind_stat.corr, 1000) * 100.0) +
                                "</tr>" +
                                "<tr>" +
                                "<th>Коэффициент сопряженности Пирсона**</th>" +
                                "<td colspan='4'>{0}%</td>".format(round(ind_stat.pearson_c, 1000) * 100.0) +
                                "</tr>" +
                                "</tbody>" +
                                "</table>");
                    }
                }
            }
        });
    }
    function visOneGr(resp){
        var lay = {
            title: 'Белки',
            font: {
                size: 17
            },
            titleFont: {
                size: 18
            },
            legend: {
                font: {
                    size: 16
                }
            },
            yaxis:{
                title: "Корреляция, %",
                titleFont: {
                    size: 14
                }
            },
            barmode: 'group'
        };
        var protein = [
            {
                type: 'bar',
                name: 'Оксалаты',
                x: ['Общий белок', 'Альбумин'],
                y: [
                    resp['Общий белок'].stone_corr.oxalates_corr * 100.0,
                    resp['Альбумин'].stone_corr.oxalates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Фосфаты',
                x: ['Общий белок', 'Альбумин'],
                y: [
                    resp['Общий белок'].stone_corr.phosphates_corr * 100.0,
                    resp['Альбумин'].stone_corr.phosphates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Ураты',
                x: ['Общий белок', 'Альбумин'],
                y: [
                    resp['Общий белок'].stone_corr.urates_corr * 100.0,
                    resp['Альбумин'].stone_corr.urates_corr * 100.0
                ]
            }
        ];
        var metal = [
            {
                type: 'bar',
                name: 'Оксалаты',
                x: ['Кальций', 'Калий', 'Натрий'],
                y: [
                    resp['Общий кальций'].stone_corr.oxalates_corr * 100.0,
                    resp['Калий'].stone_corr.oxalates_corr * 100.0,
                    resp['Натрий'].stone_corr.oxalates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Фосфаты',
                x: ['Кальций', 'Калий', 'Натрий'],
                y: [
                    resp['Общий кальций'].stone_corr.phosphates_corr * 100.0,
                    resp['Калий'].stone_corr.phosphates_corr * 100.0,
                    resp['Натрий'].stone_corr.phosphates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Ураты',
                x: ['Кальций', 'Калий', 'Натрий'],
                y: [
                    resp['Общий кальций'].stone_corr.urates_corr * 100.0,
                    resp['Калий'].stone_corr.urates_corr * 100.0,
                    resp['Натрий'].stone_corr.urates_corr * 100.0
                ]
            }
        ];
        var acid = [
            {
                type: 'bar',
                name: 'Оксалаты',
                x: ['Креатинин', 'Мочевая кислота'],
                y: [
                    resp['Креатинин'].stone_corr.oxalates_corr * 100.0,
                    resp['Мочевая кислота'].stone_corr.oxalates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Фосфаты',
                x: ['Креатинин', 'Мочевая кислота'],
                y: [
                    resp['Креатинин'].stone_corr.phosphates_corr * 100.0,
                    resp['Мочевая кислота'].stone_corr.phosphates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Ураты',
                x: ['Креатинин', 'Мочевая кислота'],
                y: [
                    resp['Креатинин'].stone_corr.urates_corr * 100.0,
                    resp['Мочевая кислота'].stone_corr.urates_corr * 100.0
                ]
            }
        ];
        var other = [
            {
                type: 'bar',
                name: 'Оксалаты',
                x: ['Глюкоза', 'Мочевина'],
                y: [
                    resp['Глюкоза'].stone_corr.oxalates_corr * 100.0,
                    resp['Мочевина'].stone_corr.oxalates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Фосфаты',
                x: ['Глюкоза', 'Мочевина'],
                y: [
                    resp['Глюкоза'].stone_corr.phosphates_corr * 100.0,
                    resp['Мочевина'].stone_corr.phosphates_corr * 100.0
                ]
            },
            {
                type: 'bar',
                name: 'Ураты',
                x: ['Глюкоза', 'Мочевина'],
                y: [
                    resp['Глюкоза'].stone_corr.urates_corr * 100.0,
                    resp['Мочевина'].stone_corr.urates_corr * 100.0
                ]
            }
        ];

        $("#ind-stat-panel").append("<div class='panel panel-default' id='protein-panel'></div>" +
                "<div class='panel panel-default' id='metal-ions-panel'></div>" +
                "<div class='panel panel-default' id='acid-panel'></div>" +
                "<div class='panel panel-default' id='other-panel'></div>");

        $("#protein-panel").append(("<div class='panel-heading'>{0}</div>" +
                "<div class='panel-body' id='{1}-panel-body'></div>").format("Белки", "protein"));

        $("#metal-ions-panel").append(("<div class='panel-heading'>{0}</div>" +
        "<div class='panel-body' id='{1}-panel-body'></div>").format("Ионы металлов", "metal-ions"));

        $("#acid-panel").append(("<div class='panel-heading'>{0}</div>" +
        "<div class='panel-body' id='{1}-panel-body'></div>").format("Кислоты", "acid"));

        $("#other-panel").append(("<div class='panel-heading'>{0}</div>" +
        "<div class='panel-body' id='{1}-panel-body'></div>").format("Прочие хим. соединения", "other"));

        $("#protein-panel-body").append("<div id='protein-bar'></div>");
        $("#metal-ions-panel-body").append("<div id='metal-ions-bar'></div>");
        $("#acid-panel-body").append("<div id='acid-bar'></div>");
        $("#other-panel-body").append("<div id='other-bar'></div>");

        Plotly.newPlot('protein-bar', protein, lay);
        lay.title = 'Ионы металлов';
        Plotly.newPlot('metal-ions-bar', metal, lay);
        lay.title = 'Кислоты';
        Plotly.newPlot('acid-bar', acid, lay);
        lay.title = 'Прочие хим. соединения';
        Plotly.newPlot('other-bar', other, lay);

        $("#protein-panel-body").append("<div id='protein-table'></div>");
        $("#metal-ions-panel-body").append("<div id='metal-ions-table'></div>");
        $("#acid-panel-body").append("<div id='acid-table'></div>");
        $("#other-panel-body").append("<div id='other-table'></div>");

        $("#protein-table").append("<table class='table table-bordered table-hover'>" +
                "<thead class='thead-inverse'>" +
                "<tr>" +
                "<th>Показатель</th>" +
                "<th>Оксалаты, %</th>" +
                "<th>Фосфаты, %</th>" +
                "<th>Ураты, %</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "<th>Общий белок</th>" +
                "<td>{0}%</td>".format(round(protein[0].y[0], 1000)) +
                "<td>{0}%</td>".format(round(protein[1].y[0], 1000)) +
                "<td>{0}%</td>".format(round(protein[2].y[0], 1000)) +
                "</tr>" +
                "<tr>" +
                "<th>Альбумин</th>" +
                "<td>{0}%</td>".format(round(protein[0].y[1], 1000)) +
                "<td>{0}%</td>".format(round(protein[1].y[1], 1000)) +
                "<td>{0}%</td>".format(round(protein[2].y[1], 1000)) +
                "</tr>" +
                "</tbody>" +
                "</table>");

        $("#metal-ions-table").append("<table class='table table-bordered table-hover'>" +
                "<thead class='thead-inverse'>" +
                "<tr>" +
                "<th>Показатель</th>" +
                "<th>Оксалаты, %</th>" +
                "<th>Фосфаты, %</th>" +
                "<th>Ураты, %</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "<th>Кальций</th>" +
                "<td>{0}%</td>".format(round(metal[0].y[0], 1000)) +
                "<td>{0}%</td>".format(round(metal[1].y[0], 1000)) +
                "<td>{0}%</td>".format(round(metal[2].y[0], 1000)) +
                "</tr>" +
                "<tr>" +
                "<th>Калий</th>" +
                "<td>{0}%</td>".format(round(metal[0].y[1], 1000)) +
                "<td>{0}%</td>".format(round(metal[1].y[1], 1000)) +
                "<td>{0}%</td>".format(round(metal[2].y[1], 1000)) +
                "</tr>" +
                "<tr>" +
                "<th>Натрий</th>" +
                "<td>{0}%</td>".format(round(metal[0].y[2], 1000)) +
                "<td>{0}%</td>".format(round(metal[1].y[2], 1000)) +
                "<td>{0}%</td>".format(round(metal[2].y[2], 1000)) +
                "</tr>" +
                "</tbody>" +
                "</table>");
        $("#acid-table").append("<table class='table table-bordered table-hover'>" +
                "<thead class='thead-inverse'>" +
                "<tr>" +
                "<th>Показатель</th>" +
                "<th>Оксалаты, %</th>" +
                "<th>Фосфаты, %</th>" +
                "<th>Ураты, %</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "<th>Креатинин</th>" +
                "<td>{0}%</td>".format(round(acid[0].y[0], 1000)) +
                "<td>{0}%</td>".format(round(acid[1].y[0], 1000)) +
                "<td>{0}%</td>".format(round(acid[2].y[0], 1000)) +
                "</tr>" +
                "<tr>" +
                "<th>Мочевая кислота</th>" +
                "<td>{0}%</td>".format(round(acid[0].y[1], 1000)) +
                "<td>{0}%</td>".format(round(acid[1].y[1], 1000)) +
                "<td>{0}%</td>".format(round(acid[2].y[1], 1000)) +
                "</tr>" +
                "</tbody>" +
                "</table>");
        $("#other-table").append("<table class='table table-bordered table-hover'>" +
                "<thead class='thead-inverse'>" +
                "<tr>" +
                "<th>Показатель</th>" +
                "<th>Оксалаты, %</th>" +
                "<th>Фосфаты, %</th>" +
                "<th>Ураты, %</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "<th>Глюкоза</th>" +
                "<td>{0}%</td>".format(round(other[0].y[0], 1000)) +
                "<td>{0}%</td>".format(round(other[1].y[0], 1000)) +
                "<td>{0}%</td>".format(round(other[2].y[0], 1000)) +
                "</tr>" +
                "<tr>" +
                "<th>Мочевина</th>" +
                "<td>{0}%</td>".format(round(other[0].y[1], 1000)) +
                "<td>{0}%</td>".format(round(other[1].y[1], 1000)) +
                "<td>{0}%</td>".format(round(other[2].y[1], 1000)) +
                "</tr>" +
                "</tbody>" +
                "</table>");
    }
</script>
</body>
</html>
