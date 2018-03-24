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
    <link href="${ctx}/static/css/goods-new.css" rel="stylesheet" type="text/css"/>
</head>

<body id="goods-type">
<div class="page-content" style="min-height:1318px">
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${ctx}/back/goods">商品管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>新增商品</span>
            </li>
        </ul>
    </div>
    <h3 class="page-title"> 新增商品
        <small></small>
    </h3>
    <%--</div>--%>
    <!-- END PAGE BAR -->
    <!-- END PAGE HEADER-->
    <div class="portlet light bordered">
        <div class="portlet-body form">
            <form class="form-horizontal" role="form">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">商品名称</label>
                        <div class="col-md-9">
                            <input type="text" name="name" value="${name}" class="form-control" placeholder="输入商品名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">所属品牌</label>
                        <div class="col-md-9">
                            <select class="form-control" name="brandId">
                                <option>Option 1</option>
                                <option>Option 2</option>
                                <option>Option 3</option>
                                <option>Option 4</option>
                                <option>Option 5</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">所属专卖店</label>
                        <div class="col-md-9">
                            <select class="form-control" name="storeId">
                                <option>Option 1</option>
                                <option>Option 2</option>
                                <option>Option 3</option>
                                <option>Option 4</option>
                                <option>Option 5</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">上传图片</label>
                        <div class="col-md-9">
                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-envelope"></i>
                                                        </span>
                                <input type="email" class="form-control" placeholder="Email Address"> </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">价格</label>
                        <div class="col-md-9">
                            <input type="text" name="price" class="form-control" placeholder="输入价格">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">库存</label>
                        <div class="col-md-9">
                            <input type="text" name="stock" class="form-control" placeholder="输入库存">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">描述信息</label>
                        <div class="col-md-9">
                            <textarea name="description" class="form-control" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">种类</label>
                        <div class="col-md-9">
                            <select class="form-control" name="storeId">
                                <option>Option 1</option>
                                <option>Option 2</option>
                                <option>Option 3</option>
                                <option>Option 4</option>
                                <option>Option 5</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用途</label>
                        <div class="col-md-9">
                            <select class="form-control" name="storeId">
                                <option>Option 1</option>
                                <option>Option 2</option>
                                <option>Option 3</option>
                                <option>Option 4</option>
                                <option>Option 5</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">容量</label>
                        <div class="col-md-9">
                            <input type="text" name="capacity" class="form-control" placeholder="输入容量">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">电压</label>
                        <div class="col-md-9">
                            <input type="text" name="voltage" class="form-control" placeholder="输入电压">
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">Submit</button>
                            <button type="button" class="btn default">Cancel</button>
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
        <script src="${ctx}/static/js/custom/goods-add.js" type="text/javascript"></script>
    </content>

</body>
</html>