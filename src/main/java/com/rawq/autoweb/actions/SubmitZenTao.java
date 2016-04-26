package com.rawq.autoweb.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.rawq.autoweb.domain.WZentaobug;
import com.rawq.autoweb.utils.GlobalSettings;
import com.rawq.autoweb.utils.HClient;
import com.rawq.autoweb.utils.Json2Obj;
import com.rawq.autoweb.utils.Log;

public class SubmitZenTao {

	private String username = "";
	private String password = "";
	private String loginUrl = "";
	
	public SubmitZenTao(){
		this.username = GlobalSettings.username;
		this.password = GlobalSettings.password;
		this.loginUrl = "http://"+ GlobalSettings.zentaoServer + GlobalSettings.loginUri;
		this.loginZenTao();
	}
	
	public SubmitZenTao(String username, String password, String loginUrl) {
		this.username = username;
		this.password = password;
		this.loginUrl = loginUrl;
		this.loginZenTao();
	}
	
	/**
	 * 登录禅道，已写入构造函数
	 */
	public void loginZenTao(){
		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("account", username));
			parameters.add(new BasicNameValuePair("password", password));
			HClient.doPost(loginUrl, parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交bug返回json结果
	 * @param url
	 * @param parameters
	 * @return
	 */
	public String submitBug(String url ,List<NameValuePair> parameters){
		return HClient.doPostJson(url, parameters);
	}
	/**
	 * 提交bug返回json结果
	 * @param url
	 * @param post
	 * @return
	 */
	public String submitBug(String url ,Map<String ,String > post){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for(String key : post.keySet()){
			parameters.add(new BasicNameValuePair(key, post.get(key)));
		}
		return HClient.doPostJson(url, parameters);
	}
	/**
	 * 提交bug返回json结果,使用配置文件中的bug提交uri地址
	 * @param post
	 * @return
	 */
	public String submitBug(Map<String ,String > post){
		return this.submitBug("http://" + GlobalSettings.zentaoServer + GlobalSettings.bugUri, post);
	}
	/**
	 * 使用 WZentaobug 实体提交bug
	 * @param url
	 * @param bug
	 * @return json
	 */
	@SuppressWarnings("unchecked")
	public String submitBug(String url ,WZentaobug bug){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		String json = JSON.toJSONString(bug);
		Map<String ,String > post = JSON.parseObject(json, Map.class);
		
		Log.debug(this.getClass(), "ZenTaoBug to Map : "+post);
		
		for(String key : post.keySet()){
			if ("id".equals(key)){		//id为int类型，且不用传入
				continue;
			}
			if ("case_".equals(key)){	//由于case 为jdk关键字，需在此转换
				parameters.add(new BasicNameValuePair("case", post.get(key)));
				continue;
			}
			parameters.add(new BasicNameValuePair(key, post.get(key)));
		}
		return HClient.doPostJson(url, parameters);
	}
	/**
	 * 使用 WZentaobug 实体提交bug ,使用配置文件中的bug提交uri地址
	 * @param bug
	 * @return
	 */
	public String submitBug(WZentaobug bug){
		return this.submitBug("http://" + GlobalSettings.zentaoServer + GlobalSettings.bugUri, bug);
	}
	
	/**
	 * 仅适用于自定义模板（修改zentao源码获得）
	 * @param responseJson
	 * @return bug id
	 */
	public int getZenTaoBugId(String responseJson){
		if (responseJson == null || "".equals(responseJson)){
			return 0;
		}
		String msg = JSON.parseObject(responseJson).get("bugId").toString();
		Log.info(this.getClass(), "bug submit response : "+responseJson + ", msg = "+msg);
		return Integer.valueOf(msg);
	}
	
	/**
	 * 上传图片，并返回该图片的url地址
	 * @param url
	 * @param image
	 * @return
	 */
	public String uploadImage(String url ,File image){
		String imageUrl = "http://" + GlobalSettings.zentaoServer;
		try {
			imageUrl += JSON.parseObject(HClient.uploadFile(url, image,"imgFile","localUrl",image.getName(),null)).get("url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageUrl;
	}
	/**
	 * 上传图片，并返回该图片的url地址
	 * @param url
	 * @param image
	 * @return
	 */
	public String uploadImage(File image){
		return this.uploadImage("http://" + GlobalSettings.zentaoServer + GlobalSettings.imageUri,image);
	}
}
