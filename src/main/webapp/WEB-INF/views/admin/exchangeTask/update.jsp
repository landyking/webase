<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<script language="javascript">
</script>
<div class="pageContent">
    <form method="post" action="${ctx}/admin/exchangeTask/update?callbackType=closeCurrent"
          class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent" layoutH="58">
            <input type="hidden" name="task_id" size="28" maxlength="100" value="${data.task_id}">
            <input type="hidden" name="task_mode" size="28" maxlength="100" value="${data.task_mode}">
            <div class="unit">
                <label>任务名称：</label>
                <input type="text" name="task_name" size="28" maxlength="100" class="required"
                       value="${data.task_name}">
            </div>
            <div class="unit">
                <label>任务模式：</label>
                <input type="text" size="28" maxlength="100" disabled readonly
                       value="${data.task_mode}">
            </div>
            <c:if test="${data.task_mode == 'fetch'}">
                <div class="unit">
                    <label>源服务器地址：</label>
                    <input type="text" name="source_host" size="28" maxlength="100" class="required"
                           value="${data.source_host}">
                </div>
                <div class="unit">
                    <label>源服务器端口：</label>
                    <input type="text" name="source_port" size="28" maxlength="100" min="1" max="65535"
                           class="required digits" value="${data.source_port}">
                </div>
                <div class="unit">
                    <label>源服务器账号：</label>
                    <input type="text" name="source_account" size="28" maxlength="100" value="${data.source_account}">
                </div>
                <div class="unit">
                    <label>源服务器密码：</label>
                    <input type="text" name="source_password" size="28" maxlength="100" value="${data.source_password}">
                </div>
                <div class="unit">
                    <label>源服务器目录：</label>
                    <input type="text" name="source_directory" size="28" maxlength="100" class=""
                           value="${data.source_directory}">
                    <span class="info">留空即使用默认目录</span>
                </div>
                <div class="unit">
                    <label>扫描间隔：</label>
                    <input type="text" name="scan_interval" size="28" maxlength="100" min="1" class="required digits"
                           value="${data.scan_interval}">
                    <span class="info">单位：秒</span>
                </div>
            </c:if>
            <c:if test="${data.task_mode == 'receive'}">
                <div class="unit">
                    <label>本地FTP端口：</label>
                    <input type="text" name="local_port" size="28" maxlength="100" min="1" max="65535"
                           class="required digits" value="${data.local_port}">
                </div>
                <div class="unit">
                    <label>本地FTP账号：</label>
                    <input type="text" name="local_account" size="28" maxlength="100" class=""
                           value="${data.local_account}">
                </div>
                <div class="unit">
                    <label>本地FTP密码：</label>
                    <input type="text" name="local_password" size="28" maxlength="100" class=""
                           value="${data.local_password}">
                </div>
                <div class="unit">
                    <label>本地FTP目录：</label>
                    <input type="text" name="local_directory" size="28" maxlength="100" class=""
                           value="${data.local_directory}">
                    <span class="info">留空即使用默认目录</span>
                </div>
            </c:if>
            <div class="unit">
                <label>投递服务器：</label>
                <select name="delivery_server_id" class="required">
                    <c:forEach items="${deliveryServerList}" var="opt">
                        <option value="${opt.server_id}" ${opt.server_id == data.delivery_server_id ?"selected":""}>${opt.server_name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="unit">
                <label>目的服务器地址：</label>
                <input type="text" name="dest_host" size="28" maxlength="100" class="required"
                       value="${data.dest_host}">
            </div>
            <div class="unit">
                <label>目的服务器端口：</label>
                <input type="text" name="dest_port" size="28" maxlength="100" min="1" max="65535"
                       class="required digits" value="${data.dest_port}">
            </div>
            <div class="unit">
                <label>目的服务器账号：</label>
                <input type="text" name="dest_account" size="28" maxlength="100" value="${data.dest_account}">
            </div>
            <div class="unit">
                <label>目的服务器密码：</label>
                <input type="text" name="dest_password" size="28" maxlength="100" value="${data.dest_password}">
            </div>
            <div class="unit">
                <label>目的服务器目录：</label>
                <input type="text" name="dest_directory" size="28" maxlength="100" class=""
                       value="${data.dest_directory}">
                <span class="info">留空即使用默认目录</span>
            </div>
            <div class="unit">
                <label>缓冲空间大小：</label>
                <input type="text" name="buffer_space_size" size="28" maxlength="100" min="1"
                       class="required digits" value="${data.buffer_space_size}">
                <span class="info">单位：MB</span>
            </div>
            <div class="unit">
                <label>备注：</label>
                <textarea name="remark" rows="3" cols="57">${data.remark}</textarea>
            </div>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">确定</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>

</div>



