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
    <link href="${ctx}/static/back/css/goods-manager.css" rel="stylesheet" type="text/css"/>
</head>

<body id="goods-manager">
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
            <form role="form" class="sear-form" id="goods-form">
                <div class="form-body">
                    <div class="col-md-4">
                        <div class="row">
                            <label>商品名称: </label>
                            <input id="goods-name" name="name" class="form-control input-small" type="text"/>
                        </div>
                        <div class="row">
                            <label>所属品牌: </label>
                            <select id="goods-brand" class="form-control input-small" name="brandId">
                                <c:if test="${brands != null && brands.size() > 0}">
                                    <option value="">全部</option>
                                    <c:forEach items="${brands}" var="brand">
                                        <option value="${brand.id}">${brand.name}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${brands == null || brands.size() == 0}">
                                    <option value="">暂无可选品牌</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="row">
                            <label>所属专卖店: </label>
                            <select id="goods-store" class="form-control input-small" name="storeId">
                                <c:if test="${stores != null && stores.size() > 0}">
                                    <option value="">全部</option>
                                    <c:forEach items="${stores}" var="store">
                                        <option value="${store.id}">${store.name}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${stores == null || stores.size() == 0}">
                                    <option value="">暂无可选专卖店</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="row">
                            <label>用途: </label>
                            <select id="goods-purpose" class="form-control input-small" name="purposeId">
                                <c:if test="${usages != null && usages.size() > 0}">
                                    <option value="">全部</option>
                                    <c:forEach items="${usages}" var="usage">
                                        <option value="${usage.id}">${usage.name}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${usages == null || usages.size() == 0}">
                                    <option value="0">暂无可选专卖店</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="row">
                            <label>容量: </label>
                            <input id="goods-min-capacity" name="minCapacity" class="form-control-custom input-xsmall" type="text"/>
                            -
                            <input id="goods-max-capacity" name="maxCapacity" class="form-control-custom input-xsmall" type="text"/>
                        </div>
                        <div class="row">
                            <label>价格: </label>
                            <input id="goods-min-price" name="minPrice" class="form-control-custom input-xsmall" type="text"/>
                            -
                            <input id="goods-max-price" name="maxPrice" class="form-control-custom input-xsmall" type="text"/>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="row">
                            <label>种类: </label>
                            <select id="goods-category" class="form-control input-small" name="categoryId">
                                <c:if test="${categories != null && categories.size() > 0}">
                                    <option value="">全部</option>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${categories == null || categories.size() == 0}">
                                    <option>暂无可选种类</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="row">
                            <label>电压: </label>
                            <input id="goods-min-voltage" name="minVoltage" class="form-control-custom input-xsmall" type="text"/>
                            <span>-</span>
                            <input id="goods-max-voltage" name="maxVoltage" class="form-control-custom input-xsmall" type="text"/>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <button data-click="search" type="button" class="btn blue">查询</button>
                    <button type="reset" class="btn default">重置</button>
                </div>
            </form>
        </div>
        <div class="portlet" id="goods-content">
            <div class="actions pull-right" style="margin-bottom: 6px;">
                <a href="${ctx}/back/goods/add" class="btn btn-circle btn-default">
                    <i class="fa fa-x fa-plus"></i> 新增 </a>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered dataTable no-footer" id="goods-table">
                    <thead>
                    <th>图片</th>
                    <th>名称</th>
                    <th>品牌</th>
                    <th>专卖店</th>
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

    <content tag="page_script">
        <script src="${ctx}/static/back/js/custom/goods-manager.js" type="text/javascript"></script>
    </content>

</body>
</html>