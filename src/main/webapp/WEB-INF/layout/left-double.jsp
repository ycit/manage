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
            pageId = 'system#user-manage';
        }
        var array = pageId.split("#");
        var first = array[0];
        var second = array[1];
        var allFirstMenu = $('.page-sidebar li.nav-item:first');
        if (first) {
            $('.page-sidebar li.' + first).addClass("active open");
            allFirstMenu.find("a .arrow").removeClass("open");
            $('.page-sidebar li.' + first).find("a .arrow").addClass("open");
            allFirstMenu.find(".sub-menu").addClass("hide");
            $('.page-sidebar li.' + first).find(".sub-menu").removeClass("hide");
        }
        if (second) {
            allFirstMenu.find(".sub-menu li").removeClass("active open");
            $('.page-sidebar li.' + first).find(".sub-menu li." + second).addClass("active open");
        }

        //一级菜单点击事件
        var firstMenuClickFunc = $(".page-sidebar-menu").on("click", "li.first a:eq(0)", function() {
            var subMenu = $(this).parent("li").find(".sub-menu");
            if (subMenu.length > 0) {
                var hide = subMenu.hasClass("hide");
                if (hide) {
                    $(this).find("a .row").addClass("open");
                     subMenu.removeClass("hide");
                } else {
                    $(this).find("a .row").removeClass("open");
                    subMenu.addClass("hide");
                }
            }
    });

        //二级菜单点击事件
        var subMenuClickFunc = $(".page-sidebar-menu").on("click", ".sub-menu li", function() {
            $(".sub-menu li").removeClass("active open");
            $(this).addClass("active open");
    });

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
        <li class="first nav-item system">
            <a href="javascript:void(0)" class="nav-link">
                <span aria-hidden="true" class="icon-users"></span>
                <span class="title"> 系统配置 </span>
                <span class="arrow"></span>
            </a>
            <ul class="sub-menu">
                <li class="nav-item">
                    <a href="javascript:void(0)" class="nav-link">
                        <span aria-hidden="true" class="icon-users"></span>
                        <span class="title">系统参数</span>
                    </a>
                </li>
                <li class="nav-item user-manage">
                    <a href="${ctx}/back/users/home" class="nav-link">
                        <span aria-hidden="true" class="icon-users"></span>
                        <span class="title">用户管理</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="javascript:void(0)" class="nav-link">
                        <span aria-hidden="true" class="icon-users"></span>
                        <span class="title">岗位管理</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="javascript:void(0)" class="nav-link">
                        <span aria-hidden="true" class="icon-users"></span>
                        <span class="title">部门管理</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item  goods-manager">
            <a href="javascript:void(0)" class="nav-link">
                <i class="fa fa-cubes"></i>
                <span class="title">组件管理</span>
            </a>
        </li>
        <li class="first nav-item  order-manager">
            <a href="javascript:void(0)" class="nav-link">
                <span aria-hidden="true" class="icon-basket-loaded"></span>
                <span class="title">平台监控和告警</span>
            </a>
        </li>
    </ul>
    <!-- END SIDEBAR MENU -->
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->
<!-- END SIDEBAR -->
