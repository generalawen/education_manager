package com.awen.education.meau;

import com.awen.education.myql.Jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @Author: awen
 * @Date: 2021/9/3 20:30
 */
public class StuMenu {
    public StuMenu() throws Exception {
        int choice;
        boolean flag=true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("----------------1.添加学生----------------");
            System.out.println("----------------2.显示学生----------------");
            System.out.println("----------------3.修改学生----------------");
            System.out.println("----------------4.删除学生----------------");
            System.out.println("----------------5.查询学生----------------");
            System.out.println("----------------6.学生排序----------------");
            System.out.println("----------------7.查找成绩高于平均分学生-----");
            System.out.println("----------------0.返回主教学系统-----------");
            System.out.println("请输入你要进行的操作:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    add();
                    break;//添加学生信息
                case 2:
                    show();//显示学生
                    break;
                case 3:
                    change();
                    break;//修改学生信息
                case 4:
                    delete();
                    break;//删除学生信息
                case 5:
                    find();
                    break;//查询学生信息
                case 6:
                    sort();//排序
                    break;
                case 7:
                   show_Max();
                    break;
                case 0:
                    flag = false;
            }
        }
    }

    private void show_Max() throws Exception {
        Jdbc.connetionDatabse();
        String SQL = "select  name from students where score >(select avg(score) from students) ";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------高于平均分的学生-------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getString(1));
        }
        Jdbc.conn.close();
    }

    private void sort() throws Exception {
        Jdbc.connetionDatabse();
        String SQL = "select *from students order by score";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------成绩排序---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(1) +"\t"+ sc.getString(2)+"\t"+sc.getInt(3) +"\t"+sc.getInt(4) );
        }
        Jdbc.conn.close();

    }

    private void find() throws Exception {//查找学生
        Jdbc.connetionDatabse();
        Scanner sc=new Scanner(System.in);
        String SQL = "select *from students where id=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("输入要查找的学生号：");
        int id = sc.nextInt();
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getInt(1)  +"\t"+ rs.getString(2) +"\t"+rs.getInt(3)  +"\t"+rs.getInt(4) );
        }
        Jdbc.conn.close();
    }

    private void delete() throws Exception {
        Jdbc.connetionDatabse();
        Scanner sc=new Scanner(System.in);
        String SQL = "delete from students where id=?";
        PreparedStatement ps2 = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------删除学生信息---------------------");
        System.out.println("请输入您要删除的学生学号");
        int id=sc.nextInt();
        ps2.setInt(1,id);
        if(ps2.executeUpdate()>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
        Jdbc.conn.close();
    }

    private void change() throws Exception {//修改数据
        Jdbc.connetionDatabse();
        String SQL = "update students set name=?,age=?,score=? where id=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        Scanner sc=new Scanner(System.in);
        System.out.println("------------添加学生信息---------------------");
        System.out.println("请输入您要修改的学生学号");
        int id=sc.nextInt();
        System.out.println("请输入您要修改的学生姓名：");
        String Name=sc.next();
        System.out.println("请输入您要修改的学生年龄：");
        int age=sc.nextInt();
        System.out.println("请输入您要修改的学生成绩：");
        int score=sc.nextInt();
        ps.setInt(4,id);
        ps.setString(1,Name);
        ps.setInt(2,age);
        ps.setInt(3,score);
        if(ps.executeUpdate()>0){
            System.out.println("添加成功!");
        }else{
            System.out.println("添加失败!");
        }
        Jdbc.conn.close();
    }

    private void show() throws Exception {//显示所有学生
        Jdbc.connetionDatabse();
        String SQL = "select *from students";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------所有学生信息---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(1) +"\t"+ sc.getString(2)+"\t"+sc.getInt(3) +"\t"+sc.getInt(4) );
        }
        Jdbc.conn.close();
    }

    private void add() throws Exception {//添加
        Scanner sc=new Scanner(System.in);
        Student stu = new Student();
        System.out.println("------------添加学生信息---------------------");
        System.out.println("请输入您要添加的学生学号");
        stu.setId(sc.nextInt());
        System.out.println("请输入您要添加的学生姓名：");
        String Name=sc.next();
        System.out.println("请输入您要添加的学生年龄：");
        int age=sc.nextInt();
        System.out.println("请输入您要添加的学生成绩：");
        int score=sc.nextInt();
        Jdbc.connetionDatabse();
        String SQL = "insert  into students(id, name, age, score) values (?,?,?,?)";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        ps.setInt(1,stu.getId());
        ps.setString(2,Name);
        ps.setInt(3,age);
        ps.setInt(4,score);
        if(ps.executeUpdate()>0){
            System.out.println("添加成功!");
        }else{
            System.out.println("添加失败!");
        }
        Jdbc.conn.close();
 }

}

