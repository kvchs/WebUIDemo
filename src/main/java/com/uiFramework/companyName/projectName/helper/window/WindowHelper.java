package com.uiFramework.companyName.projectName.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class WindowHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);
	
	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * This method will switch to main window
	 */
	public void switchToParentWindow() {
		log.info("switch to parent window..");
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will switch to index window
	 * @param index
	 */
	public void switchToWindow(int index) {
		log.info("switch to parent window..");
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window: windows) {
			if (i == index) {
				log.info("Switch to " + index + " window");
				driver.switchTo().window(window);
			}else {
				i++;
			}
		}
	}
	
	/**
	 * This method will close all tab and switch to main window 
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		for (String window: windows) {
			if (!window.equalsIgnoreCase(mainWindow)) {
				driver.close();
			}
		}
		log.info("Switch to main window");
		driver.switchTo().window(mainWindow);
	}
	
	/**
	 * This method will do browser back navigate
	 */
	public void navigateBack() {
		log.info("Navigating back");
		driver.navigate().back();
	}

	/**
	 * This method will do browser forward navigate
	 */
	public void navigateForward() {
		log.info("Navigating forward");
		driver.navigate().forward();
	}

}
