package com.rawq.autoweb.utils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.mysql.fabric.xmlrpc.base.Array;

public class Test001 {

	public static void main(String[] args) {

		testdayu();
	}
	public static void testdayu(){
		int a;
		try {
			a = getnu();
			System.out.println(123);
			System.out.println("sfasd"+a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("asdas");
	}
	public static int getnu(){
		int i = 3;
		int b = 5/0;
		return i;
	}
	
	public static void testThrow(){
		try{
			System.out.println("111111");
			if(true){
				for(int i=0;i<3 ;i++){
					System.out.println("a");
					if(i==2){
						System.out.println(2);
						throw new Exception("12321");
					}
				}
				System.out.println(1234);
			}
			System.out.println("222222");
			}catch (Exception e){
				e.printStackTrace();
			}
			System.out.println(111);
	}
	
	public static void threadTest(){
		ThreadTest tt1 = new ThreadTest("zhangsan",1000);
		ThreadTest tt2 = new ThreadTest("lisi",2000);
		ThreadTest tt3 = new ThreadTest("wangwu",5000);
		
		Thread t1 = new Thread(tt1);
		Thread t2 = new Thread(tt2);
		Thread t3 = new Thread(tt3);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	public int say(){
		System.out.println("我是无参的方法");
		return 1;
	}

	public String say(String name){
		System.out.println("我有一个参数 ： "+name);
		return "hello ";
	}
	
	public void say(String name, Integer i,Date date){
		System.out.println("我有多个参数 分别是：String "+ name+" Integer "+i+" Date "+date);
	}
}
