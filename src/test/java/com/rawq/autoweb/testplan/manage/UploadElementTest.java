package com.rawq.autoweb.testplan.manage;

import org.testng.annotations.Test;

public class UploadElementTest {

  @Test
  public void uploadElementFromXls() {
	  UploadElement ue = new UploadElement();
	  String file = "/data/work/workspace/autoweb/things/testcase/element.xls";
	//  String file = "D:/rawqingworkspace/autoweb/things/testcase/element.xls";
	 System.out.println(ue.uploadFromXls(file));
  }
}
