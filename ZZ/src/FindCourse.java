
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public  class FindCourse extends JPanel implements ActionListener{
    JLabel Inputlabel;
    JTextField Inputtext;
    JLabel CourseAddress;
    JTextField CourseAddressText;
    JButton Findbt;
    JLabel Namelabel;
    JLabel Typelabel;
    JLabel Teacherlabel;
    JLabel Creditlabel;
    JTextField Nametext;
    JTextField Teachertext;
    JTextField Credittext;
    JTextField Typetext;

    public FindCourse() {

        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);



        CourseAddress=new JLabel("上课地点");
        CourseAddress.setSize(100,30);
        CourseAddress.setLocation(100, 120);
        this.add(CourseAddress);

        CourseAddressText=new JTextField();
        CourseAddressText.setSize(120,30);
        CourseAddressText.setLocation(220, 120);
        CourseAddressText.disable();
        this.add(CourseAddressText);

        Teacherlabel=new JLabel("授课教师");
        Teacherlabel.setSize(100,30);
        Teacherlabel.setLocation(100, 160);
        this.add(Teacherlabel);

        Teachertext=new JTextField();
        Teachertext.setSize(120,30);
        Teachertext.setLocation(220, 160);
        Teachertext.disable();
        this.add(Teachertext);

        Typelabel=new JLabel("课程类型");
        Typelabel.setSize(100,30);
        Typelabel.setLocation(100, 200);
        this.add(Typelabel);

        Typetext=new JTextField();
        Typetext.setSize(120, 30);
        Typetext.setLocation(220, 200);
        Typetext.disable();
        this.add(Typetext);

        Creditlabel=new JLabel("课程学分");
        Creditlabel.setSize(100,30);
        Creditlabel.setLocation(100, 240);
        this.add(Creditlabel);

        Credittext=new JTextField();
        Credittext.setSize(120, 30);
        Credittext.setLocation(220, 240);
        Credittext.disable();
        this.add(Credittext);

        Inputlabel=new JLabel("请输入课程名称");
        Inputlabel.setSize(150,50);
        Inputlabel.setLocation(100, 45);
        this.add(Inputlabel);

        Inputtext=new JTextField();
        Inputtext.setSize(160,40);
        Inputtext.setLocation(200, 45);
        this.add(Inputtext);

        Findbt=new JButton("查询");
        Findbt.setSize(90,38);
        Findbt.setLocation(420, 45);
        this.add(Findbt);
        Findbt.addActionListener(this);

        this.setVisible(true);
    }



    public void actionPerformed(ActionEvent e) {

        String inputName=Inputtext.getText();

        Course course = new Course();
        String path = "d:\\temp\\course\\" + inputName + ".txt";
        File file = new File(path);
        if (!file.exists()) {
            Teachertext.setText("");
            Typetext.setText("");
            Credittext.setText("");
            CourseAddressText.setText("");
            JOptionPane.showMessageDialog(null, "课程不存在");
            return;
        }


        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            course = (Course) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (course != null) {
            Teachertext.setText(course.getTeacher());
            Typetext.setText(course.getCourseType());
            Credittext.setText(course.getCourseScore());
            CourseAddressText.setText(course.getCourseAddress());
        } else {
            JOptionPane.showMessageDialog(null, "没有课程");
        }


    }

}