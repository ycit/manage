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


        utils.myAjax.get("/back/menus",{},function(data) {
            console.log(data);
            var html = $("#menu").html();
            var template = Handlebars.compile(html);
            $("#menu-ul").html(template({menus:data}));


            var allFirstMenu = $('.page-sidebar li.nav-item.first');
            var pageId = $('body').attr('id');
            if (typeof pageId === 'undefined') {
            pageId = 'system#user-manage';
            }
            var array = pageId.split("#");
            var first = array[0];
            var second = array[1];

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
                if (subMenu.length > 0) { //有子菜单

                    // $(this).parent("li").addClass("active open");
                    // $(this).parent("li").find("a .arrow").addClass("open");

                    var hide = subMenu.hasClass("hide"); //判断当前点击的菜单的子菜单是否隐藏
                    initMenu(); //初始化所有菜单状态
                    if (hide) {
                        $(this).find(".arrow").addClass("open"); //箭头打开
                        subMenu.removeClass("hide"); // 子菜单显示
                        $(this).parent("li").addClass("active open"); // 当前点击的高亮
                    } else {
                        $(this).parent("li").addClass("active open");
                        $(this).find(".arrow").removeClass("open");
                        subMenu.addClass("hide");
                    }
                }
            });

            function initMenu() {
                /**初始化菜单状态 begin **/
                allFirstMenu.removeClass("active open"); //关闭置灰 所有菜单
                allFirstMenu.find("a .arrow").removeClass("open"); //所有菜单开起状态改为 关闭状态
                allFirstMenu.find(".sub-menu").addClass("hide"); // 隐藏所有子菜单
                /**初始化菜单状态 end **/
            }

            //二级菜单点击事件
            var subMenuClickFunc = $(".page-sidebar-menu").on("click", ".sub-menu li", function() {
                $(".sub-menu li").removeClass("active open");
                $(this).addClass("active open");
            });
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
        data-slide-speed="200" style="padding-top: 20px" id="menu-ul">
        <%--<li class="first nav-item system">--%>
            <%--<a href="javascript:void(0)" class="nav-link">--%>
                <%--<i class="fa fa-gear"></i>--%>
                <%--<span class="title"> 系统配置 </span>--%>
                <%--<span class="arrow"></span>--%>
            <%--</a>--%>
            <%--<ul class="sub-menu">--%>
                <%--<li class="nav-item setting">--%>
                    <%--<a href="${ctx}/back/system/home" class="nav-link">--%>
                        <%--<i class="fa fa-wrench"></i>--%>
                        <%--<span class="title">系统参数</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item user-manage">--%>
                    <%--<a href="${ctx}/back/users/home" class="nav-link">--%>
                        <%--<i class="fa fa-user"></i>--%>
                        <%--<span class="title">用户管理</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item job-manage">--%>
                    <%--<a href="${ctx}/back/jobs/home" class="nav-link">--%>
                       <%--<i class="fa fa-navicon"></i>--%>
                        <%--<span class="title">岗位管理</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item dept-manage">--%>
                    <%--<a href="${ctx}/back/dept/home" class="nav-link">--%>
                        <%--<i class="fa fa-group"></i>--%>
                        <%--<span class="title">部门管理</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="first nav-item  component">--%>
            <%--<a href="${ctx}/back/component/home" class="nav-link">--%>
                <%--<i class="fa fa-cube"></i>--%>
                <%--<span class="title">组件管理</span>--%>
            <%--</a>--%>
        <%--</li>--%>
        <%--<li class="first nav-item  monitor">--%>
            <%--<a href="${ctx}/back/monitor/home" class="nav-link">--%>
                <%--<i class="fa fa-warning"></i>--%>
                <%--&lt;%&ndash;<span aria-hidden="true" class="icon-basket-loaded"></span>&ndash;%&gt;--%>
                <%--<span class="title">平台监控和告警</span>--%>
            <%--</a>--%>
        <%--</li>--%>
    </ul>
    <!-- END SIDEBAR MENU -->
    <!-- END SIDEBAR MENU -->
</div>
    <script type="text/html" id="menu">
    {{#each menus}}
           <li class="first nav-item {{code}}">
                <a href="{{url}}" class="nav-link">
                    <i class="{{icon}}"></i>
                    <span class="title"> {{name}} </span>
                    {{#if children}}<span class="arrow"></span>{{/if}}
                </a>
                <ul class="sub-menu">
                    {{#each children}}
                        <li class="nav-item {{code}}">
                            <a href="{{url}}" class="nav-link">
                                <i class="{{icon}}"></i>
                                <span class="title">{{name}}</span>
                            </a>
                        </li>
                    {{/each}}
                </ul>
           </li>
    {{/each}}
    </script>
<!-- END SIDEBAR -->
<!-- END SIDEBAR -->
