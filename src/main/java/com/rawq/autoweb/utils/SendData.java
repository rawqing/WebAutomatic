package com.rawq.autoweb.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.rawq.autoweb.enums.EnVar;
import com.rawq.autoweb.enums.PTag;

import com.rawq.autoweb.enums.PTag;


public class SendData {

	
	private List<Map<String,String>> database = null;
	//private BufferedReader br = null;

	
	/**
	 * 得到流
	 * @param filePath
	 * @return
	 */
	private BufferedReader getBufferedReader(String filePath){
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br;
	}
	
	
	public List<Map<String,String>> getData(String filePath,boolean firstLineIsName,String regex){
		//List<Map<String,String>> database = null;
		Map<String,String> linedata = null;
		
		BufferedReader reader = this.getBufferedReader(filePath);
		
		try {
			database = new ArrayList<Map<String,String>>();
			
			String tempstring;
			int line = 1;
			List<String> key = new ArrayList<String>();
			while((tempstring = reader.readLine()) != null){
				String [] temparr = tempstring.split(regex);
				
				if (line ==1){
					
					if (firstLineIsName){
						key = Arrays.asList(temparr);
						line++;
						continue;
					}else{
						for(int i=0;i<temparr.length;i++){
							key.add(i+"");
						}
					}
				}
				linedata = new HashMap<String, String>();
				for(int i = 0;i<key.size();i++){
					linedata.put(key.get(i), temparr[i]);
				}
				database.add(linedata);
				
				System.out.println(line +" : " + tempstring);
				line++ ;
			}
			database.add(linedata);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			this.closeBufferedReader(reader);
		}
		return database;
	}
	
	public Map getData(String dataFilePath){
		
		
		return null;
		
	}
	
	
	//验证得到的字符串是否符合特定的格式，如果匹配则进行对应的操作
	/**
	 * 根据字符串的标识符，获得计算后的终极字符串
	 * @param originalString
	 * @param tag
	 * @return
	 */
	public static String getUltimateString(String originalString,Map<String,String> data){
		originalString = SubString.getSubString(originalString, "{", "}");
		Log.info(SendData.class, "originalString : "+ originalString );
		if (originalString == null){
			return originalString;	//如果为null直接返回
		}
		//若为表达式则，使用beansheel的执行结果
		if (originalString.startsWith(PTag.expression.getTag())){
			List<String> strs = SubString.getSubStringList(originalString, "$", " ");
			Map<String ,String> param = new HashMap<String ,String>();
			for(String s : strs){
				try {
					param.put(s,EnVar.valueOf(s).getValue());		//意为表达式中的变量只能是环境变量
				} catch (Exception e) {
					Log.warn(SendData.class, "变量 "+ s + " 不是环境变量或没有设定相应的值 。"+e);	//如果enum中没有定义该常量，则跳过。
					continue;
				}
			}
			return Bsh.getRes(originalString, param);
		//那么这里有两种情况，一种是常量(常量直接在环境变量枚举类中取值），
		//一种是变量（变量集合，集合的话 是需要到集合中去取的）
		} else if (originalString.startsWith(PTag.parameter.getTag())){	
			if (data.containsKey(originalString)){
				return data.get(originalString);
			}else{
				try {
					return EnVar.valueOf(originalString).getValue();
				} catch (Exception e) {
					Log.warn(SendData.class, "EnVar 枚举中可能未定义 “ "+originalString+" ”" +e);
				}
			}
		}
		return originalString;
	}
	
	/**
	 * 判断字符串的数据类型，并按对应的类型返回
	 * @param str
	 * @return
	 */
	public static Object parseString(String str){
		try {
			return JSON.parse(str);
		} catch (Exception e) {
			return str;
		}
	}
	
	/**
	 * 关闭流
	 * @param reader
	 */
	private void closeBufferedReader(BufferedReader reader){
		if (reader != null){
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
