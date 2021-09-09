package com.awen.education.login;

import com.awen.education.meau.ClassMenu;
import com.awen.education.meau.GradeMenu;
import com.awen.education.meau.StuMenu;

import java.util.Scanner;

/**
 * @Author: awen
 * @Date: 2021/9/3 11:16
 */
public class login {
    public login() throws Exception {
        boolean flag1 = true;
        boolean flag2 = true;
        while (flag1) {
            System.out.println("*************欢迎进入教学管理系统**************");
            System.out.println("===用户登录界面===");
            System.out.println("输入操作命令: 【1】注册 【2】登录  【3】退出");
            Scanner sc = new Scanner(System.in);
            // 录入数据
            String choice = sc.nextLine();
            // 登录和注册都会使用接口实现类中的功能所以将 对象创建在主线程
            UserDao ud = new UserDaoImp();
            // 使用switch语句
            switch (choice) {
                case "1":
                    // 注册
                    System.out.println("请输入用户名:");
                    String newUserName = sc.nextLine();
                    System.out.println("请输入密码:");
                    String newPwd = sc.nextLine();

                    // 将用户名和密码封装User对象
                    User user = new User();
                    user.setName(newUserName);
                    user.setPassword(newPwd);

                    // 调用注册功能
                    boolean a = ud.register(user);
                    if(a){
                        System.out.println("注册成功...");
                    }
                    break;
                case "2":
                    // 登录
                    System.out.println("请输入用户名:");
                    String username = sc.nextLine();
                    System.out.println("请输入密码:");
                    String password = sc.nextLine();
                    // 调用登录功能
                    boolean flag = ud.isLogin(username, password);
                    if (flag) {
                        while(flag2){
                        System.out.println("=======欢迎进入学生管理系统======");
                        System.out.println("输入操作命令: 【1】学生管理模块  【2】班级管理模块  【3】课程管理模块   【4】退出");
                        String choice1 = sc.nextLine();
                        switch (choice1){
                            case "1":
                                new StuMenu();
                                break;
                            case "2":
                                new GradeMenu();
                                break;
                            case "3":
                                new ClassMenu();
                                break;
                            case "4":
                                System.out.println();
                                flag2=false;
                                break;
                            default:
                                System.out.println("输入有误请重试");
                                break;
                        }
                        }

                    } else {
                        // 登录失败
                        System.out.println("用户名或者密码输入错误");
                    }
                    break;
                case "3":
                    System.out.println("谢谢使用,下次再来");
                    System.exit(0);
                default:
                    System.out.println("输入有误请重试");
                    break;
            }
        }

    }
    }
