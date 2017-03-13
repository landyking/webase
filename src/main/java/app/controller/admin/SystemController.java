package app.controller.admin;

import app.config.listener.ApplicationInitListener;
import app.util.AppPropertiesUtils;
import app.util.ProgramInfo;
import app.util.Tuple;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2016/12/18 19:40
 * note:
 */
@Controller
public class SystemController {
    public void systemInfo(ModelMap modelMap) {
        List<Tuple<String, String>> rst = Lists.newArrayList();
        rst.add(Tuple.newOne("软件版本", AppPropertiesUtils.APP_VERSION));
        rst.add(Tuple.newOne("工作目录(" + AppPropertiesUtils.APP_HOME + ")", ApplicationInitListener.getAppHome()));
        rst.add(Tuple.newOne("操作系统", ProgramInfo.getOSnfo()));
        rst.add(Tuple.newOne("JAVA HOME", ProgramInfo.getJVMHome()));
        rst.add(Tuple.newOne("JAVA Vendor", ProgramInfo.getJVMVendor()));
        rst.add(Tuple.newOne("JAVA Version", ProgramInfo.getJVMVersion()));
        rst.add(Tuple.newOne("线程数量", String.valueOf(ProgramInfo.getThreadCount())));
        rst.add(Tuple.newOne("内存占用", ProgramInfo.getMemoryInfo()));
        rst.add(Tuple.newOne("运行时间", ProgramInfo.getUpTime()));
        rst.add(Tuple.newOne("PID", ProgramInfo.getPID()));
        rst.add(Tuple.newOne("本机IP", ProgramInfo.getIpInfo()));
        rst.add(Tuple.newOne("启动用户", ProgramInfo.getOSUser()));
        modelMap.addAttribute("dataList", rst);
    }
}
