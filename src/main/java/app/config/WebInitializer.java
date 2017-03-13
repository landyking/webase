package app.config;

import app.util.FreeworkDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Description： 初始化操作 <br/>
 *
 * @author: landy
 * @date: 2015/6/4 10:34
 * note:
 */
@ControllerAdvice
public class WebInitializer {
    @InitBinder
    public void initBinder(WebDataBinder binder,WebRequest request) {
        binder.registerCustomEditor(Date.class, FreeworkDateEditor.INSTANCE);
    }
}
