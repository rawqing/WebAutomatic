package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the w_zentaobug database table.
 * 
 */
public class WZentaobug implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String case_ = "0";

	private String assignedTo ;

	private String browser;

	private String caseVersion;

	private String files;

	private String keywords;

	private String labels;

	private String mailto;

	private String module = "1";

	private String openedBuild = "trunk";

	private String os;

	private String pri = "0";

	private String product = "1"; 	//产品id

	private String project = "1";	//项目id

	private String result = "0";

	private String severity = "3";	//严重等级

	private String steps = "[步骤：]";

	private String story;

	private String task = "0";

	private String testtask;

	private String title = "";	//标题

	private String type = "others";
	
	private Date createtime;
	
	public WZentaobug() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCase_() {
		return this.case_;
	}

	public void setCase_(String case_) {
		this.case_ = case_;
	}

	public String getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getCaseVersion() {
		return this.caseVersion;
	}

	public void setCaseVersion(String caseVersion) {
		this.caseVersion = caseVersion;
	}

	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLabels() {
		return this.labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getMailto() {
		return this.mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOpenedBuild() {
		return this.openedBuild;
	}

	public void setOpenedBuild(String openedBuild) {
		this.openedBuild = openedBuild;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPri() {
		return this.pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getSteps() {
		return this.steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getStory() {
		return this.story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTesttask() {
		return this.testtask;
	}

	public void setTesttask(String testtask) {
		this.testtask = testtask;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "WZentaobug [id=" + id + ", case_=" + case_ + ", assignedTo=" + assignedTo + ", browser=" + browser
				+ ", caseVersion=" + caseVersion + ", files=" + files + ", keywords=" + keywords + ", labels=" + labels
				+ ", mailto=" + mailto + ", module=" + module + ", openedBuild=" + openedBuild + ", os=" + os + ", pri="
				+ pri + ", product=" + product + ", project=" + project + ", result=" + result + ", severity="
				+ severity + ", steps=" + steps + ", story=" + story + ", task=" + task + ", testtask=" + testtask
				+ ", title=" + title + ", type=" + type + ", createtime=" + createtime + "]";
	}

	
}