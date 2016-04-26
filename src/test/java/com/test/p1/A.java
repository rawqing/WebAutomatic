package com.test.p1;

import java.util.Map;

import com.rawq.autoweb.enums.TokenClass;
import com.rawq.autoweb.service.RunSentence;
import com.rawq.autoweb.service.Upload;
import com.rawq.autoweb.utils.Msgs;

public class A implements RunSentence,Upload{

	public static  String s1 = "ssa";

	public static String getS1() {
		return s1;
	}

	public static void setS1(String s1) {
		A.s1 = s1;
	}

	@Override
	public Msgs uploadFromXls(String excelFilePath) {
		// TODO Auto-generated method stub
		System.out.println(s1);
		return null;
	}

	@Override
	public Msgs uploadFromCsv(String csvFilePath) {
		// TODO Auto-generated method stub
		System.out.println(csvFilePath);
		return null;
	}

	@Override
	public Object getResults(Map<TokenClass, String> st) {
		
		System.out.println("st st st :"+s1);
		s1 = "hello";
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
