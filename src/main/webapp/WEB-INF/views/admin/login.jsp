<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${requestScope["app.name"]}</title>
    <link rel="shortcut icon" href="${ctx}/public/favicon.ico" type="image/x-icon">
    <link rel="icon" href="${ctx}/public/favicon.ico" type="image/x-icon">
    <link href="${ctx}/public/dwz/themes/css/login.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/public/dwz/js/jquery-1.11.3.min.js" type="text/javascript"></script>
</head>
<body>
<div id="login">
    <div id="login_header">
        <h1 class="login_logo">
            <%--<a href="<c:url value="/" />"><img src="${ctx}/public/dwz/themes/default/images/logo.png"/></a>--%>
            <span style="font-size: 26px;">${requestScope["app.name"]}</span>
        </h1>
        <div class="login_headerContent">
            <div class="navList">
                <ul>
                    <%--<li><a href="#">设为首页</a></li>
                    <li><a href="#">反馈</a></li>
                    <li><a href="#">帮助</a></li>--%>
                </ul>
            </div>
            <h2 class="login_title"><img src="${ctx}/public/dwz/themes/default/images/login_title.png"/>

            </h2>
        </div>
    </div>
    <div id="login_content">
        <div class="loginForm">
            <form method="post" action="${ctx}/admin/login">
                <p>
                    <label>帐号：</label>
                    <input type="text" name="username" size="20" class="login_input"/>
                </p>
                <p>
                    <label>密码：</label>
                    <input type="password" name="password" size="20" class="login_input"/>
                </p>
                <c:if test="${not empty loginError}">
                    <div style="color:red;">
                        账号或密码错误，请重试！
                    </div>
                </c:if>
                <div class="login_bar">
                    <input class="sub" type="submit" value=" "/>
                </div>
            </form>
        </div>
        <div class="login_banner"><img src="${ctx}/public/dwz/themes/default/images/login_banner.jpg"/>
        </div>
        <div class="login_main">
            <div class="login_inner">
            </div>
        </div>
    </div>
    <div id="login_footer">
        Copyright &copy; 2016-${currentYear} XDJA Inc. All Rights Reserved.
    </div>
</div>

</body>
</html>