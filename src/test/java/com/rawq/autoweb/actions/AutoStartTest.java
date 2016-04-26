package com.rawq.autoweb.actions;

import org.testng.annotations.Test;

public class AutoStartTest {

	AutoStart as = new AutoStart("http://192.168.1.251/");
	
  @Test
  public void run() {
	  as.run(2, "1");
  }
}
