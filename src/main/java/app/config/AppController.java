package app.config;

import app.util.JsonResult;
import app.util.Texts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/9 18:05
 * note:
 */
public abstract class AppController {
    private static final Logger LOGGER = LoggerFactory.getLogger("Controller");

    protected JsonResult dwzSuccess() {
        JsonResult success = JsonResult.success();
        attachPassParam(success);
        return success;
    }

    protected JsonResult dwzSuccess(String msg) {
        JsonResult success = JsonResult.success(msg);
        attachPassParam(success);
        return success;
    }

    private void attachPassParam(JsonResult success) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String callbackType = request.getParameter("callbackType");
        if (Texts.hasText(callbackType)) {
            success.setCallbackType(callbackType);
        }
        String navTabId = request.getParameter("navTabId");
        if (Texts.hasText(navTabId)) {
            success.setNavTabId(navTabId);
        }
    }

    protected JsonResult dwzFailure(String msg) {
        JsonResult failure = JsonResult.failure(msg);
        attachPassParam(failure);
        return failure;
    }

    protected void redirect(String view) {
    }

    protected void logInfo(String msg) {
        LOGGER.info(msg);
    }

    protected void logWarn(String var1, Object... var2) {
        LOGGER.warn(var1, var2);
    }

    protected void logWarn(String var1) {
        LOGGER.warn(var1);
    }

    protected void logError(String msg, Throwable throwable) {
        LOGGER.error(msg, throwable);
    }


}
