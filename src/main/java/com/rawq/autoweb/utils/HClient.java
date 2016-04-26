package com.rawq.autoweb.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

public class HClient {
	
	//创建一个默认的httpclient实例
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	/**
	 * 发送post请求，返回状态码
	 * @param url
	 * @param parameters
	 * @param encoded
	 * @return statusCode
	 */
	public static String doPost(String url,List <NameValuePair> parameters,String encoded,boolean isjson){
		Log.info(HClient.class, "post url : "+url + "parameters : "+parameters);
		//创建post对象
		HttpPost httpPost = new HttpPost(url);
		int statusCode = 0;
		String responseStr = "";
		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(parameters,encoded));
			response = httpclient.execute(httpPost);
			statusCode = response.getStatusLine().getStatusCode();
			
			HttpEntity entity = response.getEntity();
			responseStr = entity != null ? EntityUtils.toString(entity) : "";
			
			if (statusCode < 200 || statusCode > 300) {
				Log.warn(HClient.class, responseStr);
			}else{
				Log.debug(HClient.class, responseStr);
			}
			
			// do something useful with the response body
			// and ensure it is fully consumed
			//消耗掉response
	        EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (response!=null){
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Log.info(HClient.class, "post “ "+ url+" ” "+ " status code :"+statusCode);
		if (isjson){
			return responseStr;
		}
		return String.valueOf(statusCode);
	}
	/**
	 * 自定义字符集编码
	 * @param url
	 * @param parameters
	 * @param encoded
	 * @return
	 */
	public static int doPost(String url,List <NameValuePair> parameters,String encoded){
		return Integer.valueOf(doPost(url, parameters, encoded,false));
	}
	/**
	 * 默认使用utf-8编码
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static int doPost(String url,List <NameValuePair> parameters){
		return Integer.valueOf(doPost(url, parameters, "UTF-8",false));
	}
	
	/**
	 * upload file
	 * @param url 						上传地址
	 * @param file						上传文件
	 * @param uploadFileAttribute		上传文件对应的属性名（服务器指定的属性名）
	 * @param descriptionAttribute		文件描述参数的属性名（服务器指定的属性名）
	 * @param description				文件描述的内容
	 * @param args						other attribute （目前只支持String类型）
	 * @return							上传成功后返回的 uri 
	 * @throws UnsupportedEncodingException
	 */
	public static String uploadFile(String url,File file,String uploadFileAttribute,
			String descriptionAttribute,String description,Map<String ,String> args) throws UnsupportedEncodingException{
		
		String responseStr = "";
		HttpPost httpPost = new HttpPost(url);
		FileBody filebody = new FileBody(file);
		StringBody desc = new StringBody(description,ContentType.create("text/plain", Consts.UTF_8));
		
		 MultipartEntityBuilder mebuilder = MultipartEntityBuilder.create();
		 mebuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		 mebuilder.addPart(uploadFileAttribute, filebody);	//uploadFile对应服务端类的同名属性<File类型>
		 mebuilder.addPart(descriptionAttribute, desc);		//uploadFileName对应服务端类的同名属性<String类型>
		 mebuilder.setCharset(CharsetUtils.get("UTF-8"));
		 
		 if (args != null){									//为其他属性赋值
			 for(Entry<String, String> es : args.entrySet()){
				 mebuilder.addPart(es.getKey(), new StringBody(es.getValue(),ContentType.create("text/plain",Consts.UTF_8)));
			 }
		 }
		 
		 HttpEntity reqEntity = mebuilder.build();
		 httpPost.setEntity(reqEntity);
		 CloseableHttpResponse response = null;
		 
		 Log.info(HClient.class,"将上传文件至 ： "+ httpPost.getRequestLine());
		 try{  
			 response = httpclient.execute(httpPost);
			 
			 Log.info(HClient.class, "响应结果 ： "+response.getStatusLine());
			 HttpEntity entity = response.getEntity();
			 responseStr = entity != null ? EntityUtils.toString(entity) : "";
			 
			 EntityUtils.consume(entity);
		 } catch (Exception e) {
			e.printStackTrace();
		 } finally{
			 try {
				response.close();
			 } catch (Exception e) {
				e.printStackTrace();
			 }
		 }
	   Log.info(HClient.class, "上传文件返回： "+responseStr);
	   return responseStr;
	}
	/**
	 * 发送get请求
	 * @param url
	 * @return
	 */
	public static String doGet(String url,boolean isjson){
		HttpGet httpGet = new HttpGet(url);
		int statusCode = 0;
		String responseStr = "";
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
			statusCode = response.getStatusLine().getStatusCode();
			
			HttpEntity entity = response.getEntity();
			responseStr = entity != null ? EntityUtils.toString(entity) : "";
			
			if (statusCode < 200 || statusCode > 300) {
				Log.warn(HClient.class, responseStr);
			}else{
				Log.debug(HClient.class, responseStr);
			}
			// do something useful with the response body
			// and ensure it is fully consumed
			//消耗掉response
	        EntityUtils.consume(entity);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (response!=null){
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Log.info(HClient.class, "get “ "+ url+" ” "+ " status code :"+statusCode);
		if (isjson){
			return responseStr;
		}
		return String.valueOf(statusCode);
	}
	/**
	 * 发送get请求，返回状态码
	 * @param url
	 * @return
	 */
	public static int doGet(String url){
		return Integer.valueOf(doGet(url,false));
	}
	
	
	/**
	 * 
	 * @param url
	 * @param parameters
	 * @param encoded
	 * @return
	 */
	public static String doPostJson(String url,List <NameValuePair> parameters,String encoded){
		if (url.endsWith("json")){
			return doPost(url, parameters, encoded, true);
		}
		Log.warn(HClient.class, "未使用json url，将返回状态码");
		return doPost(url, parameters, encoded, false);
	}
	/**
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static String doPostJson(String url,List <NameValuePair> parameters){
		if (url.endsWith("json")){
			return doPost(url, parameters, "UTF-8", true);
		}
		Log.warn(HClient.class, "未使用json url，将返回状态码");
		return doPost(url, parameters, "UTF-8", false);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String doGetJson(String url){
		if (url.endsWith("json")){
			return doGet(url,true);
		}
		Log.warn(HClient.class, "未使用json url，将返回状态码");
		return doGet(url,false);
	}
}
