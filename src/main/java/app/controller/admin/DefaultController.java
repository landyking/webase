package app.controller.admin;

import app.config.AppController;
import app.config.shiro.ShiroUser;
import app.constant.AppParamEnum;
import app.model.AppParam;
import app.util.ActiveJdbcMojo;
import app.util.JsonResult;
import app.util.MyAssert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/13 9:54
 * note:
 */
@Controller("admin.default")
public class DefaultController extends AppController {

    public void index(Model model) {

    }

    @ModelAttribute
    private void extraAttribute(Model model) {
        model.addAttribute("currentYear", new SimpleDateFormat("yyyy").format(new Date()));
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if (principal != null) {
            ShiroUser shiroUser = (ShiroUser) principal;
            model.addAttribute("currentUser", shiroUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(String username, String password, final ModelMap modelMap) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/admin/index";
        } catch (Exception e) {
            logWarn("{}登录失败:{}", username, e.getMessage());
            modelMap.addAttribute("loginError", e.getMessage());
            return null;
        }

    }

    public void changePassword() {

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object changePassword(String oldPass, String newPass) {
        try {
            MyAssert.hasText(newPass, "新密码不能为空");
            try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
                AppParam adminLoginUserPass = AppParam.findById(AppParamEnum.ADMIN_LOGIN_USER_PASS);
                if (oldPass.equals(adminLoginUserPass.getString("param_value"))) {
                    adminLoginUserPass.setParamValue(newPass);
                    adminLoginUserPass.saveIt();
                } else {
                    return dwzFailure("老密码不正确");
                }
            }
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            return dwzFailure(e.getMessage());
        }
        JsonResult result = dwzSuccess();
        result.setStatusCode(JsonResult.STATUS_TIMEOUT);
        result.setMessage("修改成功，请重新登录");
        return result;
    }


}
