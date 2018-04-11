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
    <title>订单管理</title>
    <meta charset="utf-8">
    <link href="${ctx}/static/back/css/order-manager.css" rel="stylesheet" type="text/css"/>
</head>

<body id="order-manager">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 订单管理
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet">
        <div class="portlet-title">
            <div class="row">
                <div class="col-md-4">
                    <select id="user-id" class="bs-select form-control" data-width="125px">
                        <option value="">全部</option>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.id}">${user.username}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <div class="input-inline input-medium">
                        <div class="input-group report-range" id="dateRange">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                                <button class="btn default date-range-toggle" type="button">
                                    <i class="fa fa-calendar"></i>
                                </button>
                            </span>
                        </div>
                    </div>


                    <%--<input type="text" id="dateRange" class="form-control">--%>
                    <%--<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>--%>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
        <div class="portlet-body results">

        </div>
    </div>

    <div style="text-align: center">
        <ul id="orders-pages" class="pagination-goods"></ul>
    </div>

    <script type="text/html" id="orders-list">
        {{#if orders}}
        <table class="table table-bordered no-footer" id="order-table">
            <thead>
            <th colspan="3" style="text-align: center">订单详情</th>
            <th>收货人</th>
            <th>总金额</th>
            <th>状态</th>
            <th>操作</th>
            </thead>
            {{#each orders}}
            <tbody>
            <input type="hidden" class="order-id" value="{{id}}"/>
            <tr class="sep-row">
                <td colspan="7"></td>
            </tr>
            <tr class="tr-th">
                <td colspan="7">
                    <span class="gap"></span>
                    <span class="dealtime" title="2018-02-20 09:58:28">{{#time createTime}}{{/time}}</span>
                    <input type="hidden" value="2018-02-20 09:58:28">
                    <span class="number">订单号：{{code}}</span>
                </td>
            </tr>
            {{#each goodsList}}
            <tr>
                <input type="hidden" class="order-goods-id" value="{{id}}"/>
                <td>
                    <img class="" src="{{goodsImg}}" title="{{goodsName}}" width="60" height="60">
                </td>
                <td>
                    <div data-toggle="tooltip" data-placement="top" title="{{goodsName}}">{{omitName}}</div>
                </td>
                <td>x{{goodsNum}}</td>
                <td>{{../receiveName}}</td>
                {{#compare @index '==' 0}}
                <td rowspan="{{../goodsSize}}" style="vertical-align: middle">¥{{../price}}</td>
                {{/compare}}
                {{#compare @index '==' 0}}
                <td class="order-status" rowspan="{{../goodsSize}}" style="vertical-align: middle">{{#compare ../status
                    '==' 0}}待发货{{/compare}}{{#compare ../status '==' 1}}待签收{{/compare}}{{#compare ../status '=='
                    2}}已完成{{/compare}}
                </td>
                {{/compare}}
                {{#compare @index '==' 0}}
                <td rowspan="{{../goodsSize}}" style="vertical-align: middle">
                    <a class="send-action {{#compare ../status '!=' 0}}hide{{/compare}}" title="发货" data-id="{{id}}">
                        <i class="fa-x fa fa-truck"></i>
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="delete-action" title="删除" data-id="{{id}}">
                        <i class="fa fa-x fa-trash-o"></i>
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                {{/compare}}
            </tr>
            {{/each}}
            </tbody>
            {{/each}}
        </table>
        {{else}}
        <div class='not-found'></div>
        <div style='text-align: center'><strong>暂无订单信息</strong></div>
        {{/if}}
    </script>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/order/order-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>