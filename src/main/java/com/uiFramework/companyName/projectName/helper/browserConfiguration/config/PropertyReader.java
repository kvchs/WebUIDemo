package com.uiFramework.companyName.projectName.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.uiFramework.companyName.projectName.helper.browserConfiguration.BrowserType;
import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties properties;

	public PropertyReader() {
		
		try {
			String filePath = ResourceHelper.getResource("/src/main/resources/configFile/config.properties");
			file = new FileInputStream(new File(filePath));
			properties = new Properties();

			properties.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImpliciteWait() {
		return Integer.parseInt(properties.getProperty("impliciteWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("explicitWait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(properties.getProperty("pageLoadTime"));
	}

	public BrowserType geBrowserType() {
		
		return BrowserType.valueOf(properties.getProperty("browserType"));
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return properties.getProperty("applicationUrl");
	}

	public String getUserName() {
		if (System.getProperty("userName") != null) {
			return System.getProperty("userName");
		}
		// TODO Auto-generated method stub
		return properties.getProperty("userName");
	}

	public String getPassword() {
		if (System.getProperty("password") != null) {
			return System.getProperty("password");
		}
		// TODO Auto-generated method stub
		return properties.getProperty("password");
	}
	
	

}
