package com.rawq.autoweb.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.rawq.autoweb.domain.Token;
import com.rawq.autoweb.enums.TokenClass;
import com.rawq.autoweb.service.RunSentence;


public class Parser {

	private final String [] keyActions = {"open","click","sendKeys","show","in"};
	private final String [] keyETypes = {"page","button","input","text",
										"link","window","alert","layer","select"};
	private final String [] keyLogics = {"and","or","not"};
	private final String [] keyJoin = {"when","into","then","end"};
	
	private int i = -1;
	
	
	/**
	 * 得到一个按词素分割的String类型列表
	 * @param str
	 * @return
	 */
	public List<String> lexemeList(String str){
		List<String> lexemes = new ArrayList<String>();
		char[] s = str.toCharArray();
		
		for(int index = 0;index < s.length;){
			String lex = "";
			
			while(true){
				lex += s[index];
				index++;
				if (!"".equals(lex = lex.trim()) && ( index >= s.length 
						|| s[index] == 32 
						|| s[index] == '(' || s[index] == ')' 
						|| s[index-1] == '(' || s[index-1] == ')'	//这里是将“()”独立拆分出来
						|| s[index] == '{' || s[index-1] == '}') ){
					
					Log.info(this.getClass(), lex);
					break;
				}
			}
			lexemes.add(lex);
			lex = "";
		}
		return lexemes;
	}
	
	/**
	 * 将语句拆分成Token类型的集合
	 * @param str
	 * @return
	 */
	public List<Token> getTokens(String str){
		if (str == null || "".equals(str)){
			return null;
		}
		List<Token> tokens = new ArrayList<Token>();
		char[] s = str.toCharArray();
		
		for(int index = 0;index < s.length;){
			String lex = "";
			
			while(true){
				lex += s[index];
				index++;
				if (!"".equals(lex = lex.trim()) && ( index >= s.length 
						|| s[index] == 32 
						|| s[index] == '(' || s[index] == ')' 
						|| s[index-1] == '(' || s[index-1] == ')'	//这里是将“()”独立拆分出来
						|| s[index] == '{' || s[index-1] == '}') ){
					
					break;
				}
			}
			Token token = new Token();
			token.setValue(lex);
			TokenClass kind = this.getTokenClass(lex);
			token.setKind(kind);
			Log.info(this.getClass(), kind + " -> " + lex);
			
			tokens.add(token);
			lex = "";
		}
		
		return tokens;
	}
	
	/**
	 * 执行一条语句并返回其结果
	 * @param tokens
	 * @param engine 执行语句并返回结果的方法的类的实例
	 * @return
	 * @throws Exception
	 */
	public boolean runSentence(List<Token> tokens ,RunSentence engine) throws Exception{
		if (tokens == null || tokens.isEmpty()){
			Log.warn(this.getClass(), "空语句被执行，将直接返回 true");
			return true;
		}
		MyStack<Object> mst = new MyStack<Object>();
		Map<TokenClass, String> st = new LinkedHashMap<TokenClass,String>();
		
		while(i<tokens.size()-1){
			i++;
			TokenClass tokenKind = tokens.get(i).getKind();
			if (tokenKind == TokenClass.left_paren){
				mst.push(this.runSentence(tokens,engine));	//递归获得结果
				continue;
			}
			if (tokenKind == TokenClass.right_paren){
				if (!st.isEmpty()){						//如果map不为空则求的该map的结果，并放入栈中，然后new一个新的实例
					this.disposeNotLogic(mst, st, engine);
					st = new LinkedHashMap<TokenClass,String>();
				}
				return this.getStackRes(mst);
			}
			if (tokenKind == TokenClass.logic){
				if (!st.isEmpty()){
					this.disposeNotLogic(mst, st, engine);
					st = new LinkedHashMap<TokenClass,String>();
				}
				if ("not".equals(tokens.get(i).getValue())){
					mst.push("not");
					continue;
				}
				if (this.lastLogicIndexFrom(i, tokens) == -1){		//如果后续没有逻辑符了，则可从当前结果与当前逻辑符推出结果
					boolean tempres = this.getStackRes(mst);
					if ("and".equals(tokens.get(i).getValue())){
						if (!tempres){
							return false;
						}
					}else if("or".equals(tokens.get(i).getValue())){
						if (tempres){
							return true;
						}
					}
					mst.push(tempres); 			//如果条件不成立则将计算的结果再压入栈中
				}
				mst.push(tokens.get(i).getValue());
				continue;
			}
			st.put(tokenKind, tokens.get(i).getValue());
		}
		// 如果为单条语句则将结果压栈后出栈即可得出结果
		if (!st.isEmpty()){
			this.disposeNotLogic(mst, st, engine);
		}
		
		this.i = -1;
		return this.getStackRes(mst);
	}
	/**
	 * 执行一条语句并返回其结果
	 * @param str
	 * @param engine 执行语句并返回结果的方法的类的实例
	 * @return
	 * @throws Exception
	 */
	public boolean runSentence(String str,RunSentence engine) throws Exception{
		Log.info(this.getClass(), "run sentence : "+str);
		return this.runSentence(this.getTokens(str),engine);
	}
	
