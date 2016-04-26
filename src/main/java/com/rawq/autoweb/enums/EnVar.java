package com.rawq.autoweb.enums;

public enum EnVar {

	$LAST_RES("LAST_RES"), $CUSTOM("CUSTOM");
	
	private String value;

	private EnVar(String value) {
		this.value = value;
	}

	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
