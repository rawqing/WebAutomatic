package com.rawq.autoweb.service.imp;

import java.util.Map;

import org.testng.annotations.Test;

public class WElementOperTest {

//  @Test
  public void getPageMap() {
	  WElementOper weo = new WElementOper();
	 // System.out.println(weo.getPageMapList());
	  for(Map<?, ?> map : weo.getPageMapList()){
		  System.out.println(map.get("alias").getClass());
		  System.out.println(map.get("id").getClass());
	  }
	  Map<String, Integer> map = weo.getPageMap();
	  for(String key : map.keySet()){
		  System.out.println(key+ map.get(key));
	  }
  }
  
//  @Test
  public void getElementByIdTest(){
	  WElementOper weo = new WElementOper();
	  System.out.println(weo.getWElementById(2));
	  System.out.println(weo.getWElementById(3));
	  
	  WCaseOper co = new WCaseOper();
	  System.out.println(co.getWCaseById(1));
	  
	  System.out.println(weo.getWElementById(3));
  }
  
  @Test
  public void getElementByNameTest(){
	  WElementOper weo = new WElementOper();
	  System.out.println(weo.getWElementByName("首页", "login"));
	  System.out.println(weo.getWElementByName("登录", "username"));
	  System.out.println(weo.getWElementByName("登录", "pwd"));
  }
}
