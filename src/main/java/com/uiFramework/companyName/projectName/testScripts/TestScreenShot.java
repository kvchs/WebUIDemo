package com.uiFramework.companyName.projectName.testScripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class TestScreenShot extends TestBase{
	
	@Test
	public void testScreen() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		driver.findElement(By.className("d")).click();
		captureScreen("testBaidu", driver);
	}

}
