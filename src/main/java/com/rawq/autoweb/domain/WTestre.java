package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the w_testres database table.
 * 
 */
public class WTestre implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String actual;

	private int bugId;

	private int caseId;

	private String expected;

	private int planId;

	private String printscreenPath;

	private int priority;

	private int result;

	private int steepId;

	private int suiteId;

	private String tester;

	private String testNumber;

	private Date testTime;

	private String useData;

	public WTestre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActual() {
		return this.actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public int getBugId() {
		return this.bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public int getCaseId() {
		return this.caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getExpected() {
		return this.expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public int getPlanId() {
		return this.planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getPrintscreenPath() {
		return this.printscreenPath;
	}

	public void setPrintscreenPath(String printscreenPath) {
		this.printscreenPath = printscreenPath;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getResult() {
		return this.result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getSteepId() {
		return this.steepId;
	}

	public void setSteepId(int steepId) {
		this.steepId = steepId;
	}

	public int getSuiteId() {
		return this.suiteId;
	}

	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
	}

	public String getTester() {
		return this.tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getTestNumber() {
		return this.testNumber;
	}

	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}

	public Date getTestTime() {
		return this.testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public String getUseData() {
		return this.useData;
	}

	public void setUseData(String useData) {
		this.useData = useData;
	}

}