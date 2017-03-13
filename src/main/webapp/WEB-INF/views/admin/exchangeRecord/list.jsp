<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/admin/exchangeRecord/list">
    <input type="hidden" name="numPerPage" value="${pageParam.numPerPage}"/>
    <input type="hidden" name="page" value="${pageParam.page}"/>
    <input type="hidden" name="_order" value="${searchParam._order}"/>
    <input type="hidden" name="_sort" value="${searchParam._sort}"/>
</form>
<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/exchangeRecord/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>交换序号：</label><input type="text" name="record_id" value="${searchParam.record_id}"/>
                </li>
                <li>
                    <label>文件名称：</label><input type="text" name="file_name" value="${searchParam.file_name}"/>
                </li>
                <li>
                    <label>交换状态：</label>
                    <select name="exchange_state" class="required combox"
                            onchange="$(this).parents('form:first').submit()">
                        <option></option>
                        <c:forEach items="${exchangeStateList}" var="opt">
                            <option value="${opt.first}" ${opt.first == searchParam.exchange_state ?"selected":""}>${opt.second}</option>
                        </c:forEach>
                    </select>
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
            <th width="130">开始时间</th>
            <th width="130">交换序号</th>
            <th width="130">所属任务</th>
            <th width="80">文件名称</th>
            <th width="80">文件大小</th>
            <%--<th width="80">文件时间戳</th>--%>
            <th width="80">交换状态</th>
            <th width="80">更新时间</th>
            <th width="65">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr target="record_id" rel="${data.record_id}">
                <td>${data.start_time}</td>
                <td>${data.record_id}</td>
                <td>${data.exchange_task.task_name}</td>
                <td>${data.file_name}</td>
                <td>${data.file_size}</td>
                <%--<td>${data.file_timestamp}</td>--%>
                <td>${data.exchange_state}</td>
                <td>${data.update_time}</td>
                <td>
                    <a href="${ctx}/admin/exchangeRecord/trackList?record_id=${data.record_id}" target="dialog"
                       mask="true">详情</a>
                </td>
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
