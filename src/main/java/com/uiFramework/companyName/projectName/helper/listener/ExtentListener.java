package com.uiFramework.companyName.projectName.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.utils.ExtentManager;

public class ExtentListener implements ITestListener {

	private Logger log = LoggerHelper.getLogger(ExtentListener.class);
	public static ExtentReports extent;
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(Status.INFO, result.getName() + " started"); --
		Reporter.log(result.getMethod().getMethodName() + " test start...");
		log.info(result.getMethod().getMethodName() + " test start...");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(Status.INFO, result.getName() + " passed"); --
		Reporter.log(result.getMethod().getMethodName() + " test success...");
		log.info(result.getMethod().getMethodName() + " test success...");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(Status.FAIL, result.getThrowable()); --
		Reporter.log(result.getMethod().getMethodName() + " test failure..." + result.getThrowable());
		log.error(result.getMethod().getMethodName() + " test failure..." + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.SKIP, result.getThrowable()); --
		Reporter.log(result.getMethod().getMethodName() + " test skip..." + result.getThrowable());
		log.warn(result.getMethod().getMethodName() + " test skip..." + result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//extent = ExtentManager.getInstance(); --
//		test = extent.createTest(getClass().getName());
		//com.uiFramework.companyName.projectName.helper.listener.ExtentListener  [case name in report]
		// test = extent.createTest(context.getName()); --
		//  <test name="third test">   show name in regressionSuite.xml configuration
		// context.getCurrentXmlTest().getName()  and context.getName()   has same result
		Reporter.log(context.getCurrentXmlTest().getName() + " Class start...");
		log.info(context.getCurrentXmlTest().getName() + " Class start...");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//extent.flush();  --
		Reporter.log(context.getName() + " test finished...");
		log.info(context.getName() + " test finished...");
		
	}

}
