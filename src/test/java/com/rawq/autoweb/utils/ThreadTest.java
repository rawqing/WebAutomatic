package com.rawq.autoweb.utils;

public class ThreadTest implements Runnable{

	private String name;
	private int timer;

	public ThreadTest(String name,int timer) {
		super();
		this.name = name;
		this.timer = timer;
	}

	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getTimer() {
		return timer;
	}



	public void setTimer(int timer) {
		this.timer = timer;
	}



	@Override
	public String toString() {
		return "ThreadTest [name=" + name + ", timer=" + timer + "]";
	}



	public void run() {
		System.out.println("hello "+ name);
		this.hello();
		try {
			Thread.sleep(timer);
			System.out.println("hello "+ name +"  "+timer);
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name);
		System.out.println(toString());
	}
	
	public void hello(){
		System.out.println("hello world");
	}
}
