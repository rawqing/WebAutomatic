package com.rawq.autoweb.actions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.rawq.autoweb.domain.WElement;
import com.rawq.autoweb.domain.WSelector;
import com.rawq.autoweb.reflex.Work;
import com.rawq.autoweb.utils.GlobalSettings;
import com.rawq.autoweb.utils.Log;
/**
 * 作为一个超类，后续的自定义action，都将继承该类。
 * @author King
 *
 */
public class DriverAction  {

	private WebDriver driver ;

	/**
	 * 无参构造方法,构建本地浏览器实例，默认等待30秒
	 */
	public DriverAction() {
		this.initDriver(null, 30);
	}
	
	// 以下为远程初始化WebDriver的构造方法
	public DriverAction(String browserType,URL url ,String baseUrl,int timeout){
		this.initDriver(browserType, url, baseUrl, timeout);
	}
	public DriverAction(String browserType,String ip_port,String baseUrl,int timeout){
		try {
			this.initDriver(browserType,new URL("http://"+ip_port+"/wd/hub"),baseUrl,timeout);
		} catch (MalformedURLException e) {
			Log.error(this.getClass(),"给定的ip 、端口非法"+e);
			e.printStackTrace();
		}
	}
	/**
	 * 使用远程的方法构建本地实例
	 * @param browserType
	 * @param baseUrl
	 */
	public DriverAction(String browserType,String baseUrl) {
		try {
			this.initDriver(browserType,new URL("http://127.0.0.1:4444/wd/hub"),baseUrl,30);
		} catch (MalformedURLException e) {
			Log.error(this.getClass(),"给定的ip 、端口非法"+e);
			e.printStackTrace();
		}
	}
	/**
	 * 默认使用firefox浏览器
	 * @param useRemote
	 */
	public DriverAction(boolean useRemote) {
		if (useRemote){
			try {
				this.initDriver("firefox",new URL("http://127.0.0.1:4444/wd/hub"),null,30);
			} catch (MalformedURLException e) {
				Log.error(this.getClass(),"给定的ip 、端口非法"+e);
				e.printStackTrace();
			}		
		}else{
			this.initDriver(null, 30);
		}
	}
	
	//以下是构造本地WebDriver实例
	public DriverAction(String baseUrl,int timeout) {
		this.initDriver(baseUrl, timeout);
	}
	public DriverAction(String baseUrl) {
		this.initDriver(baseUrl, 30);
	}
	/**
	 * 远程启动WebDriver
	 * @param browserType
	 * @param url
	 * @param baseUrl
	 * @param timeout
	 */
	private void initDriver(String browserType,URL url ,String baseUrl,int timeout){
		DesiredCapabilities dc = (DesiredCapabilities) Work.executStaticMethod(DesiredCapabilities.class, browserType);
		driver = new RemoteWebDriver(url,dc);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		    
	    if (baseUrl!=null && !"".equals(baseUrl)){
			driver.get(baseUrl);
		}    
	}
	
	/**
	 * 使用配置文件初始化一个本地WebDriver实例
	 * @param baseUrl
	 * @param timeout
	 */
	private void initDriver(String baseUrl,int timeout){
		
		System.setProperty(GlobalSettings.browserKey,GlobalSettings.browserPath);
		try {
			driver = Work.getObject(Class.forName(GlobalSettings.browserClass));
		} catch (Exception e) {
			Log.error(this.getClass(), "创建WebDriver实例失败 ： "+e);
			return;
		}
		
	    driver.manage().window().maximize(); //最大化浏览器
	    driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	    
		if (baseUrl!=null && !"".equals(baseUrl)){
			driver.get(baseUrl);
		}    
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * 判断元素是否存在
	 * @param By
	 * @return boolean
	 */
	public boolean isElementPresent(By by) {
	    try {
	    	driver.findElement(by);
	    	return true;
	    } catch (Exception e) {
	    	return false;
	    }
	}
	/**
	 * get By object
	 * @param wElement
	 * @return
	 */
	public By getBy(WSelector wSelector){
		return (By) Work.executStaticMethod("org.openqa.selenium.By", 
				wSelector.getSelector(), wSelector.getSelectPath());
	}
	/**
	 * 通过WElement的对象获取 WebElement 对象。循环遍历WElement对象中的 WSelector。
	 * @param wElement
	 * @return
	 * @throws Exception 
	 */
	public WebElement getWebElement(WElement wElement) throws Exception{
		WebElement webElement = null;
		for(WSelector selector: wElement.getWSelectors()){
			Log.debug(this.getClass(),">>> "+selector);
			try {
				webElement = driver.findElement(getBy(selector));
				return webElement;
			} catch (Exception e) {
				Log.warn(this.getClass(),"当前selector无效 ： "+selector);
			}
		}
		if (webElement == null){
			throw new Exception("所有selector无效，未获取到 WebElement ");
		}
		return webElement;
	}
	
	
	public boolean click(WElement wElement) {
		try {
			System.out.println("<<< "+wElement);
			
			getWebElement(wElement).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendKeys(WElement wElement, String value) {
		try {
			System.out.println("<<< "+wElement);
			
			WebElement wel = getWebElement(wElement);
			wel.clear();
			wel.sendKeys(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getText(WElement wElement) {
		try {
			return getWebElement(wElement).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean select(WElement wElement, String value) {
		try {
			Select select = new Select(getWebElement(wElement));
			select.selectByValue(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获得Alert 的文本，返回String	
	 * @return
	 */
	public String getAlertText(){
		String text = driver.switchTo().alert().getText();
		Log.info(this.getClass(),"获取到Alert的文本 ："+ text);
		driver.switchTo().alert().accept();
		Log.info(this.getClass(),"点击确定关闭Alert");
		return text;	
	}

	public boolean toFrame(WElement wElement) {
		try {
			driver.switchTo().frame(getWebElement(wElement));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean toFrame(String index) {
		try {
			driver.switchTo().frame(Integer.parseInt(index));
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean toDefault() {
		try {
			driver.switchTo().defaultContent();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void back() {
		driver.navigate().back();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public String getUrl() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行一段js代码
	 * @param browserDriver 
	 * @param js js代码片段
	 * @return
	 */
	public Object executeJS(String js,Object...args){
		try {
			return ((JavascriptExecutor)driver).executeScript(js,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setScroll(String height) {
		String js="document.documentElement.scrollTop=" + height+";";  
		Object res = executeJS(js);
		Log.info(this.getClass(), "executeJS return : "+res);
	}

	public void moveToSee( WElement wElement) {
		String js = "arguments[0].scrollIntoView();";
		try {
			((JavascriptExecutor)driver).executeScript(js,getWebElement(wElement));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean mouseClick(WElement wElement) {
		try {
			(new Actions(driver)).click(getWebElement(wElement));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean mouseDoubleClick(WElement wElement) {
		try {
			(new Actions(driver)).doubleClick(getWebElement(wElement));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean mouseContextClick(WElement wElement) {
		try {
			(new Actions(driver)).contextClick(getWebElement(wElement));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean mouseContextClick( ) {
		try {
			(new Actions(driver)).contextClick();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/********************************预期结果******************************/
	
	public boolean show(WElement wElement){
		try {
			this.getWebElement(wElement);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean in(WElement wElement,String data){
		try {
			data = data == null ? "" : data;
			String str = this.getText(wElement);
			return data.equals(str);
		} catch (Exception e) {
			return false;
		}
	}
}
