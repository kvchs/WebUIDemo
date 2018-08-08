package com.uiFramework.companyName.projectName.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.assertion.VerificationHelper;
import com.uiFramework.companyName.projectName.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.javaScript.JavaScriptHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;
import com.uiFramework.companyName.projectName.testbase.TestBase;

public class LoginPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	WaitHelper waitHelper;
	
	@FindBy(css="#header > div.nav > div > div > nav > div.header_user_info > a")
	WebElement link_login;
	
	@FindBy(css="#email")
	WebElement input_userName;
	
	@FindBy(css="#passwd")
	WebElement input_passowrd;
	
	@FindBy(css="#SubmitLogin > span")
	WebElement btn_login;
	
	@FindBy(xpath="//*[@id=\"center_column\"]/p")
	WebElement msg_Success;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(link_login, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnLoginLink() {
		log.info("Click login link go to login page ...");
		logExtentReport("Click login link go to login page ... Recordign by[TestBase.test.log]");
		link_login.click();
	}
	
	public void enterUserName(String userName) {
		log.info("Enter username ... " + userName);
		input_userName.sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		log.info("Enter password ... " + password);
		this.input_passowrd.sendKeys(password);
	}
	
	public HomePage clickOnSubmitButton() {
		log.info("Click on submit button ...");
		new JavaScriptHelper(driver).scrollDownVertically();
		btn_login.click();
		return new HomePage(driver);
	}
	
	public boolean verifyLoginSuccessMessage() {
		return new VerificationHelper(driver).isDisplay(msg_Success);
	}
	
	public void loginToApplication(String userName, String password) {
		clickOnLoginLink();
		enterUserName(userName);
		enterPassword(password);
		clickOnSubmitButton();
	}
	
	public void logExtentReport(String message) {
		TestBase.test.log(Status.INFO, message);
	}
	
	
}
