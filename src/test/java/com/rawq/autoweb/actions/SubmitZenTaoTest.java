package com.rawq.autoweb.actions;

import java.io.File;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rawq.autoweb.domain.WZentaobug;
import com.rawq.autoweb.domain.ZenTaoBug;
import com.rawq.autoweb.utils.HClient;
import com.rawq.autoweb.utils.Json2Obj;

public class SubmitZenTaoTest {
	
	WZentaobug ztb = new WZentaobug();
	SubmitZenTao szt = new SubmitZenTao();

 // @Test
  public void submitBug() {
	  ztb.setSteps("<img src='http://www.qishidai.com.cn/images/index/banner1.jpg' alt='' />");
	  ztb.setTitle("code test 7");
	  
	  String str = szt.submitBug("http://192.168.1.100/zentao/bug-create-1.json", ztb);
	  System.out.println("str : "+str);
	  System.out.println(szt.getZenTaoBugId(str));
	//  HClient.doGet("http://192.168.1.100/zentao/api-sql-select%20*%20from%20zt_bug.json");
  }
  
@Test
 public void uploadimage() {
	String url = "http://192.168.1.101/zentao/file-ajaxUpload.html?dir=image";  
	String imagepath = "C:\\Users\\Administrator\\Pictures\\123.jpg";
	File image = new File(imagepath);
	String bb = szt.uploadImage(url, image);
	
	System.out.println(bb);
	  
	
	
 }
 
}
