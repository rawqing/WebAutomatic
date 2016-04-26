package com.rawq.autoweb.utils;

import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelOperTest {
	private ExcelOper eo = new ExcelOper();
	
  //@Test
  public void closeWorkBook(Workbook workbook) {
	  eo.closeWorkBook(workbook);
  }

 // @Test
  public void getAllRow() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void getAssignRows() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void getCellData() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void getRowData() {
    throw new RuntimeException("Test not implemented");
  }

 // @Test
  public void getSheet() {
    throw new RuntimeException("Test not implemented");
  }

 // @Test
  public void getTotalColumns() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void getTotalRows() {
    throw new RuntimeException("Test not implemented");
  }

  //@Test
  public void getWorkbook() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void writeANDclose() {
	  String excelFilePath = "D:/rawqingworkspace/autotesting/testCase/testCase001.xls";
	  
	  HSSFWorkbook hSSFWorkbook = eo.getHSSFWorkbook(excelFilePath);
	  HSSFSheet hssfSheet = eo.getHSSFSheet(hSSFWorkbook, 0);
	  eo.putData(hssfSheet, 8, 10, "hello world 8");
	  eo.putData(hssfSheet, 10, 10, "hello world 10");
	  eo.putData(hssfSheet, 9, 10, "hello world 9");
	  eo.writeANDclose(excelFilePath, hSSFWorkbook);
  }
}
