
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public  class AddCourse extends JPanel implements ActionListener{
    JLabel Namelabel;
    JLabel Typelabel;
    JLabel Teacherlabel;
    JLabel Creditlabel;
    JLabel CourseAddress;
    JTextField Nametext;
    JTextField Teachertext;
    JTextField Credittext;
    JTextField CourseAddressText;
    JComboBox<String> Typecom;
    JButton Addbt;
    JScrollPane scrollpane;
    JTable table;


    public AddCourse() {
        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);


        Namelabel=new JLabel("请输入课程名称");
        Namelabel.setSize(100,30);
        Namelabel.setLocation(60, 20);

        this.add(Namelabel);

        Nametext=new JTextField();
        Nametext.setSize(120,30);
        Nametext.setLocation(180, 20);
        this.add(Nametext);

        Teacherlabel=new JLabel("请输入授课教师");
        Teacherlabel.setSize(100,30);
        Teacherlabel.setLocation(60, 60);
        this.add(Teacherlabel);

        Teachertext=new JTextField();
        Teachertext.setSize(120,30);
        Teachertext.setLocation(180, 60);
        this.add(Teachertext);

        Typelabel=new JLabel("请选择课程类型");
        Typelabel.setSize(100,30);
        Typelabel.setLocation(60, 100);
        this.add(Typelabel);

        Typecom=new JComboBox<String>();
        Typecom.setSize(120,30);
        Typecom.setLocation(180, 100);
        Typecom.addItem("必修课");
        Typecom.addItem("选修课");
        this.add(Typecom);

        Creditlabel=new JLabel("请输入课程学分");
        Creditlabel.setSize(100,30);
        Creditlabel.setLocation(60, 140);
        this.add(Creditlabel);

        Credittext=new JTextField();
        Credittext.setSize(120,30);
        Credittext.setLocation(180, 140);
        this.add(Credittext);
        this.setVisible(true);

        CourseAddress=new JLabel("上课地点");
        CourseAddress.setSize(100,30);
        CourseAddress.setLocation(60, 180);
        this.add(CourseAddress);

        CourseAddressText=new JTextField();
        CourseAddressText.setSize(120,30);
        CourseAddressText.setLocation(180, 180);
        this.add(CourseAddressText);
        this.setVisible(true);

        Addbt=new JButton("添加");
        Addbt.setSize(80,30);
        Addbt.setLocation(350, 80);
        this.add(Addbt);
        Addbt.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String addName=Nametext.getText();
        String addType=(String) Typecom.getSelectedItem();
        String addTeacher=Teachertext.getText();
        String addCredit=Credittext.getText();
        String addCourseAddress = CourseAddressText.getText();


        File file = new File("d:\\temp\\course");
        // 如果不存在创建文件夹

            file.mkdirs();



        // 创建文件
        File writeFile = new File("d:\\temp\\course\\"+addName+".txt");
        try {
            if(!writeFile.exists()) {
                writeFile.createNewFile();
            }else {
                JOptionPane.showMessageDialog(null, "课程存在");
                return;
            }
        } catch (IOException ex) {

        }

        try {

            FileOutputStream fos=new FileOutputStream(writeFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Course course = new Course();

            course.setCourseName(addName);
            course.setTeacher(addTeacher);
            course.setCourseScore(addCredit);
            course.setCourseType(addType);
            course.setCourseAddress(addCourseAddress);

            oos.writeObject(course);
            oos.close();

            Nametext.setText("");
            Teachertext.setText("");
            Credittext.setText("");
            CourseAddressText.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
