package com.awen.education.meau;

import com.awen.education.myql.Jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @Author: awen
 * @Date: 2021/9/4 10:49
 */
public class ClassMenu {
    public ClassMenu() throws Exception {
        int choice;
        boolean flag=true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("----------------1.添加课程----------------");
            System.out.println("----------------2.显示课程----------------");
            System.out.println("----------------3.查找课程----------------");
            System.out.println("----------------4.选择课程----------------");
            System.out.println("----------------0.返回主教学系统-----------");
            System.out.println("请输入你要进行的操作:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    find();
                    break;
                case 4:
                    change();
                    break;
                case 0:
                    flag = false;
            }
        }
    }

    private void change() {
        System.out.println("选择成功");
    }

    private void find() throws Exception {
        Jdbc.connetionDatabse();
        Course course = new Course();
        Scanner sc=new Scanner(System.in);
        String SQL = "select *from course where number=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("输入要查找的课程号：");
        course.setNumber(sc.nextInt());
        ps.setInt(1,course.getNumber());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getInt(1)  +"\t"+ rs.getString(2));
        }
        Jdbc.conn.close();
    }

    private void show() throws Exception {
        Jdbc.connetionDatabse();
        String SQL = "select *from course";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------所有课程信息---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(1) +"\t"+ sc.getString(2));
        }
        Jdbc.conn.close();
    }

    private void add() throws Exception {
        Scanner sc=new Scanner(System.in);
        Course course = new Course();
        System.out.println("------------添加课程信息-------------------");
        System.out.println("请输入您要添加的课程编号");
        course.setNumber(sc.nextInt());
        System.out.println("请输入您要添加的课程名：");
        course.setName(sc.next());
        Jdbc.connetionDatabse();
        String SQL = "insert  into course(number, name) values (?,?)";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        ps.setInt(1,course.getNumber());
        ps.setString(2,course.getName());
        if(ps.executeUpdate()>0){
            System.out.println("添加成功!");
        }else{
            System.out.println("添加失败!");
        }
        Jdbc.conn.close();
    }


}

