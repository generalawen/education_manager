package com.awen.education.meau;

import com.awen.education.myql.Jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @Author: awen
 * @Date: 2021/9/4 15:41
 */
public class GradeMenu {
    public GradeMenu() throws Exception {
        int choice;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("----------------1.添加班级----------------");
            System.out.println("----------------2.显示班级----------------");
            System.out.println("----------------3.查找班级----------------");
            System.out.println("----------------4.选择班级----------------");
            System.out.println("----------------0.返回主教学系统-----------");
            System.out.println("请输入你要进行的操作:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    add();
                    break;//添加
                case 2:
                    show();//显示
                    break;
                case 3:
                    find();
                    break;//查找
                case 4:
                    change();
                    break;//选择
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
        String SQL = "select *from grade where number=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("输入要查找的班级号：");
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
        String SQL = "select *from grade";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------所有课班级信息---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(2) +"\t"+ sc.getString(1));
        }
        Jdbc.conn.close();
    }

    private void add() throws Exception {
        Scanner sc=new Scanner(System.in);
        Grade grade =new Grade();
        System.out.println("------------添加班级信息-------------------");
        System.out.println("请输入您要添加的班级编号");
        grade.setNumber(sc.nextInt());
        System.out.println("请输入您要添加的班级名：");
       grade.setName(sc.next());
        Jdbc.connetionDatabse();
        String SQL = "insert  into grade(number, name) values (?,?)";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        ps.setInt(1,grade.getNumber());
        ps.setString(2,grade.getName());
        if(ps.executeUpdate()>0){
            System.out.println("添加成功!");
        }else{
            System.out.println("添加失败!");
        }
        Jdbc.conn.close();
    }
}
