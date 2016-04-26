package com.rawq.autoweb.reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.rawq.autoweb.utils.Log;

public class Work extends ObjFactory{

//	private String className;
//	private String methodName;
//	private Object [] param;
//	private static Logger logger = Logger.getLogger(Work.class);
	
//	public Work(String className, String methodName, Object[] param) {
//		super();
//		this.className = className;
//		this.methodName = methodName;
//		this.param = param; 	
//	}


	/**
	 * 得到给定参数的参数类型
	 * @param param
	 * @return
	 */
	public static Class<?>[] getParamClass(Object... param) throws NullPointerException{
		if(param != null && param.length > 0){
			Class<?>[] paramClass = new Class<?>[param.length];	// 这里有个问题，传过来的参数 只有一个元素 且未null,则直接抛出空指针异常。
			for(int i=0; i<param.length; i++){
				paramClass[i] = param[i].getClass();
			}
			return paramClass;
		}
		Log.warn(Work.class,"未传入参数，可能需要调用无参的方法");
		return null;
	}
	/**
	 * 通过指定的Class对象、方法名及参数，获得Method对象
	 * @param clz			Class对象
	 * @param methodName	方法名
	 * @param parameters	可变个数的参数
	 * @return				Method
	 */
	public static Method getMethod(Class<?> clz,String methodName,Object...parameters){
		Class<?>[] parameterTypes = null;
		try {
			parameterTypes = getParamClass(parameters);
		} catch (NullPointerException e) {
			List<Method> methods = getMethods(clz, methodName);
			if (methods.size() == 1 && methods.get(0).getParameterTypes().length == parameters.length){
				return methods.get(0);	//最简单的匹配方式，若给定的方法名只匹配到一个方法，
										//且该方法的形参个数与给定的实参个数相同则直接返回该方法
			}
			for(Method m : methods){
				Class<?>[] types = m.getParameterTypes();
				for(int i = 0; types.length == parameters.length && i < types.length; i++){
					if (parameters[i] == null){
						if (i == types.length-1){
							return m;	//如果最后一个参数也匹配成功，则返回该方法。
						}
						continue;		//如果有参数为null则跳过，这样可能会匹配到多个方法，默认以最先匹配到的为准
					}
					if (types[i].isAssignableFrom(parameters[i].getClass())){
						Log.debug(Work.class, "成功匹配第 "+ i + "个形参");
						if (i == types.length-1){
							return m;	//如果最后一个参数也匹配成功，则返回该方法。
						}
					}else{
						break;
					}
				}
			}
		}
		try {
			return clz.getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			Log.error(Work.class,"没有这个方法"+e);
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		Log.error(Work.class,"没有正确匹配这个方法");
		return null;
	}
	public static List<Method> getMethods(Class<?> clz, String methodName){
		Method[] methods = clz.getMethods();
		List<Method> newMethod = new ArrayList<Method>();
		for(Method m : methods){
			if (methodName != null && methodName.equals(m.getName())){
				newMethod.add(m);
			}
		}
		return newMethod;
		
	}
	
	/**
	 * 得到方法的返回参数类型
	 * @param method
	 * @return
	 */
	public static Class<?> getReturnType(Method method){
		return method.getReturnType();
	}
	
	/**
	 * 执行有参方法
	 * @param method
	 * @param obj
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T executMethod(Method method,Object obj,Object...parameters){
		try {
			return (T) method.invoke(obj, parameters);
		} catch (IllegalAccessException e) {
			Log.error(Work.class,e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			Log.error(Work.class,e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			Log.error(Work.class,e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 执行无参方法
	 * @param method
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T executMethod(Method method,Object obj){
		try {
			return (T) method.invoke(obj);
		} catch (Exception e) {
			Log.error(Work.class,e);
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 执行此方法必须使用无参构造器
	 * @return
	 */
	public static Object executMethod(String className,String methodName, Object...param){
		Class<?> clz = getClassObject(className);
		Method method = getMethod(clz, methodName, param);
		//得到方法的执行结果
		Object res = executMethod(method,getObject(clz),param);
		
		return res;
	}
	/**
	 * 执行给定对象的指定方法
	 * @param className
	 * @param methodName
	 * @param obj
	 * @param param
	 * @return
	 */
	public static Object executMethod(String methodName,Object obj, Object...param){
		Log.info(Work.class, "execut { method name : "+methodName + ", object : "+ obj + ", parameters : " + param + " }");
		Class<?> clz = obj.getClass();
		Method method = getMethod(clz, methodName, param);
		//得到方法的执行结果
		Object res = executMethod(method,obj,param);
		
		return res;
	}
	
	/**
	 * 执行静态的方法
	 * @param className
	 * @param methodName
	 * @param param
	 * @return
	 */
	public static Object executStaticMethod(String className,String methodName, Object...param){
		Class<?> clz = getClassObject(className);
		Method method = getMethod(clz, methodName, param);
		//得到方法的执行结果
		Object res = executMethod(method, null,param);
		
		return res;
	}
	public static Object executStaticMethod(Class<?> classObj,String methodName, Object...param){
		Method method = getMethod(classObj, methodName, param);
		//得到方法的执行结果
		Object res = executMethod(method, null,param);
		
		return res;
	}
}
