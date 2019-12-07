
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public  class DeleteCourse extends JPanel implements ActionListener{
    JLabel Namelabel;
    JTextField Nametext;
    JButton Delbt;
    JTable table;
    Student student;
    int row;

    public DeleteCourse(String name) {

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

        Delbt=new JButton("确认退选");
        Delbt.setSize(90,38);
        Delbt.setLocation(420, 280);
        this.add(Delbt);
        Delbt.addActionListener(this);

        readFile(name);


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

        //创建表格
        final JTable  table=new JTable(model);
        JScrollPane scrollpane=new JScrollPane(table);
        scrollpane.setSize(550,150);
        scrollpane.setLocation(60, 20);
        this.add(scrollpane);

        if (student.getSelectCourse() != null) {
            AddTableRowData data = new AddTableRowData();
            data.addTableRowData(table,student.getSelectCourse());
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();

                Nametext.setText(String.valueOf(table.getValueAt(row,0)));
            }
        });

        this.setVisible(true);
    }

    // 查询学生所选课程
    private void  readFile(String name) {
        File file = new File("d:\\temp\\"+name+".txt");

        student = null;

        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            student = (Student) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write(Student student) {
        File file = new File("d:\\temp\\"+student.getName()+".txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(student);

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String delName=Nametext.getText();

        List<Course> courses = student.getSelectCourse();
        System.out.println(courses);
        courses.remove(row);

        student.setSelectCourse(courses);

        write(student);

    }


}