package com.rawq.autoweb.utils;

import java.util.Random;

import org.testng.annotations.Test;

public class BshTest {

	Random random = new Random();
  //@Test
  public void getResStringMapStringObject() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getResString() {
	 System.out.println(Bsh.getRes("true !"));
  }
}
