package com.rawq.autoweb.utils;

import java.util.ArrayList;
import java.util.List;

public class SubString {

	
	/**
	 * 获取按起止字符分割后的字符串
	 * @param originalString
	 * @param startString
	 * @param endString
	 * @return
	 */
	public static String getSubString(String originalString ,String startString, String endString){
		return originalString.substring(originalString.indexOf(startString)+1
				, originalString.indexOf(endString));
	}
	
	/**
	 * 获取字符串中所有符合分割条件的字符串集合
	 * example：getSubStringList("$user $name $age", "$", " "); 将返回一个list包含["user","name","age"]
	 * @param originalString
	 * @param startString 
	 * @param endString
	 * @return
	 */
	public static List<String> getSubStringList(String originalString ,String startString, String endString){
		List<String> stringList = new ArrayList<String>();
		int sindex,endindex;
		for(int formindex = 0; formindex < originalString.length(); formindex = endindex){
			sindex =originalString.indexOf(startString,formindex);
			endindex = originalString.indexOf(endString,sindex);
			
			if (endindex <= 0){
				endindex = originalString.length();
			}
			if (sindex == endindex || sindex <= 0 ){
				break;
			}
			stringList.add(originalString.substring(sindex,endindex));
		}
		return stringList;
	}
}
