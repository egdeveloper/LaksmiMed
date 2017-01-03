<%--
  Created by IntelliJ IDEA.
  User: egdeveloper
  Date: 22.11.15
  Time: 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Добро пожаловать!!</title>
  <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
  <style>
    .carousel-caption{
      display: inline-block;
      vertical-align: middle;
      text-align:center;
      background-color: rgba(0,0,0,0.5);
    }
    .nav-tabs {
      margin-bottom: 15px;
    }
    .sign-with {
      margin-top: 25px;
      padding: 20px;
    }
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

    .marketing{
      margin-bottom: 20px;
    }
    .marketing .span4 {
      text-align: center;
    }
    .marketing h2 {
      font-weight: normal;
    }
    .marketing .span4 p {
      margin-left: 10px;
      margin-right: 10px;
    }
    .featurette-divider {
      margin: 10px 0; /* Space out the Bootstrap <hr> more */
    }
    .featurette {
      padding-top: 20px; /* Vertically center images part 1: add padding above and below text. */
      overflow: hidden; /* Vertically center images part 2: clear their floats. */
    }
    .featurette-image {
      margin-top: -120px; /* Vertically center images part 3: negative margin up the image the same amount of the padding to center it. */
    }

    /* Give some space on the sides of the floated elements so text doesn't run right into it. */
    .featurette-image.pull-left {
      margin-right: 40px;
    }
    .featurette-image.pull-right {
      margin-left: 40px;
    }

    /* Thin out the marketing headings */
    .featurette-heading {
      font-size: 40px;
      font-weight: 300;
      line-height: 1;
      letter-spacing: -1px;
    }

    body {
      padding-bottom: 40px;
      color: #5a5a5a;
      min-width: 100%;
      width: auto !important;
    }
  </style>
