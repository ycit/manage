<%--
  Created by IntelliJ IDEA.
  User: xlch
  Date: 2017/2/13
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>最新资讯管理</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/css/news-manager.css" rel="stylesheet" type="text/css"/>
</head>

<body id="news-manager">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title">最新资讯管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet">
        <div class="actions pull-right" style="margin-bottom: 6px">
            <a href="${ctx}/back/newses/add" class="btn btn-circle btn-default">
                <i class="fa fa-x fa-plus"></i> 新增 </a>
        </div>
        <div class="portlet-body">
            <table class="table table-striped table-bordered dataTable no-footer" id="news-table">
                <thead>
                <th>图片</th>
                <th>链接</th>
                <th>创建时间</th>
                <th>操作</th>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/news/news-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>