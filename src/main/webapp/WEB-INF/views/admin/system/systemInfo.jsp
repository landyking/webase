<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="pageContent">
    <div class="panel">
        <div>
            <table class="list" width="100%">
                <thead>
                <tr>
                    <td width="200"></td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dataList}" var="data">
                    <tr>
                        <td>${data.first}</td>
                        <td>${data.second}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</div>