</head>
<body style="padding-top: 50px;">
<c:url var="registerAction" value="/register"/>
<c:url var="login_action" value="/login"/>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <div class="navbar-brand">LaksmiMed</div>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="<c:url value="/"/>">Главная</a></li>
      <li><a href="<c:url value="/about2"/>">О сайте</a></li>
      <li><a href="<c:url value="/help"/>">Помощь</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#" data-toggle="modal" data-target="#login-modal">Войти</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title" id="myModalLabel">Вход в систему LaksmiMed</h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div style="padding-right: 30px; padding-left: 5px">
              <ul class="nav nav-tabs">
                <li class="active"><a href="#login" data-toggle="tab">Войти</a></li>
                <li><a href="#signup" data-toggle="tab">Регистрация</a></li>
              </ul>
              <div class="tab-content">
                <div class="tab-pane active" id="login">
                  <form:form id="login-form" role="form" class="form-horizontal" method="post" action="${login_action}" modelAttribute="loginAuth">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Логин</label>
                      <div class="col-sm-10">
                        <div class="input-group">
                          <span class="input-group-addon">
                            <i class="glyphicon glyphicon-user"></i>
                          </span>
                          <form:input class="form-control" placeholder="Логин" path="login"/>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Пароль</label>
                      <div class="col-sm-10">
                        <div class="input-group">
                          <span class="input-group-addon">
                            <i class="glyphicon glyphicon-lock"></i>
                          </span>
                          <form:password class="form-control" id="exampleInputPassword1" placeholder="Пароль" path="password"/>
                        </div>
                      </div>
                    </div>
                    <div class="row pull-right">
                      <button type="submit" class="btn btn-primary btn-sm">Войти</button>
                    </div>
                  </form:form>
                </div>
                <div class="tab-pane" id="signup">
                  <form:form id="signup-form" role="form" cssClass="form-horizontal" method="post" action="${registerAction}" modelAttribute="signupData">
                    <%--<div class="form-group">--%>
                      <%--<label class="col-sm-4 control-label">ФИО <span style="color: red">*</span></label>--%>
                      <%--<div class="col-sm-8">--%>
                        <%--<form:input cssClass="form-control" path="fullName" placeholder="ФИО"/>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                      <%--<label class="col-sm-4 control-label">Должность <span style="color: red">*</span></label>--%>
                      <%--<div class="col-sm-8">--%>
                        <%--<form:input cssClass="form-control" path="jobPost" id="jobPost" placeholder="Должность"/>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                      <%--<label class="col-sm-4 control-label">Место работы <span style="color: red">*</span></label>--%>
                      <%--<div class="col-sm-8">--%>
                        <%--<form:input cssClass="form-control" path="jobPlace" id="jobPlace" placeholder="Место работы"/>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                      <%--<label class="col-sm-4 control-label">Телефон <span style="color: red">*</span></label>--%>
                      <%--<div class="col-sm-8">--%>
                        <%--<form:input cssClass="form-control" path="phoneNumber" id="phoneNumber" placeholder="Телефон"/>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                      <%--<label class="col-sm-4 control-label">Электронная почта <span style="color: red">*</span></label>--%>
                      <%--<div class="col-sm-8">--%>
                        <%--<form:input cssClass="form-control" path="email" id="email" placeholder="Электронная почта"/>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                      <label class="col-sm-4 control-label">Логин <span style="color: red">*</span></label>
                      <div class="col-sm-8">
                        <form:input cssClass="form-control" path="login" id="login" placeholder="Логин"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-4 control-label">Пароль <span style="color: red">*</span></label>
                      <div class="col-sm-8">
                        <form:password cssClass="form-control" path="password" id="password" placeholder="Пароль"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-4 control-label">Подтвердите пароль <span style="color: red">*</span></label>
                      <div class="col-sm-8">
                        <form:password cssClass="form-control" path="confirmPassword" id="confirmPassword" placeholder="Подтверждение пароля"/>
                      </div>
                    </div>
                    <div class="row pull-right">
                      <button id="signup-button" type="submit" class="btn btn-primary">Регистрация</button>
                    </div>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<header id="slideShow" class="carousel slide">
  <ol class="carousel-indicators" style="margin-top: 10px;">
    <li data-target="#slideShow" data-slide-to="0" class="active"></li>
    <li data-target="#slideShow" data-slide-to="1"></li>
    <li data-target="#slideshow" data-slide-ti="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="item active">
      <img src="<c:url value="/resources/img/healthcare-banner.jpg"/>" style="height: 500px; width: 1440px;"/>
      <div class="carousel-caption">
          <h2>
            <%--LaksmiMed - сервис, предоставляющий Вам  широкий набор инструментов для работы с медицинскими картами пациентов--%>
            <%--и статистической обработки медицинских данных для постановки диагноза и проведения исследований--%>
              LaksmiMed - сервис для работы с медицинскими картами пациентов
              и статистической обработки медицинских данных для постановки диагноза и проведения исследований
            <br/>
          </h2>
          <a class="btn btn-lg btn-primary" href="#" data-toggle="modal" data-target="#login-modal" role="button">Войти</a>
      </div>
    </div>
    <div class="item">
      <img src="<c:url value="/resources/img/slider-img.jpg"/>" style="height: 500px; width: 1440px;"/>
      <div class="carousel-caption">
        <h2>
          Контролируйте поток пациентов, отслеживайте динамику их здоровья, составляйте отчеты с возможностью прикрепления к анализам
          необходимый контент
        </h2>
      </div>
    </div>
    <div class="item">
      <img src="<c:url value="/resources/img/slider4.jpg"/>" style="height: 500px; width: 1440px;"/>
      <div class="carousel-caption">
        <h2>
          Коммуницируйте с другими врачами, следите за статистическими сводками и своевременно диагностируйте
          заболевания
        </h2>
      </div>
    </div>
  </div>
  <a class="left carousel-control" href="#slideShow" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#slideShow" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
