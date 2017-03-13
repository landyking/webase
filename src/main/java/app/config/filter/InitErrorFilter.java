package app.config.filter;

import app.config.listener.ApplicationInitListener;
import com.google.common.base.Charsets;
import org.apache.commons.lang.SystemUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: landy
 * @date: 2015/12/20 22:19
 * note:
 */
public class InitErrorFilter implements Filter {

    private ServletContext application;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        application = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object attribute = application.getAttribute(ApplicationInitListener.INIT_ERROR);
        if (attribute != null && StringUtils.hasText(attribute.toString())) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setCharacterEncoding(Charsets.UTF_8.name());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            resp.getWriter().println("<html><head><title>系统启动出错</title></head><body><h1>系统启动出错</h1>");
            resp.getWriter().println("<h3>" +attribute.toString()+ "</h3>");
            resp.getWriter().println("<p><ul>" +
                    "<li>请检查用户"+ SystemUtils.USER_NAME+"是否拥有足够的权限</li>" +
                    "<li>请检查网络设置,例如IP设置</li>" +
                    "<li>请检查磁盘容量状况</li>" +
                    "<li>请检查其他相关内容</li>" +
                    "</ul></p>");
            resp.getWriter().println("</body></html>");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        application = null;
    }
}
