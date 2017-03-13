<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<script language="javascript">
</script>
<div class="pageContent">
    <form method="post" action="${ctx}/admin/deliveryServer/update?callbackType=closeCurrent"
          class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent" layoutH="58">
            <input type="hidden" name="server_id" value="${data.server_id}">
            <div class="unit">
                <label>投递服务器名称：</label>
                <input type="text" name="server_name" size="28" maxlength="100" minlength="4"
                       value="${data.server_name}" class="required">
            </div>
            <div class="unit">
                <label>投递服务器地址：</label>
                <input type="text" name="server_host" size="28" maxlength="100" class="required"
                       value="${data.server_host}">
            </div>
            <div class="unit">
                <label>投递服务器端口：</label>
                <input type="text" name="server_port" size="28" maxlength="100" min="1" max="65535"
                       class="required digits" value="${data.server_port}">
            </div>
            <div class="unit">
                <label>本机身份标识：</label>
                <input type="text" name="local_identity_key" size="28" minlength="6" maxlength="100" class="required"
                       value="${data.local_identity_key}">
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



