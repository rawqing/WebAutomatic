package com.rawq.autoweb.testplan.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import jxl.Sheet;

import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.service.Upload;
import com.rawq.autoweb.service.imp.WCaseOper;
import com.rawq.autoweb.service.imp.WElementOper;
import com.rawq.autoweb.utils.ExcelOper;
import com.rawq.autoweb.utils.GlobalSettings;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.Msgs;
import com.rawq.autoweb.utils.XMLOper;

public class UploadCase implements Upload{
	
	Map<String, String> actionMapping ;
	
	public UploadCase(){
		this.actionMapping = this.getActionMapping();
	}

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
		ExcelOper eo = new ExcelOper();
		WCaseOper wo = new WCaseOper();
		Sheet sheet = eo.getSheet(eo.getWorkbook(excelFilePath), 0);	//默认取Sheet1
		try {
			if(sheet == null){
				return new Msgs(-100, false, "未获取到sheet");
			}
			Map<Integer, List<String>> allSheetData = eo.getAllRow(sheet);
			String caseNo = null;
			WCase wcase = null;
			for(int key=1; key<allSheetData.size(); key++){
				Log.info(this.getClass(),"当前导入 ：（ "+key+" / "+(allSheetData.size()-1)+" )");
				//如果不是同一个caseNo 则创建一个新的
				String mycaseNo = allSheetData.get(key).get(GlobalSettings.CASE_NO);
				if (!(mycaseNo).equals(caseNo)){
					caseNo = mycaseNo;	//获取到一个caseNo
					
					if(caseNo == null || "".equals(caseNo)){
						return new Msgs(-100, false, "第 "+key+" 行用例编号为空，请检查");
					}
					if (wcase != null){
						wo.addWCase(wcase);		//写入数据库
						wcase = null;			//清空对象
					}
					wcase = new WCase();
					wcase.setCaseNumber(caseNo);
					wcase.setPremise(allSheetData.get(key).get(GlobalSettings.CASE_PREMISE));
					wcase.setModule(allSheetData.get(key).get(GlobalSettings.CASE_MODULE));
					wcase.setCaseName(allSheetData.get(key).get(GlobalSettings.CASE_NAME));
					wcase.setPriority(Integer.valueOf(allSheetData.get(key).get(GlobalSettings.CASE_PRIORITY)));
					wcase.setVersion(1); 		//新增数据默认版本为1（预留功能，目前只作为更新次数使用，
															//后期存在多版本时请删除数据库里caseNumber的唯一约束）
				}
					WStep wstep = new WStep();
					wstep.setStep(allSheetData.get(key).get(GlobalSettings.CASE_STEPS));
					
					wstep.setExpectation(allSheetData.get(key).get(GlobalSettings.CASE_EXPECTATION)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                );
					wstep.setVersion(1); 			//新增数据默认版本为1
					wstep.setPageAlias(allSheetData.get(key).get(GlobalSettings.CASE_PAGE));
					
					wcase.addWStep(wstep);
					
				if (key == allSheetData.size()-1){
					wo.addWCase(wcase);		//写入数据库
				}
			}
		} catch (Exception e) {
			Log.error(this.getClass(),e);
			e.printStackTrace();
			return new Msgs(-200, false, "上传失败");
		}
		return new Msgs(1, true, "上传成功");
	}
	
	/**
	 * steep 拆分器
	 */
	public Msgs steepResolve(String steep){
		
		for(String key : actionMapping.keySet()){
			Map<String ,String> res = new HashMap<String ,String>();
			
			if (steep.startsWith(key)){
				res.put("action", actionMapping.get(key));
				try {
					res.put("element", steep.substring(steep.indexOf("{")+1,steep.indexOf("}")));
				} catch (Exception e) {
					Log.warn(this.getClass(),"该步骤中不包含元素" + e);
				}
				return new Msgs(1, true ,res);
			}
		}
		return new Msgs(-5 ,false ,"没有配置的映射");
	}
	/**
	 * 读取action映射文件。
	 * @return
	 */
	private Map<String, String> getActionMapping(){
		String actionMapFile = GlobalSettings.class.getClassLoader().getResource("actionMapping.xml").getPath();
		
		Map<String ,String> actionMapping = new HashMap<String ,String>();
		Log.info(this.getClass(),"开始读取配置文件: "+actionMapFile);
		try {
			List<Element> mapping = XMLOper.getChildren(XMLOper.getRoot(
					XMLOper.getDocument(actionMapFile)));
			for(int i=0; i<mapping.size(); i++){
				actionMapping.put(mapping.get(i).attributeValue("key"),mapping.get(i).attributeValue("action"));
			}
		} catch (Exception e) {
			Log.error(this.getClass(),"读取配置文件 "+actionMapFile+" 失败 "+e);
			e.printStackTrace();
		}
		return actionMapping;
	}

	
	public Msgs uploadFromCsv(String csvFilePath) {
		// TODO Auto-generated method stub
		return null;
	}
}
