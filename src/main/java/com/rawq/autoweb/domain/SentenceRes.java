package com.rawq.autoweb.domain;

public class SentenceRes {

	private String sentence;
	
	private Object res;
	
	
	public SentenceRes(){
		
	}
	public SentenceRes(String sentence, Object res) {
		this.sentence = sentence;
		this.res = res;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public Object getRes() {
		return res;
	}

	public void setRes(Object res) {
		this.res = res;
	}
	
	@Override
	public String toString() {
		return "SentenceRes [sentence=" + sentence + ", res=" + res + "]";
	}
	
	
}
