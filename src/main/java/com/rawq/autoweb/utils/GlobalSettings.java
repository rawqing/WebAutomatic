package com.rawq.autoweb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Element;

public class GlobalSettings {
	private static Properties config = new Properties();
	private static String configFile = GlobalSettings.class.getClassLoader().getResource("config.properties").getPath();
	
	
	public static int CASE_NO=0;
	public static int CASE_PAGE=1;
	public static int CASE_PREMISE=2;
	public static int CASE_MODULE=3;
	public static int CASE_NAME=4;
	public static int CASE_PRIORITY=5;
	public static int CASE_STEPS=6;
	public static int CASE_EXPECTATION=7;
	
	public static int PAGE_ALIAS=0;
	public static int PAGE_TITLE=1;
	public static int PAGE_URI=2;
	
	public static int ELEMENT_PAGE=0;
	public static int ELEMENT_NAME=1;
	public static int ELEMENT_DESC=2;
	public static int ELEMENT_SELECTOR_1=3;
	public static int ELEMENT_PATH_1=4;
	public static int ELEMENT_SELECTOR_2=5;
	public static int ELEMENT_PATH_2=6;
	public static int ELEMENT_SELECTOR_3=7;
	public static int ELEMENT_PATH_3=8;
	
	public static String browserType="firefox";
	public static String browserPath="D:/Program Files (x86)/Mozilla Firefox/firefox.exe";
	public static String browserKey="webdriver.firefox.bin";
	public static String browserClass="org.openqa.selenium.firefox.FirefoxDriver";
	
	public static String zentaoServer="";
	public static String username="";
	public static String password="";
	public static String loginUri="";
	public static String bugUri="";
	public static String imageUri="";
	public static String sendUser="";
	public static String mailCc="";
	
	
	private GlobalSettings(){
		
	}
	
	 static  {    
		 	
		 	
	        InputStream in = null;
			try {
				// read settings file
				in = GlobalSettings.class.getClassLoader().getResourceAsStream("config.properties");
			//	in = new BufferedInputStream(new FileInputStream("configFile"));
				Log.info(GlobalSettings.class,"获取到输入流： "+in);
				// load settings file
				config.load(in);   
				//get property
				Log.info(GlobalSettings.class,"开始读取配置文件: "+configFile);
				
				CASE_NO=Integer.parseInt(config.getProperty("CASE_NO").trim());
				CASE_PAGE=Integer.parseInt(config.getProperty("CASE_PAGE").trim());
				CASE_PREMISE=Integer.parseInt(config.getProperty("CASE_PREMISE").trim());
				CASE_MODULE=Integer.parseInt(config.getProperty("CASE_MODULE").trim());
				CASE_NAME=Integer.parseInt(config.getProperty("CASE_NAME").trim());
				CASE_PRIORITY=Integer.parseInt(config.getProperty("CASE_PRIORITY").trim());
				CASE_STEPS=Integer.parseInt(config.getProperty("CASE_STEPS").trim());
				CASE_EXPECTATION=Integer.parseInt(config.getProperty("CASE_EXPECTATION").trim());
				
				PAGE_ALIAS=Integer.parseInt(config.getProperty("PAGE_ALIAS").trim());
				PAGE_TITLE=Integer.parseInt(config.getProperty("PAGE_TITLE").trim());
				PAGE_URI=Integer.parseInt(config.getProperty("PAGE_URI").trim());
				
				ELEMENT_PAGE=Integer.parseInt(config.getProperty("ELEMENT_PAGE").trim());
				ELEMENT_NAME=Integer.parseInt(config.getProperty("ELEMENT_NAME").trim());
				ELEMENT_DESC=Integer.parseInt(config.getProperty("ELEMENT_DESC").trim());
				ELEMENT_SELECTOR_1=Integer.parseInt(config.getProperty("ELEMENT_SELECTOR_1").trim());
				ELEMENT_PATH_1=Integer.parseInt(config.getProperty("ELEMENT_PATH_1").trim());
				ELEMENT_SELECTOR_2=Integer.parseInt(config.getProperty("ELEMENT_SELECTOR_2").trim());
				ELEMENT_PATH_2=Integer.parseInt(config.getProperty("ELEMENT_PATH_2").trim());
				ELEMENT_SELECTOR_3=Integer.parseInt(config.getProperty("ELEMENT_SELECTOR_3").trim());
				ELEMENT_PATH_3=Integer.parseInt(config.getProperty("ELEMENT_PATH_3").trim());
				
				browserType = config.getProperty("browserType").trim();
				browserPath = config.getProperty("browserPath").trim();
				browserKey = config.getProperty("browserKey").trim();
				browserClass = config.getProperty("browserClass").trim();
				
				zentaoServer = config.getProperty("zentaoServer").trim();
				username = config.getProperty("username").trim();
				password = config.getProperty("password").trim();
				loginUri = config.getProperty("loginUri").trim();
				bugUri = config.getProperty("bugUri").trim();
				imageUri = config.getProperty("imageUri").trim();
				sendUser = config.getProperty("sendUser").trim();
				mailCc = config.getProperty("mailCc","").trim();
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(GlobalSettings.class,"读取配置文件 "+configFile+" 失败 "+e);
			} finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					Log.error(GlobalSettings.class,"关闭 InputStream ："+in+" 异常 。 " +e);
				}
				Log.info(GlobalSettings.class,"结束读取配置文件: "+configFile);
			}
			
	    }    
	
	
}
