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
    <link href="${ctx}/static/back/css/user-manager.css" rel="stylesheet" type="text/css"/>
</head>

<body id="dict-manager">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 字典表管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet">
        <div class="actions pull-right" style="margin-bottom: 5px;">
            <a href="${ctx}/back/dict/add" class="btn btn-circle btn-default">
                <i class="fa fa-x fa-plus"></i> 新增 </a>
        </div>
        <div class="portlet-body">
            <table class="table table-striped table-bordered dataTable no-footer" id="dict-table" style="width:100%">
                <thead>
                <th>序号</th>
                <th>主键</th>
                <th>字典表名称</th>
                <th>备注</th>
                <th>创建时间</th>
                <th>操作</th>
                </thead>
                <tbody></tbody>
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

    <script type="text/html" id="channels_warning">
        <tr>
            <td>{name}</td>
            <td><a data-id="{id}" data-click="yesterday">{sendNum}</a></td>
            <td><a data-id="{id}" data-click="waitSend">{waitNum}</a></td>
            <td><i class="fa fa-warning"></i></td>
        </tr>
    </script>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/dict-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>