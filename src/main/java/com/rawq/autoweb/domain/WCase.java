package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the w_case database table.
 * 
 */
public class WCase implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String caseNumber;

	private String author;

	private String caseName;

	private Date createTime;

	private String description;

	private String module;

	private String premise;

	private int priority;

	private Date updateTime;

	private int version;

	//bi-directional many-to-one association to WSteep
	private List<WStep> wSteps = new ArrayList<WStep>();
	
	private List<WData> wDatas = new ArrayList<WData>();

	public WCase() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaseNumber() {
		return this.caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPremise() {
		return this.premise;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<WStep> getWSteps() {
		return this.wSteps;
	}

	public void setWSteps(List<WStep> WSteps) {
		this.wSteps = WSteps;
	}

	public void addWStep(WStep WStep) {
		this.getWSteps().add(WStep);
	}

	public boolean removeWStep(WStep WStep) {
		return this.getWSteps().remove(WStep);
	}
	
	
	public List<WData> getWDatas() {
		return wDatas;
	}

	public void setWDatas(List<WData> wDatas) {
		this.wDatas = wDatas;
	}

	@Override
	public String toString() {
		return "WCase [id=" + id + ", caseNumber=" + caseNumber + ", author=" + author + ", caseName=" + caseName
				+ ", createTime=" + createTime + ", description=" + description + ", module=" + module + ", premise="
				+ premise + ", priority=" + priority + ", updateTime=" + updateTime + ", version=" + version
				+ ", wSteeps=" + wSteps + ", wDatas=" + wDatas + "]";
	}

}