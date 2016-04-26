package com.rawq.autoweb.reflex;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class WorkTest {

	 String className = "com.rawq.autoweb.actions.SubmitZenTao";
	 Object[] param = {"立即登录"};
	 
//@Test
  public void executMethod() {
	  String methodName = "linkText";
	  Object[] param = {"立即登录",new HashMap()};
	  
	  Object o = Work.executStaticMethod(className,methodName,param);
	  System.out.println(o.getClass());
	  System.out.println(o);
  }
  
  @Test
  public void getMethod() throws ClassNotFoundException{
	  Class<?> clz = Class.forName(className);
	  String methodName = "submitBug";
	  String str = null;
	  List<Method> a = Work.getMethods(clz,methodName);
	  Method m =   Work.getMethod(clz, methodName,str);
	 
	  System.out.println(m);
  }
  
}
