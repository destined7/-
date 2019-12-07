
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SelectCourse  extends JPanel implements ActionListener {
    JLabel Namelabel;
    JTextField Nametext;
    JButton Delbt;
    JTable table;
    Course course;
    List<Course> courses;
    String userName;

    public SelectCourse(String name) {

        userName = name;

        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);


        Namelabel=new JLabel("请输入退选课程");
        Namelabel.setSize(150,50);
        Namelabel.setLocation(100, 280);
        this.add(Namelabel);

        Nametext=new JTextField();
        Nametext.setSize(160,40);
        Nametext.setLocation(200, 280);
        this.add(Nametext);

        Delbt=new JButton("确认选课");
        Delbt.setSize(90,38);
        Delbt.setLocation(420, 280);
        this.add(Delbt);
        Delbt.addActionListener(this);


        File file = new File("d:\\temp\\course");
        File[] tempList = file.listFiles();

        courses = new Vector<Course>();

        for (int i = 0; i< tempList.length;i++) {

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(tempList[i]);
                ObjectInputStream ois = new ObjectInputStream(fis);

                course = (Course) ois.readObject();

                courses.add(course);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Object[] columnTitle= {"课程名称","授课老师","课程类型","课程学分","上课地点"};

//        //表格行对象数据
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

        AddTableRowData data = new AddTableRowData();
        data.addTableRowData(table,courses);

        this.add(scrollpane);
        this.setVisible(true);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                Nametext.setText(String.valueOf(table.getValueAt(row,0)));
            }
        });

    }


    public void actionPerformed(ActionEvent e) {
        String selName = Nametext.getText();

        String cPath  = "d:\\temp\\course\\"+selName+".txt";
        String sPath = "d:\\temp\\"+userName+".txt";

        ObjectInputStream cOis = fileObjectInputStream(cPath);
        ObjectInputStream sOis = fileObjectInputStream(sPath);

        try {
            Course course1 = (Course) cOis.readObject();

            Student student = (Student) sOis.readObject();
            List<Course> sCourse;
            if (student.getSelectCourse() != null) {
                sCourse = student.getSelectCourse();
            }else {
                sCourse = new ArrayList<>();
            }

            sCourse.add(course1);
            student.setSelectCourse(sCourse);

            cOis.close();
            sOis.close();

            writeFile(sPath,student);

            System.out.println(student);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public ObjectInputStream fileObjectInputStream(String path) {

        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void writeFile(String path,Object content) {
        File file = new File(path);

        FileOutputStream   fos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
