package com.rawq.autoweb.testplan.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WElement;
import com.rawq.autoweb.domain.WSelector;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.service.Upload;
import com.rawq.autoweb.service.imp.WCaseOper;
import com.rawq.autoweb.service.imp.WElementOper;
import com.rawq.autoweb.utils.ExcelOper;
import com.rawq.autoweb.utils.GlobalSettings;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.Msgs;

public class UploadElement implements Upload{

	/**
	 * 判断上传文件的类型
	 * @param filePath
	 * @return Msgs
	 */
	public Msgs getFileType(String filePath){
		String [] s = filePath.split(".");
		String type = s[s.length-1];
		
		if("xml".equals(type) || "xls".equals(type) || "csv".equals(type)){
			return new Msgs(1, true, type);
		}
		//不支持的类型
		return new Msgs(-3, false ,type);
	}
	
	
	public Msgs uploadFromXls(String excelFilePath){
		WElementOper weo = new WElementOper();
		Map<String, Integer> pageMap = weo.getPageMap();
		ExcelOper eo = new ExcelOper();
		Sheet sheet = eo.getSheet(eo.getWorkbook(excelFilePath), 0);	//默认取Sheet1
		int err = 0;
		
		if(sheet == null){
			return new Msgs(-100, false, "未获取到sheet");
		}
		Map<Integer, List<String>> allSheetData = eo.getAllRow(sheet);
		
		try {
			for(int key=1; key<allSheetData.size(); key++){				//key=1 ：从第二行开始 去除表头
				Log.info(this.getClass(),"当前导入 ：（ "+key+" / "+(allSheetData.size()-1)+" )");
				
				String pageName = allSheetData.get(key).get(GlobalSettings.ELEMENT_PAGE);
				if (!pageMap.containsKey(pageName)){
					return new Msgs(-100,false,"没有匹配的页面："+pageName);
				}
				//查询该页面是否已存在该元素，如果存在则跳过（即同页面不可存在同名元素）
				String elementName = allSheetData.get(key).get(GlobalSettings.ELEMENT_NAME);
				int id = weo.getWElementIdByName(pageName, elementName);
				if (id > 0){
					Log.warn(this.getClass(),"该元素：("+pageName+" >>> "+elementName+" )已存在，请勿重复添加。elementId ："+id);
					err+=1;
					continue;
				}
				
				WElement welement = new WElement();
				welement.setPageID(pageMap.get(pageName));
				welement.setElememtName(elementName);
				welement.setDescription(allSheetData.get(key).get(GlobalSettings.ELEMENT_DESC));
			
				int i = GlobalSettings.ELEMENT_SELECTOR_1;
				while(i<=GlobalSettings.ELEMENT_PATH_3){
					
					if (allSheetData.get(key).get(i)!=null && !"".equals(allSheetData.get(key).get(i))
							&& allSheetData.get(key).get(i+1)!=null && !"".equals(allSheetData.get(key).get(i+1))){
						WSelector selector = new WSelector();
						selector.setSelector(allSheetData.get(key).get(i));
						selector.setSelectPath(allSheetData.get(key).get(i+1));
						welement.addWSelector(selector);
					}
					i+=2;
				}
				//写入数据库
				weo.addWElement(welement);
			}
			
		} catch (Exception e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
			return new Msgs(-200, false, "上传失败");
		}
		return new Msgs(1, true, "共 "+(allSheetData.size()-1)+" 条记录 ，"+"失败 "+ err+ " 条");
		
	}
	

	public Msgs uploadFromCsv(String csvFilePath) {
		// TODO Auto-generated method stub
		return null;
	}
}
