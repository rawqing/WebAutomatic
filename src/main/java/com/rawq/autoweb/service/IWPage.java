package com.rawq.autoweb.service;

import java.util.List;
import java.util.Map;

import com.rawq.autoweb.domain.WPage;

public interface IWPage {

	WPage getWPageById(int id);
	
	WPage getWPageByAlias(String alias);
	
	int getWPageIdByAlias(String alias);
	
	List<Map<?, ?>> getPageMapList();
	
	int addWPage(WPage wPage);
	
	int addWPageList(List<WPage> wPages);
	
}
