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
    <title>用户管理</title>
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
                <a href="${ctx}/back/dict">字典表管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>
                    <c:if test="${type == null}">新增字典表</c:if>
                    <c:if test="${type != null}">编辑字典表</c:if>
                </span>
            </li>
        </ul>
    </div>
    <h3 class="page-title">
        <c:if test="${type == null}">新增字典表</c:if>
        <c:if test="${type != null}">编辑字典表</c:if>
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body form">
            <form id="dict-form" class="form-horizontal" role="form">
                <input id="dict-id" type="hidden" name="id" value="${type.id}">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">字典表名称</label>
                        <div class="col-md-9">
                            <input id="dict-name" type="text" value="${type.name}" name="name" class="form-control" placeholder="输入商品名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">备注信息</label>
                        <div class="col-md-9">
                            <textarea id="dict-desc" name="description" placeholder="输入备注信息" class="form-control" rows="3">${type.description}</textarea>
                        </div>
                    </div>
                </div>
                <div class="">
                    <div> <label id="notice" class="error-msg"></label> </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <c:if test="${type == null}"><button data-click="submit" type="button" class="btn green">新增</button></c:if>
                            <c:if test="${type != null}"><button data-click="submit" type="button" class="btn green">编辑</button></c:if>
                            <a href="${ctx}/back/dict" class="btn default">返回</a>
                        </div>
                    </div>
                </div>
            </form>
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
        <script src="${ctx}/static/back/js/custom/dict-add.js" type="text/javascript"></script>
    </content>

</body>
</html>