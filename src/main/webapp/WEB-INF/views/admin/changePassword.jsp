<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="page">
    <div class="pageContent">
        <form method="post" action="${ctx}/admin/changePassword?callbackType=closeCurrent"
              class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="58">
                <div class="unit">
                    <label>老密码</label><input type="password" name="oldPass"
                                             class="required alphanumeric" value="" minlength="4" maxlength="20">
                </div>
                <div class="unit"><label>新密码</label><input id="Publicpassword" type="password" name="newPass"
                                                           class="required alphanumeric" minlength="4" maxlength="20">
                </div>
                <div class="unit"><label>确认密码</label><input type="password" name="repassword"
                                                            class="required alphanumeric" minlength="4" maxlength="20"
                                                            equalto="#Publicpassword"></div>
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
</div>