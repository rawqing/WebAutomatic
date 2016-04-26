package com.rawq.autoweb.testplan.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import com.rawq.autoweb.domain.WElement;
import com.rawq.autoweb.domain.WPage;
import com.rawq.autoweb.domain.WSelector;
import com.rawq.autoweb.service.Upload;
import com.rawq.autoweb.service.imp.WElementOper;
import com.rawq.autoweb.utils.ExcelOper;
import com.rawq.autoweb.utils.GlobalSettings;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.Msgs;

public class UploadPage implements Upload {

	public Msgs uploadFromXls(String excelFilePath) {
		WElementOper weo = new WElementOper();
		ExcelOper eo = new ExcelOper();
		Sheet sheet = eo.getSheet(eo.getWorkbook(excelFilePath), 0);	//默认取Sheet1
		try {
			if(sheet == null){
				return new Msgs(-100, false, "未获取到sheet");
			}
			Map<Integer, List<String>> allSheetData = eo.getAllRow(sheet);
			List<WPage> wPages = new ArrayList<WPage>();
			for(int key=1; key<allSheetData.size(); key++){				//key=1 ：从第二行开始 去除表头
				Log.info(this.getClass(),"当前导入 ：（ "+key+" / "+(allSheetData.size()-1)+" )");
				
				WPage page = new WPage();
				page.setAlias(allSheetData.get(key).get(GlobalSettings.PAGE_ALIAS));
				page.setTitle(allSheetData.get(key).get(GlobalSettings.PAGE_TITLE));
				page.setUri(allSheetData.get(key).get(GlobalSettings.PAGE_URI));
				
				wPages.add(page);
			}
			weo.addWPageList(wPages);
			
		} catch (Exception e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
			return new Msgs(-200, false, "上传失败");
		}
		return new Msgs(1, true, "上传成功");
		
	}

	public Msgs uploadFromCsv(String csvFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

}
