package app.controller;

import app.config.AppController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2016/12/18 15:53
 * note:
 */
@Controller
public class DefaultController extends AppController {
    @RequestMapping({"/", "/index", "/home"})
    public String home(ModelMap model) {
        return "redirect:/admin/index";
    }
}
