package com.rawq.autoweb.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rawq.autoweb.domain.WElement;

public class XMLOper {

	/**
	 * get the document object
	 * @param xmlFilePath
	 * @return
	 */
	public static Document getDocument(String xmlFilePath){
		Document document = null;
		try {
			document = new SAXReader().read(new File(xmlFilePath));
		} catch (DocumentException e) {
			Log.error(XMLOper.class,"没有指定xml文件路径 或找不到该文件"+e);
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * get root element
	 * @param document
	 * @return
	 */
	public static Element getRoot(Document document){
		return document.getRootElement();
	}
	
	/**
	 * get child element list
	 * @param rootElement
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChildren(Element rootElement){
		return rootElement.elements();
	}
	
	
	
	/**
	 * element list to java baen map
	 * @param elemList
	 * @return Map<String,WElement>
	 */
	/*
	public static Map<String,WElement> xmlToBeanMap(List<Element> elemList){
		if(elemList!=null && elemList.size() > 0){
			Map<String,WElement> weMap = new HashMap<String ,WElement>();
			for(Element element : elemList){
				String name = element.attributeValue("name","none");
				
				WElement webElement = new WElement();
				webElement.setName(name);
				webElement.setType(element.attributeValue("type"));
				webElement.setTag(element.attributeValue("tag"));
				webElement.setDescription(element.attributeValue("description"));
				webElement.setValue(element.getTextTrim());
				
				if(weMap.containsKey(name)){
					logger.error("指定的xml文档中存在重复的 name属性值"+ name);
				}else{
					weMap.put(name, webElement);
				}
			}
			return weMap;
		}
		return null;
	}
	
	
	/**
	 * ͨ通过指定的xml文件 返回WElement对象映射
	 * @param xmlFilePath
	 * @return Map<String,WElement>
	 *
	public static Map<String,WElement> xmlToBeanMap(String xmlFilePath){
		return xmlToBeanMap(getChildren(getRoot(getDocument(xmlFilePath))));
	}
	*/
}
