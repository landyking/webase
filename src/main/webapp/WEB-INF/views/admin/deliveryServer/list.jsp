<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/admin/deliveryServer/list">
    <input type="hidden" name="numPerPage" value="${pageParam.numPerPage}"/>
    <input type="hidden" name="page" value="${pageParam.page}"/>
    <input type="hidden" name="_order" value="${searchParam._order}"/>
    <input type="hidden" name="_sort" value="${searchParam._sort}"/>
</form>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="${ctx}/admin/deliveryServer/add" target="dialog" mask="true"
                   height="270"><span>新增投递服务器</span></a>
            </li>
            <li><a class="edit" href="${ctx}/admin/deliveryServer/update?server_id={server_id}" target="dialog"
                   mask="true"
                   height="270"><span>编辑投递服务器</span></a>
            </li>
            <li><a class="delete" href="${ctx}/admin/deliveryServer/delete?server_id={server_id}" target="ajaxTodo"
                   title="你确定要删除吗？"><span>删除投递服务器</span></a>
            </li>
            <li class="line">line</li>
            <li><a class="icon" href="${ctx}/admin/deliveryServer/enable?server_id={server_id}" target="ajaxTodo"
                   title="你确定要启用吗？"><span>启用</span></a>
            </li>
            <li><a class="icon" href="${ctx}/admin/deliveryServer/disable?server_id={server_id}" target="ajaxTodo"
                   title="你确定要停用吗？"><span>停用</span></a>
            </li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="75">
        <thead>
        <tr>
            <th width="130">服务器名称</th>
            <th width="130">服务器地址</th>
            <th width="130">服务器端口</th>
            <th width="130">本机身份标识</th>
            <th width="80">启用状态</th>
            <th width="65">连接状态</th>
            <th width="130">服务器备注</th>
            <th width="130">创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr target="server_id" rel="${data.server_id}">
                <td>${data.server_name}</td>
                <td>${data.server_host}</td>
                <td>${data.server_port}</td>
                <td>${data.local_identity_key}</td>
                <td>${data.is_enable=="1"?"启用":"停用"}</td>
                <td>${data.connection_state}</td>
                <td>${data.remark}</td>
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
