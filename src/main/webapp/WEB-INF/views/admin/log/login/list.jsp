<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/admin/log/login/list">
    <input type="hidden" name="numPerPage" value=""/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="_order" value=""/>
    <input type="hidden" name="_sort" value=""/>
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="${ctx}/admin/log/login/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>用户名：</label><input type="text" name="keyword"/>
                </li>
                <li>
                    <label>登录IP：</label><input type="text" name="loginIp"/>
                </li>
                <li class="dateRange">
                    <label>登录时间:</label>
                    <input name="startDate" class="date readonly" readonly="readonly" type="text" value="">
                    <span class="limit">-</span>
                    <input name="endDate" class="date readonly" readonly="readonly" type="text" value="">
                </li>
            </ul>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">查询</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <table class="table" width="100%" layoutH="110">
        <thead>
        <tr>
            <th width="150">登录时间</th>
            <th width="130">登录IP</th>
            <th>用户名</th>
            <th width="150">离线时间</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>2016-10-11 21:21:31</td>
            <td>225.225.225.225</td>
            <td>administrator</td>
            <td>2016-10-12 21:21:31</td>
        </tr><tr>
            <td>2016-10-11 21:21:31</td>
            <td>225.225.225.225</td>
            <td>administrator</td>
            <td>2016-10-12 21:21:31</td>
        </tr><tr>
            <td>2016-10-11 21:21:31</td>
            <td>225.225.225.225</td>
            <td>administrator</td>
            <td>2016-10-12 21:21:31</td>
        </tr>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="200">200</option>
            </select>
            <span>条，共${totalCount}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10"
             currentPage="1"></div>

    </div>
</div>
