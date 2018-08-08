package com.uiFramework.companyName.projectName.testScripts.loginPage;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.pageObject.LoginPage;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class TestLoginWithDataDriver extends TestBase{
	private final Logger log = LoggerHelper.getLogger(TestLoginWithDataDriver.class);
	LoginPage loginPage;
	
	@DataProvider(name="testData")
	public Object[][] testData(){
		Object[][] data = getExcelData("ExcelData.xlsx", "Sheet2");
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + " ");
			
			}
			System.out.println();
		}
		return data;
	}
	
	@BeforeClass
	public void beforeClass() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		loginPage = new LoginPage(driver);
	}
	
	
	@Test(dataProvider="testData")
	public void loginTest(String userName, String password, String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode of this TEST is marked as [N]");
		}
		
		System.out.println("DataDriver: " + userName + "======" + password);
		//loginPage.loginToApplication(userName, password);
	}
	

}
