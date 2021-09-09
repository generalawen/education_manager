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
            System.out.println("*************��ӭ�����ѧ����ϵͳ**************");
            System.out.println("===�û���¼����===");
            System.out.println("�����������: ��1��ע�� ��2����¼  ��3���˳�");
            Scanner sc = new Scanner(System.in);
            // ¼������
            String choice = sc.nextLine();
            // ��¼��ע�ᶼ��ʹ�ýӿ�ʵ�����еĹ������Խ� ���󴴽������߳�
            UserDao ud = new UserDaoImp();
            // ʹ��switch���
            switch (choice) {
                case "1":
                    // ע��
                    System.out.println("�������û���:");
                    String newUserName = sc.nextLine();
                    System.out.println("����������:");
                    String newPwd = sc.nextLine();

                    // ���û����������װUser����
                    User user = new User();
                    user.setName(newUserName);
                    user.setPassword(newPwd);

                    // ����ע�Ṧ��
                    boolean a = ud.register(user);
                    if(a){
                        System.out.println("ע��ɹ�...");
                    }
                    break;
                case "2":
                    // ��¼
                    System.out.println("�������û���:");
                    String username = sc.nextLine();
                    System.out.println("����������:");
                    String password = sc.nextLine();
                    // ���õ�¼����
                    boolean flag = ud.isLogin(username, password);
                    if (flag) {
                        while(flag2){
                        System.out.println("=======��ӭ����ѧ������ϵͳ======");
                        System.out.println("�����������: ��1��ѧ������ģ��  ��2���༶����ģ��  ��3���γ̹���ģ��   ��4���˳�");
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
                                System.out.println("��������������");
                                break;
                        }
                        }

                    } else {
                        // ��¼ʧ��
                        System.out.println("�û������������������");
                    }
                    break;
                case "3":
                    System.out.println("ллʹ��,�´�����");
                    System.exit(0);
                default:
                    System.out.println("��������������");
                    break;
            }
        }

    }
    }
