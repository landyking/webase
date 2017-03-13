<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="pageHeader" style="border:1px #B8D0D6 solid">
        ${classInfo.className} - 学生列表
        <form id="pagerForm" onsubmit="return divSearch(this,'jbsxBox3');"
              action="${ctx}/admin/example/masterSlave/slave?classId=${classInfo.classId}"
              method="post">
            <input type="hidden" name="numPerPage" value="${pageParam.numPerPage}"/>
            <input type="hidden" name="page" value="${pageParam.page}"/>
            <input type="hidden" name="_order" value="${searchParam._order}"/>
            <input type="hidden" name="_sort" value="${searchParam._sort}"/>
        </form>
    </div>
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="${ctx}/admin/agent/ipList/add" target="dialog" mask="true"><span>添加</span></a>
            </li>
            <li><a class="delete"
                   href="${ctx}/admin/example/masterSlave/deleteStudent?rel=jbsxBox3&classId=${classInfo.classId}&studentId={studentId}"
                   target="ajaxTodo"
                   title="确定要删除吗?"><span>删除</span></a>
            </li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="100">
        <thead>
        <tr>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dataList}" var="data">
            <tr target="studentId" rel="${data.studentId}">
                <td>${data.name}</td>
                <td>${data.age}</td>
                <td>${data.sex}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage"
                    onchange="navTabPageBreak({numPerPage:this.value},'jbsxBox3')">
                <option value="20" ${pageParam.numPerPage == 20 ? "selected": ""}>20</option>
                <option value="50" ${pageParam.numPerPage == 50 ? "selected": ""}>50</option>
                <option value="100" ${pageParam.numPerPage == 100 ? "selected": ""}>100</option>
                <option value="200" ${pageParam.numPerPage == 200 ? "selected": ""}>200</option>
            </select>
            <span>条，共${pageParam.totalCount}条</span>
        </div>

        <div class="pagination" rel="jbsxBox3" targetType="navTab" totalCount="${pageParam.totalCount}"
             numPerPage="${pageParam.numPerPage}" pageNumShown="10"
             currentPage="${pageParam.page}"></div>

    </div>
</div>