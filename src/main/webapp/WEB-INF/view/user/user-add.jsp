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
    <title>新增用户</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/js/ztree/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/user/user-add.css" rel="stylesheet" type="text/css"/>
</head>

<body id="system#user-manage">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${ctx}/back/users/home">用户管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>新增用户</span>
            </li>
        </ul>
    </div>
    <h3 class="page-title"> 新增用户
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body">
            <div class="portlet-body">
                <div class="tabbable-line">
                    <ul class="nav nav-tabs ">
                        <li class="active">
                            <a href="#tab_15_1" data-toggle="tab" aria-expanded="true"> 基本信息 </a>
                        </li>
                        <li class="">
                            <a href="#tab_15_2" data-toggle="tab" aria-expanded="false"> 头像照片 </a>
                        </li>
                        <li class="">
                            <a href="#tab_15_3" data-toggle="tab" aria-expanded="false"> 密码修改 </a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_15_1">
                            <div class="portlet-body form">
                                <form role="form" class="form-horizontal" id="user-form">
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">用户名</label>
                                            <div class="col-md-9">
                                                <input type="text" name="name" value="${name}" class="form-control input-medium" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">密码</label>
                                            <div class="col-md-9">
                                                <input type="password" name="password" value="${name}" class="form-control input-medium" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">确认密码</label>
                                            <div class="col-md-9">
                                                <input type="password" name="rePassword" value="${name}" class="form-control input-medium" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">全名</label>
                                            <div class="col-md-9">
                                                <input type="text" name="name" value="${name}" class="form-control input-medium" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">昵称</label>
                                            <div class="col-md-9">
                                                <input type="text" name="name" value="${name}" class="form-control input-medium" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">性别</label>
                                            <div class="col-md-9">
                                                <select name="sex" class="form-control input-medium">
                                                    <option value="-1">未知</option>
                                                    <option value="1">男</option>
                                                    <option value="0">女</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">所属部门</label>
                                            <input id="brand-name" type="hidden" value="<c:if test='${brands != null && brands.size() > 0}'>${brands[0].name}</c:if>" name="brandName">
                                            <div class="col-md-9">
                                                <select id="brand-select" class="form-control input-medium" name="brandId">
                                                    <c:if test="${brands != null && brands.size() > 0}">
                                                        <c:forEach items="${brands}" var="brand">
                                                            <option value="${brand.id}">${brand.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${brands == null || brands.size() == 0}">
                                                        <option value="">暂无可选品牌</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">所属岗位</label>
                                            <input id="store-name" type="hidden" value="<c:if test='${stores != null && stores.size() > 0}'>${stores[0].name}</c:if>" name="storeName">
                                            <div class="col-md-9">
                                                <select id="store-select" class="form-control input-medium" name="storeId">
                                                    <c:if test="${stores != null && stores.size() > 0}">
                                                        <c:forEach items="${stores}" var="store">
                                                            <option value="${store.id}">${store.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${stores == null || stores.size() == 0}">
                                                        <option value="">暂无可选专卖店</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                            </div>
                            <div>
                                <ul id="tree" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab_15_2">
                            <div class="portlet-body form">
                                <form id="img-form" class="form-horizontal" role="form" enctype="multipart/form-data">
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">上传图片</label>
                                            <div class="col-md-9">
                                                <input id="user-image" type="file" name="img">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab_15_3">
                            <div class="portlet-body form">
                                <form id="pw-form" class="form-horizontal">
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">当前密码</label>
                                            <div class="col-md-4">
                                                <input type="password" class="form-control" name="oldPw">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">新&nbsp;&nbsp;密&nbsp;&nbsp;码</label>
                                            <div class="col-md-4">
                                                <input type="password" id="password" class="form-control" name="newPw">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">确认密码</label>
                                            <div class="col-md-4">
                                                <input type="password" class="form-control" name="reNewPw">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <div class="row">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button data-click="password" type="button" class="btn green">保存</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/util/ztree-util.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/custom/user/user-add.js" type="text/javascript"></script>
    </content>

</body>
</html>