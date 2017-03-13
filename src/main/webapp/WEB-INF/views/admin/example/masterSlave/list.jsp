<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="pageContent">
    <div id="jbsxBox2" class="unitBox" style="float:left; display:block; overflow:auto; width:614px;">
        <div class="pageHeader" style="border:1px #B8D0D6 solid">
            <form id="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/example/masterSlave/list"
                  method="post">
                <input type="hidden" name="numPerPage" value="${pageParam.numPerPage}"/>
                <input type="hidden" name="page" value="${pageParam.page}"/>
                <input type="hidden" name="_order" value="${searchParam._order}"/>
                <input type="hidden" name="_sort" value="${searchParam._sort}"/>
                <div class="searchBar">
                    <table class="searchContent">
                        <tr>
                            <td>
                                <label>名称：</label><input type="text" name="keyword" value="${searchParam.keyword}"/>
                            </td>
                            <td>
                                <div class="buttonActive">
                                    <div class="buttonContent">
                                        <button type="submit">查询</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>

        <div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
            <div class="panelBar">
                <ul class="toolBar">
                    <li><a class="icon" href="${ctx}/admin/example/masterSlave/deleteClass?classId={classId}"
                           target="ajaxTodo" title="确认要删除吗？"><span>删除</span></a></li>
                </ul>
            </div>
            <table class="table" width="100%" layoutH="113">
                <thead>
                <tr>
                    <th width="100">班级ID</th>
                    <th width="100">班级名称</th>
                    <th width="100">班主任名称</th>
                    <th width="80">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dataList}" var="data">
                    <tr target="classId" rel="${data.classId}">
                        <td>${data.classId}</td>
                        <td>${data.className}</td>
                        <td>${data.teacherName}</td>
                        <td>
                            <a href="${ctx}/admin/example/masterSlave/slave?classId=${data.classId}" target="ajax"
                               rel="jbsxBox3">学生列表</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panelBar">
                <div class="pages">
                    <span>显示</span>
                    <select class="combox" name="numPerPage"
                            onchange="navTabPageBreak({numPerPage:this.value})">
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
    </div>
    <div id="jbsxBox3" class="unitBox" style="margin-left:620px;">
    </div>
</div>