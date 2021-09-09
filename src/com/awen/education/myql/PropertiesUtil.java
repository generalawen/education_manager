package com.awen.education.myql;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * @Author: awen
 * @Date: 2021/9/4 11:02
 */
public class PropertiesUtil {
        public static Properties drive() throws Exception {
            File file = new File("connection.properties");
            FileInputStream fis = new FileInputStream(file);
            Properties p = new Properties();
            p.load(fis);
            return  p;
        }
}
