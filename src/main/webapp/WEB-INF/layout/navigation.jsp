<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="sitemash" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<li class="dropdown dropdown-user">
    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
       data-close-others="true">
        <%--<img alt="" class="img-circle" src="${ctx}/static/assets/layouts/layout/img/avatar3_small.jpg">--%>
        <span class="username username-hide-on-mobile"> <shiro:principal/> </span>
        <i class="fa fa-angle-down"></i>
    </a>
    <ul class="dropdown-menu dropdown-menu-default">
        <%--<li>--%>
            <%--<a href="page_user_profile_1.html">--%>
                <%--<i class="icon-user"></i> 个人信息 </a>--%>
        <%--</li>--%>
        <li class="divider"></li>
        <li>
            <a href="${ctx}/back/logout">
                <i class="icon-key"></i> 退出 </a>
        </li>
    </ul>
</li>