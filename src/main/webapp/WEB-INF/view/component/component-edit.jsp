<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>编辑组件</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/js/jedate/jedate.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/job/job-add.css" rel="stylesheet" type="text/css"/>
</head>

<body id="system#dept-manage">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${ctx}/back/component/home">组件管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>编辑组件</span>
            </li>
        </ul>
    </div>
    <h3 class="page-title"> 编辑组件
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body">
            <div class="portlet-body form">
                <form id="com-add-form" class="form-horizontal">
                    <input type="hidden" id="cron-time" value="${task.cron}">
                    <input type="hidden" value="${task.id}" name="id">
                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">任务名称</label>
                                    <div class="col-md-9">
                                        <input name="name" type="text" class="form-control" value="${task.name}">
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">任务组</label>
                                    <input type="hidden" value="${task.groupName}" name="groupName">
                                    <div class="col-md-9">
                                        <select name="groupId" class="form-control">
                                            <option value="${task.groupId}">系统监控任务</option>
                                        </select>
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                        </div>
                        <!--/row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">执行时间</label>
                                    <div class="col-md-9">
                                        <input class="form-control" name="cron" id="time" type="text" placeholder="hh:mm:ss"  readonly>
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                        </div>
                        <!--/row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">状态</label>
                                    <div class="col-md-9">
                                        <div style="margin-top: 6px">
                                            <input type="radio" <c:if test="${task.status == 0}">checked</c:if> name="status" value="0">开启&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" <c:if test="${task.status == 1}">checked</c:if> name="status" value="1">关闭
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                        </div>
                        <div class="row">
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button data-click="save" type="button" class="btn green">保存</button>
                                        <button data-click="back" type="button" class="btn default">返回</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6"> </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script type="text/html" id="component-template">
        <option value="{{id}}">{{fullName}}</option>
    </script>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/jedate/jedate.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/custom/component/component-edit.js" type="text/javascript"></script>
    </content>

</body>
</html>