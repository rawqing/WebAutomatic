package com.rawq.autoweb.utils;

import java.util.HashMap;
import java.util.Map;
import com.rawq.autoweb.enums.TokenClass;

public class LAnalyzer {

	private final String [] keyActions = {"open","click","sendKeys","show","in"};
	private final String [] keyETypes = {"page","button","input","text",
										"link","window","alert","layer","select"};
	private final String [] keyJoins = {"and","when","into","then","or","not","end"};
	private char nextchar;
	private String lexeme = "";
	private int i = 0;
	private int charClass = 0;		//0 空格，1 小写字母，2 标记“(){}”、 其他,
	private String action ="", etype ="", element ="", data ="";
	private Map<String ,Map<String ,String>> res = new HashMap<String,Map<String ,String>>();
	
	/**
	 * 解析语句字符串
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public Map<String ,Map<String ,String>> parsing(String str) throws Exception{
		char [] s = str.toCharArray();
		while(i < s.length){
			getChar(s);
			switch (charClass) {
			case 1:
				lexeme += nextchar;
				break;
			case 2:
				this.putlexme();
				if (nextchar == '('){
					element = str.substring(i ,i = this.indexOf(str,')',i));
					Log.info(LAnalyzer.class, "element : "+element);
					i++;
				}else if(nextchar == '{'){
					data = str.substring(i ,i = this.indexOf(str,'}',i));
					Log.info(LAnalyzer.class, "data : "+data);
					i++;
				}else if(nextchar == 32){
					Log.info(LAnalyzer.class, "一个空格输出");
				}else{
					Log.warn(LAnalyzer.class, "没有定义的标记 ： "+nextchar);
				}
				break;
			default:
				Log.info(LAnalyzer.class, "一个未定义字符输出 ： "+nextchar);
				break;
			}
		}
		this.putlexme();
		lexeme = "end";
		this.putlexme();
		return res;
	}
	
	public String getLexeme(String str) throws Exception{
		String lex = "";
		char [] s = str.toCharArray();
		while(i < s.length){
			getChar(s);
			switch (charClass) {
			case 1:
				lex += nextchar;
				break;
			case 2:
				//this.putlexme();
				System.out.println("lex : "+lex);
				lex = "";
				if (nextchar == '('){
					element = str.substring(i ,this.indexOf(str,')',i));
					Log.info(LAnalyzer.class, "element : "+element);
					lex = getLexeme(element);
					i++;
				}else if(nextchar == '{'){
					data = str.substring(i ,i = this.indexOf(str,'}',i));
					Log.info(LAnalyzer.class, "data : "+data);
					i++;
				}else if(nextchar == 32){
					Log.info(LAnalyzer.class, "一个空格输出");
				}else{
					Log.warn(LAnalyzer.class, "没有定义的标记 ： "+nextchar);
				}
				break;
			default:
				Log.info(LAnalyzer.class, "一个未定义字符输出 ： "+nextchar);
				break;
			}
		}
		return lex;
	}
	
	public int indexOf(String str, char ch, int startIndex) throws Exception{
		int index = str.indexOf(ch,startIndex);
		if (index == -1){
			throw new Exception("没有匹配的结束字符："+ch);
		}
		return index;
	}
	/**
	 * 跳过空格，赋值单个字符，并判断字符类型,这里不关心下标越界
	 *  下划线和大、小写字母 为1
	 *  空格、(、{、
	 * @param s
	 */
	private void getChar(char [] s){
		if ((nextchar = s[i]) == 32 || nextchar == 40 || nextchar == 123){
			charClass = 2;
		}else if((97 <= nextchar && 122 >= nextchar) || nextchar == 95 
				|| (65 <= nextchar && 90 >= nextchar)){
			charClass = 1;
		}else{
			charClass = 0;
		}
		i++;
	}
	
	/**
	 * 判断词素的类型，然后分类存放，遇到连接类型的词素则拆分子语句
	 */
	private void putlexme(){
		if ("".equals(lexeme=lexeme.trim())){
			return;
		}
		
		Log.info(LAnalyzer.class, "lexeme : "+lexeme);
		
		if (findKey(lexeme, keyActions)){
			action = lexeme;
			lexeme = "";
		} else if(findKey(lexeme, keyETypes)){
			etype = lexeme;
			lexeme = "";
		} else if(findKey(lexeme, keyJoins)){
			Map<String ,String> sentence = new HashMap<String,String>();
			sentence.put(TokenClass.action.name(), action);
			sentence.put(TokenClass.etype.name(), etype);
			sentence.put(TokenClass.element.name(), element);
			sentence.put(TokenClass.data.name(), data);
			action = ""; etype = ""; element = ""; data = "";	//clean temp parameter
			
			res.put(lexeme, sentence);
			lexeme = "";
		} else{
			Log.warn(LAnalyzer.class, "没有匹配的类型");
		}
		
	}
	
	/**
	 * 从关键字列表中查找当前单词
	 * @param world
	 * @param keys
	 * @return 可找到在为true，找不到则为false
	 */
	private boolean findKey(String world,String [] keys){
		for(String key : keys){
			if (key.equals(world)){
				return true;
			}
		}
		return false;
	}
}
