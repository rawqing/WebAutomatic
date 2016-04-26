package com.rawq.autoweb.utils;


/**
 * 提供操作excel的方法 ，包括读和写
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelOper {

	/**
	 * get the workbook
	 * @param excelFilePath
	 * @return
	 */
	public Workbook getWorkbook(String excelFilePath){
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(excelFilePath));
			Log.info(this.getClass(),workbook);
		} catch (Exception e) {
			Log.error(this.getClass(),e);
		}
		return workbook;
	}
	/**
	 * get the sheet
	 * @param workbook
	 * @param sheetName
	 * @return Sheet
	 */
	public Sheet getSheet(Workbook workbook,String sheetName){
		return workbook.getSheet(sheetName);
	}
	/**
	 * get sheet by sheet index (index start 0)
	 * @param workbook
	 * @param sheetIndex
	 * @return
	 */
	public Sheet getSheet(Workbook workbook,int sheetIndex){
		return workbook.getSheet(sheetIndex);
	}
	/**
	 * close workbook
	 * @param workbook
	 */
	public void closeWorkBook(Workbook workbook){
		workbook.close();
	}
	/**
	 * get the total columns
	 * @param sheet
	 * @return
	 */
	public int getTotalColumns(Sheet sheet){
		return sheet.getColumns();
	}
	/**
	 * get the total rows
	 * @param sheet
	 * @return
	 */
	public int getTotalRows(Sheet sheet){
		return sheet.getRows();
	}

	/**
	 * 得到单元格的值
	 */
	public String getCellData(Sheet sheet,int column, int row){
		return (String)sheet.getCell(column, row).getContents();
	}
	
	/**
	 * 得到一行的数据
	 */
	public List<String> getRowData(Sheet sheet,int rowIndex){
		List<String> rowData = new ArrayList<String>();
		for(int column = 0; column < getTotalColumns(sheet); column++){
			rowData.add((String)sheet.getCell(column, rowIndex).getContents());
		}
		Log.info(this.getClass(),"获得一行数据： "+rowData);
		return rowData;
	}
	
	
	/**
	 * 得到符合条件的所有行
	 * @param excelFilePath
	 * @param sheetName
	 * @param condition 给定的条件
	 * @param conditionColumn 条件所在列
	 * @return
	 */
	public Map<Integer,List<String>> getAssignRows(Sheet sheet,String condition,int conditionColumn){
		Map<Integer,List<String>> rowsData = new LinkedHashMap<Integer,List<String>>();
		List<String> rowData = null;
		for(int row=0;row < sheet.getRows() ;row++){
			String data = sheet.getCell(conditionColumn, row).getContents();
			
			if(data!=null && condition.equals(data)){
				rowData = new ArrayList<String>();  //一定要在这里new
				
				for(int column = 0 ;column < getTotalColumns(sheet); column++){
					rowData.add((String)sheet.getCell(column,row).getContents());
					
				}
				rowsData.put(row, rowData);
				data = null;
				rowData = null;
			}
		}
		return rowsData;
	}
	/**
	 * 在指定的工作表，指定列中查找指定的数据 ，找到则返回true
	 * @param excelFilePath
	 * @param sheetName
	 * @param condition
	 * @param conditionColumn
	 * @return
	 */
	public boolean isExist(Sheet sheet,String condition,int conditionColumn){
		
		for(int row=0;row < sheet.getRows() ;row++){
			String data = sheet.getCell(conditionColumn, row).getContents();
			
			if(condition.equals(data)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取指定sheet中的所有内容
	 * @param sheet
	 * @return
	 */
	public Map<Integer,List<String>> getAllRow(Sheet sheet){
		Map<Integer,List<String>> allRows = new HashMap<Integer,List<String>>();
		
		for(Integer row = 0; row<getTotalRows(sheet) ; row++){
			List<String> rowData = new ArrayList<String>();
			
			for(Integer column = 0; column < getTotalColumns(sheet); column++){
				rowData.add((String)sheet.getCell(column,row).getContents());
			}
			allRows.put(row, rowData);
		}
		return allRows;
	}
	
	/**
	 * 向Excel表中写入数据，如果需要连续写入，请不要使用该方法
	 * @param fileName
	 * @param sheetName
	 * @param column
	 * @param row
	 * @param date
	 */
	public void writeExcel(String fileName,String sheetName,int column,int row,String date){
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);		//获取到文件
			POIFSFileSystem fs = new POIFSFileSystem(fis);		//得到文件信息
			HSSFWorkbook wb = new HSSFWorkbook(fs);	//得到工作薄
		
			HSSFSheet sheet = wb.getSheet(sheetName);	//得到工作表
			HSSFRow hRow = sheet.getRow(row);		//得到行
			HSSFCell cell = hRow.createCell(column);	//在得到的行上创建一个单元格
			cell.setCellValue(date);	//将传入的数据放入该单元格
			
			FileOutputStream out = new FileOutputStream(fileName);	//创建写入文件对象
			out.flush();	//清空缓冲区
			wb.write(out);	//执行写入
			//关闭资源
			wb.close();
			out.close();
			fis.close();
		} catch (Exception e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * get HSSFWorkbook
	 * @param excelFilePath
	 * @return
	 */
	public HSSFWorkbook getHSSFWorkbook(String excelFilePath){
		HSSFWorkbook hworkbook = null;
		try {
			hworkbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFilePath)));
		} catch (FileNotFoundException e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
		} catch (IOException e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
		}
		return hworkbook;
	}
	
	
	/**
	 * 将数据写入文件并关闭,该方法需要使用跟InputStream同一个HSSFWorkbook对象。
	 * @param excelFilePath
	 * @param hworkbook
	 */
	public void writeANDclose(String excelFilePath,HSSFWorkbook hworkbook){
		FileOutputStream out;
		try {
			out = new FileOutputStream(excelFilePath);		//创建写入文件对象
			out.flush();	//清空缓冲区
			hworkbook.write(out);	//执行写入
			//关闭资源
			hworkbook.close();
			out.close();
		} catch (Exception e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
		}	
	}
	/**
	 * get HSSFSheet
	 * @param hworkbook
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet getHSSFSheet(HSSFWorkbook hworkbook, String sheetName){
		return hworkbook.getSheet(sheetName);
	}
	/**
	 * get HSSFSheet by sheet index (index start 0)
	 * @param hworkbook
	 * @param sheetIndex
	 * @return
	 */
	public HSSFSheet getHSSFSheet(HSSFWorkbook hworkbook, int sheetIndex){
		return hworkbook.getSheetAt(sheetIndex);
	}
	/**
	 * 向单元格内放入数据(下标row、column 都从0开始)
	 * @param sheet
	 * @param row
	 * @param column
	 * @param date
	 */
	public void putData(HSSFSheet sheet ,int row, int column,String date){
		HSSFRow hRow = sheet.getRow(row);		//得到行
		HSSFCell cell = hRow.createCell(column);	//在得到的行上创建一个单元格
		cell.setCellValue(date);	//将传入的数据放入该单元格
	}
}


