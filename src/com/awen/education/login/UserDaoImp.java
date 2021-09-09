package com.awen.education.login;
import java.io.*;

/**
 * @Author: awen
 * @Date: 2021/9/3 9:25
 */
public class UserDaoImp implements UserDao {

        // 创建文件
        private static File file = new File("user.txt");

        static {
            if (file != null) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("创建文件失败...");
                }
            }
        }
        static boolean StringSearchLine(String strDst) {
            boolean out = false;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = "";                            //暂存br中取出的每一行字符串
                while ((line = br.readLine()) != null) {
                    // 字符串的分割功能
                    String[] datas = line.split("=");
                    // 判断
                    if (datas[0].equals(strDst) ) {
                        out = true;
                        break;
                    }
                }
                br.close();                                //结束对文件的访问
            } catch (Exception e) {                            //处理异常（输出异常）
                e.printStackTrace();
            }
            return out;                                    //返回查询结果
        }
        // 登录
        @Override
        public boolean isLogin(String username, String password) {

            boolean flag = false;

            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                // 一次读取一行
                String line = null;
                while ((line = br.readLine()) != null) {
                    // 字符串的分割功能
                    String[] datas = line.split("=");
                    // 判断
                    if (datas[0].equals(username) && datas[1].equals(password)) {
                        flag = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("找不到文件");
            } catch (IOException e) {
                System.out.println("登录失败");
            } finally {
                if (br != null) {
                    try {
                        // 释放资源
                        br.close();
                    } catch (IOException e) {
                        System.out.println("登录时释放资源失败");
                    }
                }
            }
            return flag;

        }

        // 注册功能
        @Override
        public boolean register(User user) {
            // 定义的规则:用户名=密码

            boolean flag = false;
            BufferedWriter bw = null;
            try {
                if(StringSearchLine(user.getName())){
                System.out.println("用户名已经存在");
                 }else{

                    bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(user.getName() + "=" + user.getPassword());
                    bw.newLine();
                    bw.flush();
                    flag = true;
                    }
            } catch (IOException e) {
                System.out.println("用户注册失败...");
            } finally {

                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        System.out.println("注册时释放的资源失败");
                    }
                }
            }
            return flag;

        }

    }

