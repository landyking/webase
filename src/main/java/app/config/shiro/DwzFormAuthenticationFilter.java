package app.config.shiro;

import app.util.HttpTools;
import app.util.JsonResult;
import app.util.JsonUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description： <br/>
 *
 * @author: landy
 * @date: 2017/2/23 9:46
 * note:
 */
public class DwzFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        if (HttpTools.isAjaxRequest((HttpServletRequest) request)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8;");//为了兼容IE，所以此处为text/html
            PrintWriter outputStream = new PrintWriter(response.getOutputStream());
            String json = JsonUtils.toJsonStr(JsonResult.timeout());
            outputStream.print(json);
            outputStream.flush();
            outputStream.close();
        } else {
            super.saveRequestAndRedirectToLogin(request, response);
        }
    }
}
