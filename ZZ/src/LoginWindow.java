
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public  class LoginWindow extends JFrame {
    //标签
    private JLabel lable1;
    private JLabel lable2;
    //文本框
    private JTextField text1;
    private JPasswordField text2;
    //按钮
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;

    //构造函数
    public LoginWindow() {
        this.init();
        this.addComponent();
        this.addListener();
    }

    public void init() {
        this.setSize(500, 400);
        this.setVisible(true);
        this.setTitle("登录界面");
        this.setLayout(null);
        this.setLocation(700, 300);
    }

    private void addComponent() {
        lable1 = new JLabel("用户名");
        lable1.setSize(100, 70);
        lable1.setLocation(100, 80);
        this.add(lable1);
        lable2 = new JLabel("密    码");
        lable2.setSize(100, 70);
        lable2.setLocation(100, 130);
        this.add(lable2);

        text1 = new JTextField();
        text1.setSize(150, 30);
        text1.setLocation(160, 100);
        this.add(text1);
        text2 = new JPasswordField();
        text2.setSize(150, 30);
        text2.setLocation(160, 150);
        this.add(text2);

        bt1 = new JButton("登录");
        bt1.setSize(70, 30);
        bt1.setLocation(140, 195);
        this.add(bt1);
        bt2 = new JButton("注册");
        bt2.setSize(70, 30);
        bt2.setLocation(250, 195);
        this.add(bt2);
        bt3 = new JButton("确定");
        bt3.setSize(70,30);
        bt3.setLocation(200,195);
        bt3.hide();
        this.add(bt3);
        this.setBackground(Color.blue);

    }

    private void addListener() {
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                String path = "d:\\temp\\" + text1.getText() + ".txt";
                File file = new File(path);
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "用户不存在");
                    return;
                }


                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    student = (Student) ois.readObject();
                    ois.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                if (student.getName().equals(text1.getText()) && student.getPassword().equals(String.valueOf(text2.getPassword()))) {
                    new MainWindow(text1.getText());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "登陆密码错误");
                }
            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bt2.hide();
                bt1.hide();
                bt3.show();

                text1.setText("");
                text2.setText("");
            }
        });

        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	File fileM = new File("d:\\temp\\course");
                // 如果不存在创建文件夹
            	if (!fileM.exists()) {
            		fileM.mkdirs();
            	}
                
            	
                File file = new File("d:\\temp\\"+text1.getText() + ".txt");
                // 如果不存在创建
                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    System.out.println("文件存在");
                    return;
                }

                try {

                    FileOutputStream fos=new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    Student student = new Student();

                    student.setName(text1.getText());
                    student.setPassword(text2.getText());

                    oos.writeObject(student);
                    oos.close();

                    bt1.show();
                    bt2.show();
                    bt3.hide();

                    text1.setText("");
                    text2.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}