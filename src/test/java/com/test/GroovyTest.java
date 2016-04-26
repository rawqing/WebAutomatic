package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyTest {

	public static void main(String[] args) throws EvalError {
		
	//	Binding binding = new Binding(); 
	//	String str = "$LAST_RES - 1";
	//	beanShellTest( bsh,str);
		run();
	}

	public static void timeCompare() throws EvalError{
		Interpreter bsh = new Interpreter();
		Binding binding = new Binding(); 
		long start ,end ;
		//开始第一段时间计算
		System.out.println(start = new Date().getTime());
		for(int i=1;i<10;i++){
			groovyTest(binding);
		}
		System.out.println(end = new Date().getTime());
		System.out.println(end-start);
		
		//开始第二段时间计算
		System.out.println(start = new Date().getTime());
		for(int i=1;i<10;i++){
			
		//	beanShellTest(bsh,"");
		}
		System.out.println(end = new Date().getTime());
		System.out.println(end-start);
	}
	public static void groovyTest(Binding binding){
		String varname="a";
		 
		binding.setVariable(varname, new Integer(2));  
		GroovyShell shell = new GroovyShell(binding);  
		  
		Object value = shell.evaluate("return "+varname+" * 10");  
		//System.out.println(value);
	}
	
	public static String beanShellTest(String str,Map<String,Object>pram ) throws EvalError{
		String varname="a";
		Interpreter bsh = new Interpreter();
		// 表达式求值   
		//bsh.eval("foo=Math.sin(0.5)"); 
		bsh.set(varname, 2);
		for(String key : pram.keySet()){
			bsh.set(key, pram.get(key));
			System.out.println(key + "  :  "+pram.get(key));
		}
		bsh.eval("res = "+str);
		bsh.eval("res1 = "+varname+" * 100"); 
		//System.out.println(bsh.get("res"));
		System.out.println(bsh.get("res1"));
		
		String res = bsh.get("res").toString();
		System.out.println(res);
		return res;
	}
	
	public static ArrayList<String> getData(String datafilepath){
		System.out.println(datafilepath);
		ArrayList<String> data = new ArrayList<String>();
		data.add("123000");
		data.add("hello");
		data.add("#{100 + 1 * 20 + 1}");
		
		return data;
	}
	
	public static void say(String str){
		System.out.println("str : "+str);
	}
	
	public static void run() throws EvalError{
		int $LASET_RES = 1000;
		String $var = "sss";
		for(String s : getData("get data start")){
			if (s.startsWith("#")){
				String str = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				Map<String,Object> parme = new HashMap<String,Object>();
				int sindex,endindex;
				for(int formindex = 0; formindex < s.length(); formindex = endindex){
					sindex =s.indexOf("$",formindex);
					endindex = s.indexOf(" ",sindex);
					
					if (endindex <= 0){
						endindex = s.length();
					}
					if (sindex == endindex || sindex <= 0 ){
						break;
					}
					parme.put(s.substring(sindex,endindex),$LASET_RES);
				}
				System.out.println(">>>>>."+parme);
				say(beanShellTest(str, parme));
			}
		}
	}
}
