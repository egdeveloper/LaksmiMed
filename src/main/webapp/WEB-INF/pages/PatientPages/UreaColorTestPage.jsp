<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>${patient.fullName} - Хроматография</title>
  <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/bootstrap-datepicker/datepicker/css/datepicker.css"/>"/>
  <style rel="stylesheet" type="text/css">
    .panel-body > .row{
      margin-left: 10px;
      margin-right: 10px;
    }
  </style>
</head>
<body style="padding-top: 70px;">
<c:url var="addUreaColorTest" value="/test/urea_color"/>
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
<div class="container">
  <fieldset>
    <legend>Хроматография</legend>
    <form:form method="post" action="${addUreaColorTest}" modelAttribute="medicalTest">
      <div class="panel panel-primary" style="margin-bottom: 20px;">
        <div class="panel-heading">
          Основная информация
        </div>
        <div class="panel-body">
          <div class="row">
            <label class="col-md-12">ФИО пациента: ${patient.fullName}</label>
          </div>
          <div class="row">
            <div class="col-md-4">
              <div class="form-group">
                <label class="control-label">Дата</label>
                <form:input cssClass="form-control dateField" path="testDate"/>
              </div>
            </div>
            <div class="col-md-4">
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
            <div class="col-md-4">
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
          </div> <!-- testDate, patientState, treatmentNumber -->
          <div class="row">
            <div class="col-md-12">
              <div class="form-group">
                <label class="control-label">Лечение до проведения анализа</label>
                <form:input cssClass="form-control" path="treatment"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="form-group">
                <label class="control-label">Описание</label>
                <form:input cssClass="form-control" path="description"/>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          Показатели
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="form-group">
              <label class="control-label">Диурез</label>
              <form:input cssClass="form-control" path="duv"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Хлорид</label>
              <form:input cssClass="form-control" path="clSalt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Нитрит</label>
              <form:input cssClass="form-control" path="no2Salt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Нитрат</label>
              <form:input cssClass="form-control" path="no3Salt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Сульфат</label>
              <form:input cssClass="form-control" path="so3Salt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Фосфат</label>
              <form:input cssClass="form-control" path="po3Salt"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Цитрат</label>
              <form:input cssClass="form-control" path="citrate"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Изоцитрат</label>
              <form:input cssClass="form-control" path="isoCitrate"/>
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label class="control-label">Мочевая кислота</label>
              <form:input cssClass="form-control" path="ureaAcid"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row pull-right">
        <button type="submit" class="btn btn-primary">Сохранить анализы</button>
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

