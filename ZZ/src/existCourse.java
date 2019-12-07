
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class existCourse extends javax.swing.JPanel {

    JTable table;
    Student student;
    String userName;

    public existCourse(String name) {

        userName = name;

        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);


        File file = new File("d:\\temp\\"+userName+".txt");

        student = null;


        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            student = (Student) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] columnTitle= {"课程名称","授课老师","课程类型","课程学分","上课地点"};

        //表格行对象数据
        Object[][] tableData= {
        };

        DefaultTableModel model = new DefaultTableModel(tableData,columnTitle) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table=new JTable(model);
        JScrollPane scrollpane=new JScrollPane(table);
        scrollpane.setSize(550,150);
        scrollpane.setLocation(60, 20);

        if (student.getSelectCourse() != null) {
            AddTableRowData data = new AddTableRowData();
            data.addTableRowData(table,student.getSelectCourse());
        }

        this.add(scrollpane);
        this.setVisible(true);

    }

}



