package com.uiFramework.companyName.projectName.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.BrowserType;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.FirefoxBrowser;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.IEBrowser;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.PropertyReader;
import com.uiFramework.companyName.projectName.helper.excel.ExcelHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;
import com.uiFramework.companyName.projectName.utils.ExtentManager;

public class TestBase {

	public static ExtentReports extent;
	public static ExtentTest test;

	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectery;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}

	

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + " test start...");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(), driver);
			// Only test failed add picture in report
			test.addScreenCaptureFromPath(imagePath);
			System.out.println("add image in report");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is passed");
			//String imagePath = captureScreen(result.getName(), driver);
			// test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}

		extent.flush();
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(ResourceHelper.getResource("/src/main/resources/screenShots"));
		setUpDriver(ObjectReader.reader.geBrowserType());
		test = extent.createTest(getClass().getSimpleName());

	}

	@AfterTest
	public void afterTest() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getBrowserObjcet(BrowserType bType) throws Exception {
		try {
			switch (bType) {
			case Chrome:
				ChromeBrowser chromeBrowser = ChromeBrowser.class.newInstance();
				return chromeBrowser.getChromeDriver(chromeBrowser.getChromeOptions());

			case Firefox:
				FirefoxBrowser firefoxBrowser = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefoxBrowser.getFirefoxOptions();
				return firefoxBrowser.getFirefoxDriver(options);

			case IE:
				IEBrowser ieBrowser = IEBrowser.class.newInstance();
				InternetExplorerOptions cap = ieBrowser.getIExplorerCapabilities();
				return ieBrowser.getIExplorerDriver(cap);
			default:
				throw new Exception("Driver " + bType + " not found!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			throw e;
		}

	}

	public void setUpDriver(BrowserType bType) throws Exception {
		driver = getBrowserObjcet(bType);
		log.info("Initialize Web Driver : " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public String captureScreen(String fileName, WebDriver driver) {
		if (driver == null) {
			log.info("driver is null ...");
			return null;
		}

		if (fileName == "") {
			fileName = "blank";
		}

		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			destFile = new File(reportDirectery + "/" + fileName + "_" + format.format(calendar.getTime()) + ".png");
			// Files.copy(screFile.toString(), destFile.toString());
			// make a mistake :Notice
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void getNavigationScreen(WebDriver driver) {
		log.info("Capturing UI navigation screen");
		String screen = captureScreen("", driver);
		try {
			test.addScreencastFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// public String getScreenshot(String FileName, WebDriver driver) {
	//
	// }

	public static void logExtentReport(String s1) {
		test.log(Status.INFO, s1);
	}

	public void getApplicationUrl(String url) {
		driver.get(url);
		logExtentReport("navigating to ..." + url);
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = ResourceHelper.getResource("/src/main/resources/configFile/") + excelName;
		log.info("Excel location : " + excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}

}
