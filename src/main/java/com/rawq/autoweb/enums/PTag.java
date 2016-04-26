package com.rawq.autoweb.enums;

public enum PTag {

	expression("#"),parameter("$");
	
	//定义value
	private String tag;

	//构造枚举
	private PTag(String tag) {
		this.tag = tag;
	}

	//定义获取value的方法
	public String getTag() {
		return tag;
	}

	
}
