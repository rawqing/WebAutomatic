package com.test.p1;

import org.testng.annotations.Test;

public class BTest {

  @Test
  public void init() {
	  B b = new B();
	  b.setS("dsfsd");
	  System.out.println(b.s);
  }
}
