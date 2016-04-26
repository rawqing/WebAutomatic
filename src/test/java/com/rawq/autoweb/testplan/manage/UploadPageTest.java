package com.rawq.autoweb.testplan.manage;

import org.testng.annotations.Test;

public class UploadPageTest {

  @Test
  public void uploadFromXls() {
	  UploadPage up = new UploadPage();
	  String excelFilePath = "D:/rawqingworkspace/autoweb/things/testcase/page.xls";
	  up.uploadFromXls(excelFilePath);
  }
}
