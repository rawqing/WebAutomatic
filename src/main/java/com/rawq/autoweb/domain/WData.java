package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the w_data database table.
 * 
 */
public class WData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int caseId;
	private Date createTime;
	private String expect;
	private int level;
	private String parameValue;
	private Date updateTime;

	public WData() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getCaseId() {
		return this.caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}


	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getExpect() {
		return this.expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}


	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public String getParameValue() {
		return this.parameValue;
	}

	public void setParameValue(String parameValue) {
		this.parameValue = parameValue;
	}


	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public String toString() {
		return "WData [id=" + id + ", caseId=" + caseId + ", createTime=" + createTime + ", expect=" + expect
				+ ", level=" + level + ", parameValue=" + parameValue + ", updateTime=" + updateTime + "]";
	}

	
}