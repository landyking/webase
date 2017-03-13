<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${requestScope["app.name"]}</title>
    <link rel="shortcut icon" href="${ctx}/public/favicon.ico" type="image/x-icon">
    <link rel="icon" href="${ctx}/public/favicon.ico" type="image/x-icon">
    <link href="${ctx}/public/dwz/themes/silver/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/public/dwz/themes/css/core.css" rel="stylesheet" type="text/css"/>
    <!--[if IE]>
    <link href="${ctx}/public/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css"/>
    <![endif]-->
    <style type="text/css">
        fieldset.nowrap {
            padding: 5px;
            margin-top: 10px;
        }

        fieldset.nowrap p {
            clear: both;
            padding: 5px;
        }

        table.weeklyReport td {
            vertical-align: top;
        }

        #header .logo {
            background: none;
            line-height: 50px;
            padding-left: 10px;
            font-weight: bold;
            font-size: 28px;
            text-indent: 0px;
            text-decoration: none;
            color: #211919;
        }

        #header .nav {
            position: absolute;
            top: 30px;
            right: 5px;
        }
    </style>

    <!--[if lt IE 9]>
    <script src="${ctx}/public/dwz/js/speedup.js" type="text/javascript"></script>
    <script src="${ctx}/public/dwz/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <![endif]-->
    <!--[if gte IE 9]><!-->
    <script src="${ctx}/public/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <!--<![endif]-->

    <script src="${ctx}/public/dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctx}/public/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="${ctx}/public/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/public/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
    <script src="${ctx}/public/dwz/js/dwz.min.js" type="text/javascript"></script>
    <script src="${ctx}/public/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

    <script type="text/javascript">

        $(function () {
            DWZ.init("${ctx}/public/dwz/dwz.frag.xml", {
                loginUrl: "${ctx}/admin/login",
//		loginUrl:"__APP__/Public/login",	//跳到登录页面
                statusCode: {ok: 1, error: 0},
//                keys: {statusCode: "status", message: "info"},
                pageInfo: {pageNum: "page", numPerPage: "numPerPage", orderField: "_order", orderDirection: "_sort"}, //【可选】
                debug: false,	// 调试模式 【true|false】
                callback: function () {
                    initEnv();
                    $("#themeList").theme({themeBase: "${ctx}/public/dwz/themes"});
                }
            });
        });
    </script>
</head>

<body scroll="no">
<div id="layout">
    <div id="header">
        <div class="headerNav">
            <a class="logo" href="#"> ${requestScope["app.name"]}</a>
            <ul class="nav">
                <li>你好：${currentUser.userName}</li>
                <li><a href="${ctx}/admin/changePassword" target="dialog" mask="true">修改密码</a></li>
                <li><a href="${ctx}/admin/logout">退出</a></li>
            </ul>

        </div>
    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse">
                    <div></div>
                </div>
            </div>
        </div>

        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2>
                <div>收缩</div>
            </div>
            <div class="accordion" fillSpace="sideBar">
                <div class="accordionHeader">
                    <h2><span>Folder</span>示例程序</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                        <li><a href="${ctx}/admin/example/masterSlave/list" target="navTab"
                               rel="master-slave-list"><span>主从局部刷新</span></a>
                        </li>
                        <li><a href="${ctx}/admin/example/list" target="navTab"
                               rel="exchange-record-list"><span>交换记录查看</span></a>
                        </li>
                        <li><a href="${ctx}/admin/deliveryServer/list" target="navTab"
                               rel="delivery-server-list"><span>投递服务器管理</span></a></li>
                    </ul>
                </div>
                <div class="accordionHeader">
                    <h2><span>Folder</span>系统管理</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                        <%--<li><a href="${ctx}/admin/log/login/list" target="navTab"--%>
                        <%--rel="login-log-list"><span>登录日志</span></a></li>--%>
                        <li><a href="${ctx}/admin/runtimeLog/list" target="navTab"
                               rel="runtime-log-list"><span>运行日志</span></a>
                        </li>
                        <li><a href="${ctx}/admin/system/systemInfo" target="navTab"
                               rel="system-info-list"><span>系统信息</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:void(0)"><span><span
                                class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:void(0)">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">

                    <div class="pageFormContent" layoutH="80" style="min-width: 200px;">
                        <h2>欢迎使用${requestScope["app.name"]}！</h2>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<div id="footer">Copyright &copy; 2016-${currentYear} - &nbsp;${requestScope["app.name"]}
    - ${requestScope["app.version"]}</div>

</body>
</html>