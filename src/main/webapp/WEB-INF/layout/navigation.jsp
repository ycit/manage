<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="sitemash" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<ul class="">
    <li class="menu login">
        <div class="menu-hd">
            <shiro:guest>
                <div class="sign">
                    <a href="${ctx}/front/login/home" target="_top" class="h">亲，请登录</a>
                    <a href="${ctx}/front/register/home" target="_top">免费注册</a>
                </div>
            </shiro:guest>
            <shiro:authenticated>
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img alt="" class="img-circle" src="${ctx}/static/assets/layouts/layout/img/avatar3_small.jpg">
                                <span class="username username-hide-on-mobile"> <shiro:principal/> </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="page_user_profile_1.html">
                                        <i class="icon-user"></i> 个人信息 </a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a href="${ctx}/front/logout">
                                        <i class="icon-key"></i> 退出 </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </shiro:authenticated>
            <div class="user hide" style="line-height: 46px">
                <a href="" target="_top" class="login-info-nick"></a>
                <span class="arrow">
                    <span class="icon"></span>
                </span>
            </div>
        </div>
        <div class="site-nav-menu-bd" id="J_SiteNavLoginPanel"></div>
    </li>
    <li class="pipe">|</li>
    <li class="menu order ">
        <div class="menu-hd">
            <a href="${ctx}/front/register/home" target="_top">我的订单</a>
        </div>
    </li>
    <li class="pipe">|</li>
    <li class="menu cart">
        <div class="menu-hd">
            <a href="${ctx}/front/register/home" target="_top">我的购物车</a>
        </div>
    </li>
</ul>
