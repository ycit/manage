<%--
  Created by IntelliJ IDEA.
  User: xlch
  Date: 2017/2/21
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="sitemash" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    $(document).ready(function() {
        var pageId = $('body').attr('id');

        if (typeof pageId === 'undefined') {
            pageId = 'start';
        }
        $('.page-sidebar li.' + pageId).addClass("active open");
    });
</script>

<!-- BEGIN SIDEBAR -->

<!-- BEGIN SIDEBAR -->
<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
<div class="page-sidebar navbar-collapse collapse">
    <input type="hidden" id="menuId" value="<decorator:getProperty property='body.hl' default='' />">
    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
        data-slide-speed="200" style="padding-top: 20px">
        <li class="nav-item user-manager">
            <a href="${ctx}/back/users" class="nav-link">
                <span aria-hidden="true" class="icon-users"></span>
                <span class="title">用户管理</span>
            </a>
        </li>
        <li class="nav-item  goods-manager">
            <a href="${ctx}/back/goods" class="nav-link">
                <i class="fa fa-cubes"></i>
                <span class="title">商品管理</span>
            </a>
        </li>
        <li class="nav-item  order-manager">
            <a href="${ctx}/back/orders" class="nav-link">
                <span aria-hidden="true" class="icon-basket-loaded"></span>
                <span class="title">订单管理</span>
            </a>
        </li>
        <li class="nav-item  store-manager">
            <a href="${ctx}/back/stores" class="nav-link">
                <i class="fa fa-institution"></i>
                <span class="title">专卖店管理</span>
            </a>
        </li>
        <li class="nav-item  news-manager">
            <a href="${ctx}/back/newses" class="nav-link">
                <i class="fa fa-newspaper-o"></i>
                <span class="title">资讯管理</span>
            </a>
        </li>
        <li class="nav-item  dict-manager">
            <a href="${ctx}/back/dict" class="nav-link">
                <i class="fa fa-bars"></i>
                <span class="title">字典表管理</span>
            </a>
        </li>
    </ul>
    <!-- END SIDEBAR MENU -->
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->
<!-- END SIDEBAR -->
