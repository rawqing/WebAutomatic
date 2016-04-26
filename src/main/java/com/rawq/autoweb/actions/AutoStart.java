package com.rawq.autoweb.actions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.alibaba.fastjson.JSON;
import com.rawq.autoweb.domain.SentenceRes;
import com.rawq.autoweb.domain.WCase;
import com.rawq.autoweb.domain.WData;
import com.rawq.autoweb.domain.WElement;
import com.rawq.autoweb.domain.WStep;
import com.rawq.autoweb.domain.WSuite;
import com.rawq.autoweb.domain.WTestre;
import com.rawq.autoweb.domain.WZentaobug;
import com.rawq.autoweb.enums.EnVar;
import com.rawq.autoweb.enums.TokenClass;
import com.rawq.autoweb.reflex.Work;
import com.rawq.autoweb.service.RunSentence;
import com.rawq.autoweb.service.imp.WCaseOper;
import com.rawq.autoweb.service.imp.WElementOper;
import com.rawq.autoweb.service.imp.WTestresOper;
import com.rawq.autoweb.utils.GlobalSettings;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.Parser;
import com.rawq.autoweb.utils.SendData;
import com.rawq.autoweb.utils.XMLOper;

import jxl.common.BaseUnit;

public class AutoStart implements RunSentence{
	
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";
	
	private WCaseOper co = new WCaseOper();
	private WElementOper eo = new WElementOper();
	private WTestresOper to = new WTestresOper();
	private DriverAction da = null;
	SubmitZenTao szt = new SubmitZenTao();
	
	private String productId;
	private int testsuiteId;
	private int testPlanId;
	private String pageName;
	private Map<String,String> parma;
	private List<SentenceRes> singleRes;
	private String useData;
	private String testNumber = "";
	
	
	
	// 以下为远程初始化WebDriver的构造方法
	public AutoStart(String browserType,URL url ,String baseUrl,int timeout){
		da = new DriverAction(browserType, url, baseUrl, timeout);
	}
	public AutoStart(String browserType,String ip_port,String baseUrl,int timeout){
		da = new DriverAction(browserType, ip_port, baseUrl, timeout);
	}
	public AutoStart(String browserType,String baseUrl){
		da = new DriverAction(browserType, baseUrl);
	}
	public AutoStart(boolean useRemote){
		da = new DriverAction(useRemote);
	}
	
	
	//以下是构造本地WebDriver实例
	public AutoStart(String baseUrl,int timeout) {
		da = new DriverAction(baseUrl, timeout);
	}
	public AutoStart(String baseUrl) {
		da = new DriverAction(baseUrl);
	}
	public AutoStart() {
		da = new DriverAction();
	}
	

