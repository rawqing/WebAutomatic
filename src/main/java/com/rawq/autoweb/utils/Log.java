package com.rawq.autoweb.utils;

import org.apache.log4j.Logger;

public class Log {

//	private static Logger logger = Logger.getLogger(Log.class);
	
	//level 7
	public static void debug(Class<?> classobj ,Object message){
		
		Logger.getLogger(classobj).debug(message);
	}
	
	//level 6
	public static void info(Class<?> classobj ,Object message){
		Logger.getLogger(classobj).info(message);
	}
	
	//level 4
	public static void warn(Class<?> classobj ,Object message){
		Logger.getLogger(classobj).warn(message);
	}
	
	//level 3
	public static void error(Class<?> classobj ,Object message){
		Logger.getLogger(classobj).error(message);
	}
	
	//level 0
	public static void fatal(Class<?> classobj ,Object message){
		Logger.getLogger(classobj).fatal(message);
	}
}
