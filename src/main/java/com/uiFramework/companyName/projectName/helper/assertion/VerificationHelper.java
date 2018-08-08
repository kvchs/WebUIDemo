package com.uiFramework.companyName.projectName.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class VerificationHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public boolean isDisplay(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is present... " + element.getText());
			TestBase.logExtentReport("element is present... " + element.getText());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			//log.error("element is no present... " + e.getCause());
			log.error("element is no present... " + e.getCause().getMessage());
			TestBase.logExtentReport("element is no present... " + e.getCause().getMessage());
			return false;
		}
	}
	
	public boolean isNotDisplay(WebElement element) {
		try {
			element.isDisplayed();
			log.error("element is present... " + element.getText());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("element is no present... " + e.getCause());
			return true;
		}
	}
	
	public String getText(WebElement element) {
		if (element == null) {
			log.info("element is null... ");
			return null;
		}
		boolean status = element.isDisplayed();
		if (status) {
			log.info("element is : " + element.getText());
			return element.getText();
		}else {
			return null;
		}
	}
	

}
