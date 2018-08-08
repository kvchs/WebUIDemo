package com.uiFramework.companyName.projectName.testScripts.loginPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.pageObject.LoginPage;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class TestLogin extends TestBase {

	private final Logger log = LoggerHelper.getLogger(TestLogin.class);

	@Test(description = "test login function")
	public void testLoginToApplication() {

		getApplicationUrl(ObjectReader.reader.getUrl());

		LoginPage loginPage = new LoginPage(driver);
		AssertionHelper.updateTestStatus(false);

		//loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		// boolean status = loginPage.verifySuccessLoginMsg();

		// AssertionHelper.updateTestStatus(status);

	}

}
