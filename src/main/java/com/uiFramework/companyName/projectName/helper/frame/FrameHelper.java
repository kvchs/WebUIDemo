package com.uiFramework.companyName.projectName.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class FrameHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);
	
	
	
	public FrameHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * This method will switch to frame base on index
	 * @param frameIndex
	 */
	public void switchFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("Switch to: " + frameIndex + " frame");
	}
	
	/**
	 * This method will switch to frame base on frameName
	 * @param frameName
	 */
	public void switchFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("Switch to: " + frameName + " frame");
	}
	
	/**
	 * This method will switch to frame base on element locator
	 * @param element
	 */
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("Switch to: " + element.toString() + " frame");
	}

}
