<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <meta charset="utf-8" />
    <title>电池在线销售平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <link type="image/x-icon" href="${ctx}/static/back/img/favicon.ico" rel="shortcut icon">
    <link href="${ctx}/static/back/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/back/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/back/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="${ctx}/static/back/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css">
    <link href="${ctx}/static/back/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css">
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${ctx}/static/back/assets/pages/css/login-4.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/back/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/back/css/login.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/static/back/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <style type="text/css">
        img {
            width: 50px;
        }
    </style>
</head>

<body class=" login">
<!-- BEGIN LOGO -->
<div class="logo">
    <a href="${ctx}/front/index">
        <img src="${ctx}/static/back/img/shop.jpg" alt=""> </a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form id="login-form" class="login-form" novalidate="novalidate">
        <h3 class="form-title">管理员登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span> Enter any username and password. </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input id="username" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input id="password" class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"> </div>
        </div>
        <div class="form-group margin-top-20 margin-bottom-20">
            <div> <label id="notice" class="error-msg"></label> </div>
        </div>
        <div class="form-actions">
            <label class="pull-left"><input id="remember" type="checkbox" name="remember"> 记住我 </label>
            <label class="pull-right"><button type="button" data-click="login" class="btn green pull-right"> 登录 </button></label>
        </div>
    </form>
    <!-- END REGISTRATION FORM -->
</div>
<script src="${ctx}/static/back/js/jquery-validator/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/util/utils.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/custom/login.js" type="text/javascript"></script>
</body>
</html>

