<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>系统参数</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/css/sys/system.css" rel="stylesheet" type="text/css"/>
</head>

<body id="system#setting">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 系统参数
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet-body light bordered">
        <form role="form" class="form-horizontal" id="system-form">
            <div class="form-body">
                <c:forEach items="${configs}" var="config">
                    <div class="form-group">
                        <label class="col-md-3 control-label">${config.showName}</label>
                        <div class="col-md-9">
                            <input id="${config.name}" type="text" name="${config.name}" value="${config.value}" class="form-control input-medium" placeholder="">
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%--<shiro:hasAnyRoles  name="superadmin,admin">--%>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button data-click="save" type="button" class="btn green">保存</button>
                        </div>
                    </div>
                </div>
            <%--</shiro:hasAnyRoles  >--%>
        </form>
    </div>

    <div class="alert-notice-wrap hide">
        <div class="alert-notice">
            <span class="glyphicon glyphicon-ok"> </span>
            <div>操作成功</div>
        </div>
    </div>


    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/system/system.js" type="text/javascript"></script>
    </content>

</body>
</html>