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
    <link href="${ctx}/static/back/css/goods-new.css" rel="stylesheet" type="text/css"/>
</head>

<body id="news-manager">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${ctx}/back/newses">最新资讯管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>编辑资讯</span>
            </li>
        </ul>
    </div>
    <h3 class="page-title"> 编辑资讯
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body form">
            <input id="news-img-url" type="hidden" value="${news.img}">
            <input id="news-id" type="hidden" value="${news.id}">
            <form id="news-form" class="form-horizontal" role="form" enctype="multipart/form-data">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">资讯图片</label>
                        <div class="col-md-9">
                            <input id="news-image" type="file" name="image">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">资讯对应链接</label>
                        <div class="col-md-9">
                            <input type="text" id="news-url" value="${news.url}" name="url" class="form-control" placeholder="输入资讯对应链接">
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button data-click="edit" type="button" class="btn green">编辑</button>
                            <a data-click="back" type="button" class="btn default">返回</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/news/news-edit.js" type="text/javascript"></script>
    </content>

</body>
</html>