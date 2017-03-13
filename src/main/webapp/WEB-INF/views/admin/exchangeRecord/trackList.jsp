<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="pageContent">
    <table class="table" width="100%" layoutH="50">
        <thead>
        <tr>
            <%--<th>ID</th>--%>
            <th width="130">时间</th>
            <th>内容</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr target="track_id" rel="${data.track_id}">
                <%--<td>${data.track_id}</td>--%>
                <td>${data.create_time}</td>
                <td>${data.track_desc}</td>
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
