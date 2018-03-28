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
    <title>专卖店管理</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/css/goods-new.css" rel="stylesheet" type="text/css"/>
</head>

<body id="goods-type">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${ctx}/back/stores">专卖店管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>新增专卖店</span>
            </li>
        </ul>
    </div>
    <h3 class="page-title"> 新增专卖店
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body form">
            <input type="hidden" value="${store.img}" id="store-img-url">
            <input type="hidden" value="${store.id}" id="store-id">
            <input type="hidden" value="${store.provinceName}" id="store-province">
            <input type="hidden" value="${store.cityName}" id="store-city">
            <input type="hidden" value="${store.districtName}" id="store-district">
            <form id="store-form" class="form-horizontal" role="form" enctype="multipart/form-data">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">专卖店名称</label>
                        <div class="col-md-9">
                            <input type="text" id="store-name" value="${store.name}" name="name" class="form-control" placeholder="输入专卖店名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">电话</label>
                        <div class="col-md-9">
                            <input type="text" id="store-tel" value="${store.tel}" name="tel" class="form-control" placeholder="输入电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">地址</label>
                        <div class="col-md-9">
                            <input id="city" readonly type="text" style="width:50%;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">地址详情</label>
                        <div class="col-md-9">
                            <input type="text" id="store-address" value="${store.addressDetail}" name="addressDetail" class="form-control" placeholder="输入地址详情">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">店铺图片</label>
                        <div class="col-md-9">
                            <input id="store-image" type="file" name="img">
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button data-click="edit" type="button" class="btn green">编辑</button>
                            <a href="${ctx}/back/stores" type="button" class="btn default">返回</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/store/store-edit.js" type="text/javascript"></script>
    </content>

</body>
</html>