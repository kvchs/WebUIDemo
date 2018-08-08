package com.uiFramework.companyName.projectName.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;
import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.pageObject.LoginPage;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class TestLogin extends TestBase{
	
	private final Logger Log = LoggerHelper.getLogger(TestLogin.class);
	
	
	// lesson38
	@Test(description="This is a demo write case ")  
	public void testLogin() {
		//driver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		getApplicationUrl(ObjectReader.reader.getUrl());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		boolean status = loginPage.verifyLoginSuccessMessage();
		AssertionHelper.updateTestStatus(status);
		
	}

}

