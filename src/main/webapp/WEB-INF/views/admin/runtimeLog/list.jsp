<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/admin/runtimeLog/list">
    <input type="hidden" name="numPerPage" value="${pageParam.numPerPage}"/>
    <input type="hidden" name="page" value="${pageParam.page}"/>
    <input type="hidden" name="_order" value="${searchParam._order}"/>
    <input type="hidden" name="_sort" value="${searchParam._sort}"/>
</form>
<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/runtimeLog/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>描述：</label><input type="text" name="log_desc" value="${searchParam.log_desc}"/>
                </li>
                <li class="dateRange">
                    <label>开始时间:</label>
                    <input name="start_date" class="date readonly" readonly="readonly" type="text"
                           value="${searchParam.start_date}">
                    <span class="limit">-</span>
                    <input name="end_date" class="date readonly" readonly="readonly" type="text"
                           value="${searchParam.end_date}">
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
            <th width="150">时间</th>
            <th>描述</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr>
                <td>${data.start_time}</td>
                <td>${data.log_desc}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20" ${pageParam.numPerPage == 20 ? "selected": ""}>20</option>
                <option value="50" ${pageParam.numPerPage == 50 ? "selected": ""}>50</option>
                <option value="100" ${pageParam.numPerPage == 100 ? "selected": ""}>100</option>
                <option value="200" ${pageParam.numPerPage == 200 ? "selected": ""}>200</option>
            </select>
            <span>条，共${pageParam.totalCount}条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="${pageParam.totalCount}"
             numPerPage="${pageParam.numPerPage}" pageNumShown="10"
             currentPage="${pageParam.page}"></div>
    </div>
</div>
