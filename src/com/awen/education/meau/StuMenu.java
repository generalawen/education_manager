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
            System.out.println("----------------1.���ѧ��----------------");
            System.out.println("----------------2.��ʾѧ��----------------");
            System.out.println("----------------3.�޸�ѧ��----------------");
            System.out.println("----------------4.ɾ��ѧ��----------------");
            System.out.println("----------------5.��ѯѧ��----------------");
            System.out.println("----------------6.ѧ������----------------");
            System.out.println("----------------7.���ҳɼ�����ƽ����ѧ��-----");
            System.out.println("----------------0.��������ѧϵͳ-----------");
            System.out.println("��������Ҫ���еĲ���:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    add();
                    break;//���ѧ����Ϣ
                case 2:
                    show();//��ʾѧ��
                    break;
                case 3:
                    change();
                    break;//�޸�ѧ����Ϣ
                case 4:
                    delete();
                    break;//ɾ��ѧ����Ϣ
                case 5:
                    find();
                    break;//��ѯѧ����Ϣ
                case 6:
                    sort();//����
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
        System.out.println("------------����ƽ���ֵ�ѧ��-------------");
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
        System.out.println("------------�ɼ�����---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(1) +"\t"+ sc.getString(2)+"\t"+sc.getInt(3) +"\t"+sc.getInt(4) );
        }
        Jdbc.conn.close();

    }

    private void find() throws Exception {//����ѧ��
        Jdbc.connetionDatabse();
        Scanner sc=new Scanner(System.in);
        String SQL = "select *from students where id=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("����Ҫ���ҵ�ѧ���ţ�");
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
        System.out.println("------------ɾ��ѧ����Ϣ---------------------");
        System.out.println("��������Ҫɾ����ѧ��ѧ��");
        int id=sc.nextInt();
        ps2.setInt(1,id);
        if(ps2.executeUpdate()>0){
            System.out.println("ɾ���ɹ���");
        }else{
            System.out.println("ɾ��ʧ�ܣ�");
        }
        Jdbc.conn.close();
    }

    private void change() throws Exception {//�޸�����
        Jdbc.connetionDatabse();
        String SQL = "update students set name=?,age=?,score=? where id=?";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        Scanner sc=new Scanner(System.in);
        System.out.println("------------���ѧ����Ϣ---------------------");
        System.out.println("��������Ҫ�޸ĵ�ѧ��ѧ��");
        int id=sc.nextInt();
        System.out.println("��������Ҫ�޸ĵ�ѧ��������");
        String Name=sc.next();
        System.out.println("��������Ҫ�޸ĵ�ѧ�����䣺");
        int age=sc.nextInt();
        System.out.println("��������Ҫ�޸ĵ�ѧ���ɼ���");
        int score=sc.nextInt();
        ps.setInt(4,id);
        ps.setString(1,Name);
        ps.setInt(2,age);
        ps.setInt(3,score);
        if(ps.executeUpdate()>0){
            System.out.println("��ӳɹ�!");
        }else{
            System.out.println("���ʧ��!");
        }
        Jdbc.conn.close();
    }

    private void show() throws Exception {//��ʾ����ѧ��
        Jdbc.connetionDatabse();
        String SQL = "select *from students";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        System.out.println("------------����ѧ����Ϣ---------------");
        ResultSet sc = ps.executeQuery();
        while(sc.next()) {
            System.out.println(sc.getInt(1) +"\t"+ sc.getString(2)+"\t"+sc.getInt(3) +"\t"+sc.getInt(4) );
        }
        Jdbc.conn.close();
    }

    private void add() throws Exception {//���
        Scanner sc=new Scanner(System.in);
        Student stu = new Student();
        System.out.println("------------���ѧ����Ϣ---------------------");
        System.out.println("��������Ҫ��ӵ�ѧ��ѧ��");
        stu.setId(sc.nextInt());
        System.out.println("��������Ҫ��ӵ�ѧ��������");
        String Name=sc.next();
        System.out.println("��������Ҫ��ӵ�ѧ�����䣺");
        int age=sc.nextInt();
        System.out.println("��������Ҫ��ӵ�ѧ���ɼ���");
        int score=sc.nextInt();
        Jdbc.connetionDatabse();
        String SQL = "insert  into students(id, name, age, score) values (?,?,?,?)";
        PreparedStatement ps = Jdbc.conn.prepareStatement(SQL);
        ps.setInt(1,stu.getId());
        ps.setString(2,Name);
        ps.setInt(3,age);
        ps.setInt(4,score);
        if(ps.executeUpdate()>0){
            System.out.println("��ӳɹ�!");
        }else{
            System.out.println("���ʧ��!");
        }
        Jdbc.conn.close();
 }

}

