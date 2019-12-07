
import java.util.List;

public class Student extends Person {
    List<Course> selectCourse;

    public List<Course> getSelectCourse() {
        return selectCourse;
    }

    public void setSelectCourse(List<Course> selectCourse) {
        this.selectCourse = selectCourse;
    }

    @Override
    public String toString() {
        return "System.Student{" +
                "System.SelectCourse=" + selectCourse +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
