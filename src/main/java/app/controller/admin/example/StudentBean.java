package app.controller.admin.example;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2017/03/13 23:11
 * note:
 */
public class StudentBean {
    private String studentId;
    private String classId;
    private String name;
    private Integer age;
    private String sex;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public StudentBean(String studentId, String classId, String name, Integer age, String sex) {
        this.studentId = studentId;
        this.classId = classId;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
