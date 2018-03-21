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
    <link href="${ctx}/static/css/user-manager.css" rel="stylesheet" type="text/css"/>
</head>

<body id="channel-page">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <%--<div class="page-bar" style="margin-top: 1px;">--%>
    <h3 class="page-title"> 用户管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet-body">
        <table class="table table-striped table-bordered dataTable no-footer" id="user-table">
            <thead>
            <th>用户名</th>
            <th>昵称</th>
            <th>性别</th>
            <th>生日</th>
            <th>余额</th>
            <th>操作</th>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                            ${user.username}
                    </td>
                    <td>
                            ${user.nickname}
                    </td>
                    <td>
                        <c:if test="${user.sex == 0}">女</c:if>
                        <c:if test="${user.sex == -1}">未知</c:if>
                        <c:if test="${user.sex == 1}">男</c:if>
                    </td>
                    <td>
                            ${user.birthday}
                    </td>
                    <td>
                        <label id="${user.id}">${user.balance}</label>
                        &nbsp;&nbsp;
                        <a data-click="inpour" data-id="${user.id}"><i class="fa fa-x fa-plus-square"></i></a>
                    </td>
                    <td>
                        <a data-id="${user.id}" data-click="delete">
                            <i class="fa fa-x fa-trash-o"></i>
                        </a>
                    </td>

                </tr>

            </c:forEach>
            </tbody>
        </table>
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
                                        <input id="user-num" type="text" name="num" class="form-control" placeholder="输入充值金额">
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

    <!--     bootstrap modal          -->
    <div class="modal fade" id="wait-send-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">待发消息详情</h4>
                </div>
                <div class="modal-body">
                    <div class="portlet-body-title" id="wait-portlet-body-title"></div>
                    <div class="table-scrollable">
                        <table class="table table-hover" align="center" id="wait-result">
                            <thead>
                            <tr>
                                <th> 调用应用名称</th>
                                <th> 发送目的地</th>
                                <th> 请求时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div id="wait-table"></div>
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
    <script type="text/html" id="channels_normal">
        <tr>
            <td>{name}</td>
            <td><a data-id="{id}" data-click="yesterday">{sendNum}</a></td>
            <td><a data-id="{id}" data-click="waitSend">{waitNum}</a></td>
            <td><span class="glyphicon glyphicon-ok-circle"> </span></td>
        </tr>
    </script>
    <script type="text/html" id="msg">
        <tr>
            <td>{appName}</td>
            <td>{destination}</td>
            <td>{createTime}</td>
        </tr>
    </script>
    <script type="text/html" id="wait-msg">
        <tr>
            <td>{appName}</td>
            <td>{destination}</td>
            <td>{createTime}</td>
        </tr>
    </script>
    <script type="text/html" id="alert">
        <tr>
            <td colspan="4">
                <div class="alert alert-warning" role="alert">
                    暂无可用通道
                </div>
            </td>
        </tr>
    </script>
    <script type="text/html" id="msg-alert">
        <tr>
            <td colspan="3">
                <div class="alert alert-warning" role="alert">
                    暂无数据
                </div>
            </td>
        </tr>
    </script>
    <script type="text/html" id="page">
        <div style="text-align: center">
            <ul class="my-pagination"></ul>
        </div>
    </script>
    <script type="text/html" id="msg-page">
        <div style="text-align: center">
            <ul class="msg-pagination"></ul>
        </div>
    </script>
    <script type="text/html" id="wait-page">
        <div style="text-align: center">
            <ul class="wait-pagination"></ul>
        </div>
    </script>

    <content tag="page_script">
        <script src="${ctx}/static/js/custom/user-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>