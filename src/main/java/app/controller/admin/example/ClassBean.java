package app.controller.admin.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2017/03/13 23:11
 * note:
 */
public class ClassBean {
    private String classId;
    private String className;
    private String teacherName;


    public ClassBean(String classId, String className, String teacherName) {
        this.classId = classId;
        this.className = className;
        this.teacherName = teacherName;
    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
