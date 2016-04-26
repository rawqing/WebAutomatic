package com.rawq.autoweb.utils;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class SendDataTest {

	SendData sd = new SendData();
	
  @Test
  public void readFileT() {
//	  List<Map<String, String>> a = sd.readFile("D:\\rawqingworkspace\\autoweb\\things\\user.csv",false,"\t");
	  
	 Object a =  sd.parseString("\"10000000\"");
	 
	 System.out.println(a.getClass()+"  :  "+a);
  }
}
