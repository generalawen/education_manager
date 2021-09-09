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
            System.out.println("----------------1.��ӿγ�----------------");
            System.out.println("----------------2.��ʾ�γ�----------------");
            System.out.println("----------------3.���ҿγ�----------------");
            System.out.println("----------------4.ѡ��γ�----------------");
            System.out.println("----------------0.��������ѧϵͳ-----------");
            System.out.println("��������Ҫ���еĲ���:");
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
        System.out.println("ѡ��ɹ�");
    }

    private void find() throws Exception {
        Jdbc.connetionDatabse();
        Course course = new Course();
        Scanner sc=new Scanner(System.in);
        String SQL = "select *from course where number=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("����Ҫ���ҵĿγ̺ţ�");
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
        System.out.println("------------���пγ���Ϣ---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(1) +"\t"+ sc.getString(2));
        }
        Jdbc.conn.close();
    }

    private void add() throws Exception {
        Scanner sc=new Scanner(System.in);
        Course course = new Course();
        System.out.println("------------��ӿγ���Ϣ-------------------");
        System.out.println("��������Ҫ��ӵĿγ̱��");
        course.setNumber(sc.nextInt());
        System.out.println("��������Ҫ��ӵĿγ�����");
        course.setName(sc.next());
        Jdbc.connetionDatabse();
        String SQL = "insert  into course(number, name) values (?,?)";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        ps.setInt(1,course.getNumber());
        ps.setString(2,course.getName());
        if(ps.executeUpdate()>0){
            System.out.println("��ӳɹ�!");
        }else{
            System.out.println("���ʧ��!");
        }
        Jdbc.conn.close();
    }


}

