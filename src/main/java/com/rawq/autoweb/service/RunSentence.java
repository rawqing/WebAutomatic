package com.rawq.autoweb.service;

import java.util.Map;

import com.rawq.autoweb.enums.TokenClass;

public interface RunSentence {

	Object getResults(Map<TokenClass,String> st);
}
