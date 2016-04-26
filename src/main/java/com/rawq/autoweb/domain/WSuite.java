package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the w_suite database table.
 * 
 */
public class WSuite implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String suietName;
	
	private int planId;
	
	private String caseList;

	private String version;

	private Date createTime;
	
	private Date updateTime;

	private String description;
	
	private String author;
	

	public WSuite() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSuietName() {
		return suietName;
	}


	public void setSuietName(String suietName) {
		this.suietName = suietName;
	}


	public int getPlanId() {
		return planId;
	}


	public void setPlanId(int planId) {
		this.planId = planId;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getCaseList() {
		return caseList;
	}


	public void setCaseList(String caseList) {
		this.caseList = caseList;
	}


	@Override
	public String toString() {
		return "WSuite [id=" + id + ", suietName=" + suietName + ", planId="
				+ planId + ", caseList=" + caseList + ", version=" + version
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", description=" + description + ", author=" + author + "]";
	}



}