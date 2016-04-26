package com.rawq.autoweb.reflex;

import java.lang.reflect.Constructor;

import com.rawq.autoweb.utils.Log;

public class ObjFactory  {

	
	/**
	 * 获得指定类名的class对象
	 * @param className
	 * @return
	 */
	public static Class<?> getClassObject(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			Log.error(ObjFactory.class,"get class object error : "+e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回一个有参的构造方法
	 * @param classObject
	 * @param parameterTypes
	 * @return
	 */
	public static Constructor<?> getConstrcutorObject(Class<?> classObject,Class<?>... parameterTypes){
		try {
			return classObject.getConstructor(parameterTypes);
		} catch (Exception e) {
			Log.error(ObjFactory.class,e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 调用无参构造方法创建对象
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getObject(Class<?> clz){
		try {
			return (T) clz.newInstance();
		} catch (Exception e) {
			Log.error(ObjFactory.class,e);
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 返回指定构造方法，和参数的对象
	 * @param constructor 构造方法
	 * @param initargs 参数
	 * @return <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getObject(Constructor<?> constructor,Object... initargs){
		try {
			return (T) constructor.newInstance(initargs);
		} catch (Exception  e) {
			Log.error(ObjFactory.class,e);
			e.printStackTrace();
			return null;
		}
	}
	
	
}
