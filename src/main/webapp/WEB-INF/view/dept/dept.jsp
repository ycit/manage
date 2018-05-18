<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<head>
    <title>部门管理</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/js/jquery-treegrid/css/jquery.treegrid.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/back/css/user/user-manage.css" rel="stylesheet" type="text/css"/>
</head>

<body id="system#dept-manage">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 部门管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet">
        <div class="actions pull-right" style="margin-bottom: 5px;">
            <a href="${ctx}/back/dept/add/home" class="btn btn-circle blue">
                <i class="fa fa-x fa-plus"></i> 新增 </a>
            <a href="javascript:void(0)" data-click="edit" class="btn btn-circle blue">
                <i class="fa fa-pencil-square-o"></i> 修改 </a>
            <a href="javascript:void(0)" data-click="delete" class="btn btn-circle blue">
                <i class="fa fa-x fa-trash"></i> 删除 </a>
        </div>
        <div class="portlet-body">
            <table id="dept-table" class="table tree">

            </table>
        </div>
    </div>
    <!--     bootstrap modal          -->
    <div class="modal fade" id="inpour-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">用户充值</h4>
                </div>
                <div class="modal-body">
                    <div class="portlet-body">
                        <form id="inpour-form" class="form-horizontal" role="form">
                            <input type="hidden" value="" id="user-id"/>
                            <div class="form-body">
                                <div class="form-group">
                                    <%--<div class="col-md-3">--%>
                                    <label class="col-md-3 control-label">输入充值金额</label>
                                    <%--</div>--%>
                                    <div class="col-md-4">
                                        <input id="user-num" type="text" name="num" class="form-control"
                                               placeholder="输入充值金额">
                                    </div>
                                    <div class="col-md-3">
                                        <button type="button" data-click="recharge" class="btn green">充值</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <%--<button type="button" class="btn btn-primary" data-click="comment">评论</button>--%>
                </div>
            </div>
        </div>
    </div>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/jquery-treegrid/js/jquery.treegrid.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/jquery-treegrid/js/jquery.treegrid.bootstrap3.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/jquery-treegrid/extension/jquery.treegrid.extension.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/util/tree-table-object.js" type="text/javascript"></script>
        <script src="${ctx}/static/back/js/custom/dept/dept.js" type="text/javascript"></script>
    </content>

</body>
</html>