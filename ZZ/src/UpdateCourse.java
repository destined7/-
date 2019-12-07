
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public  class UpdateCourse extends JPanel implements ActionListener {

    JLabel Namelabel;
    JLabel Typelabel;
    JLabel Teacherlabel;
    JLabel Creditlabel;
    JTextField Nametext;
    JTextField Teachertext;
    JTextField Credittext;
    JComboBox<String> Typecom;
    JButton Updatebt;
    JScrollPane scrollpane;
    JTable table;

    public UpdateCourse() {
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

        Updatebt=new JButton("修改");
        Updatebt.setSize(80,30);
        Updatebt.setLocation(350, 80);
        this.add( Updatebt);
        Updatebt.addActionListener(this);

        Creditlabel=new JLabel("请输入课程学分");
        Creditlabel.setSize(100,30);
        Creditlabel.setLocation(60, 140);
        this.add(Creditlabel);

        Credittext=new JTextField();
        Credittext.setSize(120,30);
        Credittext.setLocation(180, 140);
        this.add(Credittext);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String updateName=Nametext.getText();
        String updateType=(String) Typecom.getSelectedItem();
        String updateTeacher=Teachertext.getText();
        String updateCredit=Credittext.getText();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //加载对应的jdbc驱动
            String url="jdbc:sqlserver://localhost:1433; DatabaseName=StudentCourse";
            //配置连接字符串
            String user="sa";//sa超级管理员
            String password="2386180";//密码
            Connection conn=DriverManager.getConnection(url,user,password);
            //创建数据库连接对象
            Statement st=conn.createStatement();
            //创建SQL语句执行对象
            String  strSQL1="update dbo.Table_2 set CourseType='"+updateType+"' where CourseName='"+updateName+"'";
            String  strSQL2="update dbo.Table_2 set CourseTeacher='"+updateTeacher+"' where CourseName='"+updateName+"'";
            String  strSQL3="update dbo.Table_2 set CourseCredit='"+updateCredit+"' where CourseName='"+updateName+"'";

            int rs1=st.executeUpdate(strSQL1);
            int rs2=st.executeUpdate(strSQL2);
            int rs3=st.executeUpdate(strSQL3);
            if(rs1==1&&rs2==1&&rs3==1) {
                JOptionPane.showMessageDialog(null,"课程修改成功");
            }
            else{
                JOptionPane.showMessageDialog(null,"课程修改失败");
            }
            conn.close();

            //关闭数据库连接
        }
        catch (ClassNotFoundException ex) {
            System.out.println("没有找到对应的数据库驱动类");
        }
        catch (SQLException ex) {
            System.out.println("数据库连接或者是数据库操作失败");
        }

    }

}