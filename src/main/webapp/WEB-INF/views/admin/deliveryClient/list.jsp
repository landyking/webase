<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="pageContent">
    <table class="table" width="100%" layoutH="48">
        <thead>
        <tr>
            <th width="130">开始时间</th>
            <th width="130">客户端标识</th>
            <th width="80">连接地址</th>
            <th width="80">连接时长</th>
            <th width="65">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr>
                <td>${data.createTime}</td>
                <td>${data.identityKey}</td>
                <td>${data.sourceAddress}</td>
                <td>${data.ago}</td>
                <td>
                    <a href="${ctx}/admin/deliveryClient/disconnect?clientId=${data.clientId}&navTabId=delivery-client-list"
                       target="ajaxTodo"
                       title="你确定要断开吗？">断开</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>共${fn:length(dataList)}条</span>
        </div>
    </div>
</div>
