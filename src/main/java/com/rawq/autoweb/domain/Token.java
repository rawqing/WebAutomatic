package com.rawq.autoweb.domain;

import com.rawq.autoweb.enums.TokenClass;

public class Token {

	private TokenClass kind;
	private String value;
	
	public Token() {
	}
	
	public Token(TokenClass kind, String value) {
		this.kind = kind;
		this.value = value;
	}
	
	
	public TokenClass getKind() {
		return kind;
	}
	public void setKind(TokenClass kind) {
		this.kind = kind;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return "Token [kind=" + kind + ", value=" + value + "]";
	}
	
	
	
	
}
