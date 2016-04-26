package com.rawq.autoweb.utils;

import com.alibaba.fastjson.JSON;

public class Msgs {

	private int code;
	private boolean success;
	private Object messages;
	
	public Msgs(int code, boolean success, Object messages){
		this.code = code;
		this.success = success;
		this.messages = messages;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	@Override
	public String toString() {
		return "Msgs [code=" + code + ", success=" + success + ", messages="
				+ messages + "]";
	}

	public String toJson(){
		return JSON.toJSONString(this);
	}
}
