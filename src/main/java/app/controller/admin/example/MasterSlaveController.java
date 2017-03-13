package app.controller.admin.example;

import app.config.AppController;
import app.util.MyAssert;
import app.util.PageRequest;
import app.util.Texts;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2017/03/13 22:53
 * note:
 */
@Controller
public class MasterSlaveController extends AppController {
    private static List<ClassBean> classList = new ArrayList<>();
    private static Map<String, List<StudentBean>> class2student = new HashMap<>();

    static {
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            ClassBean classBean = new ClassBean(Texts.uuid(), "班级" + i, "第" + i + "老师");
            classList.add(classBean);
            int studentNum = random.nextInt(60);
            if (studentNum > 0) {
                for (int j = 0; j < studentNum; j++) {
                    StudentBean stu = new StudentBean(Texts.uuid(), classBean.getClassId(), i + "班学生" + j, 15 + random.nextInt(3), random.nextBoolean() ? "男" : "女");
                    if (!class2student.containsKey(classBean.getClassId())) {
                        class2student.put(classBean.getClassId(), new ArrayList<StudentBean>());
                    }
                    class2student.get(classBean.getClassId()).add(stu);
                }
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object deleteClass(@RequestParam("classId") String classId, ModelMap modelMap) {
        try {
            MyAssert.hasText(classId, "班级编号不能为空");
            Iterator<ClassBean> iterator = classList.iterator();
            while (iterator.hasNext()) {
                ClassBean next = iterator.next();
                if (next.getClassId().equals(classId)) {
                    iterator.remove();
                    break;
                }
            }
            if (class2student.containsKey(classId)) {
                class2student.remove(classId).clear();
            }
        } catch (Exception e) {
            logWarn("删除班级异常：" + e.getMessage());
            return dwzFailure(e.getMessage());
        }
        return dwzSuccess();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object deleteStudent(@RequestParam("classId") String classId, @RequestParam("studentId") String studentId, ModelMap modelMap) {
        try {
            MyAssert.hasText(classId, "班级编号不能为空");
            MyAssert.hasText(studentId, "学生编号不能为空");
            if (class2student.containsKey(classId)) {
                Iterator<StudentBean> iterator = class2student.get(classId).iterator();
                while (iterator.hasNext()) {
                    StudentBean next = iterator.next();
                    if (next.getStudentId().equals(studentId)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logWarn("删除学生异常：" + e.getMessage());
            return dwzFailure(e.getMessage());
        }
        return dwzSuccess();
    }

    public void list(@RequestParam final Map<String, String> params, final ModelMap modelMap) {
        PageRequest pageRequest = PageRequest.parsePageRequest();
        List<ClassBean> dataList = classList;
        pageRequest.computePageParam(dataList.size());
        Iterable<ClassBean> skip = Iterables.skip(dataList, pageRequest.getStart());
        Iterable<ClassBean> limit = Iterables.limit(skip, pageRequest.getNumPerPage());
        modelMap.addAttribute("dataList", limit.iterator());
        modelMap.addAttribute("pageParam", pageRequest);
        modelMap.addAttribute("searchParam", params);
    }

    public void slave(@RequestParam final Map<String, String> params, final ModelMap modelMap) {
        PageRequest pageRequest = PageRequest.parsePageRequest();
        String classId = params.get("classId");
        Optional<ClassBean> classInfo = getClassBean(classId);
        if (Texts.hasText(classId) && class2student.containsKey(classId)) {
            List<StudentBean> dataList = class2student.get(classId);
            pageRequest.computePageParam(dataList.size());
            Iterable<StudentBean> skip = Iterables.skip(dataList, pageRequest.getStart());
            Iterable<StudentBean> limit = Iterables.limit(skip, pageRequest.getNumPerPage());
            modelMap.addAttribute("dataList", limit.iterator());
        } else {
            modelMap.addAttribute("dataList", new ArrayList<>());
        }
        if (classInfo.isPresent()) {
            modelMap.addAttribute("classInfo", classInfo.get());
        }
        modelMap.addAttribute("pageParam", pageRequest);
        modelMap.addAttribute("searchParam", params);
    }

    private Optional<ClassBean> getClassBean(final String classId) {
        return Iterables.tryFind(classList, new Predicate<ClassBean>() {
            @Override
            public boolean apply(ClassBean input) {
                return input.getClassId().equals(classId);
            }
        });
    }
}
