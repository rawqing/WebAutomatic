package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the w_element database table.
 * 
 */
public class WElement implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String description;

	private String elememtName;

	//bi-directional many-to-one association to WPage
	private WPage wPage;
	private int pageID;

	//bi-directional many-to-one association to WSelector
	private List<WSelector> wSelectors = new ArrayList<WSelector>();

	public WElement() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getElememtName() {
		return this.elememtName;
	}

	public void setElememtName(String elememtName) {
		this.elememtName = elememtName;
	}

	public WPage getWPage() {
		return this.wPage;
	}

	public void setWPage(WPage wPage) {
		this.wPage = wPage;
	}
	
	public int getPageID() {
		return this.pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public List<WSelector> getWSelectors() {
		return this.wSelectors;
	}

	public void setWSelectors(List<WSelector> WSelectors) {
		this.wSelectors = WSelectors;
	}

	public void addWSelector(WSelector WSelector) {
		getWSelectors().add(WSelector);
	}

	public WSelector removeWSelector(WSelector WSelector) {
		getWSelectors().remove(WSelector);
		return WSelector;
	}

	@Override
	public String toString() {
		return "WElement [id=" + id + ", pageID=" + pageID +", description=" + description
				+ ", elememtName=" + elememtName + ", WPage=" + wPage
				+ ", WSelectors=" + wSelectors + "]";
	}

}