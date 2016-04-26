package com.rawq.autoweb.testplan.manage;

import org.testng.annotations.Test;

public class UploadCaseTest {

 // @Test
  public void getFileType() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void steepResolve() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void uploadCaseFromXls() {
	  UploadCase uc = new UploadCase();
	//  String excelFilePath = "/Users/king/Documents/case001.xls";
	  String excelFilePath = "./things/testcase/case001.xls";
	  uc.uploadFromXls(excelFilePath);
	  
  }
}
