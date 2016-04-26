package com.rawq.autoweb.utils;

import org.testng.annotations.Test;

public class ImageFindTest {

  @Test
  public void findImage() {
	  String keyImagePath = "D:\\1111.png";
      String oldImagePath = "d:\\1.png";
      ImageFind demo = new ImageFind();
      String a = demo.findImage(keyImagePath, oldImagePath);
      System.out.println(a);
  }
}
