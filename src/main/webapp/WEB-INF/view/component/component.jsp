<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>组件管理</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/js/jedate/jedate.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/component/component.css" rel="stylesheet" type="text/css"/>
</head>

<body id="component">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 组件管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="actions pull-right" style="margin-bottom: 5px;">
            <a href="${ctx}/back/component/add/home" class="btn btn-circle blue">
                <i class="fa fa-x fa-plus"></i> 新增 </a>
        </div>
        <div class="portlet-body">
            <table class="table table-striped table-bordered dataTable no-footer" id="component-table">
                <thead>
                <th>任务名称</th>
                <th>任务组</th>
                <th>任务执行时间</th>
                <th>状态</th>
                <th>创建时间</th>
                <%--<th>修改时间</th>--%>
                <th>操作</th>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>

    <div class="alert-notice-wrap hide">
        <div class="alert-notice">
            <span class="glyphicon glyphicon-ok"> </span>
            <div>操作成功</div>
        </div>
    </div>

</div>


<content tag="page_script">
    <script src="${ctx}/static/back/js/jedate/jedate.js" type="text/javascript"></script>
    <script src="${ctx}/static/back/js/custom/component/component.js" type="text/javascript"></script>
</content>

</body>
</html>