	/**
	 * 用例执行入口
	 * @param testPlanId	测试计划 id
	 * @param productId		同步的zentao产品 id （在提交bug至zentao时需要）
	 */
	@SuppressWarnings("unchecked")
	public void run(int testPlanId,String productId){
		this.testPlanId = testPlanId;
		this.productId = productId;
		testNumber = String.valueOf(testPlanId)+"-"+String.valueOf(new Date().getTime());
		
		for(WSuite testsuite : co.getWPlanById(testPlanId).getWSuites()){					//从指定id的计划中取出所有的测试套件
			List<Integer> caseIdList = JSON.parseArray(testsuite.getCaseList(),int.class);	//将每个套件中的CaseList(存放一组id的字符串)转化为caseid集合
			this.testsuiteId = testsuite.getId();
			Log.info(this.getClass(), "case list : "+ caseIdList);
			
			for(Integer caseId : caseIdList){
				WCase wcase =  co.getWCaseById(caseId);		//通过id获得case
				List<WData> datas = wcase.getWDatas();		//得到该case中的所有data
				try {
					
					if (datas != null && !datas.isEmpty()){		//如果该条用例有参数化data，则循环多次执行该case
						for(WData data : datas){				//迭代执行参数化case
							parma = JSON.parseObject(data.getParameValue(),Map.class);		//将json串类型的data转换为map
							this.excuteCase(wcase, data);
						}
					}else{
						this.excuteCase(wcase, null);			//执行当前case无参数化，只执行一次
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		da.getDriver().quit();
	}
	
	/**
	 * 执行单个用例，并提交bug，写入记录，恢复数据
	 * @param wcase
	 * @param data
	 * @throws Exception
	 */
	private void excuteCase(WCase wcase,WData data) throws Exception{

		for(WStep casestep : wcase.getWSteps()){						//通过case取出包含的所有步骤
			WTestre tr = new WTestre();
			Parser parser = new Parser();
			singleRes = new ArrayList<SentenceRes>();
			pageName = casestep.getPageAlias();							//设置页面别名
			String expected = data == null ? casestep.getExpectation() : data.getExpect();
																		//获得预期，若没有参数化则使用step中的预期，有则用data中的预期
			parser.runSentence(casestep.getStep(), this);				//执行步骤
			File tempFile = this.printscreen(da.getDriver());			//临时保存截屏文件
			
			EnVar.$LAST_RES.setValue(singleRes.size() == 1
					? singleRes.get(0).getRes().toString() 
					: singleRes.get(singleRes.size()-1).getRes().toString());			//将该步骤的执行结果写入环境变量$LAST_RES
			
			singleRes = new ArrayList<SentenceRes>();									//new 一个新的 以接受预期的实际结果
			boolean result = parser.runSentence(expected, this);		//执行预期结果
			String actual = this.actualFormat();
			
			if (!result){		//预期失败则保存截图并上传
				this.outputFile(tempFile, this.createFilePath("./ErrorScreenshot", true, 
						testPlanId+"-"+wcase.getId()+"-"+casestep.getId(), ".png"));	//保存至文件
				
				String errImageUrl = szt.uploadImage(tempFile);							//上传至禅道并得到url地址
				String reproSteps = this.createSteep(wcase.getWSteps(), casestep.getStep(), useData, expected, actual , errImageUrl);
				int bugId = this.submitBug(productId, wcase.getCaseName(), null, reproSteps);
				
				tr.setResult(1);
				tr.setBugId(bugId);
				tr.setPrintscreenPath(errImageUrl);
			}else{
				tr.setResult(0);
			}
			tempFile = null;											//清空临时文件
		
			tr.setTestNumber(testNumber);
			tr.setCaseId(wcase.getId());
			tr.setPlanId(testPlanId);
			tr.setSuiteId(testsuiteId);
			tr.setSteepId(casestep.getId());
			tr.setUseData(useData);
			tr.setExpected(expected);
			tr.setActual(actual);
			tr.setTestTime(new Date());
			tr.setPriority(data == null ? wcase.getPriority() : data.getLevel());
			
			to.addWTestre(tr);		//记录测试结果
			
			//清理数据
			this.useData = null;
			this.parma = null;
		}
		
	}
	
	/**
	 * 格式化实际结果
	 * @return
	 */
	private String actualFormat(){
		String actual = "";
		for(SentenceRes single : singleRes){
			actual += single.getSentence() + " -> " + single.getRes() + " ; ";
		}
		return actual;
	}
	
	/**
	 * 实现错误截图
	 * @param wDriver
	 * @param imageFilePath
	 * @return
	 */
	public String errorPrintscreen(WebDriver wDriver, String imageFilePath){
		File screenFile =  ((TakesScreenshot)wDriver).getScreenshotAs(OutputType.FILE);
		String time = new SimpleDateFormat("YYYYMMdd_HH-mm-ss-ms").format(new Date());
		String imagePath = imageFilePath + "/" + testNumber+time + ".png";
		try {
			FileUtils.copyFile(screenFile, new File(imagePath));
			Log.info(this.getClass(),"成功获得错误截屏，文件保存至 ： "+ imagePath);
			return imagePath;
		} catch (IOException e) {
			Log.error(this.getClass(),"截屏失败  " + e);
			return null;
		}
	}
	
	/**
	 * 根据指定的格式创建一个文件路径字符串
	 * @param dirPath			文件目录路径
	 * @param useDate			是否使用格式化后的时间加入文件名
	 * @param firstFileName		文件的起始名
	 * @param lastFileName		文件的结束名（包括后缀）如无特殊要求的结束名则直接写后缀 ：“.png”
	 * @return
	 */
	public String createFilePath(String dirPath,boolean useDate,String firstFileName,String lastFileName){
		String time = "";
		if (useDate){
			time =  new SimpleDateFormat("YYYYMMdd_HH-mm-ss-ms").format(new Date());
		}
		return dirPath + File.separator + firstFileName + time + lastFileName;
	}
	
	/**
	 * 屏幕截图 返回File对象
	 * @param wDriver
	 * @return
	 */
	public File printscreen(WebDriver wDriver){
		return ((TakesScreenshot)wDriver).getScreenshotAs(OutputType.FILE);
	}
	
	/**
	 * 将File对象输出到指定路径的文件
	 * @param file
	 * @param filePath
	 */
	public void outputFile(File file,String filePath){
		try {
			FileUtils.copyFile(file, new File(filePath));
			Log.info(this.getClass(),"文件成功保存至 ： "+ filePath);
		} catch (IOException e) {
			e.printStackTrace();
			Log.error(this.getClass(),"写入文件失败  " + e);
		}
		
	}
		
	/**
	 * 向bug管理系统(这里代指禅道)提交bug，并返回bug编号
	 * @param productId
	 * @param caseName
	 * @param module
	 * @param steps
	 * @return bug id
	 */
	public int  submitBug(String productId,String caseName,String module,String steps){
		String bugUrl = GlobalSettings.bugUri.replace("*", String.valueOf(productId));
		bugUrl = "http://" + GlobalSettings.zentaoServer + bugUrl;
		WZentaobug wzb = new WZentaobug();
		wzb.setProduct(productId);
		
		if (module != null && !"".equals(module)){
			wzb.setModule(module);		// default use module 1
		}
		
		wzb.setAssignedTo(GlobalSettings.sendUser);
		wzb.setBrowser(GlobalSettings.browserType);
		wzb.setTitle(caseName+"-异常");
		wzb.setMailto(GlobalSettings.sendUser);
		wzb.setSteps(steps);
		
		
		int wBugId = to.addWZentaobug(wzb);		//提交到本地数据库
		int bugId = szt.getZenTaoBugId(szt.submitBug(bugUrl, wzb));
		
		return bugId == 0?(0-wBugId):bugId;		//如果
		
	}
	
	/**
	 * 创建bug的重现步骤
	 * @param wsteeps
	 * @param errSteep
	 * @param useData
	 * @param expected
	 * @param actual
	 * @param errImageUrl
	 * @return
	 */
	public String createSteep(List<WStep> wsteeps,String errStepDesc,String useData,String expected 
			,String actual, String errImageUrl){
		String steep = "<p><span style='color:#006600;'>[步骤]</span></p>";
		for(int i = 0;i < wsteeps.size(); i++){
			steep = steep + "<p>"+(i+1)+"、"+wsteeps.get(i).getStep()+"</p>";
		}
		steep = steep + "<p><span style='color:#006600;'>[结果]</span></p>";
		
		if (useData == null){
			steep = steep +"<p>error step ："+errStepDesc+" </p><p> actual："+actual+"</p>";
			steep = steep+"<p>如下图：</p><p><img src='"+errImageUrl+"' alt='' /></p>";
			
			steep = steep + "<p><span style='color:#006600;'>[期望]</span></p>";
			steep = steep +"<p>"+expected+"</p>";
			
		}else{
			useData = "".equals(useData)?"'空字符串'":useData; 
			steep = steep +"<p>error step ："+errStepDesc+" ;  use data ( "+useData+" )  </p><p> actual："+actual+"</p>";
			steep = steep+"<p>如下图：</p><p><img src='"+errImageUrl+"' alt='' /></p>";
			
			steep = steep + "<p><span style='color:#006600;'>[期望]</span></p>";
			steep = steep +"<p>"+expected+"</p>";
		}
		return steep;
	}
	
	/**
	 * 实现接口的方法，提供外部调用，非直接调用方法
	 */
	@Override
	public Object getResults(Map<TokenClass, String> st) {
		Object res = null;
		String idata = st.get(TokenClass.data);
		try {
			String elementName = st.get(TokenClass.element);
			WElement element = null;
			if (elementName != null){
				 element = eo.getWElementByName(pageName, elementName);
				 if (element == null){
					 throw new Exception("未定义的对象 ：pageName > "+pageName+" , elementName > "+ elementName);
				 }
				 if (idata == null ){
						
					 res = Work.executMethod(st.get(TokenClass.action).trim(),da, element);	//执行无输入数据的步骤
				 }else {
					 useData = SendData.getUltimateString(idata, parma);
					 res = Work.executMethod(st.get(TokenClass.action).trim(),da, element,useData);	//执行有输入数据的步骤
				 }
			}else{
				if (idata == null ){
					
					res = Work.executMethod(st.get(TokenClass.action).trim(),da);	//执行无输入数据的步骤
				}else {
					useData = SendData.getUltimateString(idata, parma);
					res = Work.executMethod(st.get(TokenClass.action).trim(),da,useData);	//执行有输入数据的步骤
				}
			}
		} catch (Exception e) {
			res = false;
			Log.error(this.getClass(), "方法执行异常" + e);
		}
		
		this.singleStepRes(st, res);			//写出执行结果记录
		return res;
	}
	
	/**
	 * 处理每个单步的执行结果 依赖于 getResults(Map<TokenClass, String> st) 方法的调用
	 * @param st
	 * @param res
	 */
	private void singleStepRes(Map<TokenClass, String> st,Object res){
		String sentence = "";
		for(Entry<TokenClass, String> en : st.entrySet()){
			if (en.getKey() == TokenClass.data){
				sentence += " {\""+useData + "\"}";
			}else{
				sentence += " "+en.getValue();
			}
		}
		
		singleRes.add(new SentenceRes(sentence , res));
	}
}
