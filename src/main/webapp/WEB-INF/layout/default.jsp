<%--
  Created by IntelliJ IDEA.
  User: xlch
  Date: 2017/2/21
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="module" value="channel"/>

<!DOCTYPE html>
<html>
<head>
    <title>系统管理</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />


    <link type="image/x-icon" href="${ctx}/static/back/img/favicon.ico" rel="shortcut icon">
    <link href="${ctx}/static/back/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/static/back/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/assets/global/css/components.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/assets/layouts/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/js/slider/unslider.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/static/back/assets/global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/back/assets/global/plugins/bootstrap-multiselect/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/back/js/datatables/DataTables-1.10.16/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/js/bootstrap-fileinput/css/fileinput.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/js/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/js/city-picker/css/city-picker.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/static/back/css/custom.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/back-common.css" rel="stylesheet" type="text/css"/>
    
    <script src="${ctx}/static/back/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/back/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <sitemesh:head />

</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white" <sitemesh:getProperty property="body.id" writeEntireProperty="true" />>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner container">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
            <img src="${ctx}/static/back/img/setting.jpg" alt="logo" class="logo-default" />
        </div>
        <div class="site-title">系统管理</div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <%--<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>--%>
        <div class="action pull-right">
            <ul class="nav navbar-nav pull-right">

                <%@include file="/WEB-INF/layout/navigation.jsp"%>

                <!-- BEGIN USER LOGIN DROPDOWN -->
                <%--<shiro:user>--%>
                <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->

                <%--</shiro:user>--%>
                <!-- END USER LOGIN DROPDOWN -->

            </ul>
        </div>
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"> </div>
<!-- END HEADER & CONTENT DIVIDER -->
<!-- BEGIN CONTAINER -->
<div class="container">
    <div class="page-container">
        <div class="page-sidebar-wrapper">
            <%@ include file="/WEB-INF/layout/left-double.jsp" %>
        </div>
        <!-- BEGIN CONTENT -->
        <div class="page-content-wrapper">
            <sitemesh:body></sitemesh:body>
        </div>
        <!-- END CONTENT -->

    </div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="/WEB-INF/layout/footer.jsp"%>
<!-- END FOOTER -->
<!--[if lt IE 9]>
<script src="${ctx}/static/back/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/back/global/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<%--<script src="${ctx}/static/back/global/plugins/js.cookie.min.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/static/back/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/static/back/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/static/back/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/back/js/momentjs/moment.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${ctx}/static/back/js/handlebars/handlebars-v4.0.11.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/datatables/datatables.js" type="text/javascript"></script>
<%--<script src="${ctx}/static/back/assets/global/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/back/assets/global/plugins/bootstrap/js/tooltip.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/datatables/DataTables-1.10.16/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/bootstrap-fileinput/locales/zh.js" type="text/javascript"></script>
<script src="${ctx}/static/back/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
<script src="${ctx}/static/back/assets/global/plugins/bootstrap-multiselect/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/jquery-validator/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/jquery-validator/locale/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/underscore/underscore.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/bootstrap-paginator/1.0.2/bootstrap-paginator.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/ztree/jquery.ztree.all.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/util/utils.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/city-picker/js/city-picker.data.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/city-picker/js/city-picker.js" type="text/javascript"></script>
<script src="${ctx}/static/back/js/custom/global-init.js" type="text/javascript"></script>
<sitemesh:getProperty property="page.page_script"></sitemesh:getProperty>
</body>
</html>

