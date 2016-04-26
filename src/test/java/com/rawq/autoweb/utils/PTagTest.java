package com.rawq.autoweb.utils;

import java.util.EnumMap;

import org.testng.annotations.Test;

import com.rawq.autoweb.enums.EnVar;
import com.rawq.autoweb.enums.PTag;
import com.test.p1.A;

public class PTagTest {

  //@Test
  public void PTag() {
	  System.out.println(PTag.expression.getTag());
  }
  
  @Test
  public void envar(){
//	  System.out.println(EnVar.$CUSTOM.getValue());
//	  setvalue();
//	  System.out.println(EnVar.$CUSTOM.getValue());
//	  EnumMap<EnVar, String> em = new EnumMap<>(EnVar.class);
//	  
	  System.out.println(EnVar.valueOf("$LAST_RES1"));
	  
	 
	  String a = EnVar.valueOf("$LAST_RES1").getValue();
	 
	  System.out.println(a);
  }
  
  public void setvalue(){
	  EnVar.$CUSTOM.setValue("hello");
  }
  public void say(Enum []  o){
	 
	  for(Enum e : o){
		  System.out.println();
	  }
  }
}
