package com.uiFramework.companyName.projectName.helper.resource;

public class ResourceHelper {
	
	public static String getResource(String path) {
		String basePath = System.getProperty("user.dir");
		return basePath + path;
	}
	
	public static void main(String[] args) {
		String path = ResourceHelper.getResource("\\src\\main\\resources\\configFile\\log4j.properties");
		System.out.println(path);
	}

}
