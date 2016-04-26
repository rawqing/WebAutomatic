package com.rawq.autoweb.domain;

import java.io.Serializable;


/**
 * The persistent class for the w_selector database table.
 * 
 */
public class WSelector implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String selector;

	private String selectPath;

	//bi-directional many-to-one association to WElement
	private int elementID;

	public WSelector() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSelector() {
		return this.selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getSelectPath() {
		return this.selectPath;
	}

	public void setSelectPath(String selectPath) {
		this.selectPath = selectPath;
	}

	public int getElementID() {
		return this.elementID;
	}

	public void setElementID(int elementID) {
		this.elementID = elementID;
	}

	@Override
	public String toString() {
		return "WSelector [id=" + id + ", selector=" + selector
				+ ", selectPath=" + selectPath + ", elementID=" + elementID + "]";
	}

}