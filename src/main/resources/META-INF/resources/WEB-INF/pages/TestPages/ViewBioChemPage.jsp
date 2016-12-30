<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-datepicker/datepicker/css/datepicker.css"/>"/>
</head>
<body style="padding-top: 70px;">
  <c:url var="addBiochemTest" value="/addBiochemTest"/>
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
            <li><a href="<c:url value="/indicatorDeviationsStatPage"/>">Отклонения от нормы</a></li>
            <li><a href="<c:url value="/indicatorDeviationsForStoneTypeStatPage"/>">По типу камня</a></li>
            <li><a href="<c:url value="/stoneComponentsStatPage"/>">Компоненты мочевых камней</a></li>
          </ul>
        </li>
        <li><a href="<c:url value="/patientList"/>">Пациенты</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/personalPage"/>">Личная страница</a></li>
        <li><a href="<c:url value="/logout"/>">Выйти</a></li>
      </ul>
    </div>
  </nav>
  <div class="container">
    <fieldset>
      <form:form modelAttribute="medicalTest">
      <div class="page-header">
        <div class="btn-toolbar pull-right">
          <div class="btn-group">
            <button class="btn btn-secondary btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" type="button">Экспортировать анализ</button>
            <ul class = "dropdown-menu" role = "menu">
              <li>
                <a class="dropdown-item" href="<c:url value="/generateTestReport/${medicalTest.retrieveEntityID()}/${medicalTest.id}"/>?type=application/vnd.openxmlformats-officedocument.wordprocessingml.document" download>Word</a>
              </li>
              <li>
                <a class="dropdown-item" href="<c:url value="/generateTestReport/${medicalTest.retrieveEntityID()}/${medicalTest.id}"/>?type=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" download>Excel</a>
              </li>
              <li>
                <a class="dropdown-item" href="<c:url value="/generateTestReport/${medicalTest.retrieveEntityID()}/${medicalTest.id}"/>?type=text/xml" download>XML</a>
              </li>
              <li>
                <a class="dropdown-item" href="<c:url value="/generateTestReport/${medicalTest.retrieveEntityID()}/${medicalTest.id}"/>?type=application/json" download>JSON</a>
              </li>
            </ul>
          </div>
        </div>
        <h2>Биохимический анализ крови</h2>
      </div>
        <div class="form-horizontal">
          <div class="row">
            <div class="form-group">
              <label class="control-label">Дата</label>
              <form:input cssClass="form-control dateField" path="testDate"/>
            </div>
          </div> <!-- testDate -->
          <div class="row">
            <div class="form-group">
              <label class="control-label">Описание</label>
              <form:input cssClass="form-control" path="description"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Лечение до проведения анализа</label>
              <form:input cssClass="form-control" path="treatment"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Номер стадии лечения</label>
              <form:select cssClass="form-control" path="treatmentNumber">
                <form:option value="NONE">не было</form:option>
                <form:option value="I">лечение 1</form:option>
                <form:option value="II">лечение 2</form:option>
                <form:option value="III">лечение 3</form:option>
                <form:option value="IV">лечение 4</form:option>
                <form:option value="V">лечение 5</form:option>
                <form:option value="VI">лечение 6</form:option>
                <form:option value="VII">лечение 7</form:option>
                <form:option value="VIII">лечение 8</form:option>
                <form:option value="IX">лечение 9</form:option>
                <form:option value="X">лечение 10</form:option>
                <form:option value="XI">лечение 11</form:option>
                <form:option value="XII">лечение 12</form:option>
              </form:select>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Состояние пациента на момент обследования</label>
              <form:select cssClass="form-control" path="patientState">
                <form:option value="HEALTHY">здоров</form:option>
                <form:option value="FAIR">стабилен</form:option>
                <form:option value="SERIOUS">болен</form:option>
                <form:option value="CRITICAL">серьезно болен</form:option>
                <form:option value="UNDERTERMINED">неопределенное</form:option>
              </form:select>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Общий белок</label>
              <form:input cssClass="form-control" path="commonProtein"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Мочевина</label>
              <form:input cssClass="form-control" path="urea"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Креатинин</label>
              <form:input cssClass="form-control" path="creatinine"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Общий билирубин</label>
              <form:input cssClass="form-control" path="commonBiliRuby"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Связанный билирубин</label>
              <form:input cssClass="form-control" path="linkedBiliRuby"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Холестерин</label>
              <form:input cssClass="form-control" path="cholesterol"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Триглицериды</label>
              <form:input cssClass="form-control" path="tag"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Липопр. выс. кон.</label>
              <form:input cssClass="form-control" path="hdl"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Липопр. низ. кон.</label>
              <form:input cssClass="form-control" path="ldl"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Коэф. атер.</label>
              <form:input cssClass="form-control" path="cholesterolRatio"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Аланинаминотрасфераза</label>
              <form:input cssClass="form-control" path="alt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Аспартатаминотрансфераза</label>
              <form:input cssClass="form-control" path="ast"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Щелочная фосфотаза</label>
              <form:input cssClass="form-control" path="alkp"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Креатинфосфокиназа</label>
              <form:input cssClass="form-control" path="ck"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Креатинфосфокиназа МВ</label>
              <form:input cssClass="form-control" path="ckmb"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Лактатдкгидрогеназа</label>
              <form:input cssClass="form-control" path="ldh"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">ГГТ</label>
              <form:input cssClass="form-control" path="ggt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Амилаза</label>
              <form:input cssClass="form-control" path="amylase"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Пакриатическая амилаза</label>
              <form:input cssClass="form-control" path="pancrAmylase"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Глюкоза</label>
              <form:input cssClass="form-control" path="glucose"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Мочевая кислота</label>
              <form:input cssClass="form-control" path="ureaAcid"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">С-реактивный белок</label>
              <form:input cssClass="form-control" path="crp"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Ревматоидный фактор</label>
              <form:input cssClass="form-control" path="rf"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Калий</label>
              <form:input cssClass="form-control" path="potassium"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Натрий</label>
              <form:input cssClass="form-control" path="sodium"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Хлор</label>
              <form:input cssClass="form-control" path="chlorine"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Общий кальций</label>
              <form:input cssClass="form-control" path="commonCalcium"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Ионизированный кальций</label>
              <form:input cssClass="form-control" path="ionCalcium"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Фосфор</label>
              <form:input cssClass="form-control" path="phosphor"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Железо</label>
              <form:input cssClass="form-control" path="ferrum"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Ферритин</label>
              <form:input cssClass="form-control" path="ferritin"/>
            </div>
          </div>
        </div>
      </form:form>
    </fieldset>
  </div>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/bootstrap-datepicker/datepicker/js/bootstrap-datepicker.js"/>"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      $(".dateField").datepicker({
        format : "dd.mm.yyyy",
        autoclose: true,
        locale: 'ru'
      });
    });
  </script>
</body>
</html>
