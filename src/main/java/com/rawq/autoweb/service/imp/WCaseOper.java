package com.rawq.autoweb.service.imp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WPlan;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.domain.WSuite;
import com.rawq.autoweb.service.IWCase;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.MybatisUtil;

public class WCaseOper implements IWCase{

	private SqlSession sqlSession = MybatisUtil.getSqlSession();
	
	//>>>>>>>>>>>>>>>>>>>>>>> case 操作 <<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 以ID查询用例
	 */
	public WCase getWCaseById(int id) {
		WCase wcase = null;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWCase icase = sqlSession.getMapper(IWCase.class);
			wcase = icase.getWCaseById(id);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： id ："+id +"  ;"+e);
		} 
		return wcase;
	}

	/**
	 * 以case name 查询同名用例。
	 */
	public List<WCase> getWCaseByName(String caseName) {
		List<WCase> wcases = null;
		try {
			//sqlSession = MybatisUtil.getSqlSession();
			IWCase icase = sqlSession.getMapper(IWCase.class);
			wcases = icase.getWCaseByName(caseName);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： caseName ："+caseName +"  ;"+e);
		}
		return wcases;
	}

	public List<WCase> getWCaseByVersion(int caseVersion) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过一组case id 查询一组 case
	 */
	public List<WCase> getWCaseByIdList(List<Integer> caseIds) {

		List<WCase> wcases = null;
		try {
			//sqlSession = MybatisUtil.getSqlSession();
			IWCase icase = sqlSession.getMapper(IWCase.class);
			wcases = icase.getWCaseByIdList(caseIds);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： caseIds ："+caseIds +"  ;"+e);
		}
		return wcases;
	}
	
	/**
	 * 插入一条包含步骤的用例，注意：最后确保caseNumber 的唯一性，否则添加失败
	 */
	public int addWCase(WCase wCase) {
		int id = -1;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWCase icase = sqlSession.getMapper(IWCase.class);
			int cr = icase.addWCase(wCase);
			Log.info(this.getClass(),"insert 影响行 ： "+ cr);
			id = wCase.getId();		//获得数据库返回的id
			Log.info(this.getClass(),"数据库返回 id : "+id);
			
			//将id 写入wSteep对象的caseId属性
			if(id != -1 && cr > 0){
				
				Log.info(this.getClass(),"包含步骤 "+wCase.getWSteps().size()+ " 条");
				for(WStep steep : wCase.getWSteps()){
					steep.setCaseID(id);
				}
				int res = this.addWStepList(wCase.getWSteps());
				//提交
				if (res != -1){
					sqlSession.commit();
				}else{
					Log.warn(this.getClass(),"未成功插入 wSteep ");
				}
			}else{
				Log.warn(this.getClass(),"未成功插入 wCase ");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return id;
	}

	public int addWCaseList(List<WCase> caseList) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>steep operate <<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 添加一条steep 记录，只在特殊情况使用
	 */
	public int addWStep(WStep wStep){
		int res = -1;
		try {
		//	sqlSession = MybatisUtil.getSqlSession();
			IWCase isteep = sqlSession.getMapper(IWCase.class);
			res = isteep.addWStep(wStep);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}

	public int delWSteepById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 一次插入多条 steep
	 */
	public int addWStepList(List<WStep> wSteps) {
		int res = -1;
		try {
			IWCase isteep = sqlSession.getMapper(IWCase.class);
			res = isteep.addWStepList(wSteps);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>plan operate <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 查询一个测试计划
	 */
	public WPlan getWPlanById(int id) {
		WPlan wPlan = null;
		try {
			IWCase icase = sqlSession.getMapper(IWCase.class);
			wPlan = icase.getWPlanById(id);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"查询失败 ： id ："+id +"  ;"+e);
		} 
		return wPlan;
	}

	/**
	 * 添加一条测试计划
	 */
	public int addWPlan(WPlan wPlan) {
		int res = -1;
		try {
			IWCase iplan = sqlSession.getMapper(IWCase.class);
			res = iplan.addWPlan(wPlan);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}

	/**
	 * 添加一条测试集合
	 */
	public int addWSuite(WSuite wSuite) {
		int res = -1;
		try {
			IWCase isuite = sqlSession.getMapper(IWCase.class);
			res = isuite.addWSuite(wSuite);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}

	/**
	 * 添加一组测试集合
	 */
	public int addWSuiteList(List<WSuite> wSuites) {
		int res = -1;
		try {
			IWCase isuite = sqlSession.getMapper(IWCase.class);
			res = isuite.addWSuiteList(wSuites);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this.getClass(),"添加记录失败  ;"+e);
		} 
		return res;
	}


}
