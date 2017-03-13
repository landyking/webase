<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include.inc.jsp" %>
<div class="pageContent">
    <div class="panel">
        <h1>投递服务信息</h1>
        <div>
            <div class="pageFormContent">
                <dl>
                    <dt>当前状态：</dt>
                    <dd>${data.engineDeliveryServiceState}</dd>
                </dl>
                <dl class="nowrap">
                    <dt>监听端口：</dt>
                    <dd>${data.engineDeliveryServiceListenPort}</dd>
                </dl>

                <div class="divider"></div>
                <dl>
                    <dt><a class="button" href="${ctx}/admin/deliveryService/start?navTabId=delivery-service-setting" target="ajaxTodo"
                           title="你确定要启动吗？"><span>启动</span></a></dt>
                    <dd><a class="button" href="${ctx}/admin/deliveryService/stop?navTabId=delivery-service-setting" target="ajaxTodo"
                           title="你确定要停止吗？"><span>停止</span></a></dd>
                </dl>
            </div>
        </div>
    </div>
    <div class="panel">
        <h1>设置</h1>
        <div>
            <form method="post" action="${ctx}/admin/deliveryService/updateListenPort"
                  class="pageForm required-validate"
                  onsubmit="return validateCallback(this, navTabAjaxDone);">
                <div class="pageFormContent">
                    <dl class="nowrap">
                        <dt>监听端口：</dt>
                        <dd><input class="required" name="listenPort" type="text" size="30" min="1"
                                   max="65535"
                                   class="required digits" value="${data.databaseDeliveryServiceListenPort}"/></dd>
                    </dl>

                    <div class="divider"></div>
                    <dl>
                        <dt>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">更新</button>
                            </div>
                        </div>
                        </dt>
                        <dd></dd>
                    </dl>
                </div>
            </form>
        </div>
    </div>


</div>
