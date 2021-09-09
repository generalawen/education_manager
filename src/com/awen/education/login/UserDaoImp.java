package com.awen.education.login;
import java.io.*;

/**
 * @Author: awen
 * @Date: 2021/9/3 9:25
 */
public class UserDaoImp implements UserDao {

        // �����ļ�
        private static File file = new File("user.txt");

        static {
            if (file != null) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("�����ļ�ʧ��...");
                }
            }
        }
        static boolean StringSearchLine(String strDst) {
            boolean out = false;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = "";                            //�ݴ�br��ȡ����ÿһ���ַ���
                while ((line = br.readLine()) != null) {
                    // �ַ����ķָ��
                    String[] datas = line.split("=");
                    // �ж�
                    if (datas[0].equals(strDst) ) {
                        out = true;
                        break;
                    }
                }
                br.close();                                //�������ļ��ķ���
            } catch (Exception e) {                            //�����쳣������쳣��
                e.printStackTrace();
            }
            return out;                                    //���ز�ѯ���
        }
        // ��¼
        @Override
        public boolean isLogin(String username, String password) {

            boolean flag = false;

            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                // һ�ζ�ȡһ��
                String line = null;
                while ((line = br.readLine()) != null) {
                    // �ַ����ķָ��
                    String[] datas = line.split("=");
                    // �ж�
                    if (datas[0].equals(username) && datas[1].equals(password)) {
                        flag = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("�Ҳ����ļ�");
            } catch (IOException e) {
                System.out.println("��¼ʧ��");
            } finally {
                if (br != null) {
                    try {
                        // �ͷ���Դ
                        br.close();
                    } catch (IOException e) {
                        System.out.println("��¼ʱ�ͷ���Դʧ��");
                    }
                }
            }
            return flag;

        }

        // ע�Ṧ��
        @Override
        public boolean register(User user) {
            // ����Ĺ���:�û���=����

            boolean flag = false;
            BufferedWriter bw = null;
            try {
                if(StringSearchLine(user.getName())){
                System.out.println("�û����Ѿ�����");
                 }else{

                    bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(user.getName() + "=" + user.getPassword());
                    bw.newLine();
                    bw.flush();
                    flag = true;
                    }
            } catch (IOException e) {
                System.out.println("�û�ע��ʧ��...");
            } finally {

                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        System.out.println("ע��ʱ�ͷŵ���Դʧ��");
                    }
                }
            }
            return flag;

        }

    }

