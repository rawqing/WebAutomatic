package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the w_steep database table.
 * 
 */
public class WStep implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String pageAlias;

	private String author;

	private Date createTime;

	private String description;

	private String expectation;

	private String step;

	private Date updateTime;

	private int version;

	//bi-directional many-to-one association to WCase
	private int caseID;

	public WStep() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageAlias() {
		return pageAlias;
	}

	public void setPageAlias(String pageAlias) {
		this.pageAlias = pageAlias;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpectation() {
		return expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCaseID() {
		return caseID;
	}

	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	@Override
	public String toString() {
		return "WStep [id=" + id + ", pageAlias=" + pageAlias + ", author=" + author + ", createTime=" + createTime
				+ ", description=" + description + ", expectation=" + expectation + ", step=" + step + ", updateTime="
				+ updateTime + ", version=" + version + ", caseID=" + caseID + "]";
	}

	

	
}