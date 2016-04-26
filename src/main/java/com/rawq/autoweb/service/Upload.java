package com.rawq.autoweb.service;

import com.rawq.autoweb.utils.Msgs;

public interface Upload {

	Msgs uploadFromXls(String excelFilePath);
	
	Msgs uploadFromCsv(String csvFilePath);
}