</header>
<div class="container marketing" style="margin-top: 20px; margin-bottom: 20px;">
  <%--<div class="row">--%>
      <%--<div class="col-md-3">--%>
        <%--<img src='<c:url value="/resources/img/stethoscope.jpg"/>' width="300" height="300"/>--%>
      <%--</div>--%>
      <%--<div class="col-md-9">--%>
        <%--<div>--%>
          <%--<h2 class="leader">Усовершенствуйте работу с медицинскими картами</h2>--%>
        <%--</div>--%>
        <%--<div>--%>
          <%--<p class="lead">Полностью электронная система учета медицинских документов, медицинских карт и анализов. Вам больше не нужно--%>
            <%--вручную заполнять отчеты--%>
          <%--</p>--%>
        <%--</div>--%>
      <%--</div>--%>
  <%--</div>--%>
  <%--<hr class="featurette-divider">--%>
  <%--<div class="row">--%>
    <%--<div class="col-md-9">--%>
      <%--<div>--%>
        <%--<h2 class="leader">Организуйте процедуру занесения анализов в базу данных и следите за динамикой показателей пациента</h2>--%>
      <%--</div>--%>
      <%--<div>--%>
        <%--<p class="lead">Большая кипа анализов и отчетов по ним - забудьте об этом кошмаре. Диагностика и лечени пациентов - основная задача, а остальным--%>
          <%--пусть занимается автоматизированная система сбора и обработки медицинских анализов--%>
        <%--</p>--%>
      <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-3">--%>
      <%--<img src='<c:url value="/resources/img/dataBase.jpg"/>' width="250" height="250"/>--%>
    <%--</div>--%>
  <%--</div>--%>
  <%--<hr class="featurette-divider">--%>
  <%--<div class="row">--%>
    <%--<div class="col-md-3">--%>
      <%--<img src='<c:url value="/resources/img/stat.png"/>' width="250" height="250"/>--%>
    <%--</div>--%>
    <%--<div class="col-md-9">--%>
      <%--<div>--%>
        <%--<h2 class="leader">Получайте актуальную информацию о состоянии своих пациентов</h2>--%>
      <%--</div>--%>
      <%--<div>--%>
        <%--<p class="lead">Динамика показателей и статистическая информация, собираемая распределенной системой, помогут--%>
          <%--Вам быстрее диагностировать заболевания и своевременно назначать лечение пациенту--%>
        <%--</p>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
  <%--<hr class="featurette-divider">--%>
  <%--<div>--%>
    <%--<div class="row">--%>
      <%--<div class="col-md-4">--%>
        <%--<h3 class="leader"><b>Используются только самые передовые технологии</b></h3>--%>
      <%--</div>--%>
    <%--</div>--%>
    <%--<div class="row">--%>
      <%--<div class="col-md-4">--%>
        <%--<div style="width: 200px; height: 200px;">--%>
          <%--<img src='<c:url value="/resources/img/mysql.png"/>' width="200" height="200"/>--%>
        <%--</div>--%>
        <%--<div>--%>
          <%--<p class="lead">Высокопроизводительный сервер баз данных <a href="https://www.mysql.com/">MySQL®</a></p>--%>
        <%--</div>--%>
      <%--</div>--%>
      <%--<div class="col-md-4">--%>
        <%--<div style="width: 200px; height: 200px;">--%>
          <%--<img src='<c:url value="/resources/img/springFramework.png"/>' width="160" height="160"/>--%>
        <%--</div>--%>
        <%--<div>--%>
          <%--<p class="lead">Универсальный фреймворк для создания распределенного enterprise-приложения <a href="https://spring.io/">Spring Framework®</a></p>--%>
        <%--</div>--%>
      <%--</div>--%>
      <%--<div class="col-md-4">--%>
        <%--<div>--%>
          <%--<img src='<c:url value="/resources/img/apacheTomcat.png"/>' width="200" height="200"/>--%>
        <%--</div>--%>
        <%--<div>--%>
          <%--<p class="lead">Легко конфигурируемый и быстрый веб-сервер с открытыми исходниками <a href="http://tomcat.apache.org/">Apache Tomcat®</a></p>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript">
//  $("#slideShow").carousel({interval: 5000});

  $(document).ready(function(){
    $("#signup-form").validate({
      rules: {
        fullName: {
          required: true
        },
        jobPost: {
          required: true
        },
        jobPlace: {
          required: true
        },
        login: {
          required: true
        },
        password: {
          required: true
        },
        email: {
          required: true
        }
      },
      focusCleanup: true,
      onkeyup: false,
      messages: {
        fullName: {
          required: "Введите ФИО"
        },
        jobPost: {
          required: "Введите должность"
        },
        jobPlace: {
          required: "Введите место работы"
        },
        login: {
          required: "Введите логин"
        },
        password: {
          required: "Введите пароль"
        },
        email: {
          required: "Введите email"
        }
      }
    });
  });

  $("#signup-button").click(function(){
    if($("#signup-form").valid()){
      $("#signup-form").submit();
    }
  });
</script>
</body>
</html>