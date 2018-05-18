<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>平台监控和告警</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/css/user/user-manage.css" rel="stylesheet" type="text/css"/>
</head>

<body id="monitor">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 平台监控和告警
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <%--<div class="portlet">--%>
    <%--</div>--%>
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <!-- BEGIN PORTLET-->
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-green"></i>
                        <span class="caption-subject font-green bold uppercase">CPU利用率监控</span>
                        <span class="caption-helper">每秒使用率,监控最近60s内使用情况</span>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="">
                        <div id="cpu-line" class="chart" style="height: 300px;width: 750px"> </div>
                    </div>
                </div>
            </div>
            <!-- END PORTLET-->
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <!-- BEGIN PORTLET-->
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-share font-red-sunglo"></i>
                        <span class="caption-subject font-red-sunglo bold uppercase">内存使用监控</span>
                        <span class="caption-helper">每秒使用情况,监控最近60s内使用情况</span>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="">
                        <div id="mem-line" class="chart" style="height: 300px;width: 750px"> </div>
                    </div>
                </div>
            </div>
            <!-- END PORTLET-->
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <!-- BEGIN PORTLET-->
            <div class="portlet light bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-bar-chart font-green"></i>
                        <span class="caption-subject font-green bold uppercase">磁盘使用率监控</span>
                        <span class="caption-helper">每秒使用率,监控最近60s内使用情况</span>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="">
                        <div id="disk-line" class="chart" style="height: 300px;width: 750px"> </div>
                    </div>
                    <div class="row" style="margin-left: 50px">
                        <div>各磁盘使用量(G):</div>
                        <div id="detail" class="pull-left"></div>
                    </div>
                </div>
            </div>
            <!-- END PORTLET-->
        </div>
    </div>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/echarts/echarts.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/custom/monitor/monitor.js" type="text/javascript"></script>
    </content>

</body>
</html>