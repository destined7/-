# -
JAVA实验报告

班级：计G191
姓名 : 周政
学号：2019322043

实验目的:
分析学生选课系统
使用GUI窗体及其组件设计窗体界面
完成学生选课过程业务逻辑编程
基于文件保存并读取数据
处理异常

要求：
1.设计GUI窗体，支持学生注册、课程新加、学生选课退课查课
2.基于事件模型对业务逻辑编程，实现在界面上支持上述操作
3.针对操作过程出现的异常设计异常处理程序
4.基于Github.com提交


实验过程：
使用实验2的学生老师相关数据和选课退课查课相应的布局
参考了代码，重新做了登录注册窗口和选课窗口，可以正常运行（不注册不可以登录）
实现选课退课查课的功能能对应到每门课的老师，均有异常处理
文件流保存在d盘temp下
运行结果


实验流程图：使用之前实验的相关数据-----设计GUI登录窗体和选课窗体-----实现GUI窗体的对应功能，如登录注册选课退课等--
---选课退课查课功能中添加异常处理程序-----文件保存在本地D盘中可保存和读取-----输出结果

核心代码：
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
   
运行截图：

编程感想：
Java课程临近尾声，这是最后一次实验。
基于之前实验要给学生选课系统各个组件添加相应的功能。
我基本是参照老师给的代码和每一块功能实现参考广大网友的经验把它们整合起来。
说实话，代码分开来我可以理解清楚和写出来，如何组合在一起并且实现功能这是不懂也不会的地方。
通过这次实验得知了自己的jre过于远古（1.5版本）以至于在这里卡了半天和通过properties解决文字乱码问题（因为乱码的代码是截的，都看了）
java课程结束了，但对java的学习才刚刚开始（通过这次好像感觉也不是很难，或许）。
            
