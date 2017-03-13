package app.util;

import javax.servlet.http.HttpServletRequest;


public class HttpTools {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return (header != null && "XMLHttpRequest".equalsIgnoreCase(header.toString()));
    }
}
