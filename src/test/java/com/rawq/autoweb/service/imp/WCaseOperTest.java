package com.rawq.autoweb.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.domain.WSuite;

public class WCaseOperTest {

	private WCaseOper co = new WCaseOper();
	
 // @Test
  public void addWCase() {
	  WStep s = new WStep();
	  
	  WCase c = new WCase();
	  c.setCaseName("reg");
	  c.setPriority(2);
	  c.setAuthor("cc");
	  c.setCaseNumber("RE008");
	  List<WStep> ss = new ArrayList<WStep>();
	  ss.add(s);
	  
	  c.setWSteps(ss);
	  
	  co.addWCase(c);
  }
  
 // @Test
  public void getWCaseBySutieTest(){
	  List<Integer> list = new ArrayList<Integer>();
	  list.add(1);
	  list.add(42);
	  list.add(53);
	  
	  List<WCase> cases = co.getWCaseByIdList(list);
	  System.out.println(cases.size());
	  System.out.println(cases);
	  
  }

 // @Test
  public void addWCaseList() {
    throw new RuntimeException("Test not implemented");
  }

 // @Test
  public void addWSteep() {
    throw new RuntimeException("Test not implemented");
  }

 // @Test
  public void delWSteepById() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getWCaseById() {
	  WCase c = co.getWCaseById(19);
	  System.out.println(c);
	  System.out.println(c.getWDatas().isEmpty());
  }

 // @Test
  public void getWCaseByName() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void getWCaseByVersion() {
    throw new RuntimeException("Test not implemented");
  }
  
 // @Test
  public void getWPlanByid(){
	  System.out.println(co.getWPlanById(2));
  }
  
}
