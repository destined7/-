
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AddTableRowData {
    public void addTableRowData(JTable table, List<Course> courses) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        for (Course course : courses) {
            String[] arr = new String[5];

            arr[0] = course.getCourseName();
            arr[1] = course.getTeacher();
            arr[2] = course.getCourseType();
            arr[3] = course.getCourseScore();
            arr[4] = course.getCourseAddress();

            tableModel.addRow(arr);
        }
    }
}
