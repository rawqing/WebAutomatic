package com.test;

public class Statictest {

	private static String a = "hello a";
	
	private String b;

	public Statictest(String b) {
		this.b = b;
		System.out.println("我是构造方法");
	}
	
	static {
		System.out.println("我是静态块");
	}
	
	public static void say(){
		System.out.println(a);
	}
}
