package com.rawq.autoweb.utils;

import com.alibaba.fastjson.JSON;

public class Json2Obj {

	
	@SuppressWarnings("unchecked")
	public static <T>T getValue(String json,String key,Class<?> clazz){
		return (T) JSON.parseObject(json).getObject(key, clazz);
	}
}
