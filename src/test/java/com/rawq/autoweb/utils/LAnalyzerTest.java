package com.rawq.autoweb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.rawq.autoweb.domain.Token;

public class LAnalyzerTest {

	List<Map<String ,String>> list = new ArrayList<Map<String ,String>>();
	Map<String ,String> map = new HashMap<String,String>();
	String sa = "";
	String str = " not show index page and ({123} in loginname input or (show login page and {$user}in loginuser input))";
	
			
	LAnalyzer ll = new LAnalyzer();
	Parser pa = new Parser();
	
	@Test
	public void testP2(){
	//	str = "show loginbt button or not show aaa input and 123 in loginname input";
		List<Token> list = pa.getTokens(str);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(Token t : list){
			System.out.println(t.toString());
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		boolean a = false;
		try {
			a = pa.runSentence(str,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end res : "+a);
	}
	
	//@Test
	public void testgetlexeme(){
		try {
		//	pa.getLexeme(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@Test
	public void testLA(){
		Map<String, Map<String, String>> res = null;
		try {
			res = ll.parsing(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(String key : res.keySet()){
			System.out.println(key);
			Map<String, String> map = res.get(key);
			for(String k: map.keySet()){
				System.out.println(k+"  :  "+map.get(k));
			}
		}
	}
	
  //@Test
  public void putlexme() {
	  map.put("a", "a");
	  map.put("a", "aa");
	  map.put("b", "b");
	  
	  for(String key : map.keySet()){
		  System.out.println(map.get(key).hashCode()+map.get(key));
	  }
	  sa = "hello";
	
	System.out.println("1  "+sa.hashCode());
	  map.put("c", sa);
	System.out.println("2  "+sa.hashCode());
	  sa = "";
	System.out.println("3  "+sa.hashCode());
	  list.add(map);
	  
	  
  }
  
  //@Test
  public void maptest(){
	  putlexme();
	  System.out.println("^^^^^^^^^^^^^^^%^*(((((((((((");
	  System.out.println(list.size());
	  for(int i=0;i<list.size();i++){
		  for(String key: list.get(i).keySet()){
			  System.out.println(map.get(key).hashCode()+map.get(key));
		  }
	  }
	 
  }
}
