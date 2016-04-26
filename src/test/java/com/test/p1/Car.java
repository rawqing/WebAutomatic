package com.test.p1;

public class Car {

	private String name;
	private int s;
	
	public Car(String name, int s) {
		super();
		this.name = name;
		this.s = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", s=" + s + "]";
	}
	
	
}
