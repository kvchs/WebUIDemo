package com.uiFramework.companyName.projectName.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class MyAccountPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(MyAccountPage.class);
	WaitHelper waitHelper;
	
	//Element location
	@FindBy(xpath="")
	public WebElement elementName;
	

	
	public MyAccountPage() {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(elementName, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("My Account Page Created");
		new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnElement(WebElement element) {
		log.info("Click on " + element.getText());
		element.click();
		TestBase.logExtentReport("Click on " + element.getText());
		
	}

}
