package com.awen.education.myql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: awen
 * @Date: 2021/9/4 11:12
 */
public class Jdbc {
    public static Connection conn;//�������Ӷ��󣨽�Connection ���ڷ����������������÷�Χ��
    //�������ݿ�ķ���
    public static void connetionDatabse() throws Exception {
        //��properties�ļ��л�ȡ�������ݿ����Ϣ
        //��������
        String driver=PropertiesUtil.drive().getProperty("driver");
        //���ݿ��û���
        String user=PropertiesUtil.drive().getProperty("user");
        //���ݿ�����
        String pwd=PropertiesUtil.drive().getProperty("pwd");
        //���ݿ�url
        String url=PropertiesUtil.drive().getProperty("url");
        //1.��������
        Class.forName(driver);
        conn= DriverManager.getConnection(url,user,pwd);
    }

}
