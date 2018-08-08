package com.uiFramework.companyName.projectName.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class AssertionHelper {
	
//	private WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);
	
	public static void verifyText(String s1, String s2) {
		log.info("Verify text " + s1 + " with " + s2);
		Assert.assertEquals(s1, s2);
	}
	
	public static void makeTrue() {
		log.info("make script pass ...");
		Assert.assertTrue(true);
	}
	
	public static void makeTrue(String message) {
		log.info("make script pass ..." + message);
		Assert.assertTrue(true, message);
	}
	
	public static void makeFalse() {
		log.info("make script false ...");
		Assert.assertTrue(false);
	}
	
	public static void makeFalse(String message) {
		log.info("make script false ..." + message);
		Assert.assertTrue(false, message);
	}
	
	public static void verifyTrue(boolean status) {
		log.info("verify true ...");
		Assert.assertTrue(status);
	}
	
	public static void verifyFalse(boolean status) {
		log.info("verify false ...");
		Assert.assertTrue(status);
	}
	
	public static void verifyNullObject(String s1) {
		log.info("verify object is null");
		Assert.assertNull(s1);
	}
	
	public static void verifyNotNullObject(String s1) {
		log.info("verify object is not null");
		Assert.assertNotNull(s1);
	}
	
	public static void fail() {
		Assert.assertTrue(false);
	}
	
	public static void pass() {
		Assert.assertTrue(true);
	}
	
	public static void updateTestStatus(boolean status) {
		if (status) {
			pass();
		}else {
			fail();
		}
	}

}
