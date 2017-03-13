package app.controller.admin;

import app.config.AppController;
import app.service.RuntimeLogService;
import app.util.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  <br/>
 *
 * @author: landy
 * @date: 2017/2/15 11:46
 * note:
 */
@Controller
public class RuntimeLogController extends AppController {
    @Resource
    private RuntimeLogService runtimeLogService;

    public void list(@RequestParam final Map<String, String> params, final ModelMap modelMap) {
        PageRequest pageRequest = PageRequest.parsePageRequest();
        List<Map<String, Object>> maps = runtimeLogService.list(pageRequest, params);
        modelMap.addAttribute("dataList", maps);
        modelMap.addAttribute("pageParam", pageRequest);
        modelMap.addAttribute("searchParam", params);
    }
}
