package com.qa.Base;


import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
   public  Properties prop;
    public TestBase()
    {
        try{


        prop= new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\Config\\config.properties");
prop.load(file);
            System.out.println(file.toString()  );
        }
        catch (Exception e)
        {
            System.out.println("error is file not found ya fer"+e);
        }
}
}
