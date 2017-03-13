package app.util;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/9 17:16
 * note:
 */
public class JsonResult {
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILURE = 0;
    public static final int STATUS_TIMEOUT = 301;
    private int statusCode;
    private String message;
    private String navTabId;
    private String rel;
    private String callbackType;
    private String forwardUrl;

    public static JsonResult timeout() {
        JsonResult rst = new JsonResult();
        rst.setStatusCode(STATUS_TIMEOUT);
        rst.setMessage("登录超时，请重新登录");
        return rst;
    }

    public static JsonResult success(String message) {
        JsonResult rst = new JsonResult();
        rst.setStatusCode(STATUS_SUCCESS);
        rst.setMessage(message);
        return rst;
    }

    public static JsonResult success() {
        return success("操作成功");
    }

    public static JsonResult failure(String msg, String callbackType) {
        JsonResult rst = new JsonResult();
        rst.setStatusCode(STATUS_FAILURE);
        rst.setMessage(msg);
        rst.setCallbackType(callbackType);
        return rst;
    }

    public static JsonResult failure(String msg) {
        return failure(msg, null);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }
}
