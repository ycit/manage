<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>编辑部门</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/js/ztree/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/job/job-add.css" rel="stylesheet" type="text/css"/>
</head>

<body id="system#dept-manage">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${ctx}/back/jobs/home">岗位管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>编辑岗位</span>
            </li>
        </ul>
    </div>
    <h3 class="page-title"> 编辑岗位
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body">
            <div class="portlet-body form">
                <form id="job-add-form" class="form-horizontal">
                    <input name="id" type="hidden" value="${job.id}">
                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">岗位全称</label>
                                    <div class="col-md-9">
                                        <input name="fullName" type="text" class="form-control" value="${job.fullName}">
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                            </div>
                            <!--/span-->
                            <!--/span-->
                        </div>
                        <!--/row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">岗位简称</label>
                                    <div class="col-md-9">
                                        <input name="nickname" type="text" class="form-control" value="${job.nickname}">
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
                                    <label class="control-label col-md-3">所属部门</label>
                                    <input type="hidden" name="deptId" value="${job.dept.id}" id="dept-id">
                                    <div class="col-md-9">
                                        <input id="dept-input" readonly="readonly" name="deptName" type="text" class="form-control" value="${job.dept.fullName}">
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                            </div>
                            <div id="dept-tree" class="dept-tree hide">
                                <ul id="tree" class="ztree"></ul>
                            </div>
                            <!--/span-->
                        </div>
                        <!--/row-->
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

    <script type="text/html" id="job-select-template">
        {{#each jobs}}
        <option value="{{id}}">{{fullName}}</option>
        {{/each}}
    </script>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/util/ztree-util.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/custom/job/job-edit.js" type="text/javascript"></script>
    </content>

</body>
</html>