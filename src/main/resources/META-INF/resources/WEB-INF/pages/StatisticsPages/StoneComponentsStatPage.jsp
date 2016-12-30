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
                        <li><a href="<c:url value="/beforeTreatmentStatVisualization"/>">До лечения</a></li>
                        <li><a href="<c:url value="/stoneComponentsStatPage"/>">Компоненты мочевых камней</a></li>
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
        <div id="anions"></div>
    </div>
    <div class="row" id="anionsTable"></div>
    <div class="row" style="margin-top: 20px; margin-bottom: 20px;">
        <div id="cations"></div>
    </div>
    <div class="row" id="cationsTable"></div>
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
    var devsStat = {};
    var anionsTable = {};
    var cationsTable = {};
    function getAnionsData(){
        var data = {};
        data["OXALATES"] = {};
        data["URATES"] = {};
        data["PHOSPHATES"] = {};
        anionsTable["Оксалат"] = {};
        anionsTable["Цитрат"] = {};
        anionsTable["Фосфат"] = {};
        anionsTable["Сульфат"] = {};
        anionsTable["Мочевая кислота"] = {};
        Object.keys(data).forEach(function(key){
            data[key] = [];
            anionsTable["Оксалат"][key] = "-";
            anionsTable["Цитрат"][key] = "-";
            anionsTable["Фосфат"][key] = "-";
            anionsTable["Сульфат"][key] = "-";
            anionsTable["Мочевая кислота"][key] = "-";

            //Оксалат
            if(devsStat[key]["oxalate"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Оксалат",
                            y: devsStat[key]["oxalate"]["deviations"] / devsStat[key]["oxalate"]["volume"] * 100
                        }
                );
                anionsTable["Оксалат"][key] = {
                    deviations: devsStat[key]["oxalate"]["deviations"] / devsStat[key]["oxalate"]["volume"] * 100,
                    volume: devsStat[key]["oxalate"]["volume"]
                };
            }
            //

            if(devsStat[key]["citrate"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Цитрат",
                            y: devsStat[key]["citrate"]["deviations"] / devsStat[key]["citrate"]["volume"] * 100
                        }
                );
                anionsTable["Цитрат"][key] = {
                    deviations: devsStat[key]["citrate"]["deviations"] / devsStat[key]["citrate"]["volume"] * 100,
                    volume: devsStat[key]["citrate"]["volume"]
                };
            }
            if(devsStat[key]["phosphate"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Фосфат",
                            y: devsStat[key]["phosphate"]["deviations"] / devsStat[key]["phosphate"]["volume"] * 100
                        }
                );
                anionsTable["Фосфат"][key] = {
                    volume: devsStat[key]["phosphate"]["volume"],
                    deviations: devsStat[key]["phosphate"]["deviations"] / devsStat[key]["phosphate"]["volume"] * 100
                };
            }
            if(devsStat[key]["sulfate"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Сульфат",
                            y: devsStat[key]["sulfate"]["deviations"] / devsStat[key]["sulfate"]["volume"] * 100
                        }
                );
                anionsTable["Сульфат"][key] = {
                    volume: devsStat[key]["sulfate"]["volume"],
                    deviations: devsStat[key]["sulfate"]["deviations"] / devsStat[key]["sulfate"]["volume"] * 100
                };
            }
            if(devsStat[key]["ureaAcid"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Мочевая кислота",
                            y: devsStat[key]["ureaAcid"]["deviations"] / devsStat[key]["ureaAcid"]["volume"] * 100
                        }
                );
                anionsTable["Мочевая кислота"][key] = {
                    volume: devsStat[key]["ureaAcid"]["volume"],
                    deviations: devsStat[key]["ureaAcid"]["deviations"] / devsStat[key]["ureaAcid"]["volume"] * 100
                };
            }
        });
        return data;
    }
    function getCationsData(){
        var data = {};
        data["OXALATES"] = {};
        data["URATES"] = {};
        data["PHOSPHATES"] = {};
        cationsTable["Натрий"] = {};
        cationsTable["Магний"] = {};
        cationsTable["Калий"] = {};
        cationsTable["Кальций"] = {};
        Object.keys(data).forEach(function(key){
            data[key] = [];
            cationsTable["Натрий"][key] = "-";
            cationsTable["Магний"][key] = "-";
            cationsTable["Калий"][key] = "-";
            cationsTable["Кальций"][key] = "-";
            if(devsStat[key]["sodium"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Натрий",
                            y: devsStat[key]["sodium"]["deviations"] / devsStat[key]["sodium"]["volume"] * 100
                        }
                );
                cationsTable["Натрий"][key] = {
                    volume: devsStat[key]["sodium"]["volume"],
                    deviations: devsStat[key]["sodium"]["deviations"] / devsStat[key]["sodium"]["volume"] * 100
                };
            }
            if(devsStat[key]["magnesium"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Магний",
                            y: devsStat[key]["magnesium"]["deviations"] / devsStat[key]["magnesium"]["volume"] * 100
                        }
                );
                cationsTable["Магний"][key] = {
                    volume: devsStat[key]["magnesium"]["volume"],
                    deviations: devsStat[key]["magnesium"]["deviations"] / devsStat[key]["magnesium"]["volume"] * 100
                };
            }
            if(devsStat[key]["potassium"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Калий",
                            y: devsStat[key]["potassium"]["deviations"] / devsStat[key]["potassium"]["volume"] * 100
                        }
                );
                cationsTable["Калий"][key] = {
                    volume: devsStat[key]["potassium"]["volume"],
                    deviations: devsStat[key]["potassium"]["deviations"] / devsStat[key]["potassium"]["volume"] * 100
                };
            }
            if(devsStat[key]["calcium"]["volume"] > 0){
                data[key].push(
                        {
                            label: "Кальций",
                            y: devsStat[key]["calcium"]["deviations"] / devsStat[key]["calcium"]["volume"] * 100
                        }
                );
                cationsTable["Кальций"][key] = {
                    volume: devsStat[key]["calcium"]["volume"],
                    deviations: devsStat[key]["calcium"]["deviations"] / devsStat[key]["calcium"]["volume"] * 100
                };
            }
        });
        return data;
    }
    $("#anions").empty();
    $("#cations").empty();
    $("#anionsTable").empty();
    $("#cationsTable").empty();
    $("#updateStat").click(function(){
       if($("#treatmentNumberSelection").val() != null){
           $.ajax({
                dataType: "json",
                async: false,
                url: retrievePath("/stat-service/tests/stone-components/" + $("#treatmentNumberSelection").val()),
                success: function(data){
                    devsStat = data;
                }
           });
           var anionsStat = getAnionsData();
           var cationsStat = getCationsData();
           $("#anions").empty();
           $("#cations").empty();
           $("#anions").append("<div class='panel panel-default'>" +
                   "<div class='panel-heading'>Анионы</div>" +
                   "<div class='panel-body'>" +
                   "<div id='anionsBar'></div>" +
                   "</div>" +
                   "</div>");
           $("#cations").append("<div class='panel panel-default'>" +
                   "<div class='panel-heading'>Катионы</div>" +
                   "<div class='panel-body'>" +
                   "<div id='cationsBar'></div>" +
                   "</div>" +
                   "</div>");
           Plotly.newPlot("anionsBar",
                   [
                       {
                           x: anionsStat["OXALATES"].map(function(ind){
                               return ind["label"]
                           }),
                           y: anionsStat["OXALATES"].map(function(ind){
                               return ind["y"]
                           }),
                           type: "bar",
                           name: "Оксалаты"
                       },
                       {
                           x: anionsStat["URATES"].map(function(ind){
                               return ind["label"]
                           }),
                           y: anionsStat["URATES"].map(function(ind){
                               return ind["y"]
                           }),
                           type: "bar",
                           name: "Ураты"
                       },
                       {
                           x: anionsStat["PHOSPHATES"].map(function(ind){
                               return ind["label"]
                           }),
                           y: anionsStat["PHOSPHATES"].map(function(ind){
                               return ind["y"]
                           }),
                           type: "bar",
                           name: "Фосфаты"
                       }
                   ],
                   {
                       title: "Анионы",
                       font: {
                           size: 18
                       },
                       titleFont:{
                           size: 20
                       },
                       barmode: "group",
                       yaxis:{
                           title: "%",
                           titleFont: {
                               size: 14
                           }
                       }
                   }
           );
           Plotly.newPlot("cationsBar",
                   [
                       {
                           x: cationsStat["OXALATES"].map(function(ind){
                               return ind["label"]
                           }),
                           y: cationsStat["OXALATES"].map(function(ind){
                               return ind["y"]
                           }),
                           type: "bar",
                           name: "Оксалаты"
                       },
                       {
                           x: cationsStat["URATES"].map(function(ind){
                               return ind["label"]
                           }),
                           y: cationsStat["URATES"].map(function(ind){
                               return ind["y"]
                           }),
                           type: "bar",
                           name: "Ураты"
                       },
                       {
                           x: cationsStat["PHOSPHATES"].map(function(ind){
                               return ind["label"]
                           }),
                           y: cationsStat["PHOSPHATES"].map(function(ind){
                               return ind["y"]
                           }),
                           type: "bar",
                           name: "Фосфаты"
                       }
                   ],
                   {
                       title: "Катионы",
                       font: {
                           size: 18
                       },
                       titleFont:{
                           size: 20
                       },
                       yaxis:{
                           title: "%",
                           titleFont: {
                               size: 14
                           }
                       },
                       barmode: "group"
                   }
           );
           $("#anionsTable").empty();
           $("#anionsTable").append("<table id='innerAnionsTable' class='table table-striped table-bordered' style='margin-top: 20px;' cellpadding='0' width='100%'></table>");
           $("#innerAnionsTable").append("<thead>" +
                   "<tr>" +
                   "<td>Показатель</td>" +
                   "<td>Оксалаты (% | Объем выборки)</td>" +
                   "<td>Ураты (% | Объем выборки)</td>" +
                   "<td>Фосфаты (% | Объем выборки)</td>" +
                   "</tr>" +
                   "</thead><tbody id='innerAnionsTable_Body'></tbody>");
           for(var ind in anionsTable){
               $("#innerAnionsTable_Body").append("<tr>" +
                       "<td>" + ind + "</td>" +
                       "<td>" + (anionsTable[ind]["OXALATES"] instanceof String ? "-" : parseFloat(anionsTable[ind]["OXALATES"].deviations.toFixed(3)).toString() + "% из " + anionsTable[ind]["OXALATES"].volume) + "</td>" +
                       "<td>" + (anionsTable[ind]["URATES"] instanceof String ? "-" : parseFloat(anionsTable[ind]["URATES"].deviations.toFixed(3)).toString() + "% из " + anionsTable[ind]["URATES"].volume) + "</td>" +
                       "<td>" + (anionsTable[ind]["PHOSPHATES"] instanceof String ? "-" : parseFloat(anionsTable[ind]["PHOSPHATES"].deviations.toFixed(3)).toString() + "% из " + anionsTable[ind]["PHOSPHATES"].volume) + "</td>" +
                       "</tr>");
           }
           $("#cationsTable").empty();
           $("#cationsTable").append("<table id='innerCationsTable' class='table table-striped table-bordered' style='margin-top: 20px;' cellpadding='0' width='100%'></table>");
           $("#innerCationsTable").append("<thead>" +
                   "<tr>" +
                   "<td>Показатель</td>" +
                   "<td>Оксалаты (% | Объем выборки)</td>" +
                   "<td>Ураты (% | Объем выборки)</td>" +
                   "<td>Фосфаты (% | Объем выборки)</td>" +
                   "</tr>" +
                   "</thead><tbody id='innerCationsTable_Body'></tbody>");
           for(var ind in cationsTable){
               $("#innerCationsTable_Body").append("<tr>" +
                       "<td>" + ind + "</td>" +
                       "<td>" + (cationsTable[ind]["OXALATES"] instanceof String ? "-" : parseFloat(cationsTable[ind]["OXALATES"].deviations.toFixed(3)).toString() + "% из " + cationsTable[ind]["OXALATES"].volume) + "</td>" +
                       "<td>" + (cationsTable[ind]["URATES"] instanceof String ? "-" : parseFloat(cationsTable[ind]["URATES"].deviations.toFixed(3)).toString() + "% из " + cationsTable[ind]["URATES"].volume) + "</td>" +
                       "<td>" + (cationsTable[ind]["PHOSPHATES"] instanceof String ? "-" : parseFloat(cationsTable[ind]["PHOSPHATES"].deviations.toFixed(3)).toString() + "% из " + cationsTable[ind]["PHOSPHATES"].volume) + "</td>" +
                       "</tr>");
           }
       }
       else{
           bootbox.alert("Выберите стадию лечения!");
       }
    });
</script>
</body>
</html>
