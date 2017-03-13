<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/admin/exchangeTask/list">
    <input type="hidden" name="numPerPage" value="${pageParam.numPerPage}"/>
    <input type="hidden" name="page" value="${pageParam.page}"/>
    <input type="hidden" name="_order" value="${searchParam._order}"/>
    <input type="hidden" name="_sort" value="${searchParam._sort}"/>
</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/exchangeTask/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>名称：</label><input type="text" name="task_name" value="${searchParam.task_name}"/>
                </li>
                <%--<li>
                    <label>任务模式：</label>
                    <select name="task_mode" class="required combox"
                            onchange="$(this).parents('form:first').submit()">
                        <option></option>
                        <option value="fetch" ${searchParam.task_mode == 'fetch' ? "selected": ""}>Fetch模式</option>
                        <option value="receive" ${searchParam.task_mode == 'receive' ? "selected": ""}>Receive模式
                        </option>
                    </select>
                </li>--%>
                <li>
                    <label>投递服务器：</label>
                    <select name="delivery_server_id" class="required combox"
                            onchange="$(this).parents('form:first').submit()">
                        <option></option>
                        <c:forEach items="${deliveryServerList}" var="opt">
                            <option value="${opt.server_id}" ${opt.server_id == searchParam.delivery_server_id ?"selected":""}>${opt.server_name}</option>
                        </c:forEach>
                    </select>
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
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="${ctx}/admin/exchangeTask/addModeFetch" target="dialog" mask="true" height="520"
                   rel="add-task-fetch"><span>新增Fetch模式交换任务</span></a>
            </li>
            <%--<li><a class="add" href="${ctx}/admin/exchangeTask/addModeReceive" target="dialog" mask="true" height="470"
                   rel="add-task-receive"><span>新增Receive模式交换任务</span></a>
            </li>--%>
            <li><a class="edit" href="${ctx}/admin/exchangeTask/update?task_id={task_id}" target="dialog" mask="true"
                   height="550"
                   rel=""><span>更新交换任务</span></a>
            </li>
            <li><a class="delete" href="${ctx}/admin/exchangeTask/delete?task_id={task_id}" target="ajaxTodo"
                   title="你确定要删除吗？"><span>删除交换任务</span></a>
            </li>
            <li class="line">line</li>
            <li><a class="icon" href="${ctx}/admin/exchangeTask/enable?task_id={task_id}" target="ajaxTodo"
                   title="你确定要启用吗？"><span>启用</span></a>
            </li>
            <li><a class="icon" href="${ctx}/admin/exchangeTask/disable?task_id={task_id}" target="ajaxTodo"
                   title="你确定要停用吗？"><span>停用</span></a>
            </li>
        </ul>
    </div>

    <table class="table" width="100%" layoutH="137">
        <thead>
        <tr>
            <th width="130">名称</th>
            <th width="70">任务模式</th>
            <th>源服务器</th>
            <th>目的服务器</th>
            <th width="100">投递服务器</th>
            <th width="65">状态</th>
            <th width="80">交换记录数</th>
            <th width="130">交换数据量</th>
            <th width="80">缓冲总量</th>
            <th width="90">已用缓冲</th>
            <th width="130">创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr target="task_id" rel="${data.task_id}">
                <td>${data.task_name}</td>
                <td>${data.task_mode}</td>
                <td>${data.source_address}</td>
                <td>${data.dest_address}</td>
                <td>${data.delivery_server.server_name}</td>
                <td>${data.is_enable=='1'?"启用":"停用"}</td>
                <td>${data.exchange_record_count}</td>
                <td>${data.exchange_data_size}</td>
                <td>${data.buffer_space_size}</td>
                <td>${data.used_buffer_space}</td>
                <td>${data.create_time}</td>
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
