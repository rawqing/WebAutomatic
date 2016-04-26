package com.rawq.autoweb.utils;

import java.util.Map;

import com.rawq.autoweb.enums.EnVar;

import bsh.EvalError;
import bsh.Interpreter;

public class Bsh {
	
	private static Interpreter bsh = new Interpreter();
	
	/**
	 * 返回beanshell执行的结果，并对code中的变量赋值
	 * @param code
	 * @param parameter
	 * @return
	 */
	public static String getRes(String code,Map<String ,String> parameter){
		Log.info(Bsh.class, "user code is : { "+code+" }");
		try {
			//如果参数为空，则认为没有参数，直接调用
			if (parameter!=null && !parameter.isEmpty()){
				for(String key : parameter.keySet()){
					bsh.set(key, SendData.parseString(parameter.get(key)));
				}
			}
			bsh.eval("res = "+code);
			return  bsh.get("res").toString();
		} catch (EvalError e) {
			Log.error(Bsh.class, e);
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 执行无变量code
	 * @param code
	 * @return
	 */
	public static String getRes(String code){
		return getRes(code,null);
	}
	
	/**
	 * 执行一段beanshell代码
	 */
	public static void run(){
		
	}
}
