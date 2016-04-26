package com.rawq.autoweb.service;

import java.util.List;

import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WPlan;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.domain.WSuite;

public interface IWCase {

	/***************SELECT***************/
	/**
	 * 通过caseid 获取唯一一条case
	 * case 必须要有 steep，没有步骤的用例是没有意义的。将不被查询出来
	 * @param id
	 * @return
	 */
	WCase getWCaseById(int id);
	
	/**
	 * 通过case name 获取到同名的 case list
	 * case 必须要有 steep，没有步骤的用例是没有意义的。将不被查询出来
	 * @param caseName
	 * @return
	 */
	List<WCase> getWCaseByName(String caseName);
	
	/**
	 * 通过case 的 version 获取一组 case
	 * case 必须要有 steep，没有步骤的用例是没有意义的。将不被查询出来
	 * @param caseVersion
	 * @return
	 */
	List<WCase> getWCaseByVersion(int caseVersion);
	
	/**
	 * 通过给定的一组 case id 获取一组 case
	 * case 必须要有 steep，没有步骤的用例是没有意义的。将不被查询出来
	 * @param caseIds
	 * @return
	 */
	List<WCase> getWCaseByIdList(List<Integer> caseIds);
	
	WPlan getWPlanById(int id);
	
	/****************INSERT********************/
	
	
	
	int addWCase(WCase wCase);
	
	int addWCaseList(List<WCase> caseList);
	
	int addWStep(WStep wStep);
	
	int addWStepList(List<WStep> wSteps);
	
	int addWPlan(WPlan wPlan);
	
	int addWSuite(WSuite wSuite);
	
	int addWSuiteList(List<WSuite> wSuites);
	
	/****************UPDATE********************/
	
	
	/****************DELETE********************/
}
