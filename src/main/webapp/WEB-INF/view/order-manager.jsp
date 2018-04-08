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
    <div class="portlet-body">
        <table class="table table-striped table-bordered dataTable no-footer" id="order-table">
            <thead>
            <th>订单详情</th>
            <th>收货人</th>
            <th>金额</th>
            <th>状态</th>
            <th>操作</th>
            </thead>
            <tbody>
            <tr class="sep-row"><td colspan="5"></td></tr>
            <tr class="tr-th">
                <td colspan="5">
                    <span class="gap"></span>
                    <span class="dealtime" title="2018-02-20 09:58:28">2018-02-20 09:58:28</span>
                    <input type="hidden" id="datasubmit-71476525048" value="2018-02-20 09:58:28">
                    <span class="number">订单号：71476525048</span>
                    <div class="tr-operate">
                        <a href="#none" clstag="click|keycount|orderlist|dingdanshanchu" class="order-del" _orderid="71476525048" _passkey="8AB2CECDB8F94452BB59AADA2FA2D771" title="删除" style="display: none;"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div></div>
                </td>
                <td>xxx</td>
                <td>89</td>
                <td>待发货</td>
                <td><a class='delete-action' title='删除' data-id='" + row.id +"'><i class="fa fa-x fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            </tbody>
            <tbody>
            <tr class="sep-row"><td colspan="5"></td></tr>
            <tr class="tr-th">
                <td colspan="5">
                    <span class="gap"></span>
                    <span class="dealtime" title="2018-02-20 09:58:28">2018-02-20 09:58:28</span>
                    <input type="hidden" id="datasubmit-71476525048" value="2018-02-20 09:58:28">
                    <span class="number">订单号：71476525048</span>
                    <div class="tr-operate">
                        <a href="#none" clstag="click|keycount|orderlist|dingdanshanchu" class="order-del" _orderid="71476525048" _passkey="8AB2CECDB8F94452BB59AADA2FA2D771" title="删除" style="display: none;"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div></div>
                </td>
                <td>xxx</td>
                <td>89</td>
                <td>待发货</td>
                <td><a class='delete-action' title='删除' data-id='" + row.id +"'><i class="fa fa-x fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            </tbody>
        </table>
    </div>

    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/order/order-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>