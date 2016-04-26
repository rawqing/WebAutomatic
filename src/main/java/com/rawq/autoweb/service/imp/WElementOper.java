package com.rawq.autoweb.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WElement;
import com.rawq.autoweb.domain.WPage;
import com.rawq.autoweb.domain.WSelector;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.service.IWCase;
import com.rawq.autoweb.service.IWElement;
import com.rawq.autoweb.service.IWPage;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.MybatisUtil;

public class WElementOper implements IWElement ,IWPage {

	private SqlSession sqlSession = MybatisUtil.getSqlSession();
	
	/**
	 * 通过元素id，查询元素
	 */
	public WElement getWElementById(int id) {
		WElement wElement = null;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			Log.info(this.getClass(),"得到sqlSession : "+sqlSession);
			IWElement iElement = sqlSession.getMapper(IWElement.class);
			wElement = iElement.getWElementById(id);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： id ："+id +"  ;"+e);
		}
		return wElement;
	}

	/**
	 * 通过页面别名、元素名称，查询元素
	 */
	public WElement getWElementByName(String pageName, String elementName) {
		WElement wElement = null;
		try {
			Log.info(this.getClass(),"得到sqlSession : "+sqlSession);
			IWElement iElement = sqlSession.getMapper(IWElement.class);
			wElement = iElement.getWElementByName(pageName, elementName);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： pageName ："+pageName + " elementName ："+elementName+"  ;"+e);
		}
		return wElement;
	}

	/**
	 * 通过页面别名、元素名称，查询元素id
	 */
	public int getWElementIdByName(String pageName, String elementName) {
		int id = -1;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			Log.info(this.getClass(),"得到sqlSession : "+sqlSession);
			IWElement iElement = sqlSession.getMapper(IWElement.class);
			id = iElement.getWElementIdByName(pageName, elementName);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： pageName ："+pageName +" elementName : "+elementName+"  ;\n"+e);
		} 
		return id;
	}

	/**
	 * 新增一个元素，包括其selector集合。注：其中一条selector insert 失败则整个元素记录insert失败
	 */
	public int addWElement(WElement wElement) {
		int id = -1;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWElement ielement = sqlSession.getMapper(IWElement.class);
			int cr = ielement.addWElement(wElement);
			Log.info(this.getClass(),"insert 影响行 ： "+ cr);
			id = wElement.getId();		//获得数据库返回的id
			Log.info(this.getClass(),"数据库返回 id : "+id);
			
			//将id 写入WSelector对象的elementID属性
			if(id != -1 && cr > 0){
				Log.info(this.getClass(),"包含步骤 "+wElement.getWSelectors().size()+ " 条");
				
				for(WSelector wSelector : wElement.getWSelectors()){
					wSelector.setElementID(id);
				}
				if (this.addWSelectorList(wElement.getWSelectors()) != -1){
					//提交
					sqlSession.commit();
				}else{
					Log.warn(this.getClass(),"未成功插入 wSelector ");
				}
				
			}else{
				Log.warn(this.getClass(),"未成功插入 wElement ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return id;
	}

	/**
	 * 通过页面id，查询页面
	 */
	public WPage getWPageById(int id) {
		WPage wPage = null;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWPage ipage = sqlSession.getMapper(IWPage.class);
			wPage = ipage.getWPageById(id);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： id ："+id +"  ;"+e);
		} 
		return wPage;
	}

	/**
	 * 通过页面别名，查询页面。注：页面别名必须保证唯一。
	 */
	public WPage getWPageByAlias(String alias) {
		WPage wPage = null;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWPage ipage = sqlSession.getMapper(IWPage.class);
			wPage = ipage.getWPageByAlias(alias);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： alias ："+alias +"  ;"+e);
		} 
		return wPage;
	}
	
	/**
	 * 通过页面别名，查询页面id
	 */
	public int getWPageIdByAlias(String alias) {
		int id = -1;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWPage ipage = sqlSession.getMapper(IWPage.class);
			id = ipage.getWPageIdByAlias(alias);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： alias ："+alias +"  ;"+e);
		}
		return id;
	}


	/**
	 * 新增一个页面
	 */
	public int addWPage(WPage wPage) {
		int res = -1;
		try {
			IWPage ipage = sqlSession.getMapper(IWPage.class);
			res = ipage.addWPage(wPage);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}
	/**
	 * 批量添加页面
	 */
	public int addWPageList(List<WPage> wPages) {
		int res = -1;
		try {
			IWPage ipage = sqlSession.getMapper(IWPage.class);
			res = ipage.addWPageList(wPages);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}
	

	/**
	 * 新增一个selector。注：只在单独增加时用到。
	 */
	public int addWSelector(WSelector wSelector) {
		int res = -1;
		try {
			//sqlSession = MybatisUtil.getSqlSession();
			IWElement ielement = sqlSession.getMapper(IWElement.class);
			res = ielement.addWSelector(wSelector);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}

	/**
	 * 添加一组wSelectors
	 */
	public int addWSelectorList(List<WSelector> wSelectors){
		int res = -1;
		try {
			IWElement ielement = sqlSession.getMapper(IWElement.class);
			res = ielement.addWSelectorList(wSelectors);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}
	/**
	 * 以List<Map<?, ?>> 的形式返回所有page对象的 id 和 alias
	 */
	public List<Map<?, ?>> getPageMapList() {
		List<Map<?, ?>> res = new ArrayList<Map<?, ?>>();
		
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			Log.debug(this.getClass(),"得到sqlSession : "+sqlSession);
			IWPage ipage = sqlSession.getMapper(IWPage.class);
			res = ipage.getPageMapList();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败   ;"+e);
		} 
		return res;
	}
	/**
	 * 将getPageMapList() 方法的返回值封装至map
	 * @return
	 */
	public Map<String, Integer> getPageMap(){
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		for(Map<?, ?> map : this.getPageMapList()){
			if (map.get("alias")!=null && !"".equals(map.get("alias")) && map.get("id")!=null && !"".equals(map.get("id"))){
				pageMap.put((String)map.get("alias"), (Integer)map.get("id"));
			}else{
				Log.warn(this.getClass(),"alias 或 id 为空 ："+map);
			}
		}
		return pageMap;
	}

	
	
}
