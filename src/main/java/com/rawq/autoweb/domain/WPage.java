package com.rawq.autoweb.domain;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the w_page database table.
 * 
 */
public class WPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String alias;

	private String title;

	private String uri;

	//bi-directional many-to-one association to WElement
	private List<WElement> WElements;

	public WPage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<WElement> getWElements() {
		return this.WElements;
	}

	public void setWElements(List<WElement> WElements) {
		this.WElements = WElements;
	}

	public WElement addWElement(WElement WElement) {
		getWElements().add(WElement);
		return WElement;
	}

	public WElement removeWElement(WElement WElement) {
		getWElements().remove(WElement);
		return WElement;
	}

	@Override
	public String toString() {
		return "WPage [id=" + id + ", alias=" + alias + ", title=" + title
				+ ", uri=" + uri + ", WElements=" + WElements + "]";
	}

}