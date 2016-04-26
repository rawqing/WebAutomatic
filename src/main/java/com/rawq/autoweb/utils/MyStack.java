package com.rawq.autoweb.utils;

import java.util.ArrayList;
import java.util.List;

public class MyStack <T> {
	
	private List<T> stackList;
	
	public MyStack(){
		this.stackList = new ArrayList<T>();
	}

	/**
	 * 显示记录数
	 * @return
	 */
	public int height() {
		return stackList.size();
	}

	/**
	 * 取出一条记录
	 * @return
	 */
	public Object pop() {
		
		return stackList.remove(stackList.size()-1);
	}

	/**
	 * 放入记录
	 * @param obj
	 */
	public void push(T obj) {
		
		stackList.add(obj);
	}

	/**
	 * 判断为空
	 * @return
	 */
	public boolean isEmpty(){
		return stackList.size() == 0;
	}
	
	/**
	 * 查看最上层的记录
	 * @return
	 */
	public Object peek(){
		return stackList.get(stackList.size()-1);
	}

}
