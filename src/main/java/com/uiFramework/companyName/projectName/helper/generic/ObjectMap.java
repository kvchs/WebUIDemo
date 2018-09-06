package com.uiFramework.companyName.projectName.helper.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class ObjectMap {
	Logger logger = LoggerHelper.getLogger(ObjectMap.class);
	Properties properties;
	
	public ObjectMap(String propFile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(propFile));
			properties.load(fileInputStream);
			fileInputStream.close();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("读取文件对象出错");
			logger.info("读取文件对象出错");
			e.printStackTrace();
		}
	}
	
	public By getLocator(String ElementNameInpropFile) throws Exception {
		String locator = properties.getProperty(ElementNameInpropFile);
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		/**
		 * 在eclipse中配置文件的默认存储方式为ISO-8859-1编码存储，使用getBytes方法可以
		 * 将字符串转换为UTF-8编码，编辑中文乱码问题
		 *
		 */
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("获取到的定位类型: " + locatorType + "\t" + "定位表达式: " + locatorValue);
		logger.info("获取到的定位类型: " + locatorType + "\t" + "定位表达式: " + locatorValue);
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);
		}else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);
		}else if (locatorType.toLowerCase().equals("classname") || (locatorType.toLowerCase().equals("class"))) {
			return By.className(locatorValue);
		}else if (locatorType.toLowerCase().equals("tagname") || (locatorType.toLowerCase().equals("tag"))) {
			return By.tagName(locatorValue);
		}else if (locatorType.toLowerCase().equals("cssselector") || (locatorType.toLowerCase().equals("css"))) {
			return By.cssSelector(locatorValue);
		}else if (locatorType.toLowerCase().equals("linktext") || (locatorType.toLowerCase().equals("link"))) {
			return By.linkText(locatorValue);
		}else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		}else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);
		}else {
			throw new Exception("输入的locator type没有定义: " + locatorType);
		}
		
		
		
	}

}
