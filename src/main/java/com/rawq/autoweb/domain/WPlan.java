package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the w_suite database table.
 * 
 */
public class WPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String planName;

	private String author;

	private String commitVersion;

	private Date createTime;

	private String description;

	private Date planExecutionTime;
	
	private List<WSuite> wSuites = new ArrayList<WSuite>();

	public WPlan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCommitVersion() {
		return this.commitVersion;
	}

	public void setCommitVersion(String commitVersion) {
		this.commitVersion = commitVersion;
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

	public Date getPlanExecutionTime() {
		return this.planExecutionTime;
	}

	public void setPlanExecutionTime(Date planExecutionTime) {
		this.planExecutionTime = planExecutionTime;
	}

	public List<WSuite> getWSuites() {
		return wSuites;
	}

	public void setWSuites(List<WSuite> wSuites) {
		this.wSuites = wSuites;
	}

	public void addWSuites(WSuite wSuite){
		this.getWSuites().add(wSuite);
	}
	
	public boolean removeWSuites(WSuite wSuite){
		return this.getWSuites().remove(wSuite);
	}

	@Override
	public String toString() {
		return "WPlan [id=" + id + ", planName=" + planName + ", author="
				+ author + ", commitVersion=" + commitVersion + ", createTime="
				+ createTime + ", description=" + description
				+ ", planExecutionTime=" + planExecutionTime + ", wSuites="
				+ wSuites + "]";
	}

	
}