<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>用户管理</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/js/ztree/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/job/job.css" rel="stylesheet" type="text/css"/>
</head>

<body id="system#job-manage">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 岗位管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet">
        <div class="portlet-title">
            <form action="#" class="form-horizontal">
                <div class="form-body">
                    <!--/row-->
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label col-md-4">岗位名称</label>
                                <div class="col-md-8">
                                    <input id="job-name" type="text" name="dept" class="form-control input-small"></div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label col-md-4">所属部门</label>
                                <input type="hidden" name="deptId" value="0" id="dept-id">
                                <div class="col-md-8">
                                    <input id="dept-input" name="deptName" type="text" class="form-control input-small" readonly="readonly"></div>
                            </div>
                        </div>
                        <div id="dept-tree" class="dept-tree hide">
                            <ul id="tree" class="ztree"></ul>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <button data-click="search" type="button" class="btn blue"><i class="fa fa-search"></i> &nbsp;查询</button>
                                <button data-click="reset" type="reset" class="btn default"><i class="fa fa-refresh"></i> &nbsp;清空</button>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        <div class="portlet">
            <div class="actions pull-right" style="margin-bottom: 5px;">
                <a href="${ctx}/back/jobs/add/home" class="btn btn-circle blue">
                    <i class="fa fa-x fa-plus"></i> 新增 </a>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered dataTable no-footer" id="job-table">
                    <thead>
                    <th>岗位全称</th>
                    <th>岗位简称</th>
                    <th>所属部门</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>

    <!--     bootstrap modal          -->
    <div class="modal fade" id="menu-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">权限分配</h4>
                </div>
                <div class="modal-body">
                    <div class="portlet portlet-form">
                        <div class="portlet-body">
                            <form id="inpour-form" class="form-horizontal" role="form">
                                <input type="hidden" value="" id="job-id"/>
                                <div class="form-body" id="menu-body" style="margin-left: 117px">

                                </div>
                                <div class="form-actions">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button type="button" data-click="save" class="btn green">保存</button>
                                            <button type="button" data-click="back" class="btn default">返回</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%--<div class="modal-footer">--%>
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>


    <div class="alert-notice-wrap hide">
        <div class="alert-notice">
            <span class="glyphicon glyphicon-ok"> </span>
            <div>操作成功</div>
        </div>
    </div>

    <script type="text/html" id="menu-tree-template">
        <div id="menu-tree" class="menu-tree">
            <ul id="menu-tree-ul" class="ztree"></ul>
        </div>
</script>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/util/ztree-util.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/custom/job/job.js" type="text/javascript"></script>
    </content>

</body>
</html>