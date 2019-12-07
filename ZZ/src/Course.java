
import java.io.Serializable;

public class Course implements Serializable {
    private String  courseName;
    private String courseType;
    private String  courseAddress;
    private String teacher;
    private String courseScore;

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(String courseScore) {
        this.courseScore = courseScore;
    }

    @Override
    public String toString() {
        return "System.Course{" +
                "courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseAddress='" + courseAddress + '\'' +
                ", teacher='" + teacher + '\'' +
                ", courseScore='" + courseScore + '\'' +
                '}';
    }
}