	/**
	 * 如果当前栈不为空，且最上层为not，则取出
	 * 这样避免not在后续中的处理麻烦的问题
	 * @param mst
	 * @param st
	 * @throws Exception
	 */
	private void disposeNotLogic(MyStack<Object> mst, Map<TokenClass, String> st, RunSentence engine) throws Exception{
		if (!mst.isEmpty() && "not".equals(mst.peek())){
			mst.pop();
			mst.push(!this.getRes(st, engine));
		}else{
			mst.push(this.getRes(st, engine));
		}
	}
	
	/**
	 * 计算给定栈中所有值得结果
	 * 该计算会调用beanshell执行，计算后会导致栈被完全消耗
	 * @param mst
	 * @return
	 * @throws Exception
	 */
	public boolean getStackRes(MyStack<Object> mst) throws Exception{
		if (mst.height() == 1){
			if (mst.peek() instanceof Boolean){
				return (boolean) mst.pop();
			}else{
				throw new Exception("非boolean类型的实例被判断: '"+mst.peek()+"' ");
			}
		}
		String logicSentence = "";
	
		while(mst.height()>0){
			Object obj = mst.pop();
			if (obj instanceof Boolean){
				logicSentence += obj.toString();
			}else if ("and".equals(obj)){
				logicSentence += " && ";
			}else if ("or".equals(obj)){
				logicSentence += " || ";
			}else{
				Log.warn(this.getClass(), "字符类型非法 ： "+obj.getClass() + " > "+obj.toString());
			}
		}
		
		return Boolean.valueOf(Bsh.getRes(logicSentence));
	}
	
	
	/**
	 * get this tokens's last logic's index
	 * @param tokens
	 * @return
	 */
	public int lastLogicIndex(List<Token> tokens){
		return this.lastLogicIndexFrom(0, tokens);
	}
	/**
	 * 获得最后一个逻辑符，到给定的index还没有则return -1
	 * @param index
	 * @param tokens
	 * @return
	 */
	public int lastLogicIndexFrom(int index ,List<Token> tokens){
		int j = tokens.size()-1;
		while(j>index){
			if (tokens.get(j).getKind() == TokenClass.logic){
				return j;
			}
			j--;
		}
		return -1;
	}
	
	
	/**
	 * test mathod 
	 * @return
	 * @throws Exception 
	 */
	public boolean getRes(Map<TokenClass,String> st, RunSentence engine) throws Exception{
		if (st.isEmpty()){
			throw new Exception("空语句求值");
		}
		if ("false".equals(engine.getResults(st).toString())){
			return false;
		}
		System.out.println("i am a res : "+st); 
		
		//这里需要调用一个函数，并传参过去，然后由该函数记录 实际结果。
		// 如： Write w = new Write(){}
		return true;
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
	/**
	 * 获得标记的类型，element类型只做虚标记
	 * 下游必须继续判断是否属于element。
	 * @param lex
	 * @return
	 */
	private TokenClass getTokenClass(String lex){
		
		if (lex == null || "".equals(lex)){
			Log.error(this.getClass(), "空词素异常");
			return null;
		}
		
		if ("(".equals(lex)){
			return TokenClass.left_paren;
			
		}else if(")".equals(lex)){
			return TokenClass.right_paren;
			
		}else if(lex.startsWith("{") && lex.endsWith("}")){
			return TokenClass.data;
			
		}else if(this.findKey(lex, keyActions)){
			return TokenClass.action;
			
		}else if(this.findKey(lex, keyETypes)){
			return TokenClass.etype;
			
		}else if(this.findKey(lex, keyLogics)){
			return TokenClass.logic;
			
		}else if(this.findKey(lex, keyJoin)){
			return TokenClass.join;
			
		}else{
			//这里只做虚标记
			return TokenClass.element;
		}
		
	}
}
