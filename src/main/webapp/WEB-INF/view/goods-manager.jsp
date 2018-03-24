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
    <link href="${ctx}/static/css/goods-manager.css" rel="stylesheet" type="text/css"/>
</head>

<body id="goods-type">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar"></div>
    <h3 class="page-title"> 商品管理
        <small>商品上架下架</small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet">
        <div class="portlet">
            <form role="form" class="sear-form">
                <div class="form-body">
                    <div class="col-md-4">
                        <div class="row">
                            <label>商品名称: </label>
                            <input type="text"/>
                        </div>
                        <div class="row">
                            <label>所属品牌: </label>
                            <input type="text"/>
                        </div>
                        <div class="row">
                            <label>所属专卖店: </label>
                            <input type="text"/>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="row">
                            <label>用途: </label>
                            <input type="text"/>
                        </div>
                        <div class="row">
                            <label>容量: </label>
                            <input type="text"/>
                        </div>
                        <div class="row">
                            <label>价格: </label>
                            <input type="text"/>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="row">
                            <label>种类: </label>
                            <input type="text"/>
                        </div>
                        <div class="row">
                            <label>电压: </label>
                            <input type="text"/>
                        </div>
                        <div class="row">
                            <label>销量: </label>
                            <input type="text"/>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="button" class="btn blue">查询</button>
                    <button type="reset" class="btn default">重置</button>
                </div>
            </form>
        </div>
        <div class="portlet" id="goods-content">
            <div class="actions pull-right">
                <a href="${ctx}/back/goods/add" class="btn btn-circle btn-default">
                    <i class="fa fa-x fa-plus"></i> 新增 </a>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered dataTable no-footer" id="user-table">
                    <thead>
                    <th>图片</th>
                    <th>名称</th>
                    <th>品牌</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>用途</th>
                    <th>种类</th>
                    <th>容量</th>
                    <th>电压</th>
                    <th>操作</th>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
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
        <script src="${ctx}/static/js/custom/goods-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>