package com.rawq.autoweb.utils;

import org.testng.annotations.Test;


public class SubSingTest {

  @Test
  public void getSubString() {
	  String str = "{#$slfjsl}";
	  String a = SubString.getSubString(str, "{", "}");
	  System.out.println(a);
  }
}
