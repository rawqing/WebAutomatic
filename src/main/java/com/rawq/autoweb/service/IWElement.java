package com.rawq.autoweb.service;

import java.util.List;

import com.rawq.autoweb.domain.WElement;
import com.rawq.autoweb.domain.WSelector;

public interface IWElement {
	
	WElement getWElementById(int id);
	
	WElement getWElementByName(String pageName,String elementName);
	
	int getWElementIdByName(String pageName,String elementName);
	
	int addWElement(WElement wElement);
	
	int addWSelector(WSelector wSelector);
	
	int addWSelectorList(List<WSelector> wSelectors);

}
