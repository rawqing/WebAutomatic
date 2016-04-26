package com.test;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rawq.autoweb.actions.SubmitZenTao;
import com.rawq.autoweb.utils.LAnalyzer;
import com.sun.xml.internal.org.jvnet.fastinfoset.EncodingAlgorithmIndexes;

public class OfenTest {

	
	
	public static void main(String[] args) {

		 Map map = new HashMap();
		 Class c = map.getClass();
		 String s = "";
		 System.out.println( s instanceof String);
	}

	public static void zhanqnianhua(float price,int days ,float ylixi ){
		for(;price <= 105; price += 0.10){
			float yearb = ((100 + ylixi) - price) / price * 365 /days *100;
			System.out.println("price : "+price +" 年化： "+yearb);
		}
		
	}
	public static void testfilep(){
		System.out.println(File.pathSeparator);
		System.out.println(File.pathSeparatorChar);
		System.out.println(File.separator);
		System.out.println(File.separatorChar);
	}
	public static void maptest(){
		Map<String, String > map = new LinkedHashMap<String ,String >();
		
		for(int i = 1;i<1000000;i++){
			map.put(i+"","a"+i);
		}
		
		long start = new Date().getTime();
		/*
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> a = it.next();
			String key = a.getKey();
			String value = a.getValue();
		//	System.out.println("key : "+key + ", value : " + value);
		}
		
		for(Entry<String, String> en : map.entrySet()){
			String key = en.getKey();
			String value = en.getValue();
		//	System.out.println("key : "+key + ", value : " + value);
		}
		
		/**/
		for(String key1 : map.keySet()){
			String key = key1;
			String value = map.get(key);
		//	System.out.println("key : "+key + ", value : " + value);
		}
	//	*/
		System.out.println("use time : "+(new Date().getTime() - start));
	}
	
	public static void stringsp(){
		String str = "show(index)page and {$user}in(loginuser)";
		//str = "open(index)page";
		int a = 0;
		int b = 1, c = 3;
		if (a == 1 && b==3 || c == 3 && a==1){
			System.out.println("hello");
		}
//		System.out.println('_' == 95);
//		int i = 30;
//		int j = str.indexOf(')',i);
//		String ns = str.substring(i + 1,j);
//		System.out.println("i : "+i);
//		System.out.println(str.charAt(i));
//		System.out.println(ns);
	}
	public static void stringtoken(){
		StringTokenizer st = new StringTokenizer("this  (is)    a test");
		
	     while (st.hasMoreTokens()) {
	         System.out.println(st.nextToken());
	     }

	}
	public static void object2string(){
		Object a = 1;
		System.out.println(a.getClass() );
		a = true;
		System.out.println(a.getClass() == Boolean.class);
		System.out.println(a.toString());
		a = "hello";
		System.out.println(a.getClass());
	//	a = null;
		//System.out.println(a.getClass());
		a = 'b';
		System.out.println(a.getClass());
	}
	public static void yihuo(){
		String a = "123";
		String b = "123";
		
		System.out.println(1 ^ 1);
		boolean bool = a == null ^ b == null;
		
		if (bool){
			System.out.println("a ^ b" + bool);
		}
		bool = a != null || b != null;
		System.out.println(bool);
		System.out.println(true ^ true);
		System.out.println();
	}
	public static void isnull(){
		List list = new ArrayList<>();
		list.add(123);
		list.add(1232);
		System.out.println(list + " is empty : "+list.isEmpty()+"  is null :"+(list == null));
		list.clear();
		System.out.println(list + " is empty : "+list.isEmpty()+"  is null :"+(list == null));
		list = null;
		System.out.println(list + " is empty : "+list.isEmpty()+"  is null :"+(list == null));
	}
	public static void substring(){
//		String s = "gsaj}l1{2 321312}dfgdsfa";
//		System.out.println(new Date().getTime());
//		int startindex = s.indexOf("{")+1;
//		String a = s.substring(startindex, s.indexOf(" ",startindex));
//	
//		System.out.println(new Date().getTime());
//		System.out.println(a);
		
		String s="#{$LASET_RES * ($var + 1)}";
		ArrayList<String> parme = new ArrayList<String>();
		int sindex,endindex;
		for(int formindex = 0; formindex < s.length(); formindex = endindex){
			sindex =s.indexOf("$",formindex)+1;
			endindex = s.indexOf(" ",sindex);
			
			if (endindex <= 0){
				endindex = s.length();
			}
			if (sindex == endindex || sindex <= 0 ){
				break;
			}
			
			parme.add(s.substring(sindex,endindex));
		}
	//	System.out.println(s.length());
	//	System.out.println(s.indexOf("%")); 
		
		System.out.println(parme);
	}
	
	public static void testmap(){
		Map m = new HashMap();
		m.put("aaa", "111");
		
		System.out.println(m.get("bb")==null);
		
	}
	
	public static void testjdbc(){
		String url = "jdbc:mysql://192.168.1.243:3306/bt_user";
		String username = "root";
		String pwd = "123456";
		String sql = "select content from bt_user.u_notifysms where phoneNo='17931000031' order by createTime desc limit 1";
		ResultSet res = null;
		PreparedStatement  pst = null;
		Connection conn = null;
		System.out.println("helll   ");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, pwd);//获取连接  
			pst = conn.prepareStatement(sql);//准备执行语句  
			
			res = pst.executeQuery(sql);
			System.out.println(res);
			System.out.println();
			while(res.next()){
				System.out.println(res.getString("content"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				res.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void timetest(){
		long start = new Date().getTime();
		System.out.println(start);
		List list = new ArrayList();
		list.add(234);
		list.add("sdlfjsl");
		list.add(32432);
		list.add(324523);
		list.add("sdfsd");
		for(int a=0;a<1000;a++){
			list.add(a);
		}
		int i = list.size();
		for(int j=0 ;j<100;j++){
		//	System.out.println(list.size());
			System.out.println(i);
		}
		
		long end = new Date().getTime();
		System.out.println(end);
		System.out.println(end-start);
	}
	
	public static void json2string(){
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		String jstr = JSON.toJSONString(list);
		System.out.println("List JSON : "+jstr);
		System.out.println("----------------------------------");
		Map<String,String> map = new LinkedHashMap<>();
		map.put("$username", "user1");
		map.put("$pwd", "123456");
		String jstr1 = JSON.toJSONString(map);
		System.out.println("map json :"+jstr1);
	}
	public static void json2arraylist(){
		String s = "[1,3,5,24,234]";
		List<Integer> list = JSON.parseArray(s, int.class);
		for(Integer i:list){
			System.out.println(i);
		}
	}
	public static void json2map(){
		String s = "{\"$username\":\"user1\",\"$pwd\":\"123456\"}";
		Map<String,String> map = JSON.parseObject(s, Map.class);
		System.out.println(map);
		for(String key : map.keySet()){
			System.out.println(key + " " + map.get(key));
		}
	}
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